package com.forum.server.converters.user;

import com.forum.server.dto.user.UserUpdateDto;
import com.forum.server.models.user.UserUpdate;
import org.springframework.core.convert.converter.Converter;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class UserUpdateDtoToUserUpdateConverter implements Converter<UserUpdateDto, UserUpdate> {
    @Override
    public UserUpdate convert(UserUpdateDto userUpdateDto) {
        return new UserUpdate.Builder()
                .DateOfBirth(userUpdateDto.getDateOfBirth())
                .Info(userUpdateDto.getInfo())
                .Mail(userUpdateDto.getMail())
                .Name(userUpdateDto.getName())
                .Avatar(userUpdateDto.getAvatar())
                .NickName(userUpdateDto.getNickName())
                .build();
    }
}
