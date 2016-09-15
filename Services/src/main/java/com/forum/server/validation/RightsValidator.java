package com.forum.server.validation;

import com.forum.server.security.exceptions.AuthException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by root on 03.09.16.
 */
@Component
public class RightsValidator {
    
    @Resource(name = "map")
    private Map<String, Integer> map;

    public void createMessage(String rights) {
        int right = map.get(rights);
        if (right < 2) {
            throw new AuthException("Недостаточно прав для создания сообщения");
        }
    }

    public void workWithStaticInfo(String rights) {
        int right = map.get(rights);
        if (right < 4) {
            throw new AuthException("Недостаточно прав для работы со статической информацией");
        }
    }

    public void getFeedbacks(String rights) {
        int right = map.get(rights);
        if (right < 4) {
            throw new AuthException("Недостаточно прав для получения информации в книге жалоб и предложений");
        }
    }

    public void banUser(String rights) {
        int right = map.get(rights);
        if (right < 4) {
            throw new AuthException("Вы не можете забанить полльзователя");
        }
    }

    public void updateMessage(String rights) {
        int right = map.get(rights);
        if (right < 3) {
            throw new AuthException("Недостаточно прав для изменения сообщения");
        }
    }

    public void updateMessageRating(String rights) {
        int right = map.get(rights);
        if (right < 2) {
            throw new AuthException("Недостаточно прав для изменения рэйтинга сообщения");
        }
    }

    public void deleteMessage(String rights) {
        int right = map.get(rights);
        if (right < 4) {
            throw new AuthException("Недостаточно прав для удаления сообщения");
        }
    }

    public void login(String rights) {
        int right = map.get(rights);
        if (right < 2) {
            throw new AuthException("Недостаточно прав для входа");
        }
    }

    public void searchUsers(String rights) {
        int right = map.get(rights);
        if (right < 2) {
            throw new AuthException("Недостаточно прав для поиска пользователей");
        }
    }

    public void createTheme(String rights) {
        int right = map.get(rights);
        if (right < 2) {
            throw new AuthException("Недостаточно прав для создания темы");
        }
    }

    public void updateTheme(String rights) {
        int right = map.get(rights);
        if (right < 3) {
            throw new AuthException("Недостаточно прав для изменения темы");
        }
    }

    public void deleteTheme(String rights) {
        int right = map.get(rights);
        if (right < 3) {
            throw new AuthException("Недостаточно прав для удалеения темы");
        }
    }

    public void getUser(String rights) {
        int right = map.get(rights);
       if (right < 2) {
            throw new AuthException("Недостаточно прав для получения информации о пользователе");
        }
    }

    public void updateUser(String rights) {
        int right = map.get(rights);
        if (right < 3) {
            throw new AuthException("Недостаточно прав для изменения информации о пользователе");
        }
    }

    public void createStatic(String rights) {
        int right = map.get(rights);
        if (right != 4) {
            throw new AuthException("Недостаточно прав для добавления");
        }
    }
}
