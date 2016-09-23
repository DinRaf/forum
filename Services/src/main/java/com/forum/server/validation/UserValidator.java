package com.forum.server.validation;

import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.exceptions.NotFoundException;
import com.forum.server.services.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by root on 03.09.16.
 */
@Component
public class UserValidator {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private EmailValidator emailValidator;

    public void compareUsersById(long userId1, long userId2) {
        if (userId1 != userId2){
            throw new AuthException("У вас недостаточно прав для совершения данной опирации");
        }
    }

    public void verifyOnExistence(String nickname) {
        if (!usersDao.isExistsNickName(nickname)) {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    public void verifyEmail(String identifier) {
        if (identifier == null) {
            throw new AuthException("Введите e-mail");
        } else if (!emailValidator.validate(identifier)) {
            throw new AuthException("Некорректный e-mail");
        } else if (usersDao.isExistsMail(identifier)) {
            throw new AuthException("Пользователь с таким e-mail уже существует");
        }
    }

    public void verifyEmailPut(String identifier) {
        if (identifier == null) {
            throw new AuthException("Введите e-mail");
        } else if (!emailValidator.validate(identifier)) {
            throw new AuthException("Некорректный e-mail");
        }
    }
}
