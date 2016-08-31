package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.models.message.Message;
import com.forum.server.models.message.MessageUpdate;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.user.ShortUser;
import com.forum.server.security.exceptions.*;
import com.forum.server.services.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private UsersDao usersDao;

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Autowired
    private ThemesDao themesDao;

    @Autowired
    private ConversionListResultFactory conversionListResultFactory;

    public ThemeDto createMessage(String token, long themeId, MessageCreateDto messageCreateDto, long count) {
        String messageText = messageCreateDto.getMessage();
        if (!tokensDao.isExistsToken(token)) {
            throw new IncorrectTokenException("Token is incorrect");
        } else {
            if (messageText.equals("")) {
                throw new MessageExeption("The message body is empty");
            }
        }
        if (messageText.length() > 16000) {
            throw new MessageExeption("Message is too large");
        }

        Message message = conversionResultFactory.convert(messageText);
        //TODO Подумать на целесобразностью
        message.setUser(usersDao.findShortUserByToken(token));
        message.setThemeId(themeId);
        messagesDao.save(message);
        long messagesCount = themesDao.findTheNumberOfMessagesInTheme(themeId);
        long offset = messagesCount - findOffsetFromEnd(messagesCount, count);
        if (offset < 0) {
            offset = 0;
        }
        List<Message> messages = messagesDao.getMessagesWithOffset(themeId, offset);
        MessagesDto messagesDto = conversionListResultFactory.convertMessages(messages);
        //TODO Проверить статус пользователя по токену
        Theme theme = themesDao.getThemeByThemeId(themeId);
        ThemeDto themeDto = conversionResultFactory.convert(theme);
        themeDto.setMessages(messagesDto);
        return themeDto;
    }

    private long findOffsetFromEnd(long messagesCount, long count) {
        return messagesCount % count;
    }

    public ThemeDto updateMessage(String token, long themeId, long messageId, MessageCreateDto updatedMessageDto, long offset, long count) {
        if (!tokensDao.isExistsToken(token)) {
            throw new IncorrectTokenException("Token is incorrect");
        }
        String messageText = updatedMessageDto.getMessage();
        if (messageText.equals("")) {
            throw new RequiredFieldsAreNotFilled("Message is empty");
        } else if (messageText.length() > 16000) {
            throw new MessageExeption("Message is too large");
        }
        long authorId = messagesDao.getAuthorIdByMessageId(messageId);
        ShortUser updater = usersDao.findByToken(token);
        long updaterId = updater.getUserId();

        if (authorId != updaterId){
            throw new AuthException("Forbidden");
        }
        messagesDao.saveUpdate(new MessageUpdate.Builder()
                .Update(System.currentTimeMillis())
                .UpdaterId(updaterId)
                .UpdaterNickName(updater.getNickName())
                .build());
        ThemeDto themeDto = conversionResultFactory.convert(themesDao.getThemeByThemeId(themeId));
        themeDto.setMessages(conversionListResultFactory
                .convertMessages(messagesDao
                        .getMessagesWithLimitOffset(themeId, count, offset)));
        return themeDto;
    }

    public void updateMessageRating(long themeId, long messageId, boolean grade, long count, long offset) {

    }

    public void deleteMessage(String token, long themeId, long messageId) {
        if (!messagesDao.messageIsExists(messageId)){
            throw new NotFoundException("The message isn't exists");
        }

        messagesDao.deleteMessageMarkByMessageId(messageId);
        messagesDao.deleteMessageById(messageId);
    }

}
