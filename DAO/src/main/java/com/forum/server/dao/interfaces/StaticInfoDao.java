package com.forum.server.dao.interfaces;

import com.forum.server.dto.staticInfo.InfoCreateDto;
import com.forum.server.models.staticInfo.Info;
import com.forum.server.models.staticInfo.Section;

import java.util.List;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface StaticInfoDao {
    List<Section> getSections();

    boolean isExistsInfo(String identifier);

    Info getInfo(String info);

    boolean isExistsSectionUrl(String url);

    void createSection(String name, String url);

    void deleteSectionByUrl(String section_url);

    void deleteInfoByIdentifier(String identifier);

    void saveInfo(InfoCreateDto infoCreateDto);

    String getSectionNameByUrl(String sectionUrl);
}
