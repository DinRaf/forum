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
public class NotAuthUserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        return new UserDto.Builder()
                .UserId(user.getUserId())
                .Rating(user.getRating())
                .Online(user.isOnline())
                .Avatar(user.getAvatar())
                .Info(user.getInfo())
                .Rights(user.getRights())
                .LastSession(user.getLastSession())
                .MessagesCount(user.getMessagesCount())
                .NickName(user.getNickName())
                .RegistrationTime(user.getRegistrationTime())
                .ThemesCount(user.getThemesCount())
                .MessagesCount(user.getMessagesCount())
                .build();

    }
}
