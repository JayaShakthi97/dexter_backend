package com.example.dexture.Service;

import com.example.dexture.Repository.BidRepo;
import com.example.dexture.model.Bid;
import com.example.dexture.model.Buyer;
import com.example.dexture.model.Farmer;
import com.example.dexture.model.Harvest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {
    @Autowired
    BidRepo bidRepo;

    public Bid getById(int id) {
        return bidRepo.findById(id).isPresent() ? bidRepo.findById(id).get() : null;
    }

    public void createBid(Buyer buyer, Harvest harvest, Bid bid) {
        bid.setBuyer(buyer);
        bid.setHarvest(harvest);
        bidRepo.save(bid);
    }

    public List getByHarvest(Harvest harvest) {
        return bidRepo.getAllByHarvest(harvest);
    }

    public List getByBuyer(Buyer buyer) {
        return bidRepo.getAllByBuyer(buyer);
    }

    public void delete(Bid bid) {
        bidRepo.delete(bid);
    }

    public boolean existsByBuyerAndHarvest(Buyer buyer, Harvest harvest){
        return bidRepo.existsByHarvestAndBuyer(harvest, buyer);
    }

    public void updateBid(Bid bid){
        bidRepo.save(bid);
    }

    public List getAllFarmerBids(Farmer farmer){
        return bidRepo.getAllFarmerBids(farmer);
    }

    public void deleteByHarvest(Harvest harvest){
        bidRepo.deleteAllByHarvest(harvest);
    }
}
