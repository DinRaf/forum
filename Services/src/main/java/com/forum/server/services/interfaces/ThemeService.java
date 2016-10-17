package com.forum.server.services.interfaces;

import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.dto.theme.ThemeUpdateDto;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface ThemeService {

    ThemeDto createTheme(String token, ThemeCreateDto theme);

    ThemeDto getTheme(String token, long themeId, Integer offset, int count);

    ThemeDto updateTheme(String token, long themeId, ThemeUpdateDto theme, long count);

    void deleteTheme(String token, long themeId);
}
