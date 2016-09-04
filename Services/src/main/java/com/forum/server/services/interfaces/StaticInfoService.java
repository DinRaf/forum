package com.forum.server.services.interfaces;

import com.forum.server.dto.staticInfo.InfoDto;
import com.forum.server.dto.staticInfo.SectionsDto;
import com.forum.server.dto.staticInfo.SubsectionsWithMetaDto;

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
}
