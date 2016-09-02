package com.forum.server.dto.staticInfo;

import com.forum.server.dto.Data;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class SubsectionDto implements Data {
    private long id;
    private String name;
    private long themesCount;
    private String url;

    public String getUrl() {
        return url;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getThemesCount() {
        return themesCount;
    }

    protected SubsectionDto() {
    }

    private SubsectionDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.themesCount = builder.themesCount;
        this.url = builder.url;
    }

    public static class Builder {
        private long id;
        private String name;
        private long themesCount;
        private String url;

        public Builder Url(String url) {
            this.url = url;
            return this;
        }

        public Builder Id(long id) {
            this.id = id;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder ThemesCount(long themesCount) {
            this.themesCount = themesCount;
            return this;
        }

        public SubsectionDto build() { return new SubsectionDto(this); }
    }
}
