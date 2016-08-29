package com.forum.server.services.implementations;

import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.security.exceptions.IncorrectTokenException;
import com.forum.server.security.exceptions.MessageExeption;
import com.forum.server.services.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 24.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private TokensDao tokensDao;

    public MessageDto createMessage(String token, int themeId, MessageCreateDto message) {
        if (tokensDao.isExistsToken(token)) {
            throw new IncorrectTokenException("Token is incorrect");
        } else {
            if (message == null) {
                throw new MessageExeption("The message body is empty");
            }
        }
        if (message.getMessage().length() > 16000) {
            throw new MessageExeption("Message is too large");
        }
        return new MessageDto();
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
