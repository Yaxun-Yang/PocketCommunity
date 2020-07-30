package org.yyx.pocket.persistence;


import org.springframework.stereotype.Repository;
import org.yyx.pocket.domain.Message;

import java.util.List;

@Repository
public interface MessageMapper {

    void insertMessage(Message message);
    void deleteMessage(String messageId);
    void updateMessage(Message message);
    String getRecentMessageId();
    Message getMessage(String messageId);
    List<Message> getMessageList();
}
