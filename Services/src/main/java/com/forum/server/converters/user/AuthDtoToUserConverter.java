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
        return User.builder()
                .nickname(authDto.getNickname())
                .mail(authDto.getMail())
                .hashPassword(encoder.encode(authDto.getPassword()))
                .registrationTime(System.currentTimeMillis())
                .messagesCount(0l)
                .themesCount(0l)
                .rating(0l)
                .rights("unverified")
                .build();
    }

    public static void main(String[] args) {
        System.out.println(encoder.encode("123456"));
    }
}
