package com.forum.server.models.user;

/**
 * 15.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ShortUser {
    private int userId;
    private String nickName;
    private Integer rating;
    private String avatar;
    private boolean isOnline;

    public int getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public Integer getRating() {
        return rating;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isOnline() {
        return isOnline;
    }

    protected ShortUser() {}

    public ShortUser(Builder builder) {
        this.userId = builder.userId;
        this.nickName = builder.nickName;
        this.rating = builder.rating;
        this.avatar = builder.avatar;
        this.isOnline = builder.isOnline;
    }

    public abstract static class Builder {
        private int userId;
        private String nickName;
        private Integer rating;
        private String avatar;
        private boolean isOnline;

        public Builder UserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder NickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder Rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder Avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder IsOnline(boolean isOnline) {
            this.isOnline = isOnline;
            return this;
        }

        public  ShortUser build() {
            return new ShortUser(this);
        }
    }
}
