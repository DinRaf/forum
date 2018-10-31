package com.forum.server.models.staticInfo;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class Info {
    private String title;
    private String text;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    protected Info() {
    }

    public Info(Builder builder) {
        this.title = builder.title;
        this.text = builder.text;
    }

    public static class Builder {
        private String title;
        private String text;

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder Text(String text) {
            this.text = text;
            return this;
        }

        public Info build() { return new Info(this); }
    }
}
