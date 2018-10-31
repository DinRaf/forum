package com.forum.server.dao.interfaces;

import com.forum.server.models.theme.ThemeSearch;
import com.forum.server.models.user.ShortUser;

import java.util.List;

/**
 * 12.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface SearchDao {
    int getUsersCountByKeyword(String keyword);

    List<ShortUser> getShortUsersByKeywordSortedLimitOffset(String keyword, Integer offset, int count, String sorting);

    List<ShortUser> getShortUsersSortedLimitOffset(Integer offset, int count, String sorting);

    int getUsersCount();

    int getThemesCount();

    List<ThemeSearch> getThemesWithLimitOffset(Integer offset, int count);

    int getCountByKeyword(String keyword);

    List<ThemeSearch> getThemesByKeywordWithLimitOffset(String keyword, int offset, int count);

    int getCountBySectionUrl(String sectionUrl);

    List<ThemeSearch> getThemesBySectionUrlWithLimitOffset(String sectionUrl, Integer offset, int count);

    int getCountByKeywordAndSectionUrl(String keyword, String sectionUrl);

    List<ThemeSearch> getThemesByKeywordSectionUrlWithLimitOffset(String keyword, String sectionUrl, int offset, int count);
}
