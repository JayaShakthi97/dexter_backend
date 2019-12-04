package com.example.dexture.Service;

import com.example.dexture.Repository.AdminRepository;
import com.example.dexture.Repository.ExpectedCultivationRepo;
import com.example.dexture.model.ExpectedCultivation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ExpectedCultivationRepo expectedCultivationRepo;

    public boolean isAdmin(String email, String password) {
        return adminRepository.existsAdminByEmailAndPassword(email, password);
    }

    public boolean addExpectedCultivation(ExpectedCultivation expectedCultivation) {
        String type = expectedCultivation.getType();
        int year = expectedCultivation.getExpectedYear();
        if (expectedCultivationRepo.existsByTypeAndExpectedYear(type, year)) {
            return false;
        } else {
            expectedCultivationRepo.save(expectedCultivation);
            return true;
        }
    }

    public List<ExpectedCultivation> getExpectedCulties() {
        List<ExpectedCultivation> list = new ArrayList<>();
        expectedCultivationRepo.findAll().forEach(list::add);
        return list;
    }

    public void editExpectedCultivation(ExpectedCultivation expectedCultivation) {
        expectedCultivationRepo.save(expectedCultivation);
    }
}
