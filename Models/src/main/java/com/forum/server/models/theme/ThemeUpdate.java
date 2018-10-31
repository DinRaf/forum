package com.forum.server.models.theme;

/**
 * Created by root on 01.09.16.
 */
public class ThemeUpdate {
    private String title;
    private String sectionUrl;

    public String getSectionUrl() {
        return sectionUrl;
    }

    public String getTitle() {
        return title;
    }

    protected ThemeUpdate() {
    }

    private ThemeUpdate(Builder builder) {
        this.title = builder.title;
        this.sectionUrl = builder.sectionUrl;
    }

    public static class Builder {
        private String title;
        private String sectionUrl;

        public Builder SectionUrl(String sectionUrl) {
            this.sectionUrl = sectionUrl;
            return this;
        }

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public ThemeUpdate build() { return new ThemeUpdate(this); }

    }
}
