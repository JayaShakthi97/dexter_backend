package com.example.dexture.Service;

import com.example.dexture.Repository.FarmerRepository;
import com.example.dexture.model.Farmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmerService {
    @Autowired
    FarmerRepository farmerRepository;

    public Farmer getById(int id) {
        if (farmerRepository.findById(id).isPresent())
            return farmerRepository.findById(id).get();
        return null;
    }

    public List<Farmer> getAll() {
        Iterable<Farmer> farmers = farmerRepository.findAll();
        List<Farmer> farmerList = new ArrayList<>();
        for (Farmer farmer : farmers) farmerList.add(farmer);
        return farmerList;
    }

    public boolean existsByEmail(String email){
        return farmerRepository.existsFarmerByEmail(email);
    }

    public void createFarmer(Farmer farmer) {
        farmerRepository.save(farmer);
    }

    public boolean isFarmer(String email, String password) {
        return farmerRepository.existsFarmerByEmailAndPassword(email, password);
    }

    public Farmer getFarmer(String email, String password) {
        return farmerRepository.getFarmerByEmailAndPassword(email, password);
    }

    public boolean approve(int id) {
        Farmer farmer = this.getById(id);
        if (farmer == null)
            return false;
        farmer.setAccepted(true);
        farmerRepository.save(farmer);
        return true;
    }
}
