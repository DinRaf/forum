package com.forum.server.dao.interfaces;

/**
 * 09.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface TokensDao {
    //TODO Реализовать методы
    boolean isExistsToken(String token);

    void addToken(Integer userId, String token);
}
