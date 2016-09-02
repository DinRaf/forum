package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.theme.ThemeSearchDto;
import com.forum.server.dto.theme.ThemeSearchResultDto;
import com.forum.server.dto.theme.ThemesSearchDto;
import com.forum.server.dto.user.SearchUsersDto;
import com.forum.server.dto.user.ShortUsersDto;
import com.forum.server.models.user.ShortUser;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.exceptions.IncorrectTokenException;
import com.forum.server.services.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ThemesDao themesDao;

    @Autowired
    private TokensDao tokensDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ConversionListResultFactory conversionListResultFactory;

    public ThemeSearchResultDto searchThemes(String keyword, Integer offset, int count, String sectionUrl, String subsectionUrl) {
        if (keyword == "" || keyword == null) {
            throw new AuthException("Keyword not found");
        } else if (offset == null) {
            offset = 0;
        }
        List<ThemeSearchDto> themeSearchDtos = new LinkedList<>();
        if (sectionUrl == null) {
            themeSearchDtos = themesDao.getThemesByKeywordSubsectionIdWithLimitOffset(keyword, subsectionUrl, offset, count);
        } else if (subsectionUrl == null) {
            themeSearchDtos = themesDao.getThemesByKeywordSectionIdWithLimitOffset(keyword, sectionUrl, offset, count);
        } else if (sectionUrl == null && subsectionUrl == null) {
            themeSearchDtos = themesDao.getThemesByKeywordWithLimitOffset(keyword, offset, count);
        }else {
            themeSearchDtos = themesDao.getThemesByKeywordSectionIdSubsectionIdWithLimitOffset(keyword, sectionUrl, subsectionUrl, offset, count);
        }
        int countOfResults = themeSearchDtos.size();
        ThemesSearchDto themesSearchDto = new ThemesSearchDto(themeSearchDtos);


        return new ThemeSearchResultDto.Builder()
                .Count(countOfResults)
                .ThemesSearhDto(themesSearchDto)
                .build();
    }

    public SearchUsersDto searchUsers(String token, String keyword, Integer offset, int count, String sorting, Boolean isOnline) {
        if (!tokensDao.isExistsToken(token)) {
            throw new IncorrectTokenException("Token is incorrect");
        }
        if (keyword == "" || keyword == null) {
            throw new AuthException("Keyword not found");
        } else if (offset == null) {
            offset = 0;
        }
        List<ShortUser> usersDto;
        if (keyword == null) {
            if (isOnline == null) {
                usersDto = usersDao
                                .getShortUsersSortedLimitOffset(offset, count, sorting);
            } else {
                 usersDto = usersDao
                                .getShortUsersIsOnlineSortedLimitOffset(offset, count, isOnline, sorting);
            }
        } else {
            if (isOnline == null) {
                usersDto = usersDao
                                .getShortUsersByTokenSortedLimitOffset(keyword, offset, count, sorting);
            } else {
                usersDto = usersDao
                                .getShortUsersByTokenIsOnlineSortedLimitOffset(keyword, offset, count, isOnline, sorting);
            }
        }
        int resultCount = usersDto.size();
        ShortUsersDto shortUsersDto = conversionListResultFactory.convertShortUsers(usersDto);

        return new SearchUsersDto.Builder()
                .Count(resultCount)
                .ShortUsersDto(shortUsersDto)
                .build();
    }
}
