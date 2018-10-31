package com.forum.server.models.theme;

import com.forum.server.models.tag.Tag;

import java.util.List;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ThemeSearch {
    private long id;
    private String title;
    private String nickname;
    private long authorId;
    private long date;
    private long messagesCount;
    private boolean status;
    private String sectionUrl;
    private List<Tag> tags;

    public String getSectionUrl() {
        return sectionUrl;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getNickname() {
        return nickname;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getAuthorId() {
        return authorId;
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

    protected ThemeSearch(){}

    private  ThemeSearch(Builder builder){
        this.authorId = builder.authorId;
        this.date = builder.date;
        this.messagesCount = builder.messagesCount;
        this.status = builder.status;
        this.title = builder.title;
        this.id = builder.id;
        this.nickname = builder.nickname;
        this.tags = builder.tags;
        this.sectionUrl = builder.sectionUrl;
    }

    public static class Builder {
        private String title;
        private long authorId;
        private long date;
        private long messagesCount;
        private boolean status;
        private long id;
        private String nickname;
        private String sectionUrl;
        private List<Tag> tags;

        public Builder SectionUrl(String sectionUrl) {
            this.sectionUrl = sectionUrl;
            return this;
        }

        public Builder Tags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder Nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder Id(long id) {
            this.id = id;
            return this;
        }

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder AuthorId(long authorId) {
            this.authorId = authorId;
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

        public ThemeSearch build(){return new ThemeSearch(this);}
    }
}
