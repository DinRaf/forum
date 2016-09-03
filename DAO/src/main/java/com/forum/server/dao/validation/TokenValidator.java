package com.forum.server.dao.validation;

import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.security.exceptions.IncorrectTokenException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by root on 03.09.16.
 */
public class TokenValidator {

    @Autowired
    private TokensDao tokensDao;

    public void verifyOnExistence(String token) {
        if (!tokensDao.isExistsToken(token)) {
            throw new IncorrectTokenException("Не верный токен");
        }
    }
}
