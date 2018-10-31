package com.forum.server.converters.tags;

import com.forum.server.dto.tag.TagDto;
import com.forum.server.models.tag.Tag;
import org.springframework.core.convert.converter.Converter;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class TagToTagDtoConverter implements Converter<Tag, TagDto> {
    @Override
    public TagDto convert(Tag tag) {
        return TagDto.builder()
                .name(tag.getName())
                .build();
    }
}
