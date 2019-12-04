package com.example.dexture.Controller;

import com.example.dexture.Service.*;
import com.example.dexture.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FarmerController {
    @Autowired
    FarmerService farmerService;
    @Autowired
    LandService landService;
    @Autowired
    HarvestService harvestService;
    @Autowired
    BidService bidService;
    @Autowired
    FutureCultivationService futureCultivationService;

    @PostMapping()
    public ResponseEntity register(@RequestBody Farmer farmer) {
        if (farmerService.existsByEmail(farmer.getEmail())) {
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        } else {
            farmerService.createFarmer(farmer);
            return new ResponseEntity<>(new ResBody("Wait for Approval", true), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity farmerLogin(@RequestBody Login login) {
        if (farmerService.isFarmer(login.email, login.password)) {
            Farmer farmer = farmerService.getFarmer(login.email, login.password);
            if (farmer.isAccepted())
                return new ResponseEntity<>(new ResBody("Farmer token", farmer), HttpStatus.OK);
            return new ResponseEntity<>(new ResBody(false), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/harvest")
    public ResponseEntity addHarvest(@RequestBody Harvest harvest) {
        Farmer farmer = farmerService.getById(harvest.getFarmerId());
        if (farmer == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        Land land = landService.getById(harvest.getLandId());
        if (land == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        System.out.println(land.getId());
        harvestService.addHarvest(harvest, farmer, land);
        return new ResponseEntity<>(new ResBody("Harvest Added", true), HttpStatus.OK);
    }

    @GetMapping("/{id}/harvest")
    public ResponseEntity getHarvest(@PathVariable int id) {
        Farmer farmer = farmerService.getById(id);
        if (farmer == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(harvestService.getAllHarvest(farmer));
    }

    @DeleteMapping("/harvest/{id}")
    public ResponseEntity deleteHarvest(@PathVariable int id) {
        Harvest harvest = harvestService.getById(id);
        if (harvest == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        harvestService.delete(harvest);
        return ResponseEntity.ok(new ResBody("Deleted", true));
    }

    @PutMapping("/harvest")
    public ResponseEntity update(@RequestBody Harvest harvest) {
        Harvest existing = harvestService.getById(harvest.getId());
        if (existing == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(harvestService.update(existing, harvest));
    }

    @GetMapping("/{id}/bids")
    public ResponseEntity getFarmerBids(@PathVariable int id) {
        Farmer farmer = farmerService.getById(id);
        if (farmer == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(bidService.getAllFarmerBids(farmer));
    }

    @GetMapping("/harvest/{id}/bids")
    public ResponseEntity getBids(@PathVariable int id) {
        Harvest harvest = harvestService.getById(id);
        if (harvest == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(bidService.getByHarvest(harvest));
    }

    @PutMapping("/bid")
    public ResponseEntity updateBid(@RequestBody Bid bid) {
        Bid existingBid = bidService.getById(bid.getId());
        if (existingBid == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        bid.setHarvest(existingBid.getHarvest());
        bid.setBuyer(existingBid.getBuyer());
        bidService.updateBid(bid);
        harvestService.setSold(bid.getHarvest());
        return new ResponseEntity<>(new ResBody("Updated", true), HttpStatus.OK);
    }

    @GetMapping("/{id}/futureCultivations")
    public ResponseEntity getFarmerFutureCultivations(@PathVariable int id) {
        return ResponseEntity.ok(futureCultivationService.getByFarmer(id));
    }

    @PostMapping("/future")
    public ResponseEntity createFutureCultivation(@RequestBody FutureCultivation futureCultivation) {
        return ResponseEntity.ok(futureCultivationService.create(futureCultivation));
    }

   /* @GetMapping("/future/{year}")
    public ResponseEntity getFutureAndCurrentSummary(@PathVariable int year) {
        return ResponseEntity.ok(futureCultivationService.getSumFutureQuantity(year));
    }*/

//   @GetMapping("/summar")
}
