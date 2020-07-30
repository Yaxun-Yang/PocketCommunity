package org.yyx.pocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yyx.pocket.domain.Message;
import org.yyx.pocket.persistence.MessageMapper;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Transactional
    public String insertMessage(Message message)
    {
        messageMapper.insertMessage(message);
        return messageMapper.getRecentMessageId();
    }

    public void deleteMessage(String messageId)
    {
        messageMapper.deleteMessage(messageId);
    }

    public void updateMessage(Message message)
    {
        messageMapper.updateMessage(message);
    }

    public Message getMessage(String messageId)
    {
        return messageMapper.getMessage(messageId);
    }

    public List<Message> getMessageList()
    {
        return messageMapper.getMessageList();
    }
}
