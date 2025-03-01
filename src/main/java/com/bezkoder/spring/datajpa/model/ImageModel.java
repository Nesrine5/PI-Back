package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "image_model")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Setter(AccessLevel.NONE)
    long idImage;
    String name;
    String type;
    @Column(length = 50000000)
    byte[] picByte;
    public ImageModel(){

    }

    public ImageModel(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }


}
