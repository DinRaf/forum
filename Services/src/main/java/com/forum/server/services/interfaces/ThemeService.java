package com.forum.server.services.interfaces;

import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.dto.theme.ThemeDto;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface ThemeService {

    ThemeDto createTheme(String token, ThemeCreateDto theme);

    ThemeDto getTheme(long themeId, Integer offset, int count);

    ThemeDto updateTheme(String token, long themeId, String title, long count);

    void deleteTheme(String token, long themeId);
}
