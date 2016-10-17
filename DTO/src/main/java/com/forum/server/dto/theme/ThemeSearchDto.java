package com.forum.server.dto.theme;

import com.forum.server.dto.Data;
import com.forum.server.dto.tag.TagsDto;

/**
 * Created by root on 02.09.16.
 */
public class ThemeSearchDto implements Data {
    private long id;
    private String title;
    private String nickname;
    private long authorId;
    private long date;
    private long messagesCount;
    private boolean status;
    private TagsDto tags;

    public TagsDto getTags() {
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

    protected ThemeSearchDto(){}

    private  ThemeSearchDto(Builder builder){
        this.authorId = builder.authorId;
        this.date = builder.date;
        this.messagesCount = builder.messagesCount;
        this.status = builder.status;
        this.title = builder.title;
        this.id = builder.id;
        this.nickname = builder.nickname;
        this.tags = builder.tags;
    }

    public static class Builder {
        private String title;
        private long authorId;
        private long date;
        private long messagesCount;
        private boolean status;
        private long id;
        private String nickname;
        private TagsDto tags;

        public Builder Tags(TagsDto tags) {
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

        public ThemeSearchDto build(){return new ThemeSearchDto(this);}
    }
}
