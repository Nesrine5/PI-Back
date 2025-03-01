package com.bezkoder.spring.datajpa.repository;


import com.bezkoder.spring.datajpa.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<FileData,Long> {
    Optional<FileData> findByName(String fileName);
    List<FileData> findAll();
}
