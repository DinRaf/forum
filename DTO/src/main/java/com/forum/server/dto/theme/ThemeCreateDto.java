package com.forum.server.dto.theme;

import com.forum.server.dto.Data;
import com.forum.server.dto.tag.TagDto;
import com.forum.server.dto.tag.TagsDto;

import java.util.List;

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
    private List<TagDto> tags;

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getSectionUrl() {
        return sectionUrl;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    protected ThemeCreateDto() {
    }
    
    private ThemeCreateDto(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.sectionUrl = builder.sectionUrl;
        this.tags = builder.tags;
    }
    
    public static class Builder {
        private String title;
        private String message;
        private String sectionUrl;
        private List<TagDto> tags;

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

        public Builder Tags(List<TagDto> tags) {
            this.tags = tags;
            return this;
        }

        public ThemeCreateDto build() {
            return new ThemeCreateDto(this);
        }
    }
}
