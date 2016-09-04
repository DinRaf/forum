package com.forum.server.services.implementations;

import com.forum.server.converters.ConversionResultFactory;
import com.forum.server.dao.interfaces.ConfirmationDao;
import com.forum.server.dao.interfaces.TokensDao;
import com.forum.server.dao.interfaces.UsersDao;
import com.forum.server.dto.auth.AuthDto;
import com.forum.server.dto.auth.LoginDto;
import com.forum.server.models.user.User;
import com.forum.server.security.exceptions.AuthException;
import com.forum.server.security.generators.TokenGenerator;
import com.forum.server.services.interfaces.RegistrationService;
import com.forum.server.services.utils.ConfirmHashGenerator;
import com.forum.server.services.utils.EmailValidator;
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
    private SimpleMailMessage mailMessage;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private ConfirmHashGenerator confirmHashGenerator;

    @Autowired
    private ConfirmationDao confirmationDao;

    @Autowired
    private RightsValidator rightsValidator;

    @Override
    public LoginDto login(String identifier, String password) {
        if (identifier == null || password == null) {
            throw new AuthException("Identifier or password missing, expected both of them");
        } else if (identifier.contains("@")) {
            if (!usersDao.isExistsMail(identifier)) {
                throw new AuthException("Incorrect identifier or password");
            }
            int rights = usersDao.getRightsByEmail(identifier);
            rightsValidator.login(rights);
            String passwordHash = usersDao.getHashByMail(identifier);
            if (encoder.matches(password, passwordHash)) {
                String token = tokenGenerator.generateToken();
                Integer userId = usersDao.getIdByMail(identifier);
                if (userId == null) {
                    throw new AuthException("Incorrect identifier or password");
                }
                tokensDao.addToken(userId, token);
                return new LoginDto.Builder()
                        .Token(token)
                        .UserId(userId)
                        .build();
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
                return new LoginDto.Builder()
                        .Token(token)
                        .UserId(userId)
                        .build();
            }
        }
        throw new AuthException("Incorrect identifier or password");
    }

    public LoginDto addUser(AuthDto authDto) {
        if (!passwordMeetsRequirements(authDto.getPassword())) {
            throw new AuthException("Password is not correct");
        }
        String nickName = authDto.getNickName();
        if (!nicknameMeetsRequirements(authDto.getNickName())) {
            throw new AuthException("Nickname is not correct");
        } else if (usersDao.isExistsNickName(nickName)) {
            throw new AuthException("Nickname already exists");
        }
        String mail = authDto.getMail();
        if (mail == null || !emailValidator.validate(mail)) {
            throw new AuthException("E-Mail is not correct or missing");
        } else if (usersDao.isExistsMail(mail)) {
            throw new AuthException("E-Mail already exists");
        }



        User user = conversionResultFactory.convert(authDto);
        usersDao.save(user);
        long userId = usersDao.getIdByNickName(user.getNickName());
        String token = tokenGenerator.generateToken();
        tokensDao.addToken(userId, token);
        sendMessage(userId, mail, nickName);
        return new LoginDto.Builder()
                .Token(token)
                .UserId(userId)
                .build();
    }

    public void confirmUser(String confirmHash) {
        if (!confirmationDao.isExistsHash(confirmHash)) {
            throw new AuthException("Ссылка более не действительна");
        } else {
            confirmationDao.confirmUser(confirmHash);
        }
    }


    private void sendMessage(long userId, String mail, String nickname) {
        SimpleMailMessage message = new SimpleMailMessage(mailMessage);
        message.setTo(mail);
        String confirmHash = confirmHashGenerator.generateHash()
                + confirmHashGenerator.generateHash();
        confirmationDao.saveConfirmHash(userId, confirmHash);
        message.setText(
                "Здравствуйте, " + nickname + "!\n" +
                "Для подтверждения аккаунта перейдите пожалуйста по ссылке ниже:\n" +
                "192.168.0.105:8080/confirmation/" + confirmHash
        );
        try {
            mailSender.send(message);
        } catch (Exception ex) {
            throw new AuthException("Регистрация не удалась");
        }

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
