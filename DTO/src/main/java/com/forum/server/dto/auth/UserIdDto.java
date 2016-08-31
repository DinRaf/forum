package com.forum.server.dto.auth;

import com.forum.server.dto.Data;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class UserIdDto implements Data{
    private long userId;

    public long getUserId() {
        return userId;
    }

    protected UserIdDto() {
    }

    private UserIdDto(Builder builder) {
        this.userId = builder.userId;
    }

    public static class Builder {
        private long userId;

        public Builder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public UserIdDto build() { return new UserIdDto(this); }
    }
}
