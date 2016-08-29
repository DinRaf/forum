package com.forum.server.dao.interfaces;

import com.forum.server.models.user.User;

/**
 * 09.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface UsersDao {

    User findByToken(String token);

    boolean isExistsMail(String identifier);

    String getHashByMail(String identifier);

    Integer getIdByMail(String identifier);

    boolean isExistsNickName(String identifier);

    String getHashByNickName(String identifier);

    Integer getIdByNickName(String identifier);

    void save(User user);
}
