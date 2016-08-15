package com.forum.server.controller;

import com.forum.server.dto.auth.AuthDto;
import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.services.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.forum.server.utils.ResponseBuilder.buildResponseLogin;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 06.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<QueryResultDto> login(@RequestHeader(name = "identifier") String identifier,
                                                @RequestHeader(name = "password") String password) {
        String token = registrationService.login(identifier, password);
        return buildResponseLogin(token);
    }

    @RequestMapping(value = "/users", method = POST)
    public ResponseEntity<QueryResultDto> addUser(@RequestBody AuthDto authDto) {

        String token = registrationService.addUser(authDto);
        return buildResponseLogin(token);
    }
}
