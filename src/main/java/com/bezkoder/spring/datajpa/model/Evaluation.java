package com.bezkoder.spring.datajpa.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evaluation {
    //Candidature
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idEvaluation;
    //état (en attente/acceptée/refusée).
    String Etat;
    // date de soumission
    Date date;
    //rating
    private int starRating;
   @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Livrable livrable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "besoin-evaluations")
    private Besoin besoin;
    // Add a transient field to hold the title of the associated Besoin
    @Transient
    private String besoinTitle;

    // Constructors, getters, setters, and other methods...

    // Add a getter method for the transient field
    public String getBesoinTitle() {
        return (besoin != null) ? besoin.getTitle() : null;
    }

    public int getLivrableNote() {
        return (livrable != null) ? livrable.getNote() : 0;
    }
}
