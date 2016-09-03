package com.forum.server.dao.validation;

import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by root on 03.09.16.
 */
public class ThemeValidator {

    @Autowired
    ThemesDao themesDao;

    public void verifyOnExistence(long themeId) {
        if (!themesDao.themeIsExists(themeId)) {
            throw new NotFoundException("The theme isn't exists");
        }
    }

    public void compareThemesById(long themeId1, long themeId2) {
        if (themeId1 != themeId2){
            throw new AuthException("Forbidden");
        }
    }

    public void verifyTitleOnNotNull(String title) {
        if (title.equals("") || title == null) {
            throw new AuthException("Введите заголовок темы");
        }
    }

    public void verifyMessageOnNotNull(String message) {
        if (message.equals("") || message == null) {
            throw new AuthException("Введите сообщение");
        }
    }

}
