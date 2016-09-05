package com.forum.server.dto.auth;

import com.forum.server.dto.Data;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class AuthDto implements Data {

    private String nickname;
    private String mail;
    private String password;

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    protected AuthDto() {
    }

    private AuthDto(Builder builder) {
        this.nickname = builder.nickname;
        this.mail = builder.mail;
        this.password = builder.password;
    }

    private static class Builder {
        private String nickname;
        private String mail;
        private String password;

        public Builder nickname(String data) {
            this.nickname = data;
            return this;
        }
        public Builder mail(String data) {
            this.mail = data;
            return this;
        }
        public Builder password(String data) {
            this.password = data;
            return this;
        }

        public AuthDto build() {
            return new AuthDto(this);
        }
    }
}