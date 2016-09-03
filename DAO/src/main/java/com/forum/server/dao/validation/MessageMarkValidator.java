package com.forum.server.dao.validation;

import com.forum.server.dao.interfaces.MarksDao;
import com.forum.server.security.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by root on 03.09.16.
 */
public class MessageMarkValidator {

    @Autowired
    MarksDao marksDao;

    public void verifyOnExistence(long userId, long messageId, boolean grade) {
        if (marksDao.isExistsMark(userId, messageId, grade)) {
            throw new AuthException("Оценка уже учтена");
        }
    }
}
