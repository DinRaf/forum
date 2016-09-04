package com.forum.server.converters.user;

import com.forum.server.dto.user.UserDto;
import com.forum.server.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
                .UserId(user.getUserId())
                .Rating(user.getRating())
                .Online(user.isOnline())
                .Avatar(user.getAvatar())
                .Info(user.getInfo())
                .LastSession(user.getLastSession())
                .MessagesCount(user.getMessagesCount())
                .NickName(user.getNickName())
                .RegistrationTime(user.getRegistrationTime())
                .ThemesCount(user.getThemesCount())
                .Rights(user.getRights())
                .MessagesCount(user.getMessagesCount())
                .build();

    }
}
