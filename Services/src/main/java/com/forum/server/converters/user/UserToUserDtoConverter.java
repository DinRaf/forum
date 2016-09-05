package com.forum.server.converters.user;

import com.forum.server.dto.user.UserDto;
import com.forum.server.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        return new UserDto.Builder()
                .UserId(user.getUserId())
                .Rating(user.getRating())
                .Avatar(user.getAvatar())
                .DateOfBirth(user.getDateOfBirth())
                .Info(user.getInfo())
                .LastSession(user.getLastSession())
                .Mail(user.getMail())
                .MessagesCount(user.getMessagesCount())
                .Name(user.getName())
                .NickName(user.getNickName())
                .RegistrationTime(user.getRegistrationTime())
                .ThemesCount(user.getThemesCount())
                .MessagesCount(user.getMessagesCount())
                .Rights(user.getRights())
                .build();

    }
}
