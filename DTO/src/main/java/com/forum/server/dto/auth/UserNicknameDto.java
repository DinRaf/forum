package com.forum.server.dto.auth;

import com.forum.server.dto.Data;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class UserNicknameDto implements Data{
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    protected UserNicknameDto() {
    }

    private UserNicknameDto(Builder builder) {
        this.nickname = builder.nickname;
    }

    public static class Builder {
        private String nickname;

        public Builder Nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserNicknameDto build() { return new UserNicknameDto(this); }
    }
}
