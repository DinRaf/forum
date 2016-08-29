package com.forum.server.dto.message;

import com.forum.server.dto.Data;

/**
 * Created by aisalin on 15.08.16.
 */
public class MessageCreateDto implements Data {

    private String message;

    public String getMessage(){return message;}

    protected MessageCreateDto() {
    }

    private MessageCreateDto(Builder builder){
        this.message = builder.message;
    }

    public static class Builder {

        private String message;

        Builder message(String data){
            this.message = data;
            return this;
        }

        public MessageCreateDto build() {
            return new MessageCreateDto(this);
        }

    }
}
