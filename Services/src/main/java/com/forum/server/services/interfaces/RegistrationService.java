package com.forum.server.services.interfaces;

import com.forum.server.dto.auth.AuthDto;
import com.forum.server.dto.auth.LoginDto;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface RegistrationService {

    LoginDto login(String identifier, String password);

    LoginDto addUser(AuthDto authDto);
}
