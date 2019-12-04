package com.example.dexture.Service;

import com.example.dexture.Repository.HarvestRepository;
import com.example.dexture.model.Buyer;
import com.example.dexture.model.Farmer;
import com.example.dexture.model.Harvest;
import com.example.dexture.model.Land;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HarvestService {
    @Autowired
    HarvestRepository harvestRepository;
    @Autowired
    BuyerService buyerService;
    @Autowired
    BidService bidService;
    @Autowired
    HarvestSummaryService harvestSummaryService;

    public void delete(Harvest harvest) {
        bidService.deleteByHarvest(harvest);
        harvestRepository.delete(harvest);
        harvestSummaryService.onRemoveHarvest(harvest);
    }

    public Harvest getById(int id) {
        return harvestRepository.findById(id).isPresent() ? harvestRepository.findById(id).get() : null;
    }

    public void addHarvest(Harvest harvest, Farmer farmer, Land land) {
        harvest.setType(harvest.getType().toLowerCase());
        harvest.setFarmer(farmer);
        harvest.setLand(land);
        harvestRepository.save(harvest);
        harvestSummaryService.onAddHarvest(harvest);
    }

    public List getAllHarvest(Farmer farmer) {
        return harvestRepository.getAllByFarmerOrderByLand(farmer);
    }

    public List getAll(int buyerId) {
        Buyer buyer = buyerService.getById(buyerId);
        List<Harvest> harvests = harvestRepository.getAllForBuyer();
        List<Harvest> result = new ArrayList<>();
        for (Harvest harvest : harvests) {
            if (!(bidService.existsByBuyerAndHarvest(buyer, harvest)))
                result.add(harvest);
        }
        return result;
    }

    public Harvest update(Harvest harvest, Harvest newHarvest) {
        newHarvest.setLand(harvest.getLand());
        newHarvest.setFarmer(harvest.getFarmer());
        return harvestRepository.save(newHarvest);
    }

    public void setSold(Harvest harvest) {
        harvest.setStatus((short) 2);
        harvestRepository.save(harvest);
    }
}
