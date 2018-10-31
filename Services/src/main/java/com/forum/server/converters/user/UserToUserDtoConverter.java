package com.forum.server.converters.user;

import com.forum.server.dto.user.UserDto;
import com.forum.server.models.user.User;
import org.springframework.core.convert.converter.Converter;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        return new UserDto.builder()
                .Rating(user.getRating())
                .Avatar(user.getAvatar())
                .DateOfBirth(user.getDateOfBirth())
                .Info(user.getInfo())
                .Mail(user.getMail())
                .MessagesCount(user.getMessagesCount())
                .Name(user.getName())
                .Nickname(user.getNickname())
                .RegistrationTime(user.getRegistrationTime())
                .ThemesCount(user.getThemesCount())
                .MessagesCount(user.getMessagesCount())
                .Rights(user.getRights())
                .build();

    }
}
