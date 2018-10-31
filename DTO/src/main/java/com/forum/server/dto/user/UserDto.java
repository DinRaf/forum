package com.forum.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends ShortUserDto {
    private String name;
    private String mail;
    private Long dateOfBirth;
    private String info;
    private long registrationTime;
    private long messagesCount;
    private long themesCount;

    @Builder
    public UserDto(String nickname, long rating, String avatar, String rights, String name, String mail, Long dateOfBirth, String info, long registrationTime, long messagesCount, long themesCount) {
        super(nickname, rating, avatar, rights);
        this.name = name;
        this.mail = mail;
        this.dateOfBirth = dateOfBirth;
        this.info = info;
        this.registrationTime = registrationTime;
        this.messagesCount = messagesCount;
        this.themesCount = themesCount;
    }
}
