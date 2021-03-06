package com.forum.server.services.interfaces;

import com.forum.server.dto.user.ShortUserDto;
import com.forum.server.dto.user.UserUpdateDto;
import com.forum.server.dto.user.UserVerifyResultDto;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface UserService {

    ShortUserDto getUser(String token, String nickname);

    ShortUserDto updateUser(String token, String nickname, UserUpdateDto userInfo);

    UserVerifyResultDto verify(String token);
}
