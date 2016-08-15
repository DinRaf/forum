package com.forum.server.converters.user;

import com.forum.server.dto.auth.AuthDto;
import com.forum.server.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 15.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Component
public class AuthDtoToUserConverter implements Converter<AuthDto, User> {

    @Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User convert(AuthDto authDto) {
        String passHash = encoder.encode(authDto.getPassword());
        return new User.Builder().NickName(authDto.getNickName())
                .Mail(authDto.getMail())
                .HashPassword(passHash)
                .RegistrationTime(System.currentTimeMillis())
                .MessagesCount(0)
                .ThemesCount(0)
                .IsOnline(true)
                .build();
    }
}
