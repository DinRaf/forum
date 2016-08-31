package com.forum.server.models.staticInfo;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class Subsection {
    private long subsection_id;
    private String name;
    private long themesCount;

    public long getSubsection_id() {
        return subsection_id;
    }

    public String getName() {
        return name;
    }

    public long getThemesCount() {
        return themesCount;
    }

    protected Subsection() {
    }

    public Subsection(Builder builder) {
        this.subsection_id = builder.subsection_id;
        this.name = builder.name;
        this.themesCount = builder.themesCount;
    }

    public static class Builder {
        private long subsection_id;
        private long section_id;
        private String name;
        private long themesCount;

        public Builder Subsection_id(long subsection_id) {
            this.subsection_id = subsection_id;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder ThemesCount(long themesCount) {
            this.themesCount = themesCount;
            return this;
        }

        public Subsection build() { return new Subsection(this); }
    }
}
