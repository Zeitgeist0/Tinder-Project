package com.Tinder.service;

import com.Tinder.dao.Message;

import java.util.List;

public interface MessagesService {
    boolean create(Message message);
    Message read(int messageId);
    boolean update(Message message);
    boolean delete(int id);
    List<Message> findUserToUserMessages(int senderId, int receiverId);
}
