package com.forum.server.converters.message;

import com.forum.server.models.message.Message;
import org.springframework.core.convert.converter.Converter;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class MessageTextToMessageConverter implements Converter<String, Message> {

    @Override
    public Message convert(String message) {
        return new Message.Builder()
                .Body(message)
                .Date(System.currentTimeMillis())
                .Rating(0l)
                .build();
    }
}
