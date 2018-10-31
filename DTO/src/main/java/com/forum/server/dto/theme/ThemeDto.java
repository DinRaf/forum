package com.forum.server.dto.theme;

import com.forum.server.dto.Data;
import com.forum.server.dto.message.MessagesDto;
import com.forum.server.dto.tag.TagsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto implements Data{
    private long themeId;
    private String title;
    private long authorId;
    private long date;
    private long messagesCount;
    private MessagesDto messages;
    private boolean status;
    private TagsDto tags;
}
