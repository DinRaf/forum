package com.forum.server.services.implementations;

import com.forum.server.dao.interfaces.FeedbackDao;
import com.forum.server.dto.message.FeedbacksDto;
import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.services.interfaces.FeedbackService;
import com.forum.server.validation.MessageValidator;
import com.forum.server.validation.RightsValidator;
import com.forum.server.validation.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 09.09.16.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private MessageValidator messageValidator;

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private RightsValidator rightsValidator;

    @Autowired
    private FeedbackDao feedbackDao;

    public void createFeedback(MessageCreateDto messageCreateDto) {
        //берём текст сообщений
        String messageText = messageCreateDto.getMessage();
        //проверяем написанный текст
        messageValidator.verifyMessageText(messageText);
        feedbackDao.saveFeedback(messageText);
    }

    public FeedbacksDto getFeedbacks(int count, Integer offset, String token) {
        tokenValidator.verifyOnExistence(token);
        rightsValidator.getFeedbacks(token);
        if (offset == null) {
            offset = 0;
        }
        return new FeedbacksDto(feedbackDao.getFeedbacksWithLimitOffset(count, offset));
    }

}
