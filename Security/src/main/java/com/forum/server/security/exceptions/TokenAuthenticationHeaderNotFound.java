package com.forum.server.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Arthur on 18.2.16.
 */
public class TokenAuthenticationHeaderNotFound extends AuthenticationException {

    public TokenAuthenticationHeaderNotFound(String msg, Throwable t) {
        super(msg, t);
    }

    public TokenAuthenticationHeaderNotFound(String msg) {
        super(msg);
    }
}
