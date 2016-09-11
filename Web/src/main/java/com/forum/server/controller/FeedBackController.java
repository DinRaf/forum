package com.forum.server.controller;

import com.forum.server.dto.message.FeedbacksDto;
import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.services.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.forum.server.utils.ResponseBuilder.buildResponseDelete;
import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
/**
 * Created by root on 09.09.16.
 */
@Controller
public class FeedBackController {

    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(value = "/feedback}", method = POST)
    public ResponseEntity<QueryResultDto> createFeedback(@RequestBody MessageCreateDto messageCreateDto) {
        feedbackService.createFeedback(messageCreateDto);
        return buildResponseDelete();
    }

    @RequestMapping(value = "/feedback", method = GET)
    public ResponseEntity<QueryResultDto> getFeedback(@RequestParam(value = "offset", required = false) Integer offset,
                                                      @RequestParam(value = "count") int count,
                                                      @RequestHeader(name = "Auth-Token") String token) {
        FeedbacksDto feedbacksDto = feedbackService.getFeedbacks(count, offset, token);
        return buildResponseGet(feedbacksDto);
    }
}
