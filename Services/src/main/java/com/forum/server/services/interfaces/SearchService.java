package com.forum.server.services.interfaces;

import com.forum.server.dto.theme.ThemesDto;
import com.forum.server.dto.user.UsersShortDto;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface SearchService {

    ThemesDto searchThemes(String keyword, Integer offset, int count, Integer sectionId, Integer subsectionId);

    UsersShortDto searchUsers(String token, String keyword, Integer offset, int count, String sorting, Boolean isOnline);
}
