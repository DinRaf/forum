package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.ConfirmationDao;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.auth.AuthDto;
import com.forum.server.dto.auth.LoginDto;
import com.forum.server.models.user.User;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.exceptions.NotFoundException;
import com.forum.server.security.generators.TokenGenerator;
import com.forum.server.services.interfaces.RegistrationService;
import com.forum.server.services.utils.ConfirmHashGenerator;
import com.forum.server.services.utils.EmailValidator;
import com.forum.server.services.utils.MessageSender;
import com.forum.server.validation.RightsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
    private EmailValidator emailValidator;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private TokensDao tokensDao;

    //    @Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private ConversionResultFactory conversionResultFactory;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private ConfirmationDao confirmationDao;

    @Autowired
    private RightsValidator rightsValidator;

    @Override
    public LoginDto login(String identifier, String password) {
        if (identifier == null || password == null) {
            throw new AuthException("Логин или пароль отсутствует");
        } else if (identifier.contains("@")) {
            if (!usersDao.isExistsMail(identifier)) {
                throw new AuthException("Неверный e-mail адрес");
            }
            String rights = usersDao.getRightsByEmail(identifier);
            rightsValidator.login(rights);
            String passwordHash = usersDao.getHashByMail(identifier);
            if (encoder.matches(password, passwordHash)) {
                String token = tokenGenerator.generateToken();
                Integer userId = usersDao.getIdByMail(identifier);
                if (userId == null) {
                    throw new AuthException("Неверный логин или пароль");
                }
                tokensDao.addToken(userId, token);
                return new LoginDto.Builder()
                        .Token(token)
                        .UserId(userId)
                        .build();
            }
            throw new AuthException("Неверный логин или пароль");
        } else {
            if (!usersDao.isExistsNickName(identifier)) {
                throw new AuthException("Неверный никнейм");
            }
            String passwordHash = usersDao.getHashByNickName(identifier);
            if (encoder.matches(password, passwordHash)) {
                String token = tokenGenerator.generateToken();
                Integer userId = usersDao.getIdByNickName(identifier);
                if (userId == null) {
                    throw new AuthException("Неверный логин или пароль");
                }
                tokensDao.addToken(userId, token);
                return new LoginDto.Builder()
                        .Token(token)
                        .UserId(userId)
                        .build();
            }
            throw new AuthException("Неверный логин или пароль");
        }
    }

    public LoginDto addUser(AuthDto authDto) {
        if (!passwordMeetsRequirements(authDto.getPassword())) {
            throw new AuthException("Пароль слишком короткий");
        }
        String nickName = authDto.getNickname();
        if (!nicknameMeetsRequirements(authDto.getNickname())) {
            throw new AuthException("Никнейм не удовлетворяет необходимым условиям");
        } else if (usersDao.isExistsNickName(nickName)) {
            throw new AuthException("Никнейм уже занят, попробуйте другой");
        }
        String mail = authDto.getMail();
        if (mail == null || !emailValidator.validate(mail)) {
            throw new AuthException("E-mail не удовлетворяет необходимым условиям");
        } else if (usersDao.isExistsMail(mail)) {
            throw new AuthException("E-mail уже занят, попробуйте другой");
        }

        User user = conversionResultFactory.convert(authDto);
        usersDao.save(user);
        long userId = usersDao.getIdByNickName(user.getNickname());
        String token = tokenGenerator.generateToken();
        tokensDao.addToken(userId, token);
        messageSender.sendMessage(userId, mail, nickName);
        return new LoginDto.Builder()
                .Token(token)
                .UserId(userId)
                .build();
    }

    public void confirmUser(String confirmHash) {
        if (!confirmationDao.isExistsHash(confirmHash)) {
            throw new AuthException("Ссылка более не действительна");
        }
        confirmationDao.confirmUser(confirmHash);
    }

    public void logout(String token) {
        if (!tokensDao.isExistsToken(token)) {
            throw new AuthException("Токен не валиден");
        }
        tokensDao.logout(token);
    }

    public void recoveryPass(String mail) {
        if (!usersDao.isExistsMail(mail)) {
            throw new NotFoundException("Пользовтель с данным адресом почты не найден");
        }
        messageSender.sendMessageRecoveryPass(usersDao.getIdByMail(mail), mail, usersDao.getNicknameByMail(mail));
    }

    public void changePass(String confirmHash, String password) {
        if (!confirmationDao.isExistsHash(confirmHash)) {
            throw new AuthException("Ссылка более не действительна");
        }
        confirmationDao.updatePassHash(confirmationDao.getIdByHash(confirmHash), encoder.encode(password));
    }




    public static boolean passwordMeetsRequirements(String password) {
        return password.length() >= 6;
    }

    public static boolean nicknameMeetsRequirements(String nickname) {
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
