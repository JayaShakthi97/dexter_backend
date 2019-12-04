package com.example.dexture.Repository;

import com.example.dexture.model.Farmer;
import com.example.dexture.model.Harvest;
import com.example.dexture.model.Land;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HarvestRepository extends CrudRepository<Harvest, Integer> {
    List<Harvest> getAllByFarmerOrderByLand(Farmer farmer);

    List<Harvest> getAllByLand(Land land);

    void deleteAllByLand(Land land);

    @Query(value = "select harvest from Harvest harvest where harvest.status = 1 order by harvest.updatedAt desc, harvest.createdAt desc")
    List<Harvest> getAllForBuyer();

    @Query(value = "select h.type from Harvest h group by h.type")
    List<String> findByCreatedAtBetween(Date st, Date end);

    @Query(value = "select sum(h.quantity) from Harvest h where h.type=:typ and h.createdAt>:st and h.createdAt<:endDate")
    int findByCreatedAtBetweenAndTypeQuery(@Param("st") Date st, @Param("endDate") Date endDate, @Param("typ") String type);

    @Query("select harvest.type from Harvest harvest group by harvest.type")
    List<String> getAllTypes();

    @Query("select sum(h.quantity), h.type from Harvest h where h.createdAt >= ?1 and h.createdAt < ?2 group by h.type")
    List<List> getQuantitySumInYear(Date yearFirstDate, Date nextYearFirstDate);
}
