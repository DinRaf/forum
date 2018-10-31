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
        return UserUpdate.builder()
                .dateOfBirth(userUpdateDto.getDateOfBirth())
                .info(userUpdateDto.getInfo())
                .mail(userUpdateDto.getMail())
                .name(userUpdateDto.getName())
                .avatar(userUpdateDto.getAvatar())
                .build();
    }
}
