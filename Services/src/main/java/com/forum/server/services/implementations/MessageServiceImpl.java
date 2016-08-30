package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.models.message.Message;
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

    @Autowired
    private MessagesDao messagesDao;

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    public ThemeDto createMessage(String token, long themeId, MessageCreateDto messageCreateDto, long count) {
        if (tokensDao.isExistsToken(token)) {
            throw new IncorrectTokenException("Token is incorrect");
        } else {
            if (messageCreateDto.getMessage().equals("")) {
                throw new MessageExeption("The message body is empty");
            }
        }
        if (messageCreateDto.getMessage().length() > 16000) {
            throw new MessageExeption("Message is too large");
        }

        Message message = conversionResultFactory.convert(messageCreateDto.getMessage());
        messagesDao.save(message);


        return new ThemeDto.Builder().build();
    }

    public ThemeDto updateMessage(String token, long themeId, long messageId, MessageDto message) {
        return null;
    }

    public void updateMessageRating(long themeId, long messageId, boolean grade, long count, long offset) {
    }

    public void deleteMessage(String token, long themeId, long messageId) {

    }
}
