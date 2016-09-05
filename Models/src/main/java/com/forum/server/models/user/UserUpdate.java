package com.forum.server.models.user;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class UserUpdate {
    private String name;
    private String mail;
    private Long dateOfBirth;
    private String info;
    private String avatar;
    private String nickname;
    private String hashPassword;

    public String getHashPassword() {
        return hashPassword;
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

    protected UserUpdate() {
    }

    private UserUpdate(Builder builder) {
        this.name = builder.name;
        this.mail = builder.mail;
        this.dateOfBirth = builder.dateOfBirth;
        this.info = builder.info;
        this.avatar = builder.avatar;
        this.nickname = builder.nickname;
        this.hashPassword = builder.hashPassword;
    }



    public static class Builder {
        private String name;
        private String mail;
        private Long dateOfBirth;
        private String info;
        private String avatar;
        private String nickname;
        private String hashPassword;

        public Builder HashPassword(String hashPassword) {
            this.hashPassword = hashPassword;
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

        public UserUpdate build() {
            return new UserUpdate(this);
        }
    }
}
