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
        FixMessageDto fixMessageDto = new FixMessageDto.Builder()
                .Date(message.getDate())
                .UserId(userDto.getUserId())
                .Username(userDto.getNickName())
                .build();
        return new MessageDto.Builder()
                .MessageId(message.getMessageId())
                .Author(userDto)
                .Message(message.getBody())
                .Date(message.getDate())
                .Rating(message.getRating())
                .Updated(fixMessageDto)
                .build();


    }
}
