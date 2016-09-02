package com.forum.server.dto.theme;

import com.forum.server.dto.Data;

/**
 * Created by root on 02.09.16.
 */
public class ThemeSearchDto implements Data {
    private String title;
    private long authorId;
    private long date;
    private long messagesCount;
    private boolean status;

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

    protected ThemeSearchDto(){}

    private  ThemeSearchDto(Builder builder){
        this.authorId = builder.authorId;
        this.date = builder.date;
        this.messagesCount = builder.messagesCount;
        this.status = builder.status;
        this.title = builder.title;
    }

    public static class Builder {
        private String title;
        private long authorId;
        private long date;
        private long messagesCount;
        private boolean status;

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

        public ThemeSearchDto build(){return new ThemeSearchDto(this);}
    }
}
