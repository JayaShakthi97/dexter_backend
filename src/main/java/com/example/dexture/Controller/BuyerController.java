package com.example.dexture.Controller;

import com.example.dexture.Service.BidService;
import com.example.dexture.Service.BuyerService;
import com.example.dexture.Service.HarvestService;
import com.example.dexture.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BuyerController {
    @Autowired
    HarvestService harvestService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    BidService bidService;

    @PostMapping()
    public ResponseEntity register(@RequestBody Buyer buyer) {
        if (buyerService.existsByEmail(buyer.getEmail())) {
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        } else {
            buyerService.createBuyer(buyer);
            return new ResponseEntity<>(new ResBody("Log In", true), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity farmerLogin(@RequestBody Login login) {
        if (buyerService.isBuyer(login.email, login.password)) {
            Buyer buyer = buyerService.getBuyer(login.email, login.password);
            return new ResponseEntity<>(new ResBody("Buyer token", buyer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/harvest")
    public ResponseEntity getAllHarvest(@PathVariable int id) { 
        return ResponseEntity.ok(harvestService.getAll(id));
    }

    @PostMapping("/bid")
    public ResponseEntity createBid(@RequestBody Bid bid) {
        Buyer buyer = buyerService.getById(bid.getBuyerId());
        if (buyer == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        Harvest harvest = harvestService.getById(bid.getHarvestId());
        if (harvest == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        bidService.createBid(buyer, harvest, bid);
        return new ResponseEntity<>(new ResBody("Done", true), HttpStatus.OK);
    }

    @DeleteMapping("/bid/{id}")
    public ResponseEntity deleteBid(@PathVariable int id) {
        Bid bid = bidService.getById(id);
        if (bid == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        bidService.delete(bid);
        return new ResponseEntity<>(new ResBody("Deleted", true), HttpStatus.OK);
    }

    @GetMapping("/{id}/bids")
    public ResponseEntity getBids(@PathVariable int id) {
        Buyer buyer = buyerService.getById(id);
        if (buyer == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(bidService.getByBuyer(buyer));
    }


}
