package com.forum.server.dao.validation;

import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.security.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by root on 03.09.16.
 */
public class StaticInfoValidator {

    @Autowired
    private StaticInfoDao staticInfoDao;

    public void verifySectionOnExistence(String url) {
        if (!staticInfoDao.isExistsSectionUrl(url)) {
            throw new NotFoundException("Несуществующий раздел");
        }
    }

    public void verifyInfoOnExistence(String identifier) {
        if (!staticInfoDao.isExistsInfo(identifier)) {
            throw new NotFoundException("Информация не найдена");
        }
    }
}
