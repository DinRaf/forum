package com.forum.server.services.implementations;

import com.forum.server.dao.interfaces.FeedbackDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.message.FeedbacksDto;
import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.staticInfo.RequestSlack;
import com.forum.server.services.interfaces.FeedbackService;
import com.forum.server.validation.MessageValidator;
import com.forum.server.validation.RightsValidator;
import com.forum.server.validation.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by root on 09.09.16.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    public static final String URL = "https://hooks.slack.com/services/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";

    @Autowired
    private MessageValidator messageValidator;

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private RightsValidator rightsValidator;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private FeedbackDao feedbackDao;

    public void createFeedback(MessageCreateDto messageCreateDto, String token, boolean anon) {
        //берём текст сообщений
        String messageText = messageCreateDto.getMessage();
        //проверяем написанный текст
        messageValidator.verifyMessageText(messageText);
        feedbackDao.saveFeedback(messageText);
        if (anon) {
            sendHook(messageText, "Anonymous", ":alien:");
        } else {
            sendHook(messageText, usersDao.getNicknameByToken(token), ":smile:");
        }
    }

    public FeedbacksDto getFeedbacks(int count, Integer offset, String token) {
        tokenValidator.verifyOnExistence(token);
        rightsValidator.getFeedbacks(token);
        if (offset == null || offset < 0) {
            offset = 0;
        }
        return new FeedbacksDto(feedbackDao.getFeedbacksWithLimitOffset(count, offset));
    }

    private static void sendHook(String text, String nickname, String emoji) {
        new RestTemplate().postForEntity(URL, new RequestSlack.builder()
                .setText(text)
                .setChannel("#feedback")
                .setIcon_emoji(emoji)
                .setUsername(nickname)
                .build(),
                String.class);
    }
}
