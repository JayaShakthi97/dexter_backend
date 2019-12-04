package com.example.dexture.Repository;

import com.example.dexture.model.Farmer;
import com.example.dexture.model.Land;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LandRepository extends CrudRepository<Land, Integer> {

    List<Land> getAllByFarmer(Farmer farmer);
}
