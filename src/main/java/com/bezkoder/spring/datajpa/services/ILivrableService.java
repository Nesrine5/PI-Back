package com.bezkoder.spring.datajpa.services;

import com.bezkoder.spring.datajpa.model.Livrable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ILivrableService {
    List<Livrable> getAllLivrabales();
    Livrable getLivrableById(long id);
    Livrable saveLivrable(Livrable livrable);
    void deleteLivrable(long id);
    void uploadFirstDocument(String stepName, MultipartFile file) throws IOException;

   Livrable addLivrableWithEvaluation(Long besoinId);

    Livrable saveLivrableAndAddIDLivrableAndIdBesoinInEnovation(Livrable livrable,Long besoinId);

    public List<Livrable> getRejectedSubmissions();
    public List<Livrable> getTopSubmissions();
}
