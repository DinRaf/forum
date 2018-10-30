package com.forum.server.models.theme;

import com.forum.server.models.user.ShortUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 29.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theme {
    private long themeId;
    private ShortUser user;
    private String sectionUrl;
    private String title;
    private long date;
    private long messagesCount;
    private boolean status;
}
