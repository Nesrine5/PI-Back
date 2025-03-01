package com.bezkoder.spring.datajpa.repository;


import com.bezkoder.spring.datajpa.model.FileDB;
import com.bezkoder.spring.datajpa.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

    List<FileDB> findAllByUser(User user);

    @Query("SELECT f FROM FileDB f WHERE f.user = ?1 ORDER BY f.livrable.note DESC")
    List<FileDB> findAllByUserOrderByLivrableNoteDesc(User user);
}
