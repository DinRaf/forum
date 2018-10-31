package com.forum.server.converters.user;

import com.forum.server.dto.user.UserUpdateDto;
import com.forum.server.models.user.UserUpdate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 31.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class UserUpdateDtoToUserUpdateWithPassConverter implements Converter<UserUpdateDto, UserUpdate> {

    //@Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserUpdate convert(UserUpdateDto userUpdateDto) {
        return UserUpdate.builder()
                .dateOfBirth(userUpdateDto.getDateOfBirth())
                .info(userUpdateDto.getInfo())
                .mail(userUpdateDto.getMail())
                .name(userUpdateDto.getName())
                .hashPassword(encoder.encode(userUpdateDto.getPassword()))
                .avatar(userUpdateDto.getAvatar())
                .build();
    }
}
