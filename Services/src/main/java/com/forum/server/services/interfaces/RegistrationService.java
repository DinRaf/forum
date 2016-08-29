package com.forum.server.services.interfaces;

import com.forum.server.dto.auth.AuthDto;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface RegistrationService {

    String login(String identifier, String password);

    String addUser(AuthDto authDto);
}
