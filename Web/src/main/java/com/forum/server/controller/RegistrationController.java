package com.forum.server.controller;

import com.forum.server.dto.auth.AuthDto;
import com.forum.server.dto.auth.LoginDto;
import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.services.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
import static com.forum.server.utils.ResponseBuilder.buildResponseLogin;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
        LoginDto token = registrationService.login(identifier, password);
        return buildResponseLogin(token);
    }

    @RequestMapping(value = "/users", method = POST)
    public ResponseEntity<QueryResultDto> addUser(@RequestBody AuthDto authDto) {

        LoginDto token = registrationService.addUser(authDto);
        return buildResponseLogin(token);
    }

    @RequestMapping(value = "/confirmation/{confirm-hash}", method = GET)
    public ResponseEntity<QueryResultDto> addUser(@PathVariable("confirm-hash") String confirmHash) {

        registrationService.confirmUser(confirmHash);
        return buildResponseGet(null);
    }
}
