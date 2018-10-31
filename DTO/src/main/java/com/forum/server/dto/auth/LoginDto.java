package com.forum.server.dto.auth;

import com.forum.server.dto.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Data {
    private String token;
    private String nickname;
}
