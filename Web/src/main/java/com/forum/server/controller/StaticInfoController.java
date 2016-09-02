package com.forum.server.controller;

import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.staticInfo.InfoDto;
import com.forum.server.dto.staticInfo.SectionsDto;
import com.forum.server.dto.staticInfo.SubsectionsDto;
import com.forum.server.services.interfaces.StaticInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 06.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Controller
public class StaticInfoController {

    @Autowired
    private StaticInfoService staticInfoService;

    @RequestMapping(value = "/sections", method = GET)
    public ResponseEntity<QueryResultDto> getSections() {
        SectionsDto sections = staticInfoService.getSections();
        return buildResponseGet(sections);
    }

    @RequestMapping(value = "/{section-url}/subsections", method = GET)
    public ResponseEntity<QueryResultDto> getSubsections(@PathVariable("section-url") String url) {
        SubsectionsDto subsections = staticInfoService.getSubsections(url);
        return buildResponseGet(subsections);
    }

    @RequestMapping(value = "/info", method = GET)
    public ResponseEntity<QueryResultDto> getStaticInfo(@RequestParam("identifier") String identifier) {
        InfoDto infoDto = staticInfoService.getInfo(identifier);
        return buildResponseGet(infoDto);
    }
}
