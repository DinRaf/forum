package com.forum.server.dto.user;

import com.forum.server.dto.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by root on 22.09.16.
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVerifyResultDto implements Data {
    private String nickname;
    private String rights;
}
