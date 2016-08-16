package com.forum.server.services.interfaces;

import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;


/**
 * Created by aisalin on 15.08.16.
 */
public interface MessageService {

    MessageDto createMessage(String token, int themeId, MessageCreateDto message);

    MessageDto updateMessage(String token, int themeId, int messageId, MessageDto message);

    MessageDto updateMessageRating(int themeId, int messageId, boolean grade);

    void deleteMessage(String token, int themeId, int messageId);
}
