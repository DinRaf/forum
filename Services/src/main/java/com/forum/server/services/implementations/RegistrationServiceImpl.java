package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.auth.AuthDto;
import com.forum.server.models.user.User;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.generators.TokenGenerator;
import com.forum.server.services.interfaces.RegistrationService;
import com.forum.server.services.ustils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private EmailValidator emailValidator;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private TokensDao tokensDao;

    //    @Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Override
    public String login(String identifier, String password) {
        if (identifier == null || password == null) {
            throw new AuthException("Identifier or password missing, expected both of them");
        } else if (identifier.contains("@")) {
            if (!usersDao.isExistsMail(identifier)) {
                throw new AuthException("Incorrect identifier or password");
            }
            String passwordHash = usersDao.getHashByMail(identifier);
            if (encoder.matches(password, passwordHash)) {
                String token = tokenGenerator.generateToken();
                Integer userId = usersDao.getIdByMail(identifier);
                if (userId == null) {
                    throw new AuthException("Incorrect identifier or password");
                }
                tokensDao.addToken(userId, token);
                return token;
            }
        } else {
            if (!usersDao.isExistsNickName(identifier)) {
                throw new AuthException("Incorrect identifier or password");
            }
            String passwordHash = usersDao.getHashByNickName(identifier);
            if (encoder.matches(password, passwordHash)) {
                String token = tokenGenerator.generateToken();
                Integer userId = usersDao.getIdByNickName(identifier);
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
        if (!passwordMeetsRequirements(authDto.getPassword())) {
            throw new AuthException("Password is not correct");
        }
        String identifier = authDto.getMail();
        if (identifier == null || !emailValidator.validate(identifier)) {
            throw new AuthException("E-Mail is not correct or missing");
        } else if (usersDao.isExistsMail(identifier)) {
            throw new AuthException("E-Mail already exists");
        }
        identifier = authDto.getNickName();
        if (!nicknameMeetsRequirements(authDto.getNickName())) {
            throw new AuthException("Nickname is not correct");
        } else if (usersDao.isExistsNickName(identifier)) {
            throw new AuthException("Nickname already exists");
        }
        User user = conversionResultFactory.convert(authDto);
        usersDao.save(user);
        long userId = usersDao.getIdByNickName(user.getNickName());
        String token = tokenGenerator.generateToken();
        tokensDao.addToken(userId, token);
        return token;
    }


    private boolean passwordMeetsRequirements(String password) {
        return password.length() >= 6;
    }

    private boolean nicknameMeetsRequirements(String nickname) {
        if (nickname == null || (nickname.length() < 4) && (nickname.length() > 64)) {
            return false;
        }
        for (int i = 0; i < nickname.length(); i++) {
            if (!((nickname.charAt(i) >= 'a' && nickname.charAt(i) <= 'z') ||
                    (nickname.charAt(i) >= '0' && nickname.charAt(i) <= '9') ||
                    (nickname.charAt(i) >= 'A' && nickname.charAt(i) <= 'Z') ||
                    nickname.charAt(i) == '.' || nickname.charAt(i) == '_' || nickname.charAt(i) == '-'))
            return false;
            }
        return true;
    }
}
