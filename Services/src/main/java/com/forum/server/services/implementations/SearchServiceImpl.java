package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.validation.RightsValidator;
import com.forum.server.validation.SearchValidator;
import com.forum.server.validation.TokenValidator;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.theme.ThemeSearchDto;
import com.forum.server.dto.theme.ThemeSearchResultDto;
import com.forum.server.dto.theme.ThemesSearchDto;
import com.forum.server.dto.user.SearchUsersDto;
import com.forum.server.dto.user.ShortUsersDto;
import com.forum.server.models.user.ShortUser;
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
    private UsersDao usersDao;

    @Autowired
    private StaticInfoDao staticInfoDao;

    @Autowired
    private ConversionListResultFactory conversionListResultFactory;

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Autowired
    private SearchValidator searchValidator;

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private RightsValidator rightsValidator;

    public ThemeSearchResultDto searchThemes(String keyword, Integer offset, int count, String sectionUrl, String subsectionUrl) {
        if (offset == null) {
            offset = 0;
        }
        List<ThemeSearchDto> themeSearchDtos;
        if (sectionUrl == null && subsectionUrl == null) {
            if (keyword == null) {
                themeSearchDtos = themesDao.getThemesWithLimitOffset(offset, count);
            } else {
                themeSearchDtos = themesDao.getThemesByKeywordWithLimitOffset(keyword, offset, count);
            }
        } else if (subsectionUrl == null) {
            if (keyword == null) {
                themeSearchDtos = themesDao.getThemesBySectionUrlWithLimitOffset(sectionUrl, offset, count);
            } else {
                themeSearchDtos = themesDao.getThemesByKeywordSectionUrlWithLimitOffset(keyword, sectionUrl, offset, count);
            }
        } else if (sectionUrl == null) {
            if (keyword == null) {
                themeSearchDtos = themesDao.getThemesBySubsectionUrlWithLimitOffset(subsectionUrl, offset, count);
            } else {
                themeSearchDtos = themesDao.getThemesByKeywordSubsectionUrlWithLimitOffset(keyword, subsectionUrl, offset, count);
            }
        } else {
            if (keyword == null) {
                themeSearchDtos = themesDao.getThemesBySectionUrlSubsectionUrlWithLimitOffset(sectionUrl, subsectionUrl, offset, count);
            } else {
                themeSearchDtos = themesDao.getThemesByKeywordSectionUrlSubsectionUrlWithLimitOffset(keyword, sectionUrl, subsectionUrl, offset, count);
            }
        }
        int countOfResults = themeSearchDtos.size();
        ThemesSearchDto themesSearchDto = new ThemesSearchDto(themeSearchDtos);
        String subsection = (subsectionUrl == null) ? null : staticInfoDao.getSubsectionByUrl(subsectionUrl);

        return new ThemeSearchResultDto.Builder()
                .Count(countOfResults)
                .ThemesSearhDto(themesSearchDto)
                .Subsection(subsection)
                .build();
    }

    public SearchUsersDto searchUsers(String token, String keyword, Integer offset, int count, String sorting, Boolean isOnline) {
        tokenValidator.verifyOnExistence(token);
        String rights = usersDao.getRightsByToken(token);
        rightsValidator.searchUsers(rights);
        searchValidator.verifyOnNotNull(keyword);
        if (offset == null) {
            offset = 0;
        }
        sorting = conversionResultFactory.getSearchSorting(sorting);
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
                                .getShortUsersByKeywordSortedLimitOffset(keyword, offset, count, sorting);
            } else {
                usersDto = usersDao
                                .getShortUsersByKeywordIsOnlineSortedLimitOffset(keyword, offset, count, isOnline, sorting);
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
