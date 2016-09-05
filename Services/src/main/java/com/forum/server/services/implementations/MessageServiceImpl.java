package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.*;
import com.forum.server.dto.message.MessageCreateDto;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.models.message.Message;
import com.forum.server.models.message.MessageUpdate;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.user.ShortUser;
import com.forum.server.services.interfaces.MessageService;
import com.forum.server.validation.*;
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
    private MessageMarkValidator messageMarkValidator;

    @Autowired
    private RightsValidator rightsValidator;

    public ThemeDto createMessage(String token, long themeId, MessageCreateDto messageCreateDto, long count) {
        //берём текст сообщений
        String messageText = messageCreateDto.getMessage();
        //проверяем токен
        tokenValidator.verifyOnExistence(token);
        //проверяем права пользователя
        String rights = usersDao.getRightsByToken(token);
        rightsValidator.createMessage(rights);
        //проверяем написанный текст
        messageValidator.verifyMessageText(messageText);
        //конвертируем текст в сообщение и собираем модель сообщения
        Message message = conversionResultFactory.convert(messageText);
        //TODO Подумать на целесобразностью
        message.setUser(usersDao.findShortUserByToken(token));
        message.setThemeId(themeId);
        //сохраняем сообщение в бд
        messagesDao.save(message);

        long messagesCount = themesDao.findTheNumberOfMessagesInTheme(themeId);
        long offset = messagesCount - findOffsetFromEnd(messagesCount, count);
        if (offset < 0) {
            offset = 0;
        }
        //берём нужные нам сообщения и собираем dto
        List<Message> messages = messagesDao.getMessagesWithOffset(themeId, offset);
        MessagesDto messagesDto = conversionListResultFactory.convertMessages(messages);
        //собираем dto тем
        Theme theme = themesDao.getThemeByThemeId(themeId);
        ThemeDto themeDto = conversionResultFactory.convert(theme);
        themeDto.setMessages(messagesDto);
        return themeDto;
    }

    private long findOffsetFromEnd(long messagesCount, long count) {
        return messagesCount % count;
    }

    public ThemeDto updateMessage(String token, long messageId, MessageCreateDto updatedMessageDto, long count) {
        tokenValidator.verifyOnExistence(token);
        //Проверка текста сообщения
        String messageText = updatedMessageDto.getMessage();
        messageValidator.verifyMessageText(messageText);
        //Сравниваем является updater и автор одим и тем же человеком
        ShortUser updater = usersDao.findByToken(token);
        long updaterId = updater.getUserId();
        if (updaterId != messagesDao.getAuthorIdByMessageId(messageId)) {
            //Проверка прав доступа
            String rights = usersDao.getRightsByToken(token);
            rightsValidator.updateMessage(rights);
        }
        //Вносим изменения сообщения
        messagesDao.saveUpdate(new MessageUpdate.Builder()
                .Update(System.currentTimeMillis())
                .UpdaterId(updaterId)
                .UpdaterNickName(updater.getNickname())
                .build(), messageId);
        //Собираем ответ
        long offset = messagesDao.getOffsetById(messageId);
        offset = offset - offset % count;
        long themeId = themesDao.getThemeIdByMessageId(messageId);
        ThemeDto themeDto = conversionResultFactory.convert(themesDao.getThemeByThemeId(themeId));
        themeDto.setMessages(conversionListResultFactory
                .convertMessages(messagesDao
                        .getMessagesWithLimitOffset(themeId, count, offset)));
        return themeDto;
    }

    public void updateMessageRating(String token, long messageId, boolean grade) {
        tokenValidator.verifyOnExistence(token);
        String rights = usersDao.getRightsByToken(token);
        rightsValidator.updateMessageRating(rights);
        long userId = usersDao.findIdByToken(token);
        messageMarkValidator.verifyOnExistence(userId, messageId, grade);
        marksDao.save(userId, messageId, grade);
    }

    public void deleteMessage(String token, long messageId) {
        //проверка токена
        tokenValidator.verifyOnExistence(token);
        //является ли человк автором данного сообщения
        if (messagesDao.getAuthorIdByMessageId(messageId) != usersDao.findIdByToken(token)) {
            //проверка прав пользователя на совершение действия
            String rights = usersDao.getRightsByToken(token);
            rightsValidator.deleteMessage(rights);
        }
        //проверяем есть ли вообще такое сообщение
        messageValidator.verifyOnExistence(messageId);
        //удаляем сообщение и внешние ключи
        messagesDao.deleteMessageById(messageId);
    }

}
