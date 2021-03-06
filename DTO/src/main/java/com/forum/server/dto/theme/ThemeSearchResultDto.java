package com.forum.server.dto.theme;

import com.forum.server.dto.Data;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ThemeSearchResultDto implements Data {
    private ThemesSearchDto themesSearchDto;
    private int count;
    private String section;

    public String getSection() {
        return section;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ThemesSearchDto getThemesSearchDto() {

        return themesSearchDto;
    }

    public int getCount() {
        return count;
    }

    protected ThemeSearchResultDto() {}

    private ThemeSearchResultDto(Builder builder) {
        this.count = builder.count;
        this.themesSearchDto = builder.themesSearchDto;
        this.section = builder.section;
    }

    public static class Builder {
        private ThemesSearchDto themesSearchDto;
        private int count;
        private String section;

        public Builder ThemesSearhDto(ThemesSearchDto themesSearchDto) {
            this.themesSearchDto = themesSearchDto;
            return  this;
        }

        public Builder Section(String section) {
            this.section = section;
            return this;
        }

        public Builder Count(int count) {
            this.count = count;
            return this;
        }
        
        public ThemeSearchResultDto build() {return new ThemeSearchResultDto(this);}
    }
}
