package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.validation.RightsValidator;
import com.forum.server.validation.ThemeValidator;
import com.forum.server.validation.TokenValidator;
import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.message.FixMessageDto;
import com.forum.server.dto.message.MessageDto;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.dto.user.ShortUserDto;
import com.forum.server.models.message.Message;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.theme.ThemeUpdate;
import com.forum.server.models.user.ShortUser;
import com.forum.server.services.interfaces.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ThemesDao themesDao;

    @Autowired
    private MessagesDao messagesDao;

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Autowired
    private ConversionListResultFactory conversionListResultFactory;

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private ThemeValidator themeValidator;

    @Autowired
    private RightsValidator rightsValidator;

    public ThemeDto createTheme(String token, ThemeCreateDto themeCreateDto) {
        tokenValidator.verifyOnExistence(token);
        String rights = usersDao.getRightsByToken(token);
        rightsValidator.createTheme(rights);
        themeValidator.verifyTitleOnNotNull(themeCreateDto.getTitle());
        themeValidator.verifyMessageOnNotNull(themeCreateDto.getMessage());
        ShortUser user = usersDao.findShortUserByToken(token);
        Theme theme = conversionResultFactory.convert(themeCreateDto);
        long userId = user.getUserId();
        theme.setUser(user);
        themesDao.save(theme);
        long themeId = themesDao.getIdByDateAndUserId(userId, theme.getDate());
        Message message = conversionResultFactory.convert(themeCreateDto.getMessage());
        message.setUser(user);
        message.setThemeId(themeId);
        messagesDao.save(message);
        message.setMessageId(messagesDao.getIdByUserIdAndDate(userId, message.getDate()));
        ShortUserDto userDto = conversionResultFactory.convert(user);
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDtos.add(new MessageDto.Builder()
                .MessageId(message.getMessageId())
                .Author(userDto)
                .Date(message.getDate())
                .Message(message.getBody())
                .Rating(message.getRating())
                .Updated(new FixMessageDto.Builder()
                        .build())
                .build());
        return new ThemeDto.Builder()
                .ThemeId(themeId)
                .Date(theme.getDate())
                .AuthorId(userId)
                .Status(theme.isStatus())
                .MessagesCount(1l)
                .Title(theme.getTitle())
                .Messages(new MessagesDto(messageDtos))
                .build();
    }

    public ThemeDto getTheme(long themeId, Integer offset, int count) {
        themeValidator.verifyOnExistence(themeId);
        if (offset == null) {
            offset = 0;
        }
        ThemeDto themeDto = conversionResultFactory.convert(themesDao.getThemeByThemeId(themeId));
        themeDto.setMessages(conversionListResultFactory
                .convertMessages(messagesDao
                        .getMessagesWithLimitOffset(themeId, count, offset)));
        return themeDto;
    }

    public ThemeDto updateTheme(String token, long themeId, String title, long count) {
        tokenValidator.verifyOnExistence(token);
        String rights = usersDao.getRightsByToken(token);
        rightsValidator.updateTheme(rights);
        themeValidator.verifyTitleOnNotNull(title);
        themeValidator.verifyOnExistence(themeId);
        themeValidator.compareThemesById(themesDao.getAuthorIdByThemeId(themeId), usersDao.findIdByToken(token));

        themesDao.saveUpdate(new ThemeUpdate.Builder()
                .Title(title)
                .build(),
                themeId);
        long offset = 0;
        ThemeDto themeDto = conversionResultFactory.convert(themesDao.getThemeByThemeId(themeId));
        themeDto.setMessages(conversionListResultFactory
                .convertMessages(messagesDao
                        .getMessagesWithLimitOffset(themeId, count, offset)));
        return themeDto;
    }

    public void deleteTheme(String token, long themeId) {
        tokenValidator.verifyOnExistence(token);
        String rights = usersDao.getRightsByToken(token);
        rightsValidator.deleteTheme(rights);
        themeValidator.verifyOnExistence(themeId);
        themeValidator.compareThemesById(themesDao.getAuthorIdByThemeId(themeId), usersDao.findIdByToken(token));
        themesDao.deleteTheme(themeId);
    }
}
