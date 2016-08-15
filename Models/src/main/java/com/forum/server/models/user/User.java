package com.forum.server.models.user;

/**
 * 09.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class User extends ShortUser{
    private String name;
    private String mail;
    private Integer dateOfBirth;
    private String info;
    private long registrationTime;
    private Integer lastSession;
    private int MessagesCount;
    private int ThemesCount;
    private String hashPassword;

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    public String getInfo() {
        return info;
    }

    public long getRegistrationTime() {
        return registrationTime;
    }

    public Integer getLastSession() {
        return lastSession;
    }

    public int getMessagesCount() {
        return MessagesCount;
    }

    public int getThemesCount() {
        return ThemesCount;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    protected User() {
    }

    public User(Builder builder) {
        super(builder);
        this.name = builder.name;
    }



    public static class Builder extends ShortUser.Builder{
        private String name;
        private String mail;
        private Integer dateOfBirth;
        private String info;
        private long registrationTime;
        private Integer lastSession;
        private int messagesCount;
        private int themesCount;
        private String hashPassword;
        
        
        @Override
        public Builder UserId(int userId) {
            super.UserId(userId);
            return this;
        }

        @Override
        public Builder NickName(String nickName) {
            super.NickName(nickName);
            return this;
        }

        @Override
        public Builder Rating(Integer rating) {
            super.Rating(rating);
            return this;
        }

        @Override
        public Builder Avatar(String avatar) {
            super.Avatar(avatar);
            return this;
        }

        @Override
        public Builder IsOnline(boolean isOnline) {
            super.IsOnline(isOnline);
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

        public Builder DateOfBirth(Integer dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder Info(String info) {
            this.info = info;
            return this;
        }

        public Builder RegistrationTime(long registrationTime) {
            this.registrationTime = registrationTime;
            return this;
        }

        public Builder LastSession(Integer lastSession) {
            this.lastSession = lastSession;
            return this;
        }

        public Builder MessagesCount(int messagesCount) {
            this.messagesCount = messagesCount;
            return this;
        }
        public Builder ThemesCount(int themesCount) {
            this.themesCount = themesCount;
            return this;
        }

        public Builder HashPassword(String hashPassword) {
            this.hashPassword = hashPassword;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
