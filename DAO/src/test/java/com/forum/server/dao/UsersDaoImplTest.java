package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.SearchDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.models.user.ShortUser;
import com.forum.server.models.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 29.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class UsersDaoImplTest {

    private static final String MAIL = "rafteri98@gmail.com";
    private static final String TOKEN = "token";
    private static final String NICKNAME = "Din";
    private static final String PASS_HASH = "lol";
    private static final long THEME_ID = 3;
    private static final int ID = 1;
    private static final int OFFSET = 0;
    private static final int COUNT = 2;
    private static final boolean IS_ONLINE = true;
    private static final String SORTING = "messages_count";
    private static final String KEYWORD = "Emil";

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private SearchDao searchDao;

    @Test
    public void findByToken() throws Exception {
        assertNotNull(usersDao.findByToken(TOKEN));
    }

    @Test
    public void getUserById() throws Exception {
        assertNotNull(usersDao.getUserById(ID));
    }

    @Test
    public void findShortUserByToken() throws Exception {
        assertNotNull(usersDao.findShortUserByToken(TOKEN));
    }

    @Test
    public void isExistsMail() throws Exception {
        assertTrue(usersDao.isExistsMail(MAIL));
    }

    @Test
    public void isExistsID() throws Exception {
        assertTrue(usersDao.isExistsId(ID));
    }

    @Test
    public void getHashByMail() throws Exception {
        assertEquals(PASS_HASH, usersDao.getHashByMail(MAIL));
    }

    @Test
    public void getIdByMail() throws Exception {
        assertTrue(ID == usersDao.getIdByMail(MAIL));
    }

    @Test
    public void isExistsNickName() throws Exception {
        assertTrue(usersDao.isExistsNickName(NICKNAME));
    }

    @Test
    public void getHashByNickName() throws Exception {
        assertEquals(PASS_HASH, usersDao.getHashByNickName(NICKNAME));
    }

    @Test
    public void getIdByNickName() throws Exception {
        assertTrue(ID == usersDao.getIdByNickName(NICKNAME));
    }

    @Test
    public void getUserByThemeId() throws Exception {
        assertNotNull(usersDao.getUserByThemeId(THEME_ID));
    }
}