package com.forum.server.controller;

import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.services.interfaces.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.forum.server.utils.ResponseBuilder.buildResponseDelete;
import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
import static com.forum.server.utils.ResponseBuilder.buildResponsePostAndPut;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 06.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Controller
public class ThemesController {
    @Autowired
    private ThemeService themeService;

    @RequestMapping(value = "/themes", method = POST)
    public ResponseEntity<QueryResultDto> createTheme(@RequestHeader(name = "Auth-Token") String token,
                                                      @RequestBody ThemeCreateDto themeCreateDto) {
        ThemeDto theme = themeService.createTheme(token, themeCreateDto);
        return buildResponsePostAndPut(theme);
    }

    @RequestMapping(value = "/themes/{theme-id}", method = GET)
    public ResponseEntity<QueryResultDto> getTheme(@PathVariable("theme-id") long themeId,
                                                   @RequestParam(value = "offset", required = false) Integer offset,
                                                   @RequestParam(value = "count") int count) {
        ThemeDto theme = themeService.getTheme(themeId, offset, count);
        return buildResponseGet(theme);
    }

    @RequestMapping(value = "/themes/{theme-id}", method = PUT)
    public ResponseEntity<QueryResultDto> updateTheme(@PathVariable("theme-id") long themeId,
                                                      @RequestParam(value = "title") String title,
                                                      @RequestParam(value = "offset") String offset,
                                                      @RequestParam(value = "count") String count,
                                                      @RequestHeader(name = "Auth-Token") String token) {
        ThemeDto theme = themeService.updateTheme(token, themeId, title, offset, count);
        return buildResponseGet(theme);
    }

    @RequestMapping(value = "/themes/{theme-id}", method = DELETE)
    public ResponseEntity<QueryResultDto> deleteTheme(@PathVariable("theme-id") long themeId,
                                                      @RequestHeader(name = "Auth-Token") String token) {
        themeService.deleteTheme(token, themeId);
        return buildResponseDelete();
    }
}
