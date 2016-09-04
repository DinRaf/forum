package com.forum.server.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 * 27.07.16.
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.forum.server"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost("smtp.mail.ru");
        javaMailSenderImpl.setPort(567);
        javaMailSenderImpl.setUsername("forum.labooda@mail.ru");
        javaMailSenderImpl.setPassword("emil4mo914");
        javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.host", "smtp.mail.ru");
        javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.port", "465");
        javaMailSenderImpl.getJavaMailProperties().put("mail.debug", "true");
        javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.auth", "true");
        javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");
        javaMailSenderImpl.getJavaMailProperties().setProperty("mail.smtp.socketFactory.port", "465");
        javaMailSenderImpl.getJavaMailProperties().setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailSenderImpl.getJavaMailProperties().setProperty("mail.smtp.socketFactory.fallback", "false");
        return javaMailSenderImpl;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("forum.labooda@mail.ru");
        simpleMailMessage.setSubject("Пожалуйста, подвтердите почту");
        return simpleMailMessage;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
