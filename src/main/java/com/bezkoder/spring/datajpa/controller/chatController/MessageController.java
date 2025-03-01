package com.bezkoder.spring.datajpa.controller.chatController;



import com.bezkoder.spring.datajpa.model.chat2Model.Message2;
import com.bezkoder.spring.datajpa.services.chat2Service.IMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/message")
public class MessageController {
    IMessageService messageService;


    @PostMapping("add")
    public Message2 addingMessage(@RequestBody Message2 message){
        return messageService.addMessage(message);
    }



    @GetMapping("getAll")
    public List<Message2> gettingAllMessage(){
        return messageService.getAllMessages();
    }


    @GetMapping("get")
    public Message2 gettingMessage(@RequestParam("idMessage") long idMessage){
        return messageService.getMessageById(idMessage);
    }


    @DeleteMapping("delete/{idMessage}")
    public void deletingMessage(@PathVariable("idMessage") long idMessage){
        messageService.deleteMessage(idMessage);
    }


    @PutMapping("update")
    public Message2 updatingMessage(@RequestBody Message2 message){
        return messageService.updateMessage(message);
    }


}
