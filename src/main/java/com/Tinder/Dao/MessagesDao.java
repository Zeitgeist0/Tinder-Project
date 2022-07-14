package com.Tinder.Dao;

import java.util.List;

public interface MessagesDao {
    boolean create(Message message);
    Message read(int messageId);
    boolean update(Message message);
    boolean delete(int id);
    List<Message> findUserToUserMessages(int senderId, int receiverId);
}
