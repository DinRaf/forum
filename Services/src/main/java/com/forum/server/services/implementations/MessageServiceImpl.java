package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.validation.MessageMarkValidator;
import com.forum.server.validation.MessageValidator;
import com.forum.server.validation.TokenValidator;
import com.forum.server.validation.UserValidator;
import com.forum.server.dao.interfaces.*;
import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.models.message.Message;
import com.forum.server.models.message.MessageUpdate;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.user.ShortUser;
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
    private MessagesDao messagesDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Autowired
    private ThemesDao themesDao;

    @Autowired
    private MarksDao marksDao;

    @Autowired
    private ConversionListResultFactory conversionListResultFactory;

    @Autowired
    private MessageValidator messageValidator;

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MessageMarkValidator messageMarkValidator;

    public ThemeDto createMessage(String token, long themeId, MessageCreateDto messageCreateDto, long count) {
        String messageText = messageCreateDto.getMessage();
        tokenValidator.verifyOnExistence(token);
        messageValidator.verifyMessageText(messageText);
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

    public ThemeDto updateMessage(String token, long messageId, MessageCreateDto updatedMessageDto, long offset, long count) {
        tokenValidator.verifyOnExistence(token);
        String messageText = updatedMessageDto.getMessage();
        messageValidator.verifyMessageText(messageText);
        long authorId = messagesDao.getAuthorIdByMessageId(messageId);
        ShortUser updater = usersDao.findByToken(token);
        long updaterId = updater.getUserId();
        userValidator.compareUsersById(authorId, updaterId);
        long themeId = themesDao.getThemeIdByMessageId(messageId);
        messagesDao.saveUpdate(new MessageUpdate.Builder()
                .Update(System.currentTimeMillis())
                .UpdaterId(updaterId)
                .UpdaterNickName(updater.getNickName())
                .build(), messageId);
        ThemeDto themeDto = conversionResultFactory.convert(themesDao.getThemeByThemeId(themeId));
        themeDto.setMessages(conversionListResultFactory
                .convertMessages(messagesDao
                        .getMessagesWithLimitOffset(themeId, count, offset)));
        return themeDto;
    }

    public void updateMessageRating(String token, long messageId, boolean grade, long count, long offset) {
        tokenValidator.verifyOnExistence(token);
        long userId = usersDao.findIdByToken(token);
        messageMarkValidator.verifyOnExistence(userId, messageId, grade);
        marksDao.save(userId, messageId, grade);
    }

    public void deleteMessage(String token, long messageId) {
        long authorId = messagesDao.getAuthorIdByMessageId(messageId);
        messageValidator.verifyOnExistence(messageId);
        messagesDao.deleteMessageMarkByMessageId(messageId);
        messagesDao.deleteMessageById(messageId);
    }

}
