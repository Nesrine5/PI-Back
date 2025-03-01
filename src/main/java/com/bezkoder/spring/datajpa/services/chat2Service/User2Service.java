package com.bezkoder.spring.datajpa.services.chat2Service;


import com.bezkoder.spring.datajpa.model.chat2Model.User2;
import com.bezkoder.spring.datajpa.model.userModel.User;
import com.bezkoder.spring.datajpa.repository.chat2.User2Repository;
import com.bezkoder.spring.datajpa.repository.userRepo.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User2Service implements IUser2Service {
    User2Repository userRepository;
    UserRepository userrRepository;

    //SEARCH USER
    public List<User2> searchUsers(String searchText) {
        return userRepository.findByNomUserContainingOrPrenomUserContaining(searchText, searchText);
    }

    @Override
    public User2 addUser(User2 user) {
        return userRepository.save(user);
    }

    /*@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
*/
    @Override
    public List<User> getAllUsers() {
        return userrRepository.findAll();
    }
    @Override
    public User2 getUserById(long idUser) {
        return userRepository.findById(idUser).get();
    }

    @Override
    public void deleteUser(long idUser) {
        userRepository.deleteById(idUser);
    }

    @Override
    public User2 updateUser(User2 user) {
        return userRepository.save(user);
    }

    @Override
    public boolean getUserOnlineStatus(Long userId) {
        Optional<User2> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User2::isOnline).orElse(false);
    }

    @Override
    public void updateUserOnlineStatus(Long userId, boolean online) {
        Optional<User2> optionalUser = userRepository.findById(userId);
        optionalUser.ifPresent(user -> {
            user.setOnline(online);
            userRepository.save(user);
        });
    }
}

