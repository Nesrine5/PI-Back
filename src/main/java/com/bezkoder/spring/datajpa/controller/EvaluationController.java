package com.bezkoder.spring.datajpa.controller;
import com.bezkoder.spring.datajpa.model.Evaluation;
import com.bezkoder.spring.datajpa.model.FileDB;
import com.bezkoder.spring.datajpa.services.EvaluationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/evaluations")
public class EvaluationController {

    EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable long id) {
        return evaluationService.getEvaluationById(id);
    }

    @PostMapping
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }

    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@PathVariable long id, @RequestBody Evaluation evaluation) {
        return evaluationService.updateEvaluation(id, evaluation);
    }
    @PutMapping("/{evaluationId}/accept")
    public ResponseEntity<String> acceptEvaluation(@PathVariable long evaluationId) {
        try {
            evaluationService.acceptEvaluation(evaluationId);
            return ResponseEntity.ok("Evaluation accepted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evaluation not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accepting evaluation");
        }
    }


    @PutMapping("/{evaluationId}/refuser")
    public ResponseEntity<String> refuserEvaluation(@PathVariable long evaluationId) {
        try {
            evaluationService.refuserEvaluation(evaluationId);
            return ResponseEntity.ok("Evaluation refuser successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evaluation not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error refusing evaluation");
        }
    }
    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable long id) {
        evaluationService.deleteEvaluation(id);
    }
    @GetMapping("/filtered")
    public ResponseEntity<List<Evaluation>> getAllEvaluationsByIdBesoinIdLivrable(
            @RequestParam Long besoinId,
            @RequestParam Long livrableId) {

        List<Evaluation> filteredEvaluations = evaluationService.getAllEvaluationsByIdBesoinIdLivrable(besoinId, livrableId);

        if (filteredEvaluations.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(filteredEvaluations);
        }
    }
//rating
    @PutMapping("/rating/{id}")
    public ResponseEntity<Evaluation> updateRating(@PathVariable Long id, @RequestBody Evaluation updatedEvaluation) {
        Evaluation evaluation = evaluationService.updateRating(id, updatedEvaluation.getStarRating());

        if (evaluation != null) {
            return new ResponseEntity<>(evaluation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/filterByEtat/{etat}")
    public List<Evaluation> filterByEtat(@PathVariable String etat) {
        System.out.println("Received etat: " + etat);
        return evaluationService.findByEtat(etat);
    }
}


