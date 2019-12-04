package com.example.dexture.Service;

import com.example.dexture.Repository.FutureCultivationRepo;
import com.example.dexture.Repository.FutureSummaryRepo;
import com.example.dexture.model.FutureCultivation;
import com.example.dexture.model.FutureCultivationSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FutureSummaryService {
    @Autowired
    FutureSummaryRepo futureSummaryRepo;
    @Autowired
    FutureCultivationRepo futureCultivationRepo;

    public void add(FutureCultivation futureCultivation) {
        String type = futureCultivation.getType();
        int year = futureCultivation.getYear();
        if (futureSummaryRepo.existsByTypeAndYear(type, year)) {
            FutureCultivationSummary futureCultivationSummary = futureSummaryRepo.getByTypeAndYear(type, year);
            futureCultivationSummary.add(futureCultivation.getQuantity());
            futureSummaryRepo.save(futureCultivationSummary);
        }
        futureSummaryRepo.save(new FutureCultivationSummary(futureCultivation));
    }

    public List getFutureSummary(int year) {
        return futureCultivationRepo.getSumOfQuantity(year);
    }
}
