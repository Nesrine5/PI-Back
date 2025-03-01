package com.bezkoder.spring.datajpa.services;

import com.bezkoder.spring.datajpa.Util.ImageUtils;
import com.bezkoder.spring.datajpa.model.FileData;
import com.bezkoder.spring.datajpa.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String uploadFile(MultipartFile file) throws IOException {
        FileData imageData=imageRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if(imageData!=null){
            return file.getOriginalFilename()+ " uploaded successfully";
        }
        return null;
    }


    public byte[] downloadFile(String fileName) {
        Optional<FileData> getImageData= imageRepository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(getImageData.get().getImageData());
        return images;
    }



    public List<FileData> getAllImages() {
        return imageRepository.findAll();
    }

    public List<String> downloadAllImages() {
        List<FileData> allImages = imageRepository.findAll();
        List<String> base64Images = new ArrayList<>();

        for (FileData fileData : allImages) {
            byte[] decompressedData = ImageUtils.decompressImage(fileData.getImageData());
            String base64Image = Base64.getEncoder().encodeToString(decompressedData);
            base64Images.add(base64Image);
        }

        return base64Images;
    }


}

