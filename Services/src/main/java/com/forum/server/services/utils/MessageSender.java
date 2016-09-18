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
            messageHelper.setSubject("Пожалуйста, подвтердите почту");
            messageHelper.setTo(mail);
            String confirmHash = confirmHashGenerator.generateHash()
                    + confirmHashGenerator.generateHash();
            confirmationDao.saveConfirmHash(userId, confirmHash);
            messageHelper.setText(
                    "<div id=\"style_14738465600000000856_BODY\">" +
                            "<table align=\"left\" border=\"2\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 500px\">" +
                            "<caption>Здравствуйте, "+ nickname +"!</caption>" +
                            "<thead>" +
                            "tr>" +
                            "<th scope=\"col\" style=\"width: 500px;\">" +
                            "<div class=\"b-letter__details\">" +
                            "<div class=\"b-letter__body\" style=\"padding: 15px; line-height: 20.8px; overflow: auto;\">" +
                            "<div class=\"js-body b-letter__body__wrap\" style=\"overflow-x: auto; position: relative; font-size: 15px;\">" +
                            "<div id=\"style_14738465600000000856_BODY\">Для подтверждения аккаунта перейдите пожалуйста по ссылке ниже:<br>" +
                            "<a href=\"http://www.labooda.ru/#confirm/"+ confirmHash +"\" rel=\"noopener\" style=\"color: #0077cc;\" target=\"_blank\">Вот сюда!</a></div>" +
                            "</div>" +
                            "</div>" +
                            "</div>" +
                            "</th>" +
                            "</tr>" +
                            "</thead>" +
                            "</table>" +
                            "</div>" +
                            "<p>&nbsp;</p>"
            , true);
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
//        SimpleMailMessage message = new SimpleMailMessage(mailMessage);
//        message.setTo(mail);
//        String confirmHash = confirmHashGenerator.generateHash()
//                + confirmHashGenerator.generateHash();
//        confirmationDao.saveConfirmHash(userId, confirmHash);
//        message.setText(
//                "Здравствуйте, " + nickname + "!\n" +
//                        "Для смены пароля пожалуйста перейдите по ссылке ниже:\n" +
//                        "192.168.0.105:8080/password/" + confirmHash
//        );
//        try {
//            mailSender.send(message);
//        } catch (Exception ex) {
//            throw new AuthException("Смена пароля не удалась");
//        }
    }
}
