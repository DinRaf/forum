package com.forum.server.dao;

import com.forum.server.dao.configs.PersistenceConfig;
import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.models.staticInfo.Info;
import com.forum.server.models.staticInfo.Section;
import com.forum.server.models.staticInfo.Subsection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class StaticInfoDaoImplTest {

    private static final long SECTION_ID = 1l;
    private static final String IDENTIFIER = "about";

    @Autowired
    private StaticInfoDao staticInfoDao;

    @Test
    public void getSections() throws Exception {
        List<Section> sections = staticInfoDao.getSections();
        assertNotNull(sections);
    }

    @Test
    public void getSubections() throws Exception {
//        List<Subsection> subsections = staticInfoDao.getSubsections(SECTION_ID);
//        assertNotNull(subsections);
    }

    @Test
    public void getInfo() throws Exception {
        Info info = staticInfoDao.getInfo(IDENTIFIER);
        assertNotNull(info);
    }

    @Test
    public void isExistsInfo() throws Exception {
        assertTrue(staticInfoDao.isExistsInfo(IDENTIFIER));
    }

    @Test
    public void isExistsSectionID() throws Exception {
//        assertTrue(staticInfoDao.isExistsSectionUrl(SECTION_ID));
    }

}