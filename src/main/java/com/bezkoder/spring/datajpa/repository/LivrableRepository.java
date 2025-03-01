package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Livrable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivrableRepository extends JpaRepository<Livrable, Long> {
    @Query("SELECT MAX(l.note) FROM Livrable l")
    Integer findMaxNote();


    // Method to find top submissions (top 3) ordered by note in descending order
    @Query("SELECT livrable FROM Livrable livrable ORDER BY livrable.note DESC LIMIT 3")
    List<Livrable> findTop3ByOrderByNoteDesc();

    // Method to find rejected submissions (note less than a certain threshold)
    List<Livrable> findByNoteLessThan(int noteThreshold);

    //top1
    @Query("SELECT livrable FROM Livrable livrable ORDER BY livrable.note DESC LIMIT 1")
    List<Livrable> findTop1ByOrderByNoteDesc();
}