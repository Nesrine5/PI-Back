package com.bezkoder.spring.datajpa.services.chat2Service;



import com.bezkoder.spring.datajpa.model.chat2Model.Message2;

import java.util.List;

public interface IMessageService {

    Message2 addMessage(Message2 message);
    List<Message2> getAllMessages();
    Message2 getMessageById(long idMessage);
    void deleteMessage(long idMessage);
    Message2 updateMessage(Message2 idMessage);
}
