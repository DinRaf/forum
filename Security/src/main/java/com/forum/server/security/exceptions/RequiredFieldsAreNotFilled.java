package com.forum.server.security.exceptions;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class RequiredFieldsAreNotFilled extends RuntimeException {
    public RequiredFieldsAreNotFilled(String message) {
        super(message);
    }
}
