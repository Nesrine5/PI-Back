package com.bezkoder.spring.datajpa.model;

import com.bezkoder.spring.datajpa.model.enumerations.TypeBesoin;
import com.bezkoder.spring.datajpa.model.userModel.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Besoin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long besoinId;

    String title;

    @Lob
    String description;
   // TypeBesoin type;
    String type;
   // @JsonFormat(pattern = "dd-MM-yyyy")
    Date date;
    private int starRating;

    private String imagePath;


    @OneToMany(mappedBy = "besoin")
    @JsonManagedReference(value = "besoin-evaluations")
    private List<Evaluation> evaluations;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference(value = "user-besoins")
    private User user;



}