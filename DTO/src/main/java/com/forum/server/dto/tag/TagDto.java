package com.forum.server.dto.tag;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class TagDto {
    private String name;

    public String getName() {
        return name;
    }

    protected TagDto() {
    }

    private TagDto(Builder builder) {
        this.name = builder.name;
    }

    public static class Builder {
        private String name;

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public TagDto build() { return new TagDto(this); }
    }
}
