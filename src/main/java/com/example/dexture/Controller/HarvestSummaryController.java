package com.example.dexture.Controller;

import com.example.dexture.Service.HarvestSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvestSummary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HarvestSummaryController {
    @Autowired
    HarvestSummaryService harvestSummaryService;

    @GetMapping("/{year}")
    public ResponseEntity getQuantitySumForPrediction(@PathVariable int year) {
        return ResponseEntity.ok(harvestSummaryService.getQuantitySumForPrediction(year));
    }
}
