package com.forum.server.controller;

import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.user.UserDto;
import com.forum.server.dto.user.UserShortDto;
import com.forum.server.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
import static com.forum.server.utils.ResponseBuilder.buildResponsePostAndPut;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * 06.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/{user-id}", method = GET)
    public ResponseEntity<QueryResultDto> getUser(@PathVariable("user-id") int userId,
                                                  @RequestHeader(name = "Auth-Token") String token) {
        UserShortDto user = userService.getUser(token, userId);
        return buildResponseGet(user);
    }

    @RequestMapping(value = "/users/{user-id}", method = PUT)
    public ResponseEntity<QueryResultDto> updateUser(@PathVariable("user-id") int userId,
                                                     @RequestHeader(name = "Auth-Token") String token,
                                                     @RequestBody UserDto userInfo) {
        UserDto user = userService.updateUser(token, userId, userInfo);
        return buildResponsePostAndPut(user);
    }
}
