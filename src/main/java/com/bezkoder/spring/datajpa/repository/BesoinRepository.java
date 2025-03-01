package com.bezkoder.spring.datajpa.repository;



import com.bezkoder.spring.datajpa.model.Besoin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BesoinRepository extends CrudRepository<Besoin,Long> {
    @Query("SELECT b FROM Besoin b JOIN FETCH b.user WHERE b.id = :besoinId")
    Optional<Besoin> findByIdWithUser(@Param("besoinId") Long besoinId);
}
