package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.user.ShortUser;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.services.interfaces.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ConversionResultFactory conversionResultFactory;

    //TODO Реализовать методы
    public ThemeDto createTheme(String token, ThemeCreateDto themeCreateDto) {
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
        long themeId = themesDao.getIdByDateAndUserId(theme, userId);



        //FIXME ДОПИСАТЬ!!!
        throw new AuthException("Title or message not found");
    }

    public ThemeDto getTheme(long themeId, Integer offset, int count) {
        return null;
    }

    public ThemeDto updateTheme(String token, long themeId, String title) {
        return null;
    }

    public void deleteTheme(String token, long themeId) {

    }
}
