package com.bezkoder.spring.datajpa.services.chat2Service;


import com.bezkoder.spring.datajpa.model.chat2Model.User2;
import com.bezkoder.spring.datajpa.model.userModel.User;

import java.util.List;

public interface IUser2Service {
    User2 addUser(User2 user);
    //List<User2> getAllUsers();
    User2 getUserById(long idUser);
    void deleteUser(long idUser);
    User2 updateUser(User2 user);
    List<User2> searchUsers(String searchText);
    boolean getUserOnlineStatus(Long userId);
    void updateUserOnlineStatus(Long userId, boolean online);
    List<User> getAllUsers();
}
