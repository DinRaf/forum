package com.forum.server.controller;

import com.forum.server.services.interfaces.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 15.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.forum.server.controller"})
public class ControllerTestContext {
    @Bean
    public RegistrationService registrationService() {
        return Mockito.mock(RegistrationService.class);
    }

    @Bean
    public MessageService messagesService() {
        return Mockito.mock(MessageService.class);
    }

    @Bean
    public SearchService searchService() {
        return Mockito.mock(SearchService.class);
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public ThemeService themeService() {
        return Mockito.mock(ThemeService.class);
    }

}
