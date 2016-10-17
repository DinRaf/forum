package com.forum.server.converters.tags;

import com.forum.server.dto.tag.TagsDto;
import com.forum.server.models.tag.Tag;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ListTagToTagsDtoConverter implements Converter<List<Tag>, TagsDto> {
    @Override
    public TagsDto convert(List<Tag> tags) {
        TagToTagDtoConverter converter = new TagToTagDtoConverter();
        return new TagsDto(tags
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }
}
