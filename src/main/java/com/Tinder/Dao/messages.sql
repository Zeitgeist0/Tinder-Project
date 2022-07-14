CREATE TABLE messages(
                         message_id int GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT PK_message PRIMARY KEY,
                         sender_id int NOT NULL,
                         receiver_id int NOT NULL,
                         message varchar(500) NOT NULL,
                         timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                         is_readed BOOLEAN NOT NULL DEFAULT FALSE,
                         is_edited BOOLEAN NOT NULL DEFAULT FALSE,

                         CONSTRAINT FK_messages_profile_sender FOREIGN KEY(sender_id) REFERENCES profile(id),
                         CONSTRAINT FK_messages_profile_receiver FOREIGN KEY(receiver_id) REFERENCES profile(id)
);

INSERT INTO messages(message, sender_id, receiver_id) VALUES ('hello', 1, 2);

UPDATE messages SET message = 'hello there', is_edited = TRUE WHERE message_id = 1;

DELETE FROM messages WHERE message_id = 1;

INSERT INTO messages(message, sender_id, receiver_id) VALUES ('hello 2, I am 1', 1, 2);
INSERT INTO messages(message, sender_id, receiver_id) VALUES ('hello 1, I am 2', 2, 1);
INSERT INTO messages(message, sender_id, receiver_id) VALUES ('hello 3, I am 1', 1, 3);

SELECT * FROM messages WHERE sender_id = 1 AND receiver_id = 2 OR sender_id = 2 AND receiver_id = 1