package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.staticInfo.*;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.validation.RightsValidator;
import com.forum.server.dto.staticInfo.InfoCreateDto;
import com.forum.server.dto.staticInfo.SubsectionsWithMetaDto;
import com.forum.server.validation.StaticInfoValidator;
import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.models.staticInfo.Section;
import com.forum.server.services.interfaces.StaticInfoService;
import com.forum.server.validation.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class StaticInfoServiceImpl implements StaticInfoService {

    @Autowired
    private StaticInfoDao staticInfoDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private RightsValidator rightsValidator;

    @Autowired
    private ConversionListResultFactory conversionListResultFactory;

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Autowired
    private StaticInfoValidator staticInfoValidator;

    public SectionsDto getSections() {
        List<Section> sections = staticInfoDao.getSections();
        for (Section s: sections) {
            s.setSubsections(staticInfoDao.getSubsections(s.getUrl()));
        }
        return conversionListResultFactory.convertSections(sections);
    }

    public SubsectionsWithMetaDto getSubsections(String url) {
        staticInfoValidator.verifySectionOnExistence(url);
        return new SubsectionsWithMetaDto.Builder()
                .Subsections(conversionListResultFactory.convertSubsections(staticInfoDao.getSubsections(url)))
                .Section(staticInfoDao.getSectionBySectionUrl(url))
                .build();
    }

    public InfoDto getInfo(String identifier) {
        staticInfoValidator.verifyInfoOnExistence(identifier);
        return conversionResultFactory.convert(staticInfoDao.getInfo(identifier));
    }

    public void createSection(String token, SectionCreateDto createDto) {
        tokenValidator.verifyOnExistence(token);
        if (!staticInfoDao.isExistsSectionUrl(createDto.getUrl())) {
            rightsValidator.createStatic(usersDao.getRightsByToken(token));
            staticInfoDao.createSection(createDto.getName(), createDto.getUrl());
        } else {
            throw new AuthException("Секция с таким урлом уже существоет");
        }
    }

    public void createSubsection(String token, SubsectionCreateDto createDto) {
        tokenValidator.verifyOnExistence(token);
        rightsValidator.createStatic(usersDao.getRightsByToken(token));
        String sectionUrl = createDto.getSectionUrl();
        staticInfoValidator.verifySectionOnExistence(sectionUrl);
        staticInfoDao.createSubsection(createDto.getName(), sectionUrl, createDto.getUrl());

    }

    public void deleteSection(String token, String section_url) {
        tokenValidator.verifyOnExistence(token);
        rightsValidator.workWithStaticInfo(usersDao.getRightsByToken(token));
        staticInfoValidator.verifyInfoOnExistence(section_url);
        staticInfoDao.deleteSectionByUrl(section_url);
    }

    public void deleteSubsection(String token, String url) {
        tokenValidator.verifyOnExistence(token);
        rightsValidator.workWithStaticInfo(usersDao.getRightsByToken(token));
        staticInfoValidator.verifySubsectionOnExistence(url);
        staticInfoDao.deleteSubsectionByUrl(url);
    }

    public void deleteInfo(String token, String identifier) {
        tokenValidator.verifyOnExistence(token);
        rightsValidator.workWithStaticInfo(usersDao.getRightsByToken(token));
        staticInfoValidator.verifyInfoOnExistence(identifier);
        staticInfoDao.deleteInfoByIdentifier(identifier);
    }

    public void createInfo(String token, InfoCreateDto infoCreateDto) {
        tokenValidator.verifyOnExistence(token);
        rightsValidator.workWithStaticInfo(usersDao.getRightsByToken(token));
        staticInfoValidator.verifyInfoOnExistence(infoCreateDto.getIdentifier());
        staticInfoDao.saveInfo(infoCreateDto);
    }

}
