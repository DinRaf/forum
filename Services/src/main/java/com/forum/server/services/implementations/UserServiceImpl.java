package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.ConfirmationDao;
import com.forum.server.dto.user.UserVerifyResultDto;
import com.forum.server.services.utils.MessageSender;
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

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private ConfirmationDao confirmationDao;

    @Resource(name = "map")
    private Map<String, Integer> map;

    public ShortUserDto getUser(String token, String nickname) {
        userValidator.verifyOnExistence(nickname);
        if (token != null && tokensDao.isExistsToken(token)) {
            String rightsString = usersDao.getRightsByToken(token);
            int rights = map.get(rightsString);
            if (usersDao.getNicknameByToken(token).toLowerCase().equals(nickname.toLowerCase()) && rights > 0) {
                return conversionResultFactory.convertUser(usersDao.getUserByNickname(nickname));
            } else if (rights > 1) {
                return conversionResultFactory.convertUser(usersDao.getUserByNickname(nickname));
            }
            throw new AuthException("Недостаточно прав для получения информации о пользователе");
        } else {
            return conversionResultFactory.convertNotAuth(usersDao.getUserByNickname(nickname));
        }
    }

    public ShortUserDto updateUser(String token, String nickname, UserUpdateDto userInfo) {
        verify(token);
        if (!usersDao.getNicknameByToken(token).toLowerCase().equals(nickname.toLowerCase())) {
            String rights = usersDao.getRightsByToken(token);
            rightsValidator.updateUser(rights);
        }
        userValidator.verifyOnExistence(nickname);
        String identifier = userInfo.getMail();
        userValidator.verifyEmailPut(identifier);
        long userId = usersDao.getIdByNickName(nickname);
        if (!usersDao.isExistsMail(identifier)) {
            messageSender.sendMessage(userId, identifier, nickname);
            confirmationDao.unconfirm(userId);
        }
        if (userInfo.getPassword() == null) {
            usersDao.update(conversionResultFactory.convert(userInfo), userId);
            return conversionResultFactory.convertUser(usersDao.getUserById(userId));
        } else if (!passwordMeetsRequirements(userInfo.getPassword())) {
            throw new AuthException("Неверный пароль");
        }
        usersDao.updateWithHash(conversionResultFactory.convertWithPass(userInfo), userId);
        return conversionResultFactory.convertUser(usersDao.getUserById(userId));
    }

    public UserVerifyResultDto verify(String token) {
        tokenValidator.verifyOnExistence(token);
        return usersDao.getNicknameAndRightsByToken(token);
    }
}
