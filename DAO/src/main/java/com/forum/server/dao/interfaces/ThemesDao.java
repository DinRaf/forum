package com.forum.server.dao.interfaces;

import com.forum.server.dto.theme.ThemeSearchDto;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.theme.ThemeUpdate;

import java.util.List;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface ThemesDao {
    void save(Theme theme);

    long getIdByDateAndUserId(long userId, long date);
    
    long findTheNumberOfMessagesInTheme(long themeId);

    long getAuthorIdByThemeId(long themeId);

    Theme getThemeByThemeId(long themeId);

    long getThemeIdByMessageId(long messageId);

    void saveUpdate(ThemeUpdate themeUpdate, long themeId);

    boolean themeIsExists(long themeId);

    void deleteTheme(long themeId);

    List<ThemeSearchDto> getThemesByKeywordSectionIdSubsectionIdWithLimitOffset(String keyword, int sectionId, int subsectionId, int offset, int count);

    List<ThemeSearchDto> getThemesByKeywordWithLimitOffset(String keyword, int offset, int count);

    List<ThemeSearchDto> getThemesByKeywordSectionIdWithLimitOffset(String keyword, int sectionId, int offset, int count);

    List<ThemeSearchDto> getThemesByKeywordSubsectionIdWithLimitOffset(String keyword, int subsectionId, int offset, int count);
}
