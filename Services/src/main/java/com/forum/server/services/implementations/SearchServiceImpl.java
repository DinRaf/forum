package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.SearchDao;
import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.dao.interfaces.ThemesDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.theme.ThemeSearchDto;
import com.forum.server.dto.theme.ThemeSearchResultDto;
import com.forum.server.dto.theme.ThemesSearchDto;
import com.forum.server.dto.user.SearchUsersDto;
import com.forum.server.dto.user.ShortUsersDto;
import com.forum.server.services.interfaces.SearchService;
import com.forum.server.validation.RightsValidator;
import com.forum.server.validation.SearchValidator;
import com.forum.server.validation.TokenValidator;
import org.apache.commons.codec.Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.cs.ext.DoubleByte;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private StaticInfoDao staticInfoDao;

    @Autowired
    private ConversionListResultFactory conversionListResultFactory;

    @Autowired
    private SearchValidator searchValidator;

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private RightsValidator rightsValidator;

    @Autowired
    private Map<String, String> sortingMap;

    @Autowired
    private SearchDao searchDao;

    public ThemeSearchResultDto searchThemes(String keyword, Integer offset, int count, String sectionUrl, String subsectionUrl) {

        if (offset == null || offset < 0) {
            offset = 0;
        }
        searchValidator.verifyOnExistenceSectionUrl(sectionUrl);
        searchValidator.verifyOnExistenceSubsectionUrl(subsectionUrl);
        try {
            if (sectionUrl != null) {
                sectionUrl = URLDecoder.decode(sectionUrl, "UTF-8");
            }
            if (subsectionUrl != null) {
                subsectionUrl = URLDecoder.decode(subsectionUrl, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            if (keyword != null) {
                keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        List<ThemeSearchDto> themeSearchDtos = new LinkedList<>();
        Integer resultCount;
        if (sectionUrl == null && subsectionUrl == null) {
            if (keyword == null) {
                resultCount = searchDao.getThemesCount();
                if (resultCount != 0) {
                    themeSearchDtos = searchDao.getThemesWithLimitOffset(offset, count);
                }
            } else {
                resultCount = searchDao.getCountByKeyword(keyword);
                if (resultCount != 0) {
                    themeSearchDtos = searchDao.getThemesByKeywordWithLimitOffset(keyword, offset, count);
                }
            }
        } else if (subsectionUrl == null) {
            if (keyword == null) {
                resultCount = searchDao.getCountBySectionUrl(sectionUrl);
                if (resultCount != 0) {
                    themeSearchDtos = searchDao.getThemesBySectionUrlWithLimitOffset(sectionUrl, offset, count);
                }
            } else {
                resultCount = searchDao.getCountByKeywordAndSectionUrl(keyword, sectionUrl);
                if (resultCount != 0) {
                    themeSearchDtos = searchDao.getThemesByKeywordSectionUrlWithLimitOffset(keyword, sectionUrl, offset, count);
                }
            }
        } else if (sectionUrl == null) {
            if (keyword == null) {
                resultCount = searchDao.getCountBySubsectionUrl(subsectionUrl);
                if (resultCount != 0) {
                    themeSearchDtos = searchDao.getThemesBySubsectionUrlWithLimitOffset(subsectionUrl, offset, count);
                }
            } else {
                resultCount = searchDao.getCountByKeywordAndSubsectionUrl(keyword, subsectionUrl);
                if (resultCount != 0) {
                    themeSearchDtos = searchDao.getThemesByKeywordSubsectionUrlWithLimitOffset(keyword, subsectionUrl, offset, count);
                }
            }
        } else {
            if (keyword == null) {
                resultCount = searchDao.getCountBySubsectionUrl(subsectionUrl);
                if (resultCount != 0) {
                    themeSearchDtos = searchDao.getThemesBySectionUrlSubsectionUrlWithLimitOffset(sectionUrl, subsectionUrl, offset, count);
                }
            } else {
                resultCount = searchDao.getCountByKeywordAndSectionUrlAndSubsectionUrl(keyword, sectionUrl, subsectionUrl);
                if (resultCount != 0) {
                    themeSearchDtos = searchDao.getThemesByKeywordSectionUrlSubsectionUrlWithLimitOffset(keyword, sectionUrl, subsectionUrl, offset, count);
                }
            }
        }
        if (subsectionUrl != null) {
            String subsection = staticInfoDao.getSubsectionByUrl(subsectionUrl);
            return new ThemeSearchResultDto.Builder()
                    .Count(resultCount)
                    .Subsection(subsection)
                    .ThemesSearhDto(new ThemesSearchDto(themeSearchDtos))
                    .build();
        }

        return new ThemeSearchResultDto.Builder()
                .Count(resultCount)
                .ThemesSearhDto(new ThemesSearchDto(themeSearchDtos))
                .build();
    }

    public SearchUsersDto searchUsers(String token, String keyword, Integer offset, int count, String sorting) {
        tokenValidator.verifyOnExistence(token);
        String rights = usersDao.getRightsByToken(token);
        rightsValidator.searchUsers(rights);
        if (offset == null || offset < 0) {
            offset = 0;
        }
        sorting = sortingMap.get(sorting);
        ShortUsersDto shortUsersDto;
        if (keyword == null) {
            return new SearchUsersDto.Builder()
                    .Count(searchDao.getUsersCount())
                    .ShortUsersDto(conversionListResultFactory.convertShortUsers(searchDao.getShortUsersSortedLimitOffset(offset, count, sorting)))
                    .build();

        } else {
            return new SearchUsersDto.Builder()
                    .Count(searchDao.getUsersCountByKeyword(keyword))
                    .ShortUsersDto(conversionListResultFactory.convertShortUsers(searchDao.getShortUsersByKeywordSortedLimitOffset(keyword, offset, count, sorting)))
                    .build();
        }


    }
}
