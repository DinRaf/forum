package com.forum.server.models.theme;

import com.forum.server.models.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeSearch {
    private long id;
    private String title;
    private String nickname;
    private long authorId;
    private long date;
    private long messagesCount;
    private boolean status;
    private String sectionUrl;
    private List<Tag> tags;
}
