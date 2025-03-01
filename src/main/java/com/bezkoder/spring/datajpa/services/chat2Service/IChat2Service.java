package com.bezkoder.spring.datajpa.services.chat2Service;



import com.bezkoder.spring.datajpa.exceptions.ChatNotFoundException;
import com.bezkoder.spring.datajpa.model.chat2Model.Chat2;
import com.bezkoder.spring.datajpa.model.chat2Model.Message2;
import com.bezkoder.spring.datajpa.model.chat2Model.User2;
import com.bezkoder.spring.datajpa.model.userModel.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IChat2Service {

    Chat2 addChat(Chat2 chat);
    List<Chat2> getAllChats();
    Chat2 getChatById(long idChat);
    void deleteChat(long idChat);
    Chat2 updateChat(Chat2 Chat);
    //public Chat2 addMessage(Long chatId, Message2 message);

    public Chat2 createChatWithUser(User2 user);
     Chat2 addMessage(Long chatId, Long senderId, Message2 message);

    Set<User> getUsersInChat(Long chatId);
    public HashSet<Chat2> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException;

}