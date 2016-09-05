package com.forum.server.dto.staticInfo;

/**
 * 05.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class SubsectionCreateDto {
    private String name;
    private String url;
    private String sectionUrl;

    public String getSectionUrl() {
        return sectionUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    protected SubsectionCreateDto() {
    }

    private SubsectionCreateDto(Builder builder) {
        this.name = builder.name;
        this.url = builder.url;
        this.sectionUrl = builder.sectionUrl;
    }

    public static class Builder {
        private String name;
        private String url;
        private String sectionUrl;

        public Builder setSectionUrl(String sectionUrl) {
            this.sectionUrl = sectionUrl;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public SubsectionCreateDto build() { return new SubsectionCreateDto(this); }
    }
}
