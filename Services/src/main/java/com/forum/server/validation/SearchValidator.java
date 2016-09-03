package com.forum.server.validation;

import com.forum.server.security.exceptions.AuthException;
import org.springframework.stereotype.Component;

/**
 * Created by root on 03.09.16.
 */
@Component
public class SearchValidator {
    public void verifyOnNotNull(String keyword) {
        if (keyword == "" || keyword == null) {
            throw new AuthException("Введите запрос");
        }
    }
}
