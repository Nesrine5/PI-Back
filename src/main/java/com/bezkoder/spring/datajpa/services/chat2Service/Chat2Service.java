package com.bezkoder.spring.datajpa.services.chat2Service;



import com.bezkoder.spring.datajpa.exceptions.ChatNotFoundException;
import com.bezkoder.spring.datajpa.exceptions.NoChatExistsInTheRepository;
import com.bezkoder.spring.datajpa.model.chat2Model.Chat2;
import com.bezkoder.spring.datajpa.model.chat2Model.Message2;
import com.bezkoder.spring.datajpa.model.chat2Model.User2;
import com.bezkoder.spring.datajpa.model.userModel.User;
import com.bezkoder.spring.datajpa.repository.chat2.Chat2Repository;
import com.bezkoder.spring.datajpa.repository.chat2.MessageRepository;
import com.bezkoder.spring.datajpa.repository.chat2.User2Repository;
import com.bezkoder.spring.datajpa.repository.userRepo.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat2Service implements IChat2Service {
    Chat2Repository chatRepository;
    UserRepository userRepository;
    MessageRepository messageRepository;
    SequenceGeneratorService sequenceGeneratorService;
   /* @Override
    public Chat2 addChat(Chat2 chat) {
        return chatRepository.save(chat);
    }*/

    @Override
    public Chat2 addChat(Chat2 chat) {
        chat.setId(sequenceGeneratorService.generateSequence(Chat2.SEQUENCE_NAME));
        return chatRepository.save(chat);
    }
    @Override
    public List<Chat2> getAllChats() {
        return (List<Chat2>) chatRepository.findAll();
    }


    @Override
    public void deleteChat(long idChat) {
        chatRepository.deleteById(idChat);
    }

    @Override
    public Chat2 updateChat(Chat2 chat) {
        return chatRepository.save(chat);
    }


    // ADD MESSAGE
    /*public Chat2 addMessage(Long chatId, Message2 message) {
        Chat2 chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found with id: " + chatId));

        chat.getMessages().add(message);
        message.setChat(chat);
        return chatRepository.save(chat);

    }*/
    @Transactional
    public Chat2 addMessage(Long chatId, Long senderId, Message2 message) {
        Chat2 chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found with id: " + chatId));

        // Fetch the sender from the repository
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + senderId));

        // Associate the sender with the message
       // message.setSender(sender.getUsername()); // Assuming sender is a combination of name and surname

        // Associate the message with the chat
        chat.getMessages().add(message);
        message.setChat(chat);


        // Associate the user with the chat
        chat.getUserss().add(sender);
        sender.getChats().add(chat);
        // Save the message separately if needed

        message.setTime(LocalDateTime.now());
        messageRepository.save(message);

        return chatRepository.save(chat);
    }

    // CREATE CHAT + ADD USER
    public Chat2 createChatWithUser(User2 user) {
        Chat2 newChat = new Chat2();
        newChat.setTitre("Chat with " + user.getNomUser());
        if (newChat.getUsers() == null) { newChat.setUsers(new HashSet<>()); }
        newChat.getUsers().add(user);
        if (user.getChats().isEmpty()) { user.setChats(new HashSet<>()); }
        user.getChats().add(newChat);
        return chatRepository.save(newChat);
    }

    public Chat2 getChatById(long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found with id: " + chatId));
    }

    public Set<User> getUsersInChat(Long chatId) {
        Chat2 chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found with id: " + chatId));
        return chat.getUserss();
    }


    @Override
    public HashSet<Chat2> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException {
        HashSet<Chat2> chat = chatRepository.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
        HashSet<Chat2> chat1 = chatRepository.getChatBySecondUserNameAndFirstUserName(firstUserName, secondUserName);
        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat.isEmpty()) {
            return chat1;
        } else {
            return chat;
        }
    }

  /*  public Chat2 addChat(Chat2 chat) {
        chat.setId(sequenceGeneratorService.generateSequence(Chat2.SEQUENCE_NAME));
        return chatRepository.save(chat);
    }
*/

}

