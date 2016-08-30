package com.forum.server.services.interfaces;

import com.forum.server.dto.user.UserDto;
import com.forum.server.dto.user.ShortUserDto;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface UserService {

    ShortUserDto getUser(String token, long userId);

    UserDto updateUser(String token, long userId, UserDto userInfo);
}
