package com.forum.server.dao.interfaces;

/**
 * 01.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface MarksDao {
    boolean isExistsMark(long userId, long messageId, boolean grade);

    void save(long userId, long messageId, boolean grade);
}
