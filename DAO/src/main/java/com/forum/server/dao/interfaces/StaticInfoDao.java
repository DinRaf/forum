package com.forum.server.dao.interfaces;

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

    List<Subsection> getSubsections(long sectionId);

    boolean isExistsInfo(String identifier);

    Info getInfo(String info);
}
