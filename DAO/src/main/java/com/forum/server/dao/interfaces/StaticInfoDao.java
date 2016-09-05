package com.forum.server.dao.interfaces;

import com.forum.server.dto.staticInfo.InfoCreateDto;
import com.forum.server.models.staticInfo.Info;
import com.forum.server.models.staticInfo.Section;
import com.forum.server.models.staticInfo.Subsection;

import java.util.List;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface StaticInfoDao {
    List<Section> getSections();

    List<Subsection> getSubsections(String url);

    boolean isExistsInfo(String identifier);

    Info getInfo(String info);

    boolean isExistsSectionUrl(String url);

    String getSubsectionByUrl(String subsectionUrl);

    String getSectionBySectionUrl(String url);

    void createSection(String name, String url);

    void createSubsection(String name, String sectionUrl, String url);

    void deleteSectionByUrl(String section_url);

    void deleteSubsectionByUrl(String url);

    void deleteInfoByIdentifier(String identifier);

    boolean isExistsSubsectionUrl(String url);

    void saveInfo(InfoCreateDto infoCreateDto);
}
