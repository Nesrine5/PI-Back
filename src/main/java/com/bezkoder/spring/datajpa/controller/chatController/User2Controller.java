package com.bezkoder.spring.datajpa.controller.chatController;


import com.bezkoder.spring.datajpa.model.chat2Model.User2;
import com.bezkoder.spring.datajpa.model.userModel.User;
import com.bezkoder.spring.datajpa.services.chat2Service.IUser2Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/user")
public class User2Controller {


    private IUser2Service userService;



    @PostMapping("add")
    public User2 addingUser(@RequestBody User2 user){
        return userService.addUser(user);
    }


   /* @GetMapping("getAll")
    public List<User2> gettingAllUser(){

        return userService.getAllUsers();
    }*/

    @GetMapping("getAll")
    public List<User> gettingAllUser(){

        return userService.getAllUsers();
    }


    @GetMapping("get")
    public User2 gettingUser(@RequestParam("idUser") long idUser){

        return userService.getUserById(idUser);
    }


    @DeleteMapping("delete/{idUser}")
    public void deletingUser(@PathVariable("idUser") long idUser){
        userService.deleteUser(idUser);
    }


    @PutMapping("update")
    public User2 updatingUser(@RequestBody User2 user){
        return userService.updateUser(user);
    }

    //SEARCH USER BY FIRSTNAME + LASTNAME USER
    @GetMapping("/search")
    public List<User2> searchUsers(@RequestParam String searchText) {
        return userService.searchUsers(searchText);

    }


    @GetMapping("/{id}/online")
    public ResponseEntity<Boolean> getUserOnlineStatus(@PathVariable Long id) {
        boolean online = userService.getUserOnlineStatus(id);
        return ResponseEntity.ok(online);
    }
    @PutMapping("/{id}/online")
    public ResponseEntity<Void> updateUserOnlineStatus(@PathVariable Long id, @RequestParam boolean online) {
        userService.updateUserOnlineStatus(id, online);
        return ResponseEntity.noContent().build();
    }

}
