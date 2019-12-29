package com.example.dexture.Controller;

import com.example.dexture.Service.FutureSummaryService;
import com.example.dexture.Service.HarvestSummaryService;
import com.example.dexture.Service.SummaryService;
import com.example.dexture.model.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/summary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SummaryController {

    @Autowired
    SummaryService summaryService;
    @Autowired
    HarvestSummaryService harvestSummaryService;
    @Autowired
    FutureSummaryService futureSummaryService;

    @GetMapping("/types")
    public List<String> getAllTypes() {
        return harvestSummaryService.getAllSummaryTypes();
    }

    @GetMapping("/{type}")
    public List getSummaryOfType(@PathVariable String type) {
        return harvestSummaryService.getTypeSummary(type);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity getFutureAndCurrentSummary(@PathVariable int year) {
        return ResponseEntity.ok(summaryService.getFutureAndCurrentSummary(year));
    }

    @GetMapping("/harvest/{year}")
    public ResponseEntity getHarvestSummary(@PathVariable int year) {
        return ResponseEntity.ok(harvestSummaryService.getQuantitySumForPrediction(year));
    }

    @GetMapping("/future/{year}")
    public ResponseEntity getFutureSummary(@PathVariable int year) {
        return ResponseEntity.ok(futureSummaryService.getFutureSummary(year));
    }

    @GetMapping("/coverage/{year}")
    public ResponseEntity expectedAndCurrent(@PathVariable int year) {
        return ResponseEntity.ok(summaryService.expectedAndCurrent(year));
    }

    /*@GetMapping("/join/{year}")
    public ResponseEntity getJoinSummary(@PathVariable int year){
        return ResponseEntity.ok(harvestSummaryService.joinSummary(year));
    }*/
}
