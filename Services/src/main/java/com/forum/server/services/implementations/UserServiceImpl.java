package com.forum.server.services.implementations;

import com.forum.server.dto.user.UserDto;
import com.forum.server.dto.user.ShortUserDto;
import com.forum.server.services.interfaces.UserService;
import org.springframework.stereotype.Service;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    //TODO Реализовать методы
    public ShortUserDto getUser(String token, long userId) {
        return null;
    }

    public UserDto updateUser(String token, long userId, UserDto userInfo) {
        return null;
    }
}
