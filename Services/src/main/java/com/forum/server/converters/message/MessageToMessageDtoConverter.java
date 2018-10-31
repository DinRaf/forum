package com.forum.server.converters.message;

import com.forum.server.converters.user.ShortUserToShortUserDtoConverter;
import com.forum.server.dto.message.FixMessageDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.dto.user.ShortUserDto;
import com.forum.server.models.message.Message;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by root on 31.08.16.
 */

public class MessageToMessageDtoConverter implements Converter<Message, MessageDto> {

    public MessageDto convert(Message message){
        ShortUserToShortUserDtoConverter converter = new ShortUserToShortUserDtoConverter();
        ShortUserDto userDto = converter.convert(message.getUser());
        FixMessageDto fixMessageDto = FixMessageDto.builder()
                .date(message.getDate())
                .username(userDto.getNickname())
                .build();
        return MessageDto.builder()
                .messageId(message.getMessageId())
                .author(userDto)
                .message(message.getBody())
                .date(message.getDate())
                .rating(message.getRating())
                .updated(fixMessageDto)
                .isLiked(message.getIsLiked())
                .build();


    }
}
