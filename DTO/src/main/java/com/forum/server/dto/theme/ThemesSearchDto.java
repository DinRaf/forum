package com.forum.server.dto.theme;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.forum.server.dto.Data;

import java.util.List;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class ThemesSearchDto implements Data {

    private List<ThemeSearchDto> themes;

    @JsonValue
    public List<ThemeSearchDto> getThemes() {
        return themes;
    }

    public ThemesSearchDto(List<ThemeSearchDto> themes) {
        this.themes = themes;
    }
}
