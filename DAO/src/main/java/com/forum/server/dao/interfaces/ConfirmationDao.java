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

    long getIdByHash(String confirmHash);

    void updatePassHash(long userId, String password);

    void unconfirm(long userId);

    boolean isExistsTicket(String ticket);

    void deleteTicket(String ticket);

    void deleteHashById(long userId);
}
