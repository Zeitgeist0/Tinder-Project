package com.Tinder.service;

import com.Tinder.dao.Message;
import com.Tinder.dao.MessagesDao;

import java.util.List;

public class MessagesJdbcService implements MessagesService {
    private MessagesDao messagesDao;

    public MessagesJdbcService(MessagesDao messagesDao) {
        this.messagesDao = messagesDao;
    }

    @Override
    public boolean create(Message message) {
        return messagesDao.create(message);
    }

    @Override
    public Message read(int messageId) {
        return messagesDao.read(messageId);
    }

    @Override
    public boolean update(Message message) {
        return messagesDao.update(message);
    }

    @Override
    public boolean delete(int id) {
        return messagesDao.delete(id);
    }

    @Override
    public List<Message> findUserToUserMessages(int senderId, int receiverId) {
        return messagesDao.findUserToUserMessages(senderId, receiverId);
    }
}
