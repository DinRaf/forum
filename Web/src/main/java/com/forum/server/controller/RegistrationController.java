package com.forum.server.controller;

import com.forum.server.dto.auth.AuthDto;
import com.forum.server.dto.auth.LoginDto;
import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.services.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
import static com.forum.server.utils.ResponseBuilder.buildResponseLogin;
import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    @RequestMapping(value = "/logout", method = DELETE)
    public ResponseEntity<QueryResultDto> logout(@RequestHeader(name = "Auth-Token") String token) {

        registrationService.logout(token);
        return buildResponseGet(null);
    }

    @RequestMapping(value = "/recovery", method = GET)
    public ResponseEntity<QueryResultDto> passwordRecovery(@RequestParam(name = "mail") String mail) {

        registrationService.recoveryPass(mail);
        return buildResponseGet(null);
    }

    @RequestMapping(value = "/password/{confirm-hash}", method = PUT)
    public ResponseEntity<QueryResultDto> passwordRecovery(@PathVariable("confirm-hash")  String hash,
                                                            @RequestHeader(name = "password") String password) {

        registrationService.changePass(hash, password);
        return buildResponseGet(null);
    }

    @RequestMapping(value = "/confirmation/email/{confirm-hash}", method = GET)
    public ResponseEntity<QueryResultDto> addUser(@PathVariable("confirm-hash") String confirmHash) {

        registrationService.confirmUser(confirmHash);
        return buildResponseGet(null);
    }
}
