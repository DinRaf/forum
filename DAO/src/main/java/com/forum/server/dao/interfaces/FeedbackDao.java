package com.forum.server.dao.interfaces;

import com.forum.server.dto.message.FeedbackDto;

import java.util.List;

/**
 * Created by root on 09.09.16.
 */
public interface FeedbackDao {
    void saveFeedback(String feedback);

    List<FeedbackDto> getFeedbacksWithLimitOffset(int count, Integer offset);
}
