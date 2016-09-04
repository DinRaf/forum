package com.forum.server.controller;

import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.services.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.forum.server.utils.ResponseBuilder.buildResponseDelete;
import static com.forum.server.utils.ResponseBuilder.buildResponsePostAndPut;
import static com.forum.server.utils.ResponseBuilder.buildResponseRating;
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
    private MessageService messageService;

    @RequestMapping(value = "/themes/{theme-id}", method = POST)
    public ResponseEntity<QueryResultDto> createMessage(@PathVariable("theme-id") long themeId,
                                                        @RequestBody MessageCreateDto messageCreateDto,
                                                        @RequestParam("count") long count,
                                                        @RequestHeader(name = "Auth-Token") String token) {
        ThemeDto message = messageService.createMessage(token, themeId, messageCreateDto, count);
        return buildResponsePostAndPut(message);
    }

    @RequestMapping(value = "/messages/{message-id}", method = PUT)
    public ResponseEntity<QueryResultDto> updateMessage(@RequestParam("count") long count,
                                                        @PathVariable("message-id") long messageId,
                                                        @RequestBody MessageCreateDto message,
                                                        @RequestHeader(name = "Auth-Token") String token) {
        ThemeDto updatedMessage = messageService.updateMessage(token, messageId, message, count);
        return buildResponsePostAndPut(updatedMessage);
    }

    @RequestMapping(value = "/messages/{message-id}/rating", method = PUT)
    public ResponseEntity<QueryResultDto> updateMessageRating(@PathVariable("message-id") long messageId,
                                                        @RequestParam("grade") boolean grade,
                                                        @RequestParam("count") long count,
                                                        @RequestParam("offset") long offset,
                                                        @RequestHeader(name = "Auth-Token") String token) {
        messageService.updateMessageRating(token, messageId, grade, count, offset);
        return buildResponseRating();
    }

    @RequestMapping(value = "/messages/{message-id}", method = DELETE)
    public ResponseEntity<QueryResultDto> deleteTheme(@PathVariable("message-id") long messageId,
                                                      @RequestHeader(name = "Auth-Token") String token) {
        messageService.deleteMessage(token, messageId);
        return buildResponseDelete();
    }
}


