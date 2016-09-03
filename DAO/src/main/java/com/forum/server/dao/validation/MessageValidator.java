package com.forum.server.dao.validation;

import com.forum.server.dao.interfaces.MessagesDao;
import com.forum.server.security.exceptions.MessageExeption;
import com.forum.server.security.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by root on 03.09.16.
 */
public class MessageValidator {
    @Autowired
    MessagesDao messagesDao;

    public void verifyMessageText(String messageText) {
        if (messageText.equals("") || messageText == null) {
            throw new MessageExeption("Введите сообщение");
        }

        if (messageText.length() > 16000) {
            throw new MessageExeption("Слишком длинное сообщение");
        }
    }

    public void verifyOnExistence (long messageId) {

        if (!messagesDao.messageIsExists(messageId)){
            throw new NotFoundException("Такого сообщения не существует");
        }
    }
}
