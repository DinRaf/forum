package com.forum.server.dao.interfaces;

import com.forum.server.dto.theme.ThemeSearchDto;
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

    List<ThemeSearchDto> getThemesWithLimitOffset(Integer offset, int count);

    int getCountByKeyword(String keyword);

    List<ThemeSearchDto> getThemesByKeywordWithLimitOffset(String keyword, int offset, int count);

    int getCountBySectionUrl(String sectionUrl);

    List<ThemeSearchDto> getThemesBySectionUrlWithLimitOffset(String sectionUrl, Integer offset, int count);

    int getCountByKeywordAndSectionUrl(String keyword, String sectionUrl);

    List<ThemeSearchDto> getThemesByKeywordSectionUrlWithLimitOffset(String keyword, String sectionUrl, int offset, int count);

    int getCountBySubsectionUrl(String subsectionUrl);

    List<ThemeSearchDto> getThemesBySubsectionUrlWithLimitOffset(String subsectionUrl, Integer offset, int count);

    int getCountByKeywordAndSubsectionUrl(String keyword, String subsectionUrl);

    List<ThemeSearchDto> getThemesByKeywordSubsectionUrlWithLimitOffset(String keyword, String subsectionUrl, int offset, int count);

    List<ThemeSearchDto> getThemesBySectionUrlSubsectionUrlWithLimitOffset(String sectionUrl, String subsectionUrl, Integer offset, int count);

    int getCountByKeywordAndSectionUrlAndSubsectionUrl(String keyword, String sectionUrl, String subsectionUrl);

    List<ThemeSearchDto> getThemesByKeywordSectionUrlSubsectionUrlWithLimitOffset(String keyword, String sectionUrl, String subsectionUrl, int offset, int count);
}
