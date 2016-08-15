package com.forum.server.dao.interfaces;

import com.forum.server.models.user.User;

/**
 * 09.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface UsersDao {
    //TODO Реализовать методы
    User findByToken(String token);

    boolean isExixstsMail(String identifier);

    String getHashByMail(String identifier);

    Integer findIdByMail(String identifier);

    boolean isExixstsNickName(String identifier);

    String getHashByNickName(String identifier);

    Integer findIdByNickName(String identifier);

    void save(User user);
}
