package com.forum.server.validation;

import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.security.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by root on 03.09.16.
 */
@Component
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

    public void verifySubsectionOnExistence(String url) {
        if (!staticInfoDao.isExistsSubsectionUrl(url)) {
            throw new NotFoundException("Несуществующий подраздел");
        }
    }
}
