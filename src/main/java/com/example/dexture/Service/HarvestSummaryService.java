package com.example.dexture.Service;

import com.example.dexture.Repository.HarvestRepository;
import com.example.dexture.Repository.HarvestSummaryRepo;
import com.example.dexture.model.Harvest;
import com.example.dexture.model.HarvestSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class HarvestSummaryService {
    @Autowired
    HarvestSummaryRepo harvestSummaryRepo;
    @Autowired
    HarvestRepository harvestRepository;
    private Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Colombo"));

    public void onAddHarvest(Harvest harvest) {
        String type = harvest.getType();

        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);

        int q = harvest.getQuantity();

        if (harvestSummaryRepo.existsByTypeAndYear(type, year)) {
            HarvestSummary harvestSummary = harvestSummaryRepo.getByTypeAndYear(type, year);
            harvestSummary.addHarvest(q);
            harvestSummaryRepo.save(harvestSummary);
        } else {
            HarvestSummary harvestSummary = new HarvestSummary(type, year, q);
            harvestSummaryRepo.save(harvestSummary);
        }
    }

    public void onRemoveHarvest(Harvest harvest) {
        String type = harvest.getType();

        cal.setTime(harvest.getCreatedAt());
        int year = cal.get(Calendar.YEAR);

        int q = harvest.getQuantity();

        if (harvestSummaryRepo.existsByTypeAndYear(type, year)) {
            HarvestSummary harvestSummary = harvestSummaryRepo.getByTypeAndYear(type, year);
            harvestSummary.removeHarvest(q);
            harvestSummaryRepo.save(harvestSummary);
        }
    }

    public List<List> getQuantitySumForPrediction(int year) {
        cal.set(year, 0, 1, 0, 0, 0);
        Date yearFirstDate = cal.getTime();
        cal.set(year + 1, 0, 1, 0, 0, 0);
        Date nextYearFirstDate = cal.getTime();
        return harvestRepository.getQuantitySumInYear(yearFirstDate, nextYearFirstDate);
    }

//    public List joinSummary(int year){
//        return harvestSummaryRepo.joinSummary(year, year + 1);
//    }
}
