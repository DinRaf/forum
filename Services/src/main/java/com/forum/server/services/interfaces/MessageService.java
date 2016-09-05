package com.forum.server.services.interfaces;

import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.theme.ThemeDto;


/**
 * Created by aisalin on 15.08.16.
 */
public interface MessageService {

    ThemeDto createMessage(String token, long themeId, MessageCreateDto messageCreateDto, long count);

    ThemeDto updateMessage(String token, long messageId, MessageCreateDto message, long count);

    void updateMessageRating(String token, long messageId, boolean grade);

    void deleteMessage(String token, long messageId);

}
