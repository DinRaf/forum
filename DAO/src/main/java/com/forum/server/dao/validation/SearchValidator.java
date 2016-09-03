package com.forum.server.dao.validation;

import com.forum.server.security.exceptions.AuthException;

/**
 * Created by root on 03.09.16.
 */
public class SearchValidator {
    public void verifyOnNotNull(String keyword) {
        if (keyword == "" || keyword == null) {
            throw new AuthException("Введите запрос");
        }
    }
}
