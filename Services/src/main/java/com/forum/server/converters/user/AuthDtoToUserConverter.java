package com.forum.server.converters.user;

import com.forum.server.dto.auth.AuthDto;
import com.forum.server.models.user.User;
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

//    @Autowired
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User convert(AuthDto authDto) {
        return new User.Builder().Nickname(authDto.getNickname())
                .Mail(authDto.getMail())
                .HashPassword(encoder.encode(authDto.getPassword()))
                .RegistrationTime(System.currentTimeMillis())
                .MessagesCount(0l)
                .ThemesCount(0l)
                .Rating(0l)
                .Rights("unverified")
                .build();
    }

    public static void main(String[] args) {
        System.out.println(encoder.encode("123456"));
    }
}
