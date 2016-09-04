package com.forum.server.dto.staticInfo;

import com.forum.server.dto.Data;

import java.util.List;

/**
 * 04.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class SubsectionsWithMetaDto implements Data {
    private SubsectionsDto subsections;
    private String section;

    public SubsectionsDto getSubsections() {
        return subsections;
    }

    public String getSection() {
        return section;
    }

    protected SubsectionsWithMetaDto() {
    }

    private SubsectionsWithMetaDto(Builder builder) {
        this.subsections = builder.subsections;
        this.section = builder.section;
    }

    public static class Builder {
        private SubsectionsDto subsections;
        private String section;

        public Builder Subsections(SubsectionsDto subsections) {
            this.subsections = subsections;
            return this;
        }

        public Builder Section(String section) {
            this.section = section;
            return this;
        }

        public SubsectionsWithMetaDto build() { return new SubsectionsWithMetaDto(this); }
    }
}
