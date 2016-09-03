package com.forum.server.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 03.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Configuration
public class MailSenderConfig {

    @Bean
    public JavaMailSender javaMailService() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug", true);
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.mail.ru");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("forum.labooda@mail.ru");
        javaMailSender.setPassword("emil4mo914");
        javaMailSender.setProtocol("smtp");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("forum.labooda@mail.ru");
        simpleMailMessage.setSubject("Подвтердите почту");
        return simpleMailMessage;
    }
}
