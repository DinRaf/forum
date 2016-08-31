package com.forum.server.services.interfaces;

import com.forum.server.dto.staticInfo.SectionsDto;
import com.forum.server.dto.staticInfo.SubsectionsDto;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface StaticInfoService {
    SectionsDto getSections();

    SubsectionsDto getSubsections(long sectionId);
}
