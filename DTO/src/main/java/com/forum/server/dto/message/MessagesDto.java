package com.forum.server.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.forum.server.dto.Data;

import java.util.List;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class MessagesDto implements Data {
    private List<MessageDto> messages;

    @JsonValue
    public List<MessageDto> getThemes() {
        return messages;
    }

    public MessagesDto(List<MessageDto> users) {
        this.messages = users;
    }

}
