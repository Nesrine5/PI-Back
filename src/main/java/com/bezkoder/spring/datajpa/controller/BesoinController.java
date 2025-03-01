package com.bezkoder.spring.datajpa.controller;


import com.bezkoder.spring.datajpa.model.Besoin;

import com.bezkoder.spring.datajpa.model.Evaluation;
import com.bezkoder.spring.datajpa.model.ImageModel;
import com.bezkoder.spring.datajpa.services.IBesoinService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("api/besoins")
public class BesoinController {
    IBesoinService besoinService;
    //{}
    //[]
    @PostMapping("add/{UserId}")
    public Besoin addingBesoin(@RequestBody Besoin besoin, @PathVariable Long UserId){

        return besoinService.addBesoin(besoin,UserId);
    }

   public  Set<ImageModel>  uploadImage(MultipartFile[] multipartFiles) throws IOException {
       Set<ImageModel> imageModels= new HashSet<>();
       for(MultipartFile file: multipartFiles){
           ImageModel imageModel = new ImageModel(
                   file.getOriginalFilename(),
                   file.getContentType(),
                   file.getBytes()
           );
           imageModels.add(imageModel);
       }
       return imageModels;

   }
   // @PreAuthorize("hasRole('ROLE_STUDENT')")
    // Order 2
    @GetMapping("getAll")
    public List<Besoin> gettingAllBesoin(){
        return besoinService.getAllBesoins();
    }

    // Order 3
    @GetMapping("get")
    public Besoin gettingBesoin(@RequestParam("idBesoin") long idBesoin){
        return besoinService.getBesoinById(idBesoin);
    }

    // Order 4
    @DeleteMapping("delete/{idBesoin}")
    public void deletingBesoin(@PathVariable("idBesoin") long idBloc){
        besoinService.deleteBesoin(idBloc);
    }

    // Order 5
    @PutMapping("update")
    public Besoin updatingBesoin(@RequestBody Besoin bloc){
        return besoinService.updateBesoin(bloc);
    }



    //rating
    @PutMapping("/rating/{id}")
    public ResponseEntity<Besoin> updateRating(@PathVariable Long id, @RequestBody Besoin updatedBesoin) {
        Besoin besoin = besoinService.updateRating(id, updatedBesoin.getStarRating());

        if (besoin != null) {
            return new ResponseEntity<>(besoin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) {
       Besoin besoin ;
       // besoin.setImagePath(file);
        // Logic to handle file upload
        // Save the file, process it, etc.
        return "File uploaded successfully";
    }
}
