package com.bezkoder.spring.datajpa.services;

import com.bezkoder.spring.datajpa.model.Besoin;
import com.bezkoder.spring.datajpa.model.Evaluation;
import com.bezkoder.spring.datajpa.model.Livrable;
import com.bezkoder.spring.datajpa.repository.BesoinRepository;
import com.bezkoder.spring.datajpa.repository.EvaluationRepository;
import com.bezkoder.spring.datajpa.repository.LivrableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private LivrableRepository livrableRepository;
    @Autowired
    private  EvaluationRepository evaluationRepository;
    @Autowired
    private BesoinRepository besoinsRepository;
    @Autowired
    public EvaluationServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public List<Evaluation> getAllEvaluations() {
        //List<Evaluation> evaluations = evaluationRepository.findAllWithLivrableAndBesoin();
       // log.info("Fetched Evaluations with Livrable and Besoin: {}", evaluations);
        Long besoinId;
        Long livrableId;
        List<Evaluation> evaluations = evaluationRepository.findAllWithLivrableAndBesoin();



        /*Besoin besoin = besoinsRepository.findById(besoinId)
                .orElseThrow(() -> new NoSuchElementException("Besoins not found"));

        Livrable savedLivrable = livrableRepository.save(livrable);

        Evaluation evaluation = new Evaluation();
        evaluation.setLivrable(savedLivrable);
        livrableRepository.findById();*/
        return evaluations;
    }

    public List<Evaluation> getAllEvaluationsByIdBesoinIdLivrable(Long besoinId, Long livrableId) {
        return evaluationRepository.findAllByBesoinIdAndLivrableId(besoinId, livrableId);
    }



    @Override
    public Evaluation getEvaluationById(long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found with id: " + id));
    }

    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(long id, Evaluation updatedEvaluation) {
        Evaluation existingEvaluation = getEvaluationById(id);
        existingEvaluation.setEtat(updatedEvaluation.getEtat());
        existingEvaluation.setDate(updatedEvaluation.getDate());
        existingEvaluation.setLivrable(updatedEvaluation.getLivrable());
        existingEvaluation.setBesoin(updatedEvaluation.getBesoin());
        return evaluationRepository.save(existingEvaluation);
    }
    @Transactional
    public void acceptEvaluation(long evaluationId) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(EntityNotFoundException::new);
        evaluation.setEtat("accepter");
        evaluationRepository.save(evaluation);
    }

    @Transactional
    public void refuserEvaluation(long evaluationId) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(EntityNotFoundException::new);
        evaluation.setEtat("refuser");
        evaluationRepository.save(evaluation);
    }
    @Override
    public void deleteEvaluation(long id) {
        evaluationRepository.deleteById(id);
    }

    //rating
    public Evaluation updateRating(Long id, int newRating) {
        Optional<Evaluation> optionalEvaluation = evaluationRepository.findById(id);

        if (optionalEvaluation.isPresent()) {
            Evaluation evaluation = optionalEvaluation.get();
            evaluation.setStarRating(newRating);
            return evaluationRepository.save(evaluation);
        }

        return null;
    }

    @Override
    public List<Evaluation> findByEtat(String etat) {
        return evaluationRepository.findByEtat(etat);
    }
}
