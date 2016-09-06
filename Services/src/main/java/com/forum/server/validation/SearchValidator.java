package com.forum.server.validation;

import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by root on 03.09.16.
 */
@Component
public class SearchValidator {

    @Autowired
    private StaticInfoDao staticInfoDao;

    public void verifyOnNotNull(String keyword) {
        if (keyword == "" || keyword == null) {
            throw new AuthException("Введите запрос");
        }
    }

    public void verifyOnExistenceSectionUrl(String sectionUrl) {
        if (staticInfoDao.isExistsSectionUrl(sectionUrl)) {
            throw new NotFoundException("Вы ввели неверный адрес секции");
        }
    }

    public void verifyOnExistenceSubsectionUrl(String subsectionUrl) {
        if (staticInfoDao.isExistsSectionUrl(subsectionUrl)) {
            throw new NotFoundException("Вы ввели неверный адрес субсекции");
        }
    }

    public void verifyOnNotNullSectionUrl(String sectionUrl) {
        if (sectionUrl == null) {
            throw new AuthException("Введите адрес секции");
        }
    }

    public void verifyOnNotNullSubsectionUrl(String subsectionUrl) {
        if (subsectionUrl == null) {
            throw new AuthException("Введите адрес субсекции");
        }
    }
}
