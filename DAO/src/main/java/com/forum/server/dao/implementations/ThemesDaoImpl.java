package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.models.theme.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ThemesDaoImpl implements ThemesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ID_BY_DATE_AND_USER_ID = "";

    public void save(Theme theme) {

    }

    public long getIdByDateAndUserId(long theme, long userId) {
        return 0;
    }
}
