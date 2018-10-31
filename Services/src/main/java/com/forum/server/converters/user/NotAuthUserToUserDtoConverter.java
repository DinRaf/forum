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
        return new UserDto.Builder()
                .Rating(user.getRating())
                .Avatar(user.getAvatar())
                .Info(user.getInfo())
                .MessagesCount(user.getMessagesCount())
                .Nickname(user.getNickname())
                .RegistrationTime(user.getRegistrationTime())
                .ThemesCount(user.getThemesCount())
                .Rights(user.getRights())
                .MessagesCount(user.getMessagesCount())
                .build();

    }
}
