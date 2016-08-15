package com.forum.server.controller;

import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.dto.theme.ThemeDto;
import com.forum.server.services.interfaces.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<QueryResultDto> createTheme(@RequestParam(name = "Auth-Token") String token,
                                                      @RequestBody ThemeCreateDto themeCreateDto) {
        ThemeDto theme = themeService.createTheme(token, themeCreateDto);
        return buildResponsePostAndPut(theme);
    }

    @RequestMapping(value = "/themes/{theme-id}", method = GET)
    public ResponseEntity<QueryResultDto> getTheme(@PathVariable("theme-id") int themeId,
                                                   @RequestParam(value = "offset", required = false) Integer offset,
                                                   @RequestParam(value = "count") int count) {
        ThemeDto theme = themeService.getTheme(themeId, offset, count);
        return buildResponseGet(theme);
    }

    @RequestMapping(value = "/themes/{theme-id}", method = PUT)
    public ResponseEntity<QueryResultDto> updateTheme(@PathVariable("theme-id") int themeId,
                                                      @RequestParam(value = "title") String title,
                                                      @RequestParam(name = "Auth-Token") String token) {
        ThemeDto theme = themeService.updateTheme(token, themeId, title);
        return buildResponseGet(theme);
    }

    @RequestMapping(value = "/themes/{theme-id}", method = DELETE)
    public ResponseEntity<QueryResultDto> deleteTheme(@PathVariable("theme-id") int themeId,
                                                      @RequestParam(name = "Auth-Token") String token) {
        themeService.deleteTheme(token, themeId);
        return buildResponseDelete();
    }
}
