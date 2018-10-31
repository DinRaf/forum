package com.forum.server.dto.theme;

import com.forum.server.dto.Data;
import com.forum.server.dto.tag.TagDto;
import com.forum.server.dto.tag.TagsDto;

import java.util.List;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ThemeUpdateDto implements Data {
    private String title;
    private String sectionUrl;
    private List<TagDto> tags;

    public String getTitle() {
        return title;
    }

    public String getSectionUrl() {
        return sectionUrl;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    protected ThemeUpdateDto() {
    }

    private ThemeUpdateDto(Builder builder) {
        this.title = builder.title;
        this.sectionUrl = builder.sectionUrl;
        this.tags = builder.tags;
    }

    public static class Builder {
        private String title;
        private String sectionUrl;
        private List<TagDto> tags;

        public ThemeUpdateDto.Builder Title(String title) {
            this.title = title;
            return this;
        }

        public ThemeUpdateDto.Builder SectionId(String sectionUrl) {
            this.sectionUrl = sectionUrl;
            return this;
        }

        public ThemeUpdateDto.Builder Tags(List<TagDto> tags) {
            this.tags = tags;
            return this;
        }

        public ThemeUpdateDto build() {
            return new ThemeUpdateDto(this);
        }
    }
}
