package com.forum.server.dto.message;

import com.forum.server.dto.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FixMessageDto implements Data {
    private long date;
    private String username;
    private long userId;
}
