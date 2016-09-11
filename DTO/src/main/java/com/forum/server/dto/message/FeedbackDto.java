package com.forum.server.dto.message;

import com.forum.server.dto.Data;

/**
 * Created by root on 10.09.16.
 */
public class FeedbackDto implements Data {
    String feedback;

    public String getFeedback() {
        return feedback;
    }

    protected FeedbackDto(){}

    private FeedbackDto(Builder builder) {
        this.feedback = builder.feedback;
    }

    public static class Builder {
        private String feedback;

        public Builder feedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public FeedbackDto build() { return new  FeedbackDto(this);}
    }
}
