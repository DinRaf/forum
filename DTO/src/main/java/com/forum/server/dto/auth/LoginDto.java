package com.forum.server.dto.auth;

import com.forum.server.dto.Data;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class LoginDto implements Data {
    private String token;
    private long userId;

    public String getToken() {
        return token;
    }

    public long getUserId() {
        return userId;
    }

    protected LoginDto() {
    }

    private LoginDto(Builder builder) {
        this.token = builder.token;
        this.userId = builder.userId;
    }

    public static class Builder {
        private String token;
        private long userId;

        public Builder Token(String token) {
            this.token = token;
            return this;
        }

        public Builder UserId(long userId) {
            this.userId = userId;
            return this;
        }
        public LoginDto build() { return new LoginDto(this); }
    }
}
