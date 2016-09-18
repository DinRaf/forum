package com.forum.server.dto.message;

import com.forum.server.dto.Data;
import com.forum.server.dto.user.ShortUserDto;

/**
 * Created by aisalin on 15.08.16.
 */
public class MessageDto implements Data{
    private long messageId;
    private ShortUserDto author;
    private String message;
    private long date;
    private long rating;
    private FixMessageDto updated;

    public long getMessageId() {
        return messageId;
    }

    public ShortUserDto getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public long getDate() {
        return date;
    }

    public long getRating() {
        return rating;
    }

    public FixMessageDto getUpdated() {
        return updated;
    }

    protected MessageDto() {
    }

    private MessageDto(Builder builder) {
        this.messageId = builder.messageId;
        this.author = builder.author;
        this.message = builder.message;
        this.date = builder.date;
        this.rating = builder.rating;
        this.updated = builder.updated;
    }

    public static class Builder {
        private long messageId;
        private ShortUserDto author;
        private String message;
        private long date;
        private long rating;
        private FixMessageDto updated;

        public Builder MessageId(long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder Author(ShortUserDto author) {
            this.author = author;
            return this;
        }

        public Builder Message(String message) {
            this.message = message;
            return this;
        }

        public Builder Date(long date) {
            this.date = date;
            return this;
        }

        public Builder Rating(long rating) {
            this.rating = rating;
            return this;
        }

        public Builder Updated(FixMessageDto updated) {
            this.updated = updated;
            return this;
        }

        public MessageDto build() { return new MessageDto(this); }
    }
}
