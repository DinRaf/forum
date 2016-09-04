package com.forum.server.dao.interfaces;

import com.forum.server.models.user.ShortUser;
import com.forum.server.models.user.User;
import com.forum.server.models.user.UserUpdate;

import java.util.List;

/**
 * 09.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface UsersDao {

    ShortUser findShortUserByToken(String token);

    User findByToken(String token);

    boolean isExistsMail(String identifier);

    String getHashByMail(String identifier);

    Integer getIdByMail(String identifier);

    boolean isExistsNickName(String identifier);

    String getHashByNickName(String identifier);

    Integer getIdByNickName(String identifier);

    ShortUser getUserByThemeId(long themeId);

    void save(User user);

    User getUserById(long userId);

    boolean isExistsId(long userId);

    long findIdByToken(String token);

    void update(UserUpdate convert, long userId);

    List<ShortUser> getShortUsersSortedLimitOffset(Integer offset, int count, String sorting);

    List<ShortUser> getShortUsersIsOnlineSortedLimitOffset(Integer offset, int count, Boolean isOnline, String sorting);

    List<ShortUser> getShortUsersByKeywordSortedLimitOffset(String keyword, Integer offset, int count, String sorting);

    List<ShortUser> getShortUsersByKeywordIsOnlineSortedLimitOffset(String keyword, Integer offset, int count, Boolean isOnline, String sorting);

    int getRightsByToken(String token);

    int getRightsByEmail(String email);

    int getRightsByUserId(long userId);
}
