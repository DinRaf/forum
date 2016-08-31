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
    private Long dateOfBirth;
    private String info;
    private long registrationTime;
    private long lastSession;
    private long messagesCount;
    private long themesCount;
    private String hashPassword;

    public void setRegistrationTime(long registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setLastSession(long lastSession) {
        this.lastSession = lastSession;
    }

    public void setMessagesCount(long messagesCount) {
        this.messagesCount = messagesCount;
    }

    public void setThemesCount(long themesCount) {
        this.themesCount = themesCount;
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

    public long getRegistrationTime() {
        return registrationTime;
    }

    public Long getLastSession() {
        return lastSession;
    }

    public long getMessagesCount() {
        return messagesCount;
    }

    public long getThemesCount() {
        return themesCount;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    protected User() {
    }

    private User(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.mail = builder.mail;
        this.dateOfBirth = builder.dateOfBirth;
        this.info = builder.info;
        this.registrationTime = builder.registrationTime;
        this.lastSession = builder.lastSession;
        this.messagesCount = builder.messagesCount;
        this.themesCount = builder.themesCount;
        this.hashPassword = builder.hashPassword;
    }



    public static class Builder extends ShortUser.Builder{
        private String name;
        private String mail;
        private Long dateOfBirth;
        private String info;
        private long registrationTime;
        private long lastSession;
        private long messagesCount;
        private long themesCount;
        private String hashPassword;

        @Override
        public Builder UserId(long userId) {
            super.UserId(userId);
            return this;
        }

        @Override
        public Builder NickName(String nickName) {
            super.NickName(nickName);
            return this;
        }

        @Override
        public Builder Rating(Long rating) {
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

        public Builder DateOfBirth(Long dateOfBirth) {
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

        public Builder LastSession(long lastSession) {
            this.lastSession = lastSession;
            return this;
        }

        public Builder MessagesCount(long messagesCount) {
            this.messagesCount = messagesCount;
            return this;
        }
        public Builder ThemesCount(long themesCount) {
            this.themesCount = themesCount;
            return this;
        }

        public Builder HashPassword(String hashPassword) {
            this.hashPassword = hashPassword;
            return this;
        }

        @Override
        public Builder Rights(String rights) {
            super.Rights(rights);
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
