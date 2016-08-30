package com.forum.server.models.message;

/**
 * Created by root on 29.08.16.
 */
public class Message {

    private long messageId;
    private long userId;
    private long themeId;
    private long date;
    private String body;
    private long update;
    private long rating;
    private long updaterId;
    private String updaterNickname;

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public long getUpdaterId() {
        return updaterId;
    }

    public String getUpdaterNickname() {
        return updaterNickname;
    }
    
    public long getMessageId() {
        return messageId;
    }

    public long getUserId() {
        return userId;
    }

    public long getThemeId() {
        return themeId;
    }

    public long getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    public long getUpdate() {
        return update;
    }

    public long getRating() {
        return rating;
    }

    protected Message(){}

    private Message(Builder builder) {
        this.messageId = builder.messageId;
        this.userId = builder.userId;
        this.themeId = builder.themeId;
        this.date = builder.date;
        this.body = builder.body;
        this.update = builder.update;
        this.rating = builder.rating;
        this.updaterId = builder.updaterId;
        this.updaterNickname = builder.updaterNickname;
    }

    public static class Builder {

        private long messageId;
        private long userId;
        private long themeId;
        private long date;
        private String body;
        private long update;
        private long rating;
        private long updaterId;
        private String updaterNickname;

        public Builder MessageId(long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder UpdaterId(long updaterId) {
            this.updaterId = updaterId;
            return this;
        }

        public Builder UpdaterNickname(String updaterNickname) {
            this.updaterNickname = updaterNickname;
            return this;
        }

        public Builder UserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder ThemeId(long themeId) {
            this.themeId = themeId;
            return this;
        }

        public Builder Date(long date) {
            this.date = date;
            return this;
        }

        public Builder Body(String body) {
            this.body = body;
            return this;
        }

        public Builder Update(long update) {
            this.update = update;
            return this;
        }

        public Builder Rating(long rating) {
            this.rating = rating;
            return this;
        }

        public Message build() {return new Message(this);}
    }
}