package com.forum.server.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Arthur on 29.2.16.
 */
public class AuthException extends AuthenticationException {
    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthException(String msg) {
        super(msg);
    }
}
