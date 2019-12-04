package com.example.dexture.Service;

import com.example.dexture.Repository.*;
import com.example.dexture.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SummaryService {

    @Autowired
    HarvestRepository harvestRepository;

    @Autowired
    BidRepo bidRepo;

    @Autowired
    SummaryRepository summaryRepository;

    @Autowired
    HarvestSummaryService harvestSummaryService;

    @Autowired
    FutureCultivationService futureCultivationService;

    @Autowired
    FutureSummaryRepo futureSummaryRepo;

    @Autowired
    ExpectedCultivationRepo expectedCultivationRepo;

    @Autowired
    HarvestSummaryRepo harvestSummaryRepo;

    @Scheduled(fixedRate = 31556952) //once per year
    public void addYearSummary() {
        Date stdate = new Date();
        stdate.setMonth(Calendar.JANUARY);
        stdate.setDate(1);
        //stdate.setYear(stdate.getYear()-1); //for initial data saving
        Date endDate = new Date();
        endDate.setMonth(Calendar.DECEMBER);
        endDate.setDate(31);
        //endDate.setYear(stdate.getYear()-1); //for initial data saving
        if (!(summaryRepository.existsByYear(stdate.getYear() + 1900))) {
            List<String> types = harvestRepository.findByCreatedAtBetween(stdate, endDate);

            for (String type : types) {
                //System.out.println(type);
                Summary summary = new Summary();
                summary.setYear(stdate.getYear() + 1900);
                summary.setType(type);
                int production = harvestRepository.findByCreatedAtBetweenAndTypeQuery(stdate, endDate, type);

                int demand;
                try {
                    demand = bidRepo.findByCreatedAtBetweenAndTypeQuery(stdate, endDate, type);
                } catch (Exception e) {
                    demand = 0;
                }

                if (production > demand) {
                    summary.setTag("Excess");
                } else {
                    summary.setTag("Shortage");
                }

                summary.setPercentage(((production - demand) * 100 / production));
                //System.out.println("Type = "+summary.getType()+" production = " + production + " Demand = "+ demand + " tag = "+summary.getTag()+" Percentage = "+summary.getPercentage()+"%" +" Year = "+ summary.getYear());
                //System.out.println(summary);
                try {
                    summaryRepository.save(summary);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

    }

    public List<String> getAllTypes() {
        return harvestRepository.getAllTypes();
    }

    public List<String> getAllSummaryTypes() {
        return summaryRepository.getAllTypes();
    }

    public List<Summary> getSummaryFromType(String type) {
        return summaryRepository.getAllByType(type);
    }

    public List<Summary> getSummaryForPastYears(int years) {
        Date current = new Date();
        int year = current.getYear() + 1900;
        System.out.println(year);
        return summaryRepository.findAllByYearBetweenOrderByYear(year - years, year);
    }

    public List<Summary> getPastYearSummary() {
        Date current = new Date();
        int year = current.getYear() + 1900 - 1;
        return summaryRepository.findAllByYearOrderByPercentage(year);
    }

    /*public List<PredictionSummary> getFutureAndCurrentSummary(int year) {
        List<List> harvestSummaryList = harvestSummaryService.getQuantitySumForPrediction(year - 1);
        List<PredictionSummary> predictionSummaryList = new ArrayList<>();
        for (List elem : harvestSummaryList) {
            String type = elem.get(1).toString();
            int q = Integer.parseInt(elem.get(0).toString());
            predictionSummaryList.add(new PredictionSummary(type, q));
        }

        List<PredictionSummary> newPredictionSumList = new ArrayList<>();
        List<List> futureSummaryList = futureCultivationService.getFutureSummary(year);
        for (List elem : futureSummaryList){
            String type = elem.get(1).toString();
            int q = Integer.parseInt(elem.get(0).toString());
            boolean found = false;
            for (PredictionSummary predictionSummary : predictionSummaryList){
                if (predictionSummary.getType().equals(type)){
                    newPredictionSumList.add(new PredictionSummary(type, predictionSummary.getCurrentQ(), q));
                    found = true;
                    break;
                }else {
                    newPredictionSumList.add(new PredictionSummary(type, predictionSummary.getCurrentQ()));
                }
            }
            if (!found){

            }
        }
    }*/

    public List getFutureAndCurrentSummary(int year) {
        return futureSummaryRepo.futureCurrentSummary(year, year - 1);
    }

    public List expectedAndCurrent(int year) {
        List<ExpectedCultivation> expectedCultivations = expectedCultivationRepo.getAllByExpectedYearOrderByType(year);
        List<FutureCultivationSummary> harvestSummaries = futureSummaryRepo.getAllByYearOrderByType(year);

        List<PredictionSummary> predictionSummaryList = new ArrayList<>();
        expectedCultivations.forEach(expectedCultivation -> {
            PredictionSummary predictionSummary = new PredictionSummary(
                    expectedCultivation.getType(), expectedCultivation.getExpectedQuantity());
            boolean found = false;
            for (FutureCultivationSummary futureCultivationSummary : harvestSummaries) {
                if (futureCultivationSummary.getType().equals(predictionSummary.getType())) {
                    predictionSummary.setCurrentQ(futureCultivationSummary.getTotalQuantity());
                    futureCultivationSummary.setExpected(true);
                    predictionSummaryList.add(predictionSummary);
                    found = true;
                    break;
                }
            }
            if (!found)
                predictionSummaryList.add(predictionSummary);
        });

        for (FutureCultivationSummary futureCultivationSummary : harvestSummaries) {
            if (!(futureCultivationSummary.isExpected()))
                predictionSummaryList.add(new PredictionSummary(
                        futureCultivationSummary.getType(), futureCultivationSummary.getTotalQuantity(), 0));
        }

        for (PredictionSummary predictionSummary : predictionSummaryList) {
            if (predictionSummary.getExpectedQ() > 0) {
                float percentage = (float) (((predictionSummary.getExpectedQ() - (double) predictionSummary.getCurrentQ()) / predictionSummary.getExpectedQ()) * 100);
                predictionSummary.setPercentage(percentage);
            } else {
                predictionSummary.setPercentage((float) 100.0);
            }
        }

        return predictionSummaryList;
    }


}
