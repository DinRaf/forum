package com.forum.server.dao.interfaces;

import com.forum.server.models.message.Message;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface MessagesDao {
    void save(Message message);

    long getIdByUserIdAndDate(long userId, long date);
}
