package com.forum.server.services.interfaces;

import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.dto.theme.ThemeDto;


/**
 * Created by aisalin on 15.08.16.
 */
public interface MessageService {

    ThemeDto createMessage(String token, long themeId, MessageCreateDto messageCreateDto, long count);

    ThemeDto updateMessage(String token, long themeId, long messageId, MessageCreateDto message, long offset, long count);

    void updateMessageRating(long themeId, long messageId, boolean grade, long count, long offset);

    void deleteMessage(String token, long themeId, long messageId);

}
