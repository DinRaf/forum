package com.forum.server.dto.staticInfo;

/**
 * 05.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class SectionCreateDto {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    protected SectionCreateDto() {
    }

    private SectionCreateDto(Builder builder) {
        this.name = builder.name;
        this.url = builder.url;
    }

    public static class Builder {
        private String name;
        private String url;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public SectionCreateDto build() { return new SectionCreateDto(this); }
    }
}
