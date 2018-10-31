package com.forum.server.converters.user;

import com.forum.server.dto.user.UserDto;
import com.forum.server.models.user.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Component
public class NotAuthUserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        return UserDto.builder()
                .rating(user.getRating())
                .info(user.getInfo())
                .messagesCount(user.getMessagesCount())
                .nickname(user.getNickname())
                .registrationTime(user.getRegistrationTime())
                .themesCount(user.getThemesCount())
                .rights(user.getRights())
                .messagesCount(user.getMessagesCount())
                .build();

    }
}
