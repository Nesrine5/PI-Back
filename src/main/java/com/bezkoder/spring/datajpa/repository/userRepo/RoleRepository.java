package com.bezkoder.spring.datajpa.repository.userRepo;


import com.bezkoder.spring.datajpa.model.userModel.ERole;
import com.bezkoder.spring.datajpa.model.userModel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}