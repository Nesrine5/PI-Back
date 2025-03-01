package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Livrable;

import com.bezkoder.spring.datajpa.services.ILivrableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/livrables")
public class LivrableController {

    @Autowired
    private ILivrableService livrableService;

    @GetMapping
    public List<Livrable> getAllLivrabales() {
        return livrableService.getAllLivrabales();
    }

    @GetMapping("/{id}")
    public Livrable getLivrableById(@PathVariable long id) {
        return livrableService.getLivrableById(id);
    }

    @PostMapping
    public Livrable saveLivrable(@RequestBody Livrable livrable) {
        return livrableService.saveLivrable(livrable);
    }

    @DeleteMapping("/{id}")
    public void deleteLivrable(@PathVariable long id) {
        livrableService.deleteLivrable(id);
    }

    @GetMapping("/addLivrable/{besoinId}")
    public String addLivrable(@PathVariable Long besoinId, Model model) {
        Livrable livrable = livrableService.addLivrableWithEvaluation(besoinId);
        model.addAttribute("livrable", livrable);
        return "addLivrable";
    }


    @PostMapping("/addLivrable/{besoinId}")
    public ResponseEntity<Livrable> addLivrable(@RequestBody Livrable livrable, @PathVariable Long besoinId) {
        Livrable savedLivrable = livrableService.saveLivrableAndAddIDLivrableAndIdBesoinInEnovation(livrable, besoinId);
        return new ResponseEntity<>(savedLivrable, HttpStatus.CREATED);
    }


    // Endpoint to fetch top submissions
    @GetMapping("/top-submissions")
    public ResponseEntity<List<Livrable>> getTopSubmissions() {
        List<Livrable> topSubmissions = livrableService.getTopSubmissions();
        return ResponseEntity.ok().body(topSubmissions);
    }

    // Endpoint to fetch rejected submissions
    @GetMapping("/rejected-submissions")
    public ResponseEntity<List<Livrable>> getRejectedSubmissions() {
        List<Livrable> rejectedSubmissions = livrableService.getRejectedSubmissions();
        return ResponseEntity.ok().body(rejectedSubmissions);
    }
}

