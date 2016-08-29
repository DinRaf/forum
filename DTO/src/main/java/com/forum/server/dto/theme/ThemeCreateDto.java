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
    private int sectionId;
    private int subsectionId;

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getSubsectionId() {
        return subsectionId;
    }

    protected ThemeCreateDto() {
    }
    
    private ThemeCreateDto(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.sectionId = builder.sectionId;
        this.subsectionId = builder.subsectionId;
    }
    
    public static class Builder {
        private String title;
        private String message;
        private int sectionId;
        private int subsectionId;

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder Message(String message) {
            this.message = message;
            return this;
        }

        public Builder SectionId(int sectionId) {
            this.sectionId = sectionId;
            return this;
        }

        public Builder SubsectionId(int subsectionId) {
            this.subsectionId = subsectionId;
            return this;
        }

        public ThemeCreateDto build() {
            return new ThemeCreateDto(this);
        }
    }
}
