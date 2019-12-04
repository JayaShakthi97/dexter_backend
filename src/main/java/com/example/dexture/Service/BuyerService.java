package com.example.dexture.Service;

import com.example.dexture.Repository.BuyerRepo;
import com.example.dexture.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {
    @Autowired
    BuyerRepo buyerRepo;

    public Buyer getById(int id){
        return buyerRepo.findById(id).isPresent() ? buyerRepo.findById(id).get() : null;
    }

    public boolean existsByEmail(String email){
        return buyerRepo.existsBuyerByEmail(email);
    }

    public void createBuyer(Buyer buyer){
        buyerRepo.save(buyer);
    }

    public boolean isBuyer(String email, String password) {
        return buyerRepo.existsBuyerByEmailAndPassword(email, password);
    }

    public Buyer getBuyer(String email, String password) {
        return buyerRepo.getBuyerByEmailAndPassword(email, password);
    }
}
