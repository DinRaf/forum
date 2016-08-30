package com.forum.server.dto.user;

import com.forum.server.dto.Data;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ShortUserDto implements Data {
    private long userId;
    private String nickName;
    private long rating;
    private boolean isOnline;
    private String avatar;

    public long getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public long getRating() {
        return rating;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getAvatar() {
        return avatar;
    }

    protected ShortUserDto() {
    }

    private ShortUserDto(Builder builder) {
        this.userId = builder.userId;
        this.nickName = builder.nickName;
        this.rating = builder.rating;
        this.isOnline = builder.isOnline;
        this.avatar = builder.avatar;
    }

    public static class Builder {
        private long userId;
        private String nickName;
        private long rating;
        private boolean isOnline;
        private String avatar;

        public Builder UserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder NickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder Rating(long rating) {
            this.rating = rating;
            return this;
        }

        public Builder Online(boolean online) {
            isOnline = online;
            return this;
        }

        public Builder Avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public ShortUserDto build() { return new ShortUserDto(this); }
    }
}
