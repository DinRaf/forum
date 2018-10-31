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
        return new UserUpdate.Builder()
                .DateOfBirth(userUpdateDto.getDateOfBirth())
                .Info(userUpdateDto.getInfo())
                .Mail(userUpdateDto.getMail())
                .Name(userUpdateDto.getName())
                .HashPassword(encoder.encode(userUpdateDto.getPassword()))
                .Avatar(userUpdateDto.getAvatar())
                .build();
    }
}
