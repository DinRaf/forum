package com.forum.server.dto.staticInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.forum.server.dto.Data;

import java.util.List;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class SubsectionsDto implements Data {

    private List<SubsectionDto> subsections;

    @JsonValue
    public List<SubsectionDto> getSubsections() {
        return subsections;
    }

    public SubsectionsDto(List<SubsectionDto> subsections) {
        this.subsections = subsections;
    }
}
