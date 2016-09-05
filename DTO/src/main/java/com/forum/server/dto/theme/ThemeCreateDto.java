package com.forum.server.dto.theme;

import com.forum.server.dto.Data;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ThemeCreateDto implements Data{

    private String title;
    private String message;
    private String sectionUrl;
    private String subsectionUrl;

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getSectionUrl() {
        return sectionUrl;
    }

    public String getSubsectionUrl() {
        return subsectionUrl;
    }

    protected ThemeCreateDto() {
    }
    
    private ThemeCreateDto(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.sectionUrl = builder.sectionUrl;
        this.subsectionUrl = builder.subsectionUrl;
    }
    
    public static class Builder {
        private String title;
        private String message;
        private String sectionUrl;
        private String subsectionUrl;

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder Message(String message) {
            this.message = message;
            return this;
        }

        public Builder SectionId(String sectionUrl) {
            this.sectionUrl = sectionUrl;
            return this;
        }

        public Builder SubsectionUrl(String subsectionUrl) {
            this.subsectionUrl = subsectionUrl;
            return this;
        }

        public ThemeCreateDto build() {
            return new ThemeCreateDto(this);
        }
    }
}
