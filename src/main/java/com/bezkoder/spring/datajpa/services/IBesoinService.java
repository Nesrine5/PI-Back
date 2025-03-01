package com.bezkoder.spring.datajpa.services;



import com.bezkoder.spring.datajpa.model.Besoin;

import java.util.List;

public interface IBesoinService {
   // Besoin addBesoin(Besoin besoin);
    List<Besoin> getAllBesoins();
    Besoin getBesoinById(long idBesoin);
    void deleteBesoin(long idBesoin);
    Besoin updateBesoin(Besoin besoin);
    Besoin updateRating(Long id, int newRating);
    Besoin addBesoin(Besoin besoin,Long idUser);
}