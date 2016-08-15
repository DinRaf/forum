package com.forum.server.services.interfaces;

import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;
import sun.plugin2.message.Message;

/**
 * Created by aisalin on 15.08.16.
 */
public interface MessagesService {

    MessageDto createMessage(String token, int themeId, MessageCreateDto message);

    MessageDto updateMessage();
}
