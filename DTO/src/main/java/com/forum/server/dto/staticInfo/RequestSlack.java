package com.forum.server.dto.staticInfo;

/**
 * 19.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class RequestSlack {
    private String channel;
    private String text;
    private String icon_emoji;
    private String username;

    public String getIcon_emoji() {
        return icon_emoji;
    }

    public String getUsername() {
        return username;
    }

    public String getChannel() {
        return channel;
    }

    public String getText() {
        return text;
    }

    protected RequestSlack() {
    }

    private RequestSlack(Builder builder) {
        this.channel = builder.channel;
        this.text = builder.text;
        this.icon_emoji = builder.icon_emoji;
        this.username = builder.username;
    }

    public static class Builder {
        private String channel;
        private String text;
        private String icon_emoji;
        private String username;

        public Builder setIcon_emoji(String icon_emoji) {
            this.icon_emoji = icon_emoji;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setChannel(String channel) {
            this.channel = channel;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public RequestSlack build() { return new RequestSlack(this); }
    }
}
