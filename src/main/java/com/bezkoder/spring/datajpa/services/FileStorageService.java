package com.bezkoder.spring.datajpa.services;

import com.bezkoder.spring.datajpa.model.Besoin;
import com.bezkoder.spring.datajpa.model.Evaluation;
import com.bezkoder.spring.datajpa.model.FileDB;
import com.bezkoder.spring.datajpa.model.Livrable;
import com.bezkoder.spring.datajpa.model.chat2Model.Chat2;
import com.bezkoder.spring.datajpa.model.userModel.User;
import com.bezkoder.spring.datajpa.repository.BesoinRepository;
import com.bezkoder.spring.datajpa.repository.EvaluationRepository;
import com.bezkoder.spring.datajpa.repository.FileDBRepository;
import com.bezkoder.spring.datajpa.repository.LivrableRepository;
import com.bezkoder.spring.datajpa.repository.chat2.Chat2Repository;
import com.bezkoder.spring.datajpa.repository.userRepo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    private LivrableRepository livrableRepository;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private BesoinRepository besoinsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    Chat2Repository chatRepository;



    public FileDB store(MultipartFile file ,Long besoinId,Long idUser) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        //user
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        //  Livrable livrable = fileDBRepository.findById();
        Livrable livrable = new Livrable();


        //  livrable.setNameFile(fileName);
        //livrable.setIdLivrable(8);
        livrable.setNom(user.getUsername());
        livrable.setEmail(user.getEmail());
        livrable.setPrenom(user.getUsername());
        livrable.setUser(user);
        livrable.setAdresse("boumhal");
        livrable.setVille("Tunis");
        FileDB.setLivrable(livrable);
        livrableRepository.save(livrable);


//Besoin
        Besoin besoin = besoinsRepository.findById(besoinId)
                .orElseThrow(() -> new NoSuchElementException("Besoins not found"));

//Evaluation
        Evaluation evaluation = new Evaluation();
        evaluation.setLivrable(livrable);
        evaluation.setBesoin(besoin);
        evaluation.setDate(new Date());
        evaluation.setEtat("en attente");
        evaluationRepository.save(evaluation);
//specialite



      /*  String besoinType = besoin.getType();
        if ("Logo".equals(besoinType)) {
            List<User> designerLogoUsers = userRepository.findBySpecialty("DesignerLogo");
            for (User user2 : designerLogoUsers) {
                FileDB.setUser(user2);
            }
        }*/
        String besoinType = besoin.getType();
        if ("Logo".equals(besoinType)) {
            List<User> designerLogoUsers = userRepository.findBySpecialty("DesignerLogo");
            for (User user2 : designerLogoUsers) {
                FileDB.setUser(user2);
            }
        } else if ("Affiche".equals(besoinType)) {
            List<User> afficheUsers = userRepository.findBySpecialty("DesignerAffiche");
            for (User user2 : afficheUsers) {
                FileDB.setUser(user2);
            }
        } else if ("Badge".equals(besoinType)) {
            List<User> badgeUsers = userRepository.findBySpecialty("DesignerBadge");
            for (User user2 : badgeUsers) {
                FileDB.setUser(user2);
            }
        } else if ("Brochure".equals(besoinType)) {
            List<User> brochureUsers = userRepository.findBySpecialty("DesignerBrochureS");
            for (User user2 : brochureUsers) {
                FileDB.setUser(user2);
            }
        }




        return fileDBRepository.save(FileDB);
    }















    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

   /*   public Stream<FileDB> getAllFiles() {
      return fileDBRepository.findAll().stream();
    }*/
   /*public Stream<FileDB> getAllFiles(Long idUser) {
       User user = userRepository.findById(idUser)
               .orElseThrow(() -> new NoSuchElementException("User not found"));

       //FileDB fileDB = new FileDB();
      // fileDB.getUser(user);
       return fileDBRepository.findAllByUser(user).stream();
   }*/


    public Stream<FileDB> getAllFilesSortedByNoteDescending(Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        return fileDBRepository.findAllByUserOrderByLivrableNoteDesc(user).stream();
    }
/*
    @Transactional
    public void updateLivrableNote(String fileId, int newNote) {
        FileDB fileDB = fileDBRepository.findById(fileId)
                .orElseThrow(() -> new EntityNotFoundException("File not found with id: " + fileId));

        Livrable livrable = fileDB.getLivrable();
        if (livrable != null) {
            livrable.setNote(newNote);
            Integer maxNote = livrableRepository.findMaxNote();
            System.out.println("Max Note: " + (maxNote != null ? maxNote : "N/A"));
            List<Evaluation> livrableEvaluations = livrable.getEvaluations();
            for (Evaluation evaluation : livrableEvaluations) {
           if (livrable != null && livrable.getNote() == maxNote) {
                evaluation.setEtat("ACCEPTED");
            } else {
                evaluation.setEtat("REFUSED");
            }
                Livrable evaluationLivrable = evaluation.getLivrable();

                System.out.println("Before Update - Evaluation: " + evaluation.getIdEvaluation() +
                        ", Livrable Note: " + (evaluationLivrable != null ? evaluationLivrable.getNote() : "N/A") +
                        ", Max Note: " + livrable.getNote() +
                        ", Etat: " + evaluation.getEtat());

             

                System.out.println("After Update - Evaluation: " + evaluation.getIdEvaluation() +
                        ", Livrable Note: " + (evaluationLivrable != null ? evaluationLivrable.getNote() : "N/A") +
                        ", Max Note: " + livrable.getNote() +
                        ", Etat: " + evaluation.getEtat());
            }

            livrableRepository.save(livrable);
        }
    }


    */
@Transactional
public void updateLivrableNote(String fileId, int newNote) {
    FileDB fileDB = fileDBRepository.findById(fileId)
            .orElseThrow(() -> new EntityNotFoundException("File not found with id: " + fileId));

    Livrable livrable = fileDB.getLivrable();
    if (livrable != null) {
        livrable.setNote(newNote);

        List<Evaluation> livrableEvaluations = livrable.getEvaluations();

        // Fetch the top submission
        List<Livrable> topSubmission = livrableRepository.findTop1ByOrderByNoteDesc();

        // Flag to track if top submission is found
        boolean topSubmissionFound = false;

        // Update evaluation states based on comparison with the top submission
        for (Evaluation evaluation : livrableEvaluations) {
            Livrable evaluationLivrable = evaluation.getLivrable();
            // Compare the evaluation's Livrable with the top submission
            if (evaluationLivrable != null && evaluationLivrable.equals(topSubmission.get(0))) {
                evaluation.setEtat("ACCEPTED");

                topSubmissionFound = true;
            } else {
                evaluation.setEtat("REFUSED");
            }
          if (evaluation.getEtat().equals("ACCEPTED")){
            Chat2 newChat = new Chat2();
            //newChat.setTitre("chat1");
            chatRepository.save(newChat);}
        }


        // If top submission is not found, mark the first evaluation as ACCEPTED
        if (!topSubmissionFound && !livrableEvaluations.isEmpty()) {
            livrableEvaluations.get(0).setEtat("ACCEPTED");
        }
    }

    // Save the updated Livrable
    livrableRepository.save(livrable);
}


}

