package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.models.message.Message;
import com.forum.server.models.user.ShortUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by root on 30.08.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class MessageDaoImplTest {

    @Autowired
    private MessagesDao messagesDao;

    private static final long USER_ID = 1;
    private static final long THEME_ID = 1;
    private static final long DATE = 25082016;
    private static final long MESSAGE_ID = 1;
    private static final long OFFSET = 2;
    private static final long COUNT = 3;
    private static final String TOKEN = "asdfghjk";

    @Test
    public void save() throws Exception {
        ShortUser user = ShortUser.baseBuilder()
                .userId(USER_ID)
                .build();
         Message message = Message.builder()
                 .user(user)
                 .themeId(THEME_ID)
                 .date(System.currentTimeMillis())
                 .body("Name can't be useless")
                 .rating(0)
                 .build();
         messagesDao.save(message);
    }

    @Test
    public void getIdByUserIdAndDate(){
        assertEquals(MESSAGE_ID, messagesDao.getIdByUserIdAndDate(USER_ID, DATE));
    }

    @Test
    public void getMessagesWithOffset() {
        assertNotNull(messagesDao.getMessagesWithOffset(THEME_ID, OFFSET));
    }

    @Test
    public void getMessagesWithLimitOffset() {
        assertNotNull(messagesDao.getMessagesWithLimitOffset(THEME_ID, COUNT, OFFSET));
    }

    @Test
    public void messageIsExists() {
        assertTrue(messagesDao.messageIsExists(MESSAGE_ID));
    }

    @Test
    public void deleteMessageById() {
        messagesDao.deleteMessageById(MESSAGE_ID);
    }

    @Test
    public void getAuthorIdByMessageId() {
        assertEquals(USER_ID, messagesDao.getAuthorIdByMessageId(MESSAGE_ID));
    }

    @Test
    public void isAuthorByMessageIdAndToken() {
        assertTrue(messagesDao.isAuthorByMessageIdAndToken(MESSAGE_ID, TOKEN));
    }

    @Test
    public void getMessagesIdsByThemeId() {
        assertNotNull(messagesDao.getMessagesIdsByThemeId(THEME_ID));
    }
}
