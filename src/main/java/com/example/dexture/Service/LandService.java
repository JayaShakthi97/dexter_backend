package com.example.dexture.Service;

import com.example.dexture.Repository.FarmerRepository;
import com.example.dexture.Repository.HarvestRepository;
import com.example.dexture.Repository.LandRepository;
import com.example.dexture.model.Farmer;
import com.example.dexture.model.Harvest;
import com.example.dexture.model.Land;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LandService {
    @Autowired
    LandRepository landRepository;
    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    HarvestRepository harvestRepository;
    @Autowired
    HarvestService harvestService;

    public void delete(Land land) {
        this.getHarvest(land).forEach(harvest -> harvestService.delete(harvest));
        landRepository.delete(land);
    }

    public Land getById(int id) {
        return landRepository.findById(id).isPresent() ? landRepository.findById(id).get() : null;
    }

    public void addLand(Land land, Farmer farmer) {
        land.setFarmer(farmer);
        landRepository.save(land);
    }

    public List<Land> getAll(Farmer farmer) {
        return landRepository.getAllByFarmer(farmer);
    }

    public List<Harvest> getHarvest(Land land) {
        return harvestRepository.getAllByLand(land);
    }
}
