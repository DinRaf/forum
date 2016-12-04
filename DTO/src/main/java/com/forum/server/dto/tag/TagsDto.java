package com.forum.server.dto.tag;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.forum.server.dto.Data;

import java.util.List;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class TagsDto implements Data{

    private List<TagDto> tags;

    @JsonValue
    public List<TagDto> getThemes() {
        return tags;
    }

    public TagsDto(List<TagDto> themes) {
        this.tags = themes;
    }
}
