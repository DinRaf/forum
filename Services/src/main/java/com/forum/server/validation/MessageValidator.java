package com.forum.server.validation;

import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.security.exceptions.MessageException;
import com.forum.server.security.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by root on 03.09.16.
 */
@Component
public class MessageValidator {
    @Autowired
    private MessagesDao messagesDao;

    public void verifyMessageText(String messageText) {
        if (messageText.equals("") || messageText == null) {
            throw new MessageException("Введите сообщение");
        }

        if (messageText.length() > 16000) {
            throw new MessageException("Слишком длинное сообщение");
        }
    }

    public void verifyOnExistence (long messageId) {

        if (!messagesDao.messageIsExists(messageId)){
            throw new NotFoundException("Такого сообщения не существует");
        }
    }
}
