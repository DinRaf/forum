package com.forum.server.controller;

import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.theme.ThemesDto;
import com.forum.server.dto.user.UsersShortDto;
import com.forum.server.services.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
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
    public ResponseEntity<QueryResultDto> searchThemes(@RequestParam(value = "find") String keyword,
                                                       @RequestParam(value = "offset", required = false) Integer offset,
                                                       @RequestParam(value = "count") int count,
                                                       @RequestParam(value = "section-id", required = false) Integer sectionId,
                                                       @RequestParam(value = "subsection-id", required = false) Integer subsectionId) {
        ThemesDto result = searchService.searchThemes(keyword, offset, count, sectionId, subsectionId);
        return buildResponseGet(result);
    }

    @RequestMapping(value = "/users", method = GET)
    public ResponseEntity<QueryResultDto> searchUsers(@RequestParam(value = "find", required = false) String keyword,
                                                      @RequestParam(value = "sort") String sorting,
                                                      @RequestParam(value = "isOnline", required = false) Boolean isOnline,
                                                      @RequestParam(value = "offset", required = false) Integer offset,
                                                      @RequestParam(value = "count") int count,
                                                      @RequestHeader(name = "Auth-Token") String token) {
        UsersShortDto result = searchService.searchUsers(token, keyword, offset, count, sorting, isOnline);
        return buildResponseGet(result);
    }
}
