package com.forum.server.dto.message;

import com.forum.server.dto.Data;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class FixMessageDto implements Data {
    private long date;
    private String username;
    private long userId;

    public long getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public long getUserId() {
        return userId;
    }

    protected FixMessageDto() {
    }
    
    private FixMessageDto(Builder builder) {
        this.date = builder.date;
        this.username = builder.username;
        this.userId =builder.userId;
    }

    public static class Builder {
        private long date;
        private String username;
        private long userId;

        public Builder Date(long date) {
            this.date = date;
            return this;
        }

        public Builder Username(String username) {
            this.username = username;
            return this;
        }

        public Builder UserId(long userId) {
            this.userId = userId;
            return this;
        }

        public FixMessageDto build() { return new FixMessageDto(this); }
    }
}
