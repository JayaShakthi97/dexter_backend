package com.example.dexture.Repository;

import com.example.dexture.model.Farmer;
import com.example.dexture.model.FutureCultivation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FutureCultivationRepo extends CrudRepository<FutureCultivation, Integer> {

    List<FutureCultivation> getAllByFarmer(Farmer farmer);

    @Query("select sum(f.quantity), f.type from FutureCultivation f where f.year = ?1 group by f.type")
    List<List> getSumOfQuantity(int year);
}
