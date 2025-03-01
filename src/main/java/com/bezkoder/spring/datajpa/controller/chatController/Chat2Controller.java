package com.bezkoder.spring.datajpa.controller.chatController;


import com.bezkoder.spring.datajpa.exceptions.ChatNotFoundException;
import com.bezkoder.spring.datajpa.model.chat2Model.Chat2;
import com.bezkoder.spring.datajpa.model.chat2Model.Message2;
import com.bezkoder.spring.datajpa.model.chat2Model.User2;
import com.bezkoder.spring.datajpa.model.userModel.User;
import com.bezkoder.spring.datajpa.repository.chat2.Chat2Repository;
import com.bezkoder.spring.datajpa.repository.userRepo.UserRepository;
import com.bezkoder.spring.datajpa.services.chat2Service.IChat2Service;
import com.bezkoder.spring.datajpa.services.chat2Service.IUser2Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("intership/api/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class Chat2Controller {


    IChat2Service chatService;
    IUser2Service userService;

    @PostMapping("add")
    public Chat2 addingChat(@RequestBody Chat2 chat){
        return chatService.addChat(chat);
    }

    @GetMapping("getAll")
    public List<Chat2> gettingAllChat(){
        log.info("test");
        return chatService.getAllChats();
    }

    @GetMapping("get")
    public Chat2 gettingChat(@RequestParam("idChat") long idChat){return chatService.getChatById(idChat);}


    @DeleteMapping("delete/{idChat}")
    public void deletingChat(@PathVariable("idChat") long idChat){
        chatService.deleteChat(idChat);
    }


    @PutMapping("update")
    public Chat2 updatingChat(@RequestBody Chat2 chat){
        return chatService.updateChat(chat);
    }

    // ADD MESSAGE TO CHAT  BY ID CHAT
    @PostMapping("/{chatId}/messages/{senderId}")
    public ResponseEntity<Set<Message2>> addMessageToChat(@PathVariable("chatId") long chatId,
                                                          @RequestBody Message2 message,
                                                          @PathVariable("senderId") Long senderId) {
        Chat2 chat = chatService.addMessage(chatId, senderId, message);
        return new ResponseEntity<>(chat.getMessages(), HttpStatus.CREATED);
    }

    // GET CHAT MESSAGES BY ID CHAT
    @GetMapping("/{chatId}/messages")
    public ResponseEntity<Set<Message2>> getChatMessages(@PathVariable("chatId") long chatId) {
        Chat2 chat = chatService.getChatById(chatId);
        return new ResponseEntity<>(chat.getMessages(), HttpStatus.OK);
    }

    @GetMapping("/{chatId}/users")
    public ResponseEntity<Set<User2>> getChatUsers(@PathVariable("chatId") long chatId) {
        Chat2 chat = chatService.getChatById(chatId);
        return new ResponseEntity<>(chat.getUsers(), HttpStatus.OK);
    }



    // CREAT CHAT + ADD USER
    @PostMapping("/add/user")
    public ResponseEntity<?> createChatWithUser(@RequestBody long userId) {
        User2 user = userService.getUserById(userId);
        Chat2 newChat = chatService.createChatWithUser(user);
        return ResponseEntity.ok(newChat);
    }



    @GetMapping("/chat/{chatId}/users")
    public ResponseEntity<Set<User>> getChatUsers(@PathVariable Long chatId) {
        Set<User> users = chatService.getUsersInChat(chatId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getChatByFirstUserNameAndSecondUserName")
    public ResponseEntity<?> getChatByFirstUserNameAndSecondUserName(@RequestParam("firstUserName") String firstUserName, @RequestParam("secondUserName") String secondUserName){

        try {
            HashSet<Chat2> chatByBothEmail = this.chatService.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
            return new ResponseEntity<>(chatByBothEmail, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.NOT_FOUND);
        }
    }

}

