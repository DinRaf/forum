package com.forum.server.validation;

import com.forum.server.dao.interfaces.MarksDao;
import com.forum.server.security.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by root on 03.09.16.
 */
@Component
public class MessageMarkValidator {

    @Autowired
    private MarksDao marksDao;

    public void verifyOnExistence(long userId, long messageId, boolean grade) {
        if (marksDao.isExistsMark(userId, messageId, grade)) {
            throw new AuthException("Оценка уже учтена");
        }
    }
}
