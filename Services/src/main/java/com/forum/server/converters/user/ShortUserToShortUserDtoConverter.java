package com.forum.server.converters.user;

import com.forum.server.dto.user.ShortUserDto;
import com.forum.server.models.user.ShortUser;
import org.springframework.core.convert.converter.Converter;

/**
 * 30.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class ShortUserToShortUserDtoConverter implements Converter<ShortUser, ShortUserDto> {

    @Override
    public ShortUserDto convert(ShortUser shortUser) {
        return new ShortUserDto.builder()
                .Avatar(shortUser.getAvatar())
                .Nickname(shortUser.getNickname())
                .Rating(shortUser.getRating())
                .Rights(shortUser.getRights())
                .build();
    }
}
