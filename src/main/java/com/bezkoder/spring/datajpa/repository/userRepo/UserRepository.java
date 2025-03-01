package com.bezkoder.spring.datajpa.repository.userRepo;

import com.bezkoder.spring.datajpa.model.chat2Model.Chat2;
import com.bezkoder.spring.datajpa.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.specialite = :specialty")
  List<User> findBySpecialty(@Param("specialty") String specialty);


}