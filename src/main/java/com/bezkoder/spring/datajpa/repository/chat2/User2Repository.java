package com.bezkoder.spring.datajpa.repository.chat2;

import com.bezkoder.spring.datajpa.model.chat2Model.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User2Repository extends JpaRepository<User2,Long> {

    List<User2> findByNomUserContainingOrPrenomUserContaining(String nom, String prenom);

    User2 findByNomUser(String nomUser);


}
