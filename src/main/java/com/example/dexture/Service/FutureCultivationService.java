package com.example.dexture.Service;

import com.example.dexture.Repository.FutureCultivationRepo;
import com.example.dexture.model.Farmer;
import com.example.dexture.model.FutureCultivation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FutureCultivationService {
    @Autowired
    FutureCultivationRepo futureCultivationRepo;
    @Autowired
    FarmerService farmerService;
    @Autowired
    FutureSummaryService futureSummaryService;

    public FutureCultivation create(FutureCultivation futureCultivation) {
        futureCultivation.setType(futureCultivation.getType().toLowerCase());
        futureCultivation.setFarmer(farmerService.getById(futureCultivation.getFarmerId()));
        futureSummaryService.add(futureCultivation);
        return futureCultivationRepo.save(futureCultivation);
    }

    public List<List> getFutureSummary(int year) {
        return futureCultivationRepo.getSumOfQuantity(year);
    }

    public List getByFarmer(int farmerId) {
        Farmer farmer = farmerService.getById(farmerId);
        return futureCultivationRepo.getAllByFarmer(farmer);
    }
}
