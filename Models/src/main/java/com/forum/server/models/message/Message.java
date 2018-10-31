package com.forum.server.models.message;

import com.forum.server.models.user.ShortUser;

/**
 * Created by root on 29.08.16.
 */
public class Message {

    private long messageId;
    private ShortUser user;
    private long themeId;
    private long date;
    private String body;
    private Long update;
    private long rating;
    private Long updaterId;
    private String updaterNickname;
    private Boolean isLiked;

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public void setUser(ShortUser user) {
        this.user = user;
    }

    public void setThemeId(long themeId) {
        this.themeId = themeId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public void setUpdate(Long update) {
        this.update = update;
    }

    public void setUpdaterNickname(String updaterNickname) {
        this.updaterNickname = updaterNickname;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public String getUpdaterNickname() {
        return updaterNickname;
    }
    
    public long getMessageId() {
        return messageId;
    }

    public ShortUser getUser() {
        return user;
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

    public Long getUpdate() {
        return update;
    }

    public long getRating() {
        return rating;
    }

    protected Message(){}

    private Message(Builder builder) {
        this.messageId = builder.messageId;
        this.user = builder.user;
        this.themeId = builder.themeId;
        this.date = builder.date;
        this.body = builder.body;
        this.update = builder.update;
        this.rating = builder.rating;
        this.updaterId = builder.updaterId;
        this.updaterNickname = builder.updaterNickname;
        this.isLiked = builder.isLiked;
    }

    public static class Builder {

        private long messageId;
        private ShortUser user;
        private long themeId;
        private long date;
        private String body;
        private Long update;
        private long rating;
        private Long updaterId;
        private String updaterNickname;
        private Boolean isLiked;

        public Builder Liked(Boolean liked) {
            isLiked = liked;
            return this;
        }

        public Builder MessageId(long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder UpdaterId(Long updaterId) {
            this.updaterId = updaterId;
            return this;
        }

        public Builder UpdaterNickname(String updaterNickname) {
            this.updaterNickname = updaterNickname;
            return this;
        }

        public Builder User(ShortUser user) {
            this.user = user;
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

        public Builder Update(Long update) {
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
