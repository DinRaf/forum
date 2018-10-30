package com.forum.server.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 09.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends ShortUser{
    private String name;
    private String mail;
    private Long dateOfBirth;
    private String info;
    private long registrationTime;
    private long messagesCount;
    private long themesCount;
    private String hashPassword;
}
