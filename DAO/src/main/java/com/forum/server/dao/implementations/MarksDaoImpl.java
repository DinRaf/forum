package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.MarksDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 01.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class MarksDaoImpl implements MarksDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_IS_EXISTS_MARK = "SELECT CASE WHEN EXISTS(SELECT user_id FROM message_mark WHERE user_id = ? AND message_id = ? AND mark = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_IS_GRADED = "SELECT CASE WHEN EXISTS(SELECT user_id FROM message_mark WHERE user_id = ? AND message_id = ?)THEN TRUE ELSE FALSE END ;";
    private static final String SQL_DELETE_GRADE = "DELETE FROM message_mark WHERE user_id = ? AND message_id = ?;";
    private static final String SQL_ADD_MARK = "INSERT INTO message_mark (user_id, message_id, mark) VALUES (?, ?, ?);";

    public boolean isExistsMark(long userId, long messageId, boolean grade) {
        return jdbcTemplate.queryForObject(SQL_IS_EXISTS_MARK,
            boolean.class,
            new Object[] {
                userId,
                messageId,
                grade
            }
        );
    }

    public void save(long userId, long messageId, boolean grade) {
        if (isGraded(userId, messageId)) {
            jdbcTemplate.update(SQL_DELETE_GRADE,
                new Object[] {
                    userId,
                    messageId
                }
            );
        }
        else {
            jdbcTemplate.update(SQL_ADD_MARK,
                    new Object[] {
                        userId,
                        messageId,
                        grade
                    }
            );
        }
    }

    private boolean isGraded(long userId, long messageId) {
        return jdbcTemplate.queryForObject(SQL_IS_GRADED,
                boolean.class,
                new Object[] {
                    userId,
                    messageId
                }
        );
    }
}
