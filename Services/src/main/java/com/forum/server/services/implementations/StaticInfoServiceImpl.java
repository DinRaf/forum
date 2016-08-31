package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionListResultFactory;
import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.StaticInfoDao;
import com.forum.server.dto.staticInfo.InfoDto;
import com.forum.server.dto.staticInfo.SectionsDto;
import com.forum.server.dto.staticInfo.SubsectionsDto;
import com.forum.server.security.exceptions.NotFoundException;
import com.forum.server.services.interfaces.StaticInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ConversionListResultFactory conversionListResultFactory;

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    public SectionsDto getSections() {
        return conversionListResultFactory.convertSections(staticInfoDao.getSections());
    }

    public SubsectionsDto getSubsections(long sectionId) {
        return conversionListResultFactory.convertSubsections(staticInfoDao.getSubsections(sectionId));
    }

    public InfoDto getInfo(String identifier) {
        if (!staticInfoDao.isExistsInfo(identifier)) {
            throw new NotFoundException("Info not found");
        }
        return conversionResultFactory.convert(staticInfoDao.getInfo(identifier));
    }

}
