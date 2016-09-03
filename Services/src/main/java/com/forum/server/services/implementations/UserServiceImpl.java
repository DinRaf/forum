package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.user.UserDto;
import com.forum.server.dto.user.ShortUserDto;
import com.forum.server.dto.user.UserUpdateDto;
import com.forum.server.models.user.ShortUser;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.exceptions.NotFoundException;
import com.forum.server.services.interfaces.UserService;
import com.forum.server.services.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.forum.server.services.implementations.RegistrationServiceImpl.nicknameMeetsRequirements;
import static com.forum.server.services.implementations.RegistrationServiceImpl.passwordMeetsRequirements;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TokensDao tokensDao;

    @Autowired
    private UsersDao usersDao;
    
    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Autowired
    private EmailValidator emailValidator;

    public ShortUserDto getUser(String token, long userId) {
        if (!usersDao.isExistsId(userId)) {
            throw new NotFoundException("User not found");
        }
        if (token != null && tokensDao.isExistsToken(token)) {
            return conversionResultFactory.convertUser(usersDao.getUserById(userId));
        } else {
            return conversionResultFactory.convertNotAuth(usersDao.getUserById(userId));
        }
    }

    public ShortUserDto updateUser(String token, long userId, UserUpdateDto userInfo) {
        if (!usersDao.isExistsId(userId)) {
            throw new NotFoundException("User not found");
        }
        if (usersDao.findIdByToken(token) != userId) {
            throw new AuthException("Forbidden");
        }
        String identifier = userInfo.getMail();
        if (identifier == null || !emailValidator.validate(identifier)) {
            throw new AuthException("E-Mail is not correct or missing");
        } else if (usersDao.isExistsMail(identifier)) {
            throw new AuthException("E-Mail already exists");
        }
        identifier = userInfo.getNickName();
        if (!nicknameMeetsRequirements(userInfo.getNickName())) {
            throw new AuthException("Nickname is not correct");
        } else if (usersDao.isExistsNickName(identifier)) {
            throw new AuthException("Nickname already exists");
        }
        if (userInfo.getPassword() == null) {
            usersDao.update(conversionResultFactory.convert(userInfo), userId);
            return conversionResultFactory.convertUser(usersDao.getUserById(userId));
        } else if (!passwordMeetsRequirements(userInfo.getPassword())) {
            throw new AuthException("Password is not correct");
        }
        usersDao.update(conversionResultFactory.convertWithPass(userInfo), userId);
        return conversionResultFactory.convertUser(usersDao.getUserById(userId));
    }
}
