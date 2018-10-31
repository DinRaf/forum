package com.forum.server.services.interfaces;

import com.forum.server.dto.message.FeedbacksDto;
import com.forum.server.dto.message.MessageCreateDto;

/**
 * Created by root on 09.09.16.
 */
public interface FeedbackService {
    void createFeedback(MessageCreateDto messageCreateDto, String token, boolean anon);

    FeedbacksDto getFeedbacks(int count, Integer offset, String token);
}
