package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.models.theme.Theme;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class ThemesDaoImplTest {

    @Autowired
    private ThemesDao themesDao;

    @Test
    public void save() throws Exception {
    }

    @Test
    public void getIdByDateAndUserId() throws Exception {
        assertEquals(1l, themesDao.getIdByDateAndUserId(1, 1));
    }

}