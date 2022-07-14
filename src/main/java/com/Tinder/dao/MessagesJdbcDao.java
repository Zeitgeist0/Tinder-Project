package com.Tinder.dao;

import org.postgresql.ds.PGPoolingDataSource;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessagesJdbcDao implements MessagesDao {

    private PGPoolingDataSource source;

    public MessagesJdbcDao() {
        source = new PGPoolingDataSource();
        source.setServerName("ec2-54-75-184-144.eu-west-1.compute.amazonaws.com");
        source.setDatabaseName("d22gk09uc1becd");
        source.setUser("sogwsxurybiycc");
        source.setPassword("4c318dde4c760f7dd53fcf14cedac9ecc2d709c5849ffceeb576a1208bb02750");
        source.setMaxConnections(10);
    }

    @Override
    public boolean create(Message message) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO public.messages(sender_id, receiver_id, message) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, (int) message.getSenderId());
            preparedStatement.setInt(2, (int) message.getReceiverId());
            preparedStatement.setString(3, message.getMessage());

            int executionResult = preparedStatement.executeUpdate();
            connection.commit();

            return executionResult > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Message read(int messageId) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.messages WHERE message_id = ?");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setLong(1, messageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("message_id");
                int senderId = resultSet.getInt("sender_id");
                int receiverId = resultSet.getInt("receiver_id");
                String message = resultSet.getString("message");
                boolean isReaded = resultSet.getBoolean("is_readed");
                boolean isEdited = resultSet.getBoolean("is_edited");

                OffsetDateTime timestampObj = resultSet.getObject("timestamp", OffsetDateTime.class);
                long timestamp = timestampObj.toInstant().toEpochMilli();

                return new Message(id, senderId, receiverId, message, timestamp, isReaded, isEdited);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(Message message) {
        Connection connection = null;
        try {
            connection = source.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE messages SET message = ?, is_edited = TRUE WHERE message_id = ?"
            );
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setInt(2, (int) message.getMessageId());

            int executionResult = preparedStatement.executeUpdate();

            return executionResult > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        try {
            connection = source.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM messages WHERE message_id = ?"
            );
            preparedStatement.setInt(1, id);

            int executionResult = preparedStatement.executeUpdate();

            return executionResult > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public List<Message> findUserToUserMessages(int senderIdReq, int receiverIdReq) {
        Connection connection = null;
        List<Message> messages = new ArrayList<>();
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM messages WHERE sender_id = ? AND receiver_id = ? OR sender_id = ? AND receiver_id = ?"
            );
            preparedStatement.setInt(1, senderIdReq);
            preparedStatement.setInt(2, receiverIdReq);
            preparedStatement.setInt(3, receiverIdReq);
            preparedStatement.setInt(4, senderIdReq);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("message_id");
                int senderId = resultSet.getInt("sender_id");
                int receiverId = resultSet.getInt("receiver_id");
                String message = resultSet.getString("message");
                boolean isReaded = resultSet.getBoolean("is_readed");
                boolean isEdited = resultSet.getBoolean("is_edited");

                OffsetDateTime timestampObj = resultSet.getObject("timestamp", OffsetDateTime.class);
                long timestamp = timestampObj.toInstant().toEpochMilli();

                messages.add(new Message(id, senderId, receiverId, message, timestamp, isReaded, isEdited));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return messages;
    }
}
