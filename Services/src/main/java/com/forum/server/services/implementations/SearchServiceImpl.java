package com.forum.server.services.implementations;

import com.forum.server.dto.theme.ThemesDto;
import com.forum.server.dto.user.UsersShortDto;
import com.forum.server.services.interfaces.SearchService;
import org.springframework.stereotype.Service;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {
    //TODO Реализовать методы
    public ThemesDto searchThemes(String keyword, Integer offset, int count, Integer sectionId, Integer subsectionId) {
        return null;
    }

    public UsersShortDto searchUsers(String token, String keyword, Integer offset, int count, String sorting, Boolean isOnline) {
        return null;
    }
}
