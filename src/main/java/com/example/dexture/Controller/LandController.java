package com.example.dexture.Controller;

import com.example.dexture.Service.FarmerService;
import com.example.dexture.Service.LandService;
import com.example.dexture.model.Farmer;
import com.example.dexture.model.Land;
import com.example.dexture.model.ResBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/land")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LandController {
    @Autowired
    private LandService landService;
    @Autowired
    private FarmerService farmerService;

    @PostMapping()
    public ResponseEntity addLand(@RequestBody Land land) {
        Farmer farmer = farmerService.getById(land.getFarmerId());
        if (farmer == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        landService.addLand(land, farmer);
        return new ResponseEntity<>(new ResBody("Land Added", true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getLands(@PathVariable int id) {
        Farmer farmer = farmerService.getById(id);
        if (farmer == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(landService.getAll(farmer));
    }

    @GetMapping("/{id}/harvest")
    public ResponseEntity getLandHarvest(@PathVariable int id) {
        Land land = landService.getById(id);
        if (land == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(landService.getHarvest(land));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLand(@PathVariable int id) {
        Land land = landService.getById(id);
        if (land == null)
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        landService.delete(land);
        return new ResponseEntity<>(new ResBody("Deleted", true), HttpStatus.OK);
    }
}
