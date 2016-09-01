package com.forum.server.models.theme;

/**
 * Created by root on 01.09.16.
 */
public class ThemeUpdate {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    protected ThemeUpdate(ThemeUpdate themeUpdate) {
    }

    private ThemeUpdate(Builder builder) {
        this.title = builder.title;
    }

    public static class Builder {
        private String title;

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public ThemeUpdate build() { return new ThemeUpdate(this); }

    }
}
