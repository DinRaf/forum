package com.forum.server.models.tag;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class Tag {
    private String name;

    public String getName() {
        return name;
    }

    protected Tag() {
    }

    private Tag(Builder builder) {
        this.name = builder.name;
    }

    public static class Builder {
        private String name;

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Tag build() { return new Tag(this); }
    }
}
