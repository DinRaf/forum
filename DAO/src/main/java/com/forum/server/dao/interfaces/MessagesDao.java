package com.forum.server.dao.interfaces;

import com.forum.server.models.message.Message;
import com.forum.server.models.theme.Theme;

import java.util.List;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface MessagesDao {
    void save(Message message);

    long getIdByUserIdAndDate(long userId, long date);

    List<Message> getMessagesWithOffset(long themeId, long offset, long messagesCount);
}
