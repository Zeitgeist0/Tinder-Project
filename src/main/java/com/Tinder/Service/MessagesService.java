package com.Tinder.Service;

import com.Tinder.Dao.Message;

import java.util.List;

public interface MessagesService {
    boolean create(Message message);
    Message read(int messageId);
    boolean update(Message message);
    boolean delete(int id);
    List<Message> findUserToUserMessages(int senderId, int receiverId);
}
