package com.forum.server.controller;

import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.staticInfo.*;
import com.forum.server.models.staticInfo.Info;
import com.forum.server.services.interfaces.StaticInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
import static com.forum.server.utils.ResponseBuilder.buildResponseGetWithSection;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static com.forum.server.utils.ResponseBuilder.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    @RequestMapping(value = "/sections", method = POST)
    public ResponseEntity<QueryResultDto> createSection(@RequestBody SectionCreateDto createDto,
                                                      @RequestHeader(name = "Auth-Token") String token) {
        staticInfoService.createSection(token, createDto);
        return buildResponseGet(null);
    }

    @RequestMapping(value = "/subsections", method = POST)
    public ResponseEntity<QueryResultDto> createSubsection(@RequestBody SubsectionCreateDto createDto,
                                                      @RequestHeader(name = "Auth-Token") String token) {
        staticInfoService.createSubsection(token, createDto);
        return buildResponseGet(null);
    }

    @RequestMapping(value = "/sections", method = DELETE)
    public ResponseEntity<QueryResultDto> deleteSection(@RequestHeader(name = "Auth-Token") String token,
                                                        @RequestParam String section_url) {
        staticInfoService.deleteSections(token, section_url);
        return buildResponseDelete();
    }

    @RequestMapping(value = "/{section-url}/subsections", method = GET)
    public ResponseEntity<QueryResultDto> getSubsections(@PathVariable("section-url") String url) {
        SubsectionsWithMetaDto subsections = staticInfoService.getSubsections(url);
        return buildResponseGetWithSection(subsections.getSubsections(), subsections.getSection());
    }

    @RequestMapping(value = "/subsections", method = DELETE)
    public ResponseEntity<QueryResultDto> deleteSubsection(@RequestParam("url") String url,
                                                           @RequestHeader(name = "Auth-Token") String token) {
        staticInfoService.deleteSubsection(token, url);
        return buildResponseDelete();
    }

    @RequestMapping(value = "/info", method = POST)
    public ResponseEntity<QueryResultDto> createStaticInfo(@RequestHeader(name = "Auth-Token") String token,
                                                           @RequestBody InfoCreateDto infoCreateDto) {
        staticInfoService.createInfo(token, infoCreateDto);
        return buildResponsePost();
    }

    @RequestMapping(value = "/info", method = GET)
    public ResponseEntity<QueryResultDto> getStaticInfo(@RequestParam("identifier") String identifier) {
        InfoDto infoDto = staticInfoService.getInfo(identifier);
        return buildResponseGet(infoDto);
    }

    @RequestMapping(value = "/info", method = DELETE)
    public ResponseEntity<QueryResultDto> deleteStaticInfo(@RequestHeader(name = "Auth-Token") String token,
                                                           @RequestParam("identifier") String identifier) {
        staticInfoService.deleteInfo(token, identifier);
        return buildResponseDelete();
    }
}
