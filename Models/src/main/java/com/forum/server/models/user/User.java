package com.forum.server.models.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 09.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User extends ShortUser {
    private String name;
    private String mail;
    private Long dateOfBirth;
    private String info;
    private long registrationTime;
    private long messagesCount;
    private long themesCount;
    private String hashPassword;

    @Builder
    public User(String name,
                String mail,
                Long dateOfBirth,
                String info,
                long registrationTime,
                long messagesCount,
                long themesCount,
                String hashPassword,
                long userId,
                String nickname,
                Long rating,
                String rights) {
        super(userId, nickname, rating, rights);
        this.name = name;
        this.mail = mail;
        this.dateOfBirth = dateOfBirth;
        this.info = info;
        this.registrationTime = registrationTime;
        this.messagesCount = messagesCount;
        this.themesCount = themesCount;
        this.hashPassword = hashPassword;
    }
}
