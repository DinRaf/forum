package com.forum.server.converters.message;

import com.forum.server.dto.message.MessagesDto;
import com.forum.server.models.message.Message;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 31.08.16.
 */

public class ListMessageToMessagesDtoConverter implements Converter<List<Message>, MessagesDto> {
    @Override
    public MessagesDto convert(List<Message> listMessages) {
        MessageToMessageDtoConverter converter = new MessageToMessageDtoConverter();
        return new MessagesDto(listMessages
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }
}
