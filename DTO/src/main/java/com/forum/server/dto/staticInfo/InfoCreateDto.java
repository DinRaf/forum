package com.forum.server.dto.staticInfo;

/**
 * Created by root on 05.09.16.
 */
public class InfoCreateDto {
    private String identifier;
    private String title;
    private String text;

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    protected InfoCreateDto() {}

    public InfoCreateDto(Builder builder) {
        this.identifier = builder.identifier;
        this.title = builder.title;
        this.text = builder.text;
    }
    
    private static class Builder {
        private String identifier;
        private String title;
        private String text;

        public Builder Identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder Text(String text) {
            this.text = text;
            return this;
        }

        InfoCreateDto build() {return new InfoCreateDto(this);}
    }
}
