package com.forum.server.dao.interfaces;

/**
 * 04.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface ConfirmationDao {
    void confirmUser(String confirmHash);

    boolean isExistsHash(String confirmHash);

    void saveConfirmHash(long userId, String confirmHash);
}