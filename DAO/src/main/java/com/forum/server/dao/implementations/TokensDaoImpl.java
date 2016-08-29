package com.forum.server.dao.implementations;

import com.forum.server.dao.interfaces.TokensDao;
import org.springframework.stereotype.Repository;

/**
 * 24.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Repository
public class TokensDaoImpl implements TokensDao {
    //TODO Реализовать методы
    public boolean isExistsToken(String token) {
        return false;
    }

    public void addToken(Integer userId, String token) {

    }
}
