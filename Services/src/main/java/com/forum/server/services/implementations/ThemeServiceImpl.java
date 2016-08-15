package com.forum.server.services.implementations;

import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.services.interfaces.ThemeService;
import org.springframework.stereotype.Service;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class ThemeServiceImpl implements ThemeService {
    //TODO Реализовать методы
    public ThemeDto createTheme(String token, ThemeCreateDto theme) {
        return null;
    }

    public ThemeDto getTheme(int themeId, Integer offset, int count) {
        return null;
    }

    public ThemeDto updateTheme(String token, int themeId, String title) {
        return null;
    }

    public void deleteTheme(String token, int themeId) {

    }
}
