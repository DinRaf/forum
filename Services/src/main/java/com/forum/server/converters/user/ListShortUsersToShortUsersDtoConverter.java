package com.forum.server.converters.user;

import com.forum.server.dto.user.ShortUsersDto;
import com.forum.server.models.user.ShortUser;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 02.09.16.
 */
public class ListShortUsersToShortUsersDtoConverter implements Converter<List<ShortUser>, ShortUsersDto> {
    @Override
    public ShortUsersDto convert(List<ShortUser> listUsers) {
        ShortUserToShortUserDtoConverter converter = new ShortUserToShortUserDtoConverter();
        return new ShortUsersDto(listUsers
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }
}
