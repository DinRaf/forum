package com.forum.server.dao.interfaces;

import com.forum.server.models.message.Message;
import com.forum.server.models.message.MessageUpdate;
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

    void saveUpdate(MessageUpdate messageUpdate, long messageId);

    boolean messageIsExists(long messageId);

    void deleteMessageById(long messageId);

    List<Message> getMessagesWithLimitOffset(long themeId, long count, long offset);

    List<Message> getMessagesWithOffset(long themeId, long offset);

    void deleteMessageMarkByMessageId(long messageId);

    long getAuthorIdByMessageId(long messageId);
}
