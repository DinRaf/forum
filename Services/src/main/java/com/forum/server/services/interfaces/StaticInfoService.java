package com.forum.server.services.interfaces;

import com.forum.server.dto.staticInfo.*;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface StaticInfoService {
    SectionsDto getSections();

    InfoDto getInfo(String identifier);

    void createSection(String token, SectionCreateDto createDto);

    void deleteSection(String token, String section_url);

    void deleteInfo(String token, String identifier);

    void createInfo(String token, InfoCreateDto infoCreateDto);
}
