package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.message.FixMessageDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.dto.user.ShortUserDto;
import com.forum.server.models.message.Message;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.user.ShortUser;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.services.interfaces.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private TokensDao tokensDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ThemesDao themesDao;

    @Autowired
    private MessagesDao messagesDao;

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    //TODO Реализовать методы
    public com.forum.server.dto.theme.ThemeDto createTheme(String token, ThemeCreateDto themeCreateDto) {
        if (!tokensDao.isExistsToken(token)) {
            throw new AuthException("Incorrect token");
        } else if (themeCreateDto.getTitle().equals("") || themeCreateDto.getMessage().equals("")) {
            throw new AuthException("Title or message not found");
        }
        ShortUser user = usersDao.findShortUserByToken(token);
        Theme theme = conversionResultFactory.convert(themeCreateDto);
        long userId = user.getUserId();
        theme.setUserId(userId);
        themesDao.save(theme);
        long themeId = themesDao.getIdByDateAndUserId(theme.getDate(), userId);
        Message message = conversionResultFactory.convert(themeCreateDto.getMessage());
        message.setUserId(userId);
        message.setThemeId(themeId);
        messagesDao.save(message);
        message.setMessageId(messagesDao.getIdByUserIdAndDate(userId, message.getDate()));
        ShortUserDto userDto = conversionResultFactory.convert(user);
        return new com.forum.server.dto.theme.ThemeDto.Builder()
                .ThemeId(themeId)
                .Date(theme.getDate())
                .AuthorId(userId)
                .Status(theme.isStatus())
                .MessagesCount(1l)
                .Title(theme.getTitle())
                .Messages(new MessagesDto(new LinkedList<>((Collection<? extends MessageDto>) new MessageDto.Builder()
                        .MessageId(message.getMessageId())
                        .Author(userDto)
                        .Date(message.getDate())
                        .Message(message.getBody())
                        .Rating(message.getRating())
                        .Updated(new FixMessageDto.Builder()
                                .build())
                        .build())))
                .build();
    }

    public com.forum.server.dto.theme.ThemeDto getTheme(long themeId, Integer offset, int count) {
        return null;
    }

    public com.forum.server.dto.theme.ThemeDto updateTheme(String token, long themeId, String title, String offset, String count) {
        return null;
    }

    public void deleteTheme(String token, long themeId) {

    }
}
