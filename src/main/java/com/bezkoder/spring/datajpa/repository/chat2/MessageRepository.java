package com.bezkoder.spring.datajpa.repository.chat2;



import com.bezkoder.spring.datajpa.model.chat2Model.Message2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message2,Long> {
}
