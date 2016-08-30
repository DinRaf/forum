package com.forum.server.dto.theme;

import com.forum.server.dto.Data;
import com.forum.server.dto.message.MessagesDto;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ThemeDto implements Data{
    private long themeId;
    private String title;
    private long authorId;
    private long date;
    private long messagesCount;
    private MessagesDto messages;
    private boolean status;

    public long getThemeId() {
        return themeId;
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

    public MessagesDto getMessages() {
        return messages;
    }

    public boolean isStatus() {
        return status;
    }

    protected ThemeDto() {
    }

    private ThemeDto(Builder builder) {
        this.themeId = builder.themeId;
        this.title = builder.title;
        this.authorId = builder.authorId;
        this.date = builder.date;
        this.messagesCount = builder.messagesCount;
        this.messages = builder.messages;
        this.status = builder.status;
    }

    public static class Builder {
        private long themeId;
        private String title;
        private long authorId;
        private long date;
        private long messagesCount;
        private MessagesDto messages;
        private boolean status;

        public Builder ThemeId(long themeId) {
            this.themeId = themeId;
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

        public Builder Messages(MessagesDto messages) {
            this.messages = messages;
            return this;
        }

        public Builder Status(boolean status) {
            this.status = status;
            return this;
        }

        public ThemeDto build() { return new ThemeDto(this); }
    }
}
