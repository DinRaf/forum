package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.dao.interfaces.SearchDao;
import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.dao.interfaces.TagsDao;
import com.forum.server.dto.tag.TagsDto;
import com.forum.server.dto.theme.ThemeSearchResultDto;
import com.forum.server.dto.user.SearchUsersDto;
import com.forum.server.models.theme.ThemeSearch;
import com.forum.server.services.interfaces.SearchService;
import com.forum.server.validation.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ConversionListResultFactory conversionListResultFactory;

    @Autowired
    private SearchValidator searchValidator;

    @Autowired
    private Map<String, String> sortingMap;

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private TagsDao tagsDao;

    @Autowired
    private StaticInfoDao staticInfoDao;

    public ThemeSearchResultDto searchThemes(String keyword, Integer offset, int count, String sectionUrl) {

        if (offset == null || offset < 0) {
            offset = 0;
        }
        searchValidator.verifyOnExistenceSectionUrl(sectionUrl);
        try {
            if (sectionUrl != null) {
                sectionUrl = URLDecoder.decode(sectionUrl, "UTF-8");
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
        List<ThemeSearch> themeSearches = new LinkedList<>();
        Integer resultCount;
        String sectionName = null;
        if (sectionUrl == null) {
            if (keyword == null) {
                resultCount = searchDao.getThemesCount();
                if (resultCount != 0) {
                    themeSearches = searchDao.getThemesWithLimitOffset(offset, count);
                }
            } else {
                resultCount = searchDao.getCountByKeyword(keyword);
                if (resultCount != 0) {
                    themeSearches = searchDao.getThemesByKeywordWithLimitOffset(keyword, offset, count);
                }
            }
        } else {
            sectionName = staticInfoDao.getSectionNameByUrl(sectionUrl);
            if (keyword == null) {
                resultCount = searchDao.getCountBySectionUrl(sectionUrl);
                if (resultCount != 0) {
                    themeSearches = searchDao.getThemesBySectionUrlWithLimitOffset(sectionUrl, offset, count);
                }
            } else {
                resultCount = searchDao.getCountByKeywordAndSectionUrl(keyword, sectionUrl);
                if (resultCount != 0) {
                    themeSearches = searchDao.getThemesByKeywordSectionUrlWithLimitOffset(keyword, sectionUrl, offset, count);
                }
            }
        }
        themeSearches.forEach(themeSearch -> themeSearch.setTags(tagsDao.getTagsByThemeId(themeSearch.getId())));
        return ThemeSearchResultDto.builder()
                .count(resultCount)
                .section(sectionName)
                .themesSearchDto(conversionListResultFactory.convertThemes(themeSearches))
                .build();
    }

    public SearchUsersDto searchUsers(String keyword, Integer offset, int count, String sorting) {
        if (offset == null || offset < 0) {
            offset = 0;
        }
        sorting = sortingMap.get(sorting);
        if (keyword == null) {
            return SearchUsersDto.builder()
                    .count(searchDao.getUsersCount())
                    .shortUsersDto(conversionListResultFactory.convertShortUsers(searchDao.getShortUsersSortedLimitOffset(offset, count, sorting)))
                    .build();

        } else {
            return SearchUsersDto.builder()
                    .count(searchDao.getUsersCountByKeyword(keyword))
                    .shortUsersDto(conversionListResultFactory.convertShortUsers(searchDao.getShortUsersByKeywordSortedLimitOffset(keyword, offset, count, sorting)))
                    .build();
        }


    }

    @Override
    public TagsDto searchTags(String keyword, Integer offset, int count) {
        if (offset == null || offset < 0) {
            offset = 0;
        }
        if (keyword == null || keyword.isEmpty()) {
            return conversionListResultFactory.convertTags(tagsDao.getTagsByCountAndOffset(count, offset));
        } else {
            return conversionListResultFactory.convertTags(tagsDao.getTagsByKeywordAndCountAndOffset(keyword, count, offset));
        }
    }
}
