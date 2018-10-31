package com.forum.server.dto.message;

import com.forum.server.dto.Data;
import com.forum.server.dto.user.ShortUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by aisalin on 15.08.16.
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Data {
    private long messageId;
    private ShortUserDto author;
    private String message;
    private long date;
    private long rating;
    private FixMessageDto updated;
    private Boolean isLiked;
}