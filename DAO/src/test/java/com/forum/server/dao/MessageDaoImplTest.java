package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.models.message.Message;
import com.forum.server.models.user.User;
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

    @Test
    public void save() throws Exception {
         Message message = new Message.Builder()
                 .UserId(USER_ID)
                 .ThemeId(THEME_ID)
                 .Date(System.currentTimeMillis())
                 .Body("Name can't be useless")
                 .Rating(0)
                 .build();
         messagesDao.save(message);
    }

    @Test
    public void getIdByUserIdAndDate(){
        assertEquals(MESSAGE_ID, messagesDao.getIdByUserIdAndDate(USER_ID, DATE));
    }
}
