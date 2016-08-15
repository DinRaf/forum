package com.forum.server.controller;

import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.services.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.forum.server.utils.ResponseBuilder.buildResponsePostAndPut;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 06.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Controller
public class MessagesController {
    @Autowired
    private MessagesService messagesService;

    @RequestMapping(value = "/themes/{theme-id}", method = POST)
    public ResponseEntity<QueryResultDto> createMessage(@RequestHeader(name = "Auth-Token") String token,
                                                        @PathVariable("theme-id") int themeId,
                                                        @RequestBody MessageCreateDto messageCreateDto) {
        MessageDto message = messagesService.createMessage(token, themeId, messageCreateDto);
        return buildResponsePostAndPut(message);
    }

}


