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
    private String nickname;

    public String getToken() {
        return token;
    }

    public String getNickname() {
        return nickname;
    }

    protected LoginDto() {
    }

    private LoginDto(Builder builder) {
        this.token = builder.token;
        this.nickname = builder.nickname;
    }

    public static class Builder {
        private String token;
        private String nickname;

        public Builder Token(String token) {
            this.token = token;
            return this;
        }

        public Builder Nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }
        public LoginDto build() { return new LoginDto(this); }
    }
}
