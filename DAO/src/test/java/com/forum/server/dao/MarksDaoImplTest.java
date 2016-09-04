package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.MarksDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * 01.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class MarksDaoImplTest {

    @Autowired
    private MarksDao marksDao;


    @Autowired
    private Map<Number, String> RightsConverter;

    @Test
    public void isExistsMark() throws Exception {
        assertTrue(marksDao.isExistsMark(1, 5, true));
    }

    @Test
    public void save() throws Exception {
        System.out.println(RightsConverter.get(1));
    }

}