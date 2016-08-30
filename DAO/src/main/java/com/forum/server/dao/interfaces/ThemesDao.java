package com.forum.server.dao.interfaces;

import com.forum.server.models.theme.Theme;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface ThemesDao {
    void save(Theme theme);

    long getIdByDateAndUserId(long theme, long userId);
}
