package com.forum.server.dto.message;

import com.fasterxml.jackson.annotation.JsonValue;
import com.forum.server.dto.Data;

import java.util.List;

/**
 * Created by root on 10.09.16.
 */
public class FeedbacksDto implements Data {
    private List<FeedbackDto> feedbackDtos;

    @JsonValue
    public List<FeedbackDto> getFeedbacks() {
        return feedbackDtos;
    }

    public FeedbacksDto(List<FeedbackDto> feedbackDtos) {
        this.feedbackDtos = feedbackDtos;
    }
}
