package com.forum.server.dto.auth;

import com.forum.server.dto.Data;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class AuthDto implements Data {

    private String nickName;
    private String mail;
    private String password;

    public String getNickName() {
        return nickName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    protected AuthDto() {
    }

    private AuthDto(Builder builder) {
        this.nickName = builder.nickName;
        this.mail = builder.mail;
        this.password = builder.password;
    }

    public static class Builder {
        private String nickName;
        private String mail;
        private String password;

        public Builder nickname(String data) {
            this.nickName = data;
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