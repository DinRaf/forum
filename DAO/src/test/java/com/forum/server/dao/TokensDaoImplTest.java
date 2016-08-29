package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.TokensDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by aisalin on 29.08.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class TokensDaoImplTest {

    @Autowired
    private TokensDao tokensDao;

    private static final String TOKEN = "token3";
    private static final int USER_ID = 1;

    @Test
    public void isExistsToken() throws Exception {
        assertTrue(tokensDao.isExistsToken(TOKEN));
    }

    @Test
    public void addToken() throws Exception {
        tokensDao.addToken(USER_ID, TOKEN);
    }

}