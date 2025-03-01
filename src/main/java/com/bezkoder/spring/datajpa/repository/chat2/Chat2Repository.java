package com.bezkoder.spring.datajpa.repository.chat2;

import com.bezkoder.spring.datajpa.model.chat2Model.Chat2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface Chat2Repository extends CrudRepository<Chat2,Long> {

    HashSet<Chat2> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName);

    HashSet<Chat2> getChatBySecondUserNameAndFirstUserName(String firstUserName, String secondUserName);


}

