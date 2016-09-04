package com.forum.server.validation;

import com.forum.server.security.exceptions.AuthException;
import org.springframework.stereotype.Component;

/**
 * Created by root on 03.09.16.
 */
@Component
public class RightsValidator {

    public void createMessage(int rights) {
        if (rights < 2) {
            throw new AuthException("Недостаточно прав для создания сообщения");
        }
    }

    public void updateMessage(int rights) {
        if (rights < 3) {
            throw new AuthException("Недостаточно прав для изменения сообщения");
        }
    }

    public void updateMessageRating(int rights) {
        if (rights < 2) {
            throw new AuthException("Недостаточно прав для изменения рэйтинга сообщения");
        }
    }

    public void deleteMessage(int rights) {
        if (rights < 4) {
            throw new AuthException("Недостаточно прав для удаления сообщения");
        }
    }

    public void login(int rights) {
        if (rights < 2) {
            throw new AuthException("Недостаточно прав для входа");
        }
    }

    public void searchUsers(int rights) {
        if (rights < 2) {
            throw new AuthException("Недостаточно прав для поиска пользователей");
        }
    }

    public void createTheme(int rights) {
        if (rights < 2) {
            throw new AuthException("Недостаточно прав для создания темы");
        }
    }

    public void updateTheme(int rights) {
        if (rights < 3) {
            throw new AuthException("Недостаточно прав для изменения темы");
        }
    }

    public void deleteTheme(int rights) {
        if (rights < 3) {
            throw new AuthException("Недостаточно прав для удалеения темы");
        }
    }

    public void getUser(int rights) {
       if (rights < 2) {
            throw new AuthException("Недостаточно прав для получения информации о пользователе");
        }
    }

    public void updateUser(int rights) {
        if (rights < 3) {
            throw new AuthException("Недостаточно прав для изменения информации о пользователе");
        }
    }
}
