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

    SubsectionsWithMetaDto getSubsections(String url);

    InfoDto getInfo(String identifier);

    void createSection(String token, SectionCreateDto createDto);

    void createSubsection(String token, SubsectionCreateDto createDto);

    void deleteSections(String token, String section_url);

    void deleteSubsection(String token, String url);

    void deleteInfo(String token, String identifier);

    void createInfo(String token, InfoCreateDto infoCreateDto);
}
