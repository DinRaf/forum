package com.forum.server.services.utils;

import com.forum.server.dao.interfaces.ConfirmationDao;
import com.forum.server.security.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 12.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class MessageSender {

    @Autowired
    private SimpleMailMessage mailMessage;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private ConfirmHashGenerator confirmHashGenerator;

    @Autowired
    private ConfirmationDao confirmationDao;

    @Async
    public void sendMessage(long userId, String mail, String nickname) {
        SimpleMailMessage message = new SimpleMailMessage(mailMessage);
        message.setTo(mail);
        String confirmHash = confirmHashGenerator.generateHash()
                + confirmHashGenerator.generateHash();
        confirmationDao.saveConfirmHash(userId, confirmHash);
        message.setText("<p style=\"margin-left: 40px;\"><font size=\"4\"><font face=\"Times New Roman, Times, serif\"><b><span style=\"font-family: Arial, Tahoma, Verdana, sans-serif; font-size: 15px; background-color: #ffffff;\">&nbsp; &nbsp; Здравствуйте, "+ nickname +"!</span></b></font></font><br style=\"font-family: Arial, Tahoma, Verdana, sans-serif; font-size: 15px; background-color: #ffffff;\">" +
	"<span style=\"font-family: Arial, Tahoma, Verdana, sans-serif; font-size: 15px; background-color: #ffffff;\">Для подтверждения аккаунта перейдите пожалуйста по ссылке ниже:</span><br style=\"font-family: Arial, Tahoma, Verdana, sans-serif; font-size: 15px; background-color: #ffffff;\">" +
	"<a href=\"http://www.labooda.ru/#confirm/"+ confirmHash +"\" rel=\"noopener\" style=\"color: #0077cc; font-family: Arial, Tahoma, Verdana, sans-serif; font-size: 15px; background-color: #ffffff;\" target=\"_blank\">http://www.labooda.ru/#confirm/"+ confirmHash +"</a></p>"

        );
        try {
            mailSender.send(message);
        } catch (Exception ex) {
            throw new AuthException("Регистрация не удалась");
        }

    }

    @Async
    public void sendMessageRecoveryPass(long userId, String mail, String nickname) {
        SimpleMailMessage message = new SimpleMailMessage(mailMessage);
        message.setTo(mail);
        String confirmHash = confirmHashGenerator.generateHash()
                + confirmHashGenerator.generateHash();
        confirmationDao.saveConfirmHash(userId, confirmHash);
        message.setText(
                "Здравствуйте, " + nickname + "!\n" +
                        "Для смены пароля пожалуйста перейдите по ссылке ниже:\n" +
                        "192.168.0.105:8080/password/" + confirmHash
        );
        try {
            mailSender.send(message);
        } catch (Exception ex) {
            throw new AuthException("Смена пароля не удалась");
        }
    }
}
