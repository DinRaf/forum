package com.forum.server.dto.user;

import com.forum.server.dto.Data;
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
@Builder(builderMethodName = "baseBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class ShortUserDto implements Data {
    private String nickname;
    private long rating;
    private String avatar;
    private String rights;
}
