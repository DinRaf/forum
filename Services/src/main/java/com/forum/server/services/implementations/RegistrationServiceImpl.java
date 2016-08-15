package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.auth.AuthDto;
import com.forum.server.models.user.User;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.generators.TokenGenerator;
import com.forum.server.services.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private TokensDao tokensDao;

    @Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Override
    public String login(String identifier, String password) {
        if (identifier == null || password == null) {
            throw new AuthException("Identifier or password missing, expected both of them");
        } else if (identifier.contains("@")) {
            if (!usersDao.isExixstsMail(identifier)) {
                throw new AuthException("Incorrect identifier or password");
            }
            String passwordHash = usersDao.getHashByMail(identifier);
            if (encoder.matches(password, passwordHash)) {
                String token = tokenGenerator.generateToken();
                Integer userId = usersDao.findIdByMail(identifier);
                if (userId == null) {
                    throw new AuthException("Incorrect identifier or password");
                }
                tokensDao.addToken(userId, token);
                return token;
            }
        } else {
            if (!usersDao.isExixstsNickName(identifier)) {
                throw new AuthException("Incorrect identifier or password");
            }
            String passwordHash = usersDao.getHashByNickName(identifier);
            if (encoder.matches(password, passwordHash)) {
                String token = tokenGenerator.generateToken();
                Integer userId = usersDao.findIdByNickName(identifier);
                if (userId == null) {
                    throw new AuthException("Incorrect identifier or password");
                }
                tokensDao.addToken(userId, token);
                return token;
            }
        }
        throw new AuthException("Incorrect identifier or password");
    }

    public String addUser(AuthDto authDto) {
        User user = conversionResultFactory.convert(authDto);
        usersDao.save(user);
        String token = tokenGenerator.generateToken();
        tokensDao.addToken(user.getUserId(), token);
        return token;
    }
}
