package com.forum.server.models.theme;

/**
 * 29.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class Theme {
    private long themeId;
    private long userId;
    private long sectionId;
    private long subsectionId;
    private String title;
    private long date;
    private long messagesCount;
    private boolean status;

    public Theme setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getThemeId() {
        return themeId;
    }

    public long getUserId() {
        return userId;
    }

    public long getSectionId() {
        return sectionId;
    }

    public long getSubsectionId() {
        return subsectionId;
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
        this.userId = builder.userId;
        this.sectionId = builder.sectionId;
        this.subsectionId = builder.subsectionId;
        this.title = builder.title;
        this.date = builder.date;
        this.messagesCount = builder.messagesCount;
        this.status = builder.status;
    }

    public static class Builder {
        private long themeId;
        private long userId;
        private long sectionId;
        private long subsectionId;
        private String title;
        private long date;
        private long messagesCount;
        private boolean status;

        public Builder ThemeId(long themeId) {
            this.themeId = themeId;
            return this;
        }

        public Builder UserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder SectionId(long sectionId) {
            this.sectionId = sectionId;
            return this;
        }

        public Builder SubsectionId(long subsectionId) {
            this.subsectionId = subsectionId;
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
