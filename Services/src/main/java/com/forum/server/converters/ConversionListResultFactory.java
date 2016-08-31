package com.forum.server.converters;

import com.forum.server.converters.message.ListMessageToMessagesDtoConverter;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.models.message.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by root on 31.08.16.
 */

@Component
public class ConversionListResultFactory {

    public MessagesDto convert(List<Message> messages) {
        ListMessageToMessagesDtoConverter converter = new ListMessageToMessagesDtoConverter();
        return converter.convert(messages);
    }
}
