package com.forum.server.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Arthur on 19.2.16.
 */
public class IncorrectTokenException extends AuthenticationException {
    public IncorrectTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public IncorrectTokenException(String msg) {
        super(msg);
    }
}
