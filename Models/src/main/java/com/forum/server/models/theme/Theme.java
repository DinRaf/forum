package com.forum.server.models.theme;

import com.forum.server.models.user.ShortUser;

/**
 * 29.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class Theme {
    private long themeId;
    private ShortUser user;
    private String sectionUrl;
    private String subsectionUrl;
    private String title;
    private long date;
    private long messagesCount;
    private boolean status;

    public void setUser(ShortUser user) {
        this.user = user;
    }

    public long getThemeId() {
        return themeId;
    }

    public ShortUser getUser() {
        return user;
    }

    public String getSectionUrl() {
        return sectionUrl;
    }

    public String getSubsectionUrl() {
        return subsectionUrl;
    }

    public String getTitle() {
        return title;
    }

    public long getDate() {
        return date;
    }

    public long getMessagesCount() {
        return messagesCount;
    }

    public boolean isStatus() {
        return status;
    }

    protected Theme() {
    }

    private Theme(Builder builder) {
        this.themeId = builder.themeId;
        this.user = builder.user;
        this.sectionUrl = builder.sectionUrl;
        this.subsectionUrl = builder.subsectionUrl;
        this.title = builder.title;
        this.date = builder.date;
        this.messagesCount = builder.messagesCount;
        this.status = builder.status;
    }

    public static class Builder {
        private long themeId;
        private ShortUser user;
        private String sectionUrl;
        private String subsectionUrl;
        private String title;
        private long date;
        private long messagesCount;
        private boolean status;

        public Builder ThemeId(long themeId) {
            this.themeId = themeId;
            return this;
        }

        public Builder User(ShortUser user) {
            this.user = user;
            return this;
        }

        public Builder SectionUrl(String sectionUrl) {
            this.sectionUrl = sectionUrl;
            return this;
        }

        public Builder SubsectionUrl(String subsectionUrl) {
            this.subsectionUrl = subsectionUrl;
            return this;
        }

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder Date(long date) {
            this.date = date;
            return this;
        }

        public Builder MessagesCount(long messagesCount) {
            this.messagesCount = messagesCount;
            return this;
        }

        public Builder Status(boolean status) {
            this.status = status;
            return this;
        }

        public Theme build() { return new Theme(this); }
    }
}
