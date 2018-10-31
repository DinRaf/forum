package com.forum.server.dto.theme;

import com.forum.server.dto.Data;
import com.forum.server.dto.tag.TagsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by root on 02.09.16.
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeSearchDto implements Data {
    private long id;
    private String title;
    private String nickname;
    private long authorId;
    private long date;
    private long messagesCount;
    private boolean status;
    private String sectionUrl;
    private TagsDto tags;
}
