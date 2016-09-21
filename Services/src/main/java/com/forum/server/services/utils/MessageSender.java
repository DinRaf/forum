package com.forum.server.services.utils;

import com.forum.server.dao.interfaces.ConfirmationDao;
import com.forum.server.security.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.forum.server.services.utils.Message.CONFIRM_MAIL;
import static com.forum.server.services.utils.Message.RECOVERY_PASS;

/**
 * 12.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class MessageSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConfirmHashGenerator confirmHashGenerator;

    @Autowired
    private ConfirmationDao confirmationDao;

    @Async
    public void sendMessage(long userId, String mail, String nickname) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom("forum.labooda@mail.ru");
            messageHelper.setSubject("Пожалуйста, подтвердите почту");
            messageHelper.setTo(mail);
            String confirmHash = confirmHashGenerator.generateHash()
                    + confirmHashGenerator.generateHash();
            confirmationDao.saveConfirmHash(userId, confirmHash);
            messageHelper.setText(CONFIRM_MAIL(confirmHash, nickname), true);
        } catch (MessagingException e) {
            throw new RuntimeException("Отправка сообщения не удалась");
        }
        try {
            mailSender.send(message);
        } catch (Exception ex) {
            throw new AuthException("Регистрация не удалась");
        }
    }

    @Async
    public void sendMessageRecoveryPass(long userId, String mail, String nickname) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom("forum.labooda@mail.ru");
            messageHelper.setSubject("Смена пароля");
            messageHelper.setTo(mail);
            String confirmHash = confirmHashGenerator.generateHash()
                    + confirmHashGenerator.generateHash();
            confirmationDao.deleteHashById(userId);
            confirmationDao.saveConfirmHash(userId, confirmHash);
            messageHelper.setText(RECOVERY_PASS(confirmHash, nickname), true);
        } catch (MessagingException e) {
            throw new RuntimeException("Отправка сообщения не удалась");
        }
        try {
            mailSender.send(message);
        } catch (Exception ex) {
            throw new AuthException("Регистрация не удалась");
        }
    }
}
