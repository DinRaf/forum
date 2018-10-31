package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.FeedbackDao;
import com.forum.server.dto.message.FeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 09.09.16.
 */
@Repository
public class FeedbackDaoImpl implements FeedbackDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String SQL_SAVE_FEEDBACK = ("INSERT INTO feedback (feedback) VALUES (?);");
    private static final String SQL_GET_FEEDBACK_WITH_LIMIT_OFFSET = ("SELECT feedback FROM feedback ORDER BY id LIMIT :count OFFSET :offset;");

    private RowMapper<FeedbackDto> feedbackDtoRowMapper(){
            return (rs, rowNum) -> FeedbackDto.builder()
                    .feedback(rs.getString("feedback"))
                    .build();
    }

    public void saveFeedback(String feedback) {
        jdbcTemplate.update(SQL_SAVE_FEEDBACK, feedback);
    }

    public List<FeedbackDto> getFeedbacksWithLimitOffset(int count, Integer offset) {
        Map<String, Number> params = new HashMap<>();
        params.put("count", count);
        params.put("offset", offset);
        return namedJdbcTemplate.query(SQL_GET_FEEDBACK_WITH_LIMIT_OFFSET, params, feedbackDtoRowMapper());
    }
}
