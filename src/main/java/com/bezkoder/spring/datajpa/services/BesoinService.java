package com.bezkoder.spring.datajpa.services;


import com.bezkoder.spring.datajpa.model.Besoin;
import com.bezkoder.spring.datajpa.model.Evaluation;
import com.bezkoder.spring.datajpa.model.userModel.User;
import com.bezkoder.spring.datajpa.repository.BesoinRepository;

import com.bezkoder.spring.datajpa.repository.userRepo.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BesoinService  implements IBesoinService{
    BesoinRepository besoinRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Besoin addBesoin(Besoin besoin, Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        besoin.setUser(user);

        Besoin savedBesoin = besoinRepository.save(besoin);
        return besoinRepository.findByIdWithUser(savedBesoin.getBesoinId())
                .orElseThrow(() -> new IllegalStateException("Failed to fetch Besoin with associated User"));
    }

    @Override
    public List<Besoin> getAllBesoins() {
        return (List<Besoin>) besoinRepository.findAll();
    }


    @Override
    public Besoin getBesoinById(long idBesoin) {
        return besoinRepository.findById(idBesoin).get();
    }

    @Override
    public void deleteBesoin(long idBesoin) {
        besoinRepository.deleteById(idBesoin);

    }



    @Override
    public Besoin updateBesoin(Besoin besoin) {
        return besoinRepository.save(besoin);
    }


    public Besoin updateRating(Long id, int newRating) {
        Optional<Besoin> optionalBesoin = besoinRepository.findById(id);

        if (optionalBesoin.isPresent()) {
            Besoin besoin = optionalBesoin.get();
            besoin.setStarRating(newRating);
            return besoinRepository.save(besoin);
        }

        return null;
    }


}