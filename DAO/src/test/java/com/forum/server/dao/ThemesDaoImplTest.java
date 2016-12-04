package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.theme.ThemeUpdate;
import com.forum.server.models.user.ShortUser;
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

    private static final long THEME_ID = 1;
    private static final long USER_ID = 11;
    private static final long THEME_DATE= 1472576342674L;
    private static final long MESSAGES_COUNT= 0;
    private static final String TITLE = "Spring JPA";
    private static final boolean STATUS = true;

    @Autowired
    private ThemesDao themesDao;

    @Test
    public void save() throws Exception {
        ShortUser user = new ShortUser.Builder()
                .UserId(USER_ID)
                .build();

        Theme theme = new Theme.Builder()
                .User(user)
                .Title(TITLE)
                .Date(System.currentTimeMillis())
                .MessagesCount(MESSAGES_COUNT)
                .Status(STATUS)
                .build();
        themesDao.saveReturnId(theme);
    }

    @Test
    public void getIdByDateAndUserId() throws Exception {
        assertEquals(THEME_ID, themesDao.getIdByDateAndUserId(USER_ID, THEME_DATE));
    }

    @Test
    public void findTheNumberOfMessagesInTheme(){
        assertEquals(MESSAGES_COUNT, themesDao.findTheNumberOfMessagesInTheme(THEME_ID));
    }

    @Test
    public void getThemeByThemeId(){
        assertNotNull(themesDao.getThemeByThemeId(THEME_ID));
    }@Test


    public void saveUpdate() {
        ThemeUpdate themeUpdate = new ThemeUpdate.Builder()
                .Title(TITLE)
                .build();
        themesDao.saveUpdate(themeUpdate, THEME_ID);
    }

    @Test
    public void themeIsExists() {
        assertTrue(themesDao.themeIsExists(THEME_ID));
    }

    @Test
    public void deleteTheme() {
        themesDao.deleteTheme(THEME_ID);
    }
}