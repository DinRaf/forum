package com.forum.server.controller;

import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.tag.TagsDto;
import com.forum.server.dto.theme.ThemeSearchResultDto;
import com.forum.server.dto.user.SearchUsersDto;
import com.forum.server.services.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static com.forum.server.utils.ResponseBuilder.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 06.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/themes", method = GET)
    public ResponseEntity<QueryResultDto> searchThemes(@RequestParam(value = "find", required = false) String keyword,
                                                       @RequestParam(value = "offset", required = false) Integer offset,
                                                       @RequestParam(value = "count") int count,
                                                       @RequestParam(value = "section-url", required = false) String sectionUrl) {
        ThemeSearchResultDto themeSearchResultDto = searchService.searchThemes(keyword, offset, count, sectionUrl);
        return buildResponseGetWithCount(themeSearchResultDto.getThemesSearchDto(), themeSearchResultDto.getCount());
    }

    @RequestMapping(value = "/tags", method = GET)
    public ResponseEntity<QueryResultDto> searchThemes(@RequestParam(value = "find", required = false) String keyword,
                                                       @RequestParam(value = "offset", required = false) Integer offset,
                                                       @RequestParam(value = "count") int count) {
        TagsDto tagsDto = searchService.searchTags(keyword, offset, count);
        return buildResponseGet(tagsDto);
    }

    @RequestMapping(value = "/users", method = GET)
    public ResponseEntity<QueryResultDto> searchUsers(@RequestParam(value = "find", required = false) String keyword,
                                                      @RequestParam(value = "sort") String sorting,
                                                      @RequestParam(value = "offset", required = false) Integer offset,
                                                      @RequestParam(value = "count") int count) {

        SearchUsersDto searchUserDto = searchService.searchUsers(keyword, offset, count, sorting);
        return buildResponseGetWithCount(searchUserDto.getShortUsersDto(), searchUserDto.getCount());
    }
}
