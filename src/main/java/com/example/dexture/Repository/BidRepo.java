package com.example.dexture.Repository;

import com.example.dexture.model.Bid;
import com.example.dexture.model.Buyer;
import com.example.dexture.model.Farmer;
import com.example.dexture.model.Harvest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BidRepo extends CrudRepository<Bid, Integer> {
    List<Bid> getAllByBuyer(Buyer buyer);

    List<Bid> getAllByHarvest(Harvest harvest);

    @Query(value = "select sum(b.harvest.quantity) from Bid b where b.harvest.type=:typ and b.createdAt>:st and b.createdAt<:endDate")
    int findByCreatedAtBetweenAndTypeQuery(@Param("st") Date st, @Param("endDate") Date endDate, @Param("typ") String type);

    @Query("select bid from Bid bid where bid.harvest.farmer = ?1 and bid.status = 0 or bid.status = 1 order by bid.createdAt desc")
    List<Bid> getAllFarmerBids(Farmer farmer);

    boolean existsByHarvestAndBuyer(Harvest harvest, Buyer buyer);

    void deleteAllByHarvest(Harvest harvest);
}
