package com.forum.server.converters;

import com.forum.server.converters.user.AuthDtoToUserConverter;
import com.forum.server.converters.user.ThemeCreateDtoToThemeConverter;
import com.forum.server.dto.auth.AuthDto;
import com.forum.server.dto.theme.ThemeCreateDto;
import com.forum.server.models.theme.Theme;
import com.forum.server.models.user.User;
import org.springframework.stereotype.Component;

/**
 * 15.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Component
public class ConversionResultFactory {

    public User convert(AuthDto authDto) {
        AuthDtoToUserConverter authDtoToUserConverter = new AuthDtoToUserConverter();
        return authDtoToUserConverter.convert(authDto);
    }

    public Theme convert(ThemeCreateDto themeCreateDto) {
        ThemeCreateDtoToThemeConverter converter = new ThemeCreateDtoToThemeConverter();
        return converter.convert(themeCreateDto);
    }
}
