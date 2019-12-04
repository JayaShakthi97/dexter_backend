package com.example.dexture.Repository;

import com.example.dexture.model.ExpectedCultivation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpectedCultivationRepo extends CrudRepository<ExpectedCultivation, Integer> {

    boolean existsByTypeAndExpectedYear(String type, int year);

    List<ExpectedCultivation> getAllByExpectedYearOrderByType(int year);

    ExpectedCultivation getByTypeAndExpectedYear(String type, int year);

    @Query(value = "select * from expected_cultivation full join harvest_summary on expected_cultivation.type = harvest_summary.type" +
            " where expected_year = ?1", nativeQuery = true)
    List expectedAndCurrent(int year);
}
