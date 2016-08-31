package com.forum.server.converters;

import com.forum.server.converters.message.ListMessageToMessagesDtoConverter;
import com.forum.server.converters.staticInfo.ListSectionToSectionsDtoConverter;
import com.forum.server.converters.staticInfo.ListSubsectionToSubsectionsDtoConverter;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.dto.staticInfo.SectionsDto;
import com.forum.server.dto.staticInfo.SubsectionsDto;
import com.forum.server.models.message.Message;
import com.forum.server.models.staticInfo.Section;
import com.forum.server.models.staticInfo.Subsection;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by root on 31.08.16.
 */

@Component
public class ConversionListResultFactory {

    public MessagesDto convertMessages(List<Message> messages) {
        ListMessageToMessagesDtoConverter converter = new ListMessageToMessagesDtoConverter();
        return converter.convert(messages);
    }

    public SectionsDto convertSections(List<Section> sections) {
        ListSectionToSectionsDtoConverter converter = new ListSectionToSectionsDtoConverter();
        return converter.convert(sections);
    }

    public SubsectionsDto convertSubsections(List<Subsection> subsections) {
        ListSubsectionToSubsectionsDtoConverter converter = new ListSubsectionToSubsectionsDtoConverter();
        return converter.convert(subsections);
    }
}
