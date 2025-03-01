package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    @Query("SELECT e FROM Evaluation e JOIN FETCH e.livrable JOIN FETCH e.besoin")
    List<Evaluation> findAllWithLivrableAndBesoin();

    @Query("SELECT e FROM Evaluation e JOIN FETCH e.livrable JOIN FETCH e.besoin " +
            "WHERE e.besoin.besoinId = :besoinId AND e.livrable.idLivrable = :livrableId")
    List<Evaluation> findAllByBesoinIdAndLivrableId(@Param("besoinId") Long besoinId, @Param("livrableId") Long livrableId);

    @Query("SELECT e FROM Evaluation e where e.Etat=:etat")
    List<Evaluation> findByEtat(@Param("etat") String etat);
}
