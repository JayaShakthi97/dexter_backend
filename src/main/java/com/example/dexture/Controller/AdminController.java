package com.example.dexture.Controller;

import com.example.dexture.Service.AdminService;
import com.example.dexture.Service.FarmerService;
import com.example.dexture.model.ExpectedCultivation;
import com.example.dexture.model.Login;
import com.example.dexture.model.ResBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private FarmerService farmerService;

    @GetMapping("/admin/values")
    public String testing() {
        return ("Admin working success *_*");
    }

    @PostMapping("/admin/login")
    public ResponseEntity logIn(@RequestBody Login login) {
        boolean valid = adminService.isAdmin(login.email, login.password);
        System.out.println(valid);
        if (valid) {
            return ResponseEntity.ok(new ResBody("Admin token"));
        } else
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/farmer")
    public ResponseEntity getAllFarmers() {
        return ResponseEntity.ok(farmerService.getAll());
    }

    @PutMapping("/farmer/approve/{id}")
    public ResponseEntity approveFarmer(@PathVariable int id) {
        if (farmerService.approve(id)) {
            return new ResponseEntity<>(new ResBody("Approved", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResBody("Invalid", true), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/expected")
    public ResponseEntity createExpectedCultivation(@RequestBody ExpectedCultivation expectedCultivation) {
        if (adminService.addExpectedCultivation(expectedCultivation)) {
            return new ResponseEntity<>(new ResBody("Created", true), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new ResBody("Failed", true), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/expected")
    public ResponseEntity getExpectedCultivations() {
        return ResponseEntity.ok(adminService.getExpectedCulties());
    }

    @PutMapping("/expected")
    public ResponseEntity editExpectedCultivation(@RequestBody ExpectedCultivation expectedCultivation) {
        adminService.editExpectedCultivation(expectedCultivation);
        return ResponseEntity.ok(new ResBody("Done", true));
    }
}
