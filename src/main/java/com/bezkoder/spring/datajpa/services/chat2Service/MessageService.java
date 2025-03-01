package com.bezkoder.spring.datajpa.services.chat2Service;

import com.bezkoder.spring.datajpa.model.chat2Model.Message2;
import com.bezkoder.spring.datajpa.repository.chat2.MessageRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageService implements IMessageService{
    MessageRepository messageRepository;

    @Override
    public Message2 addMessage(Message2 message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message2> getAllMessages() {
        return (List<Message2>)messageRepository.findAll();
    }

    @Override
    public Message2 getMessageById(long idMessage) {
        return messageRepository.findById(idMessage).get();
    }

    @Override
    public void deleteMessage(long idMessage) {
        messageRepository.deleteById(idMessage);

    }


    @Override
    public Message2 updateMessage(Message2 idMessage) {
        return messageRepository.save(idMessage);
    }
}
