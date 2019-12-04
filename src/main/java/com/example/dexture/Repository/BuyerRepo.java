package com.example.dexture.Repository;

import com.example.dexture.model.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepo extends CrudRepository<Buyer, Integer> {
    boolean existsBuyerByEmail(String email);

    boolean existsBuyerByEmailAndPassword(String email, String pw);

    Buyer getBuyerByEmailAndPassword(String email, String pw);
}
