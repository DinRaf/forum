package com.forum.server.dto.user;

import com.forum.server.dto.Data;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class UserUpdateDto implements Data{
    private String name;
    private String mail;
    private Long dateOfBirth;
    private String info;
    private String avatar;
    private String nickname;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInfo() {
        return info;
    }

    protected UserUpdateDto() {
    }

    private UserUpdateDto(Builder builder) {
        this.name = builder.name;
        this.mail = builder.mail;
        this.dateOfBirth = builder.dateOfBirth;
        this.info = builder.info;
        this.avatar = builder.avatar;
        this.nickname = builder.nickname;
        this.password = builder.password;
    }



    public static class Builder {
        private String name;
        private String mail;
        private Long dateOfBirth;
        private String info;
        private String avatar;
        private String nickname;
        private String password;

        public Builder Password(String password) {
            this.password = password;
            return this;
        }

        public Builder Avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder Nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder Mail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder DateOfBirth(Long dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder Info(String info) {
            this.info = info;
            return this;
        }

        public UserUpdateDto build() {
            return new UserUpdateDto(this);
        }
    }
}
