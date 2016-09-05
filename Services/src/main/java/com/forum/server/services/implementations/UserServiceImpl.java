package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.validation.RightsValidator;
import com.forum.server.validation.TokenValidator;
import com.forum.server.validation.UserValidator;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.user.ShortUserDto;
import com.forum.server.dto.user.UserUpdateDto;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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
    private UserValidator userValidator;

    @Autowired
    private TokenValidator tokenValidator;

    @Autowired
    private RightsValidator rightsValidator;

    @Resource(name = "map")
    private Map<String, Integer> map;

    public ShortUserDto getUser(String token, long userId) {
        userValidator.verifyOnExistence(userId);
        String rightsString = usersDao.getRightsByUserId(userId);
        int rights = map.get(rightsString);
        if (token != null && tokensDao.isExistsToken(token)) {
            if (usersDao.findIdByToken(token) == userId && rights > 0) {
                return conversionResultFactory.convertUser(usersDao.getUserById(userId));
            } else if (rights > 1) {
                return conversionResultFactory.convertUser(usersDao.getUserById(userId));
            }
            throw new AuthException("Недостаточно прав для получения информации о пользователе");
        } else {
            return conversionResultFactory.convertNotAuth(usersDao.getUserById(userId));
        }
    }

    public ShortUserDto updateUser(String token, long userId, UserUpdateDto userInfo) {
        verify(token);
        if (usersDao.findIdByToken(token) != userId) {
            String rights = usersDao.getRightsByToken(token);
            rightsValidator.updateUser(rights);
        }
        userValidator.verifyOnExistence(userId);
        String identifier = userInfo.getMail();
        userValidator.verifyEmail(identifier);
        identifier = userInfo.getNickname();
        if (!nicknameMeetsRequirements(userInfo.getNickname())) {
            throw new AuthException("Не правильный nickname");
        } else if (usersDao.isExistsNickName(identifier)) {
            throw new AuthException("Такой nickname уже существует");
        }
        if (userInfo.getPassword() == null) {
            usersDao.update(conversionResultFactory.convert(userInfo), userId);
            return conversionResultFactory.convertUser(usersDao.getUserById(userId));
        } else if (!passwordMeetsRequirements(userInfo.getPassword())) {
            throw new AuthException("Неверный пароль");
        }
        usersDao.update(conversionResultFactory.convertWithPass(userInfo), userId);
        return conversionResultFactory.convertUser(usersDao.getUserById(userId));
    }

    public void verify(String token) {
        tokenValidator.verifyOnExistence(token);
    }
}
