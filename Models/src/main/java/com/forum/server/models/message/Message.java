package com.forum.server.models.message;

import com.forum.server.models.user.ShortUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by root on 29.08.16.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private long messageId;
    private ShortUser user;
    private long themeId;
    private long date;
    private String body;
    private Long update;
    private long rating;
    private Long updaterId;
    private String updaterNickname;
    private Boolean isLiked;
}
