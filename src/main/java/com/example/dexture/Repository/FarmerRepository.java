package com.example.dexture.Repository;

import com.example.dexture.model.Farmer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Integer> {
    boolean existsFarmerByEmailAndPassword(String email, String pw);

    Farmer getFarmerByEmailAndPassword(String email, String pw);

    boolean existsFarmerByEmail(String email);
}
