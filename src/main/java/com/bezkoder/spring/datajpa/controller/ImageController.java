package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.Util.ImageUtils;
import com.bezkoder.spring.datajpa.model.FileData;
import com.bezkoder.spring.datajpa.repository.ImageRepository;
import com.bezkoder.spring.datajpa.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageController {
    @Autowired
    private ImageService storageService;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/uploadd")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage=storageService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);

    }
    @GetMapping("downloadd/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] download=storageService.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(download);
    }

    @GetMapping("/allImages")
    public ResponseEntity<List<String>> getAllImages() {
        List<String> base64Images = storageService.downloadAllImages();
        return new ResponseEntity<>(base64Images, HttpStatus.OK);
    }
    }




