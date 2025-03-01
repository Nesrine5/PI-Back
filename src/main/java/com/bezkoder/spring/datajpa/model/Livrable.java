package com.bezkoder.spring.datajpa.model;

import com.bezkoder.spring.datajpa.model.userModel.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Livrable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Setter(AccessLevel.NONE)
    long idLivrable ;
    //Matricule de l'étudiant
    String matricule;
    String nom;
    String prenom;
    String etablissement;
    String niveau;
    String phoneNumber;
    String adresse;
    String ville;
    String codePostal;
    String nameFile;
    int note ;
    //@Column(unique = true)
    //@Email(message = "Please provide a valid email address")
    private String email;
    //String stepName;
    //String fileUrl;
    @OneToMany(mappedBy = "livrable")
    @JsonManagedReference
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "livrable")
    @JsonManagedReference
    private List<FileDB> files;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "user-livrables")
    private User user;




    @ElementCollection
    @CollectionTable(name = "livrable_file_urls", joinColumns = @JoinColumn(name = "livrable_id"))
    @Column(name = "file_url")
    private List<String> fileUrls;

    public Livrable() {
        // Initialize evaluations and fileUrls if they are null
        if (evaluations == null) {
            evaluations = new ArrayList<>();
        }
        if (fileUrls == null) {
            fileUrls = new ArrayList<>();
        }
    }
    // Inside the Livrable class

    public int getNote() {
        return this.note;
    }

}