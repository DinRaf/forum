package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.FeedbackDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root on 09.09.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class FeedbackDaoImplTest {

    @Autowired
    private FeedbackDao feedbackDao;

    private static final String FEEDBACK = "Test";

    @Test
    public void saveFeedback() {
       feedbackDao.saveFeedback(FEEDBACK);
    }
}
