package com.forum.server.converters.staticInfo;

import com.forum.server.dto.staticInfo.InfoDto;
import com.forum.server.models.staticInfo.Info;
import org.springframework.core.convert.converter.Converter;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class InfoToInfoDtoConverter implements Converter<Info, InfoDto>{
    public InfoDto convert(Info info) {
        return new InfoDto.builder()
                .Title(info.getTitle())
                .Text(info.getText())
                .build();
    }
}
