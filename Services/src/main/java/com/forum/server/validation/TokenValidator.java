package com.forum.server.validation;

import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.security.exceptions.IncorrectTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by root on 03.09.16.
 */
@Component
public class TokenValidator {

    @Autowired
    private TokensDao tokensDao;

    public void verifyOnExistence(String token) {
        if (token == null || !tokensDao.isExistsToken(token)) {
            throw new IncorrectTokenException("Не верный токен");
        }
    }
}
