package com.forum.server.services.implementations;

import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.services.interfaces.MessageService;
import org.springframework.stereotype.Service;

/**
 * 24.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {
    //TODO Реализовать методы
    public MessageDto createMessage(String token, int themeId, MessageCreateDto message) {
        return null;
    }

    public MessageDto updateMessage(String token, int themeId, int messageId, MessageDto message) {
        return null;
    }

    public MessageDto updateMessageRating(int themeId, int messageId, boolean grade) {
        return null;
    }

    public void deleteMessage(String token, int themeId, int messageId) {

    }
}
