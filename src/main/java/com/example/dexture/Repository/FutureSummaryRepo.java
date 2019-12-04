package com.example.dexture.Repository;

import com.example.dexture.model.FutureCultivationSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FutureSummaryRepo extends CrudRepository<FutureCultivationSummary, Integer> {
    List<FutureCultivationSummary> getAllByYearOrderByType(int year);

    boolean existsByTypeAndYear(String type, int year);

    FutureCultivationSummary getByTypeAndYear(String type, int year);

    @Query(value = "select new PredictionSummary(h.type, h.totalQuantity, f.totalQuantity) from HarvestSummary h, FutureCultivationSummary f" +
            " where h.year = ?2 and f.year = ?1", nativeQuery = true)
    List futureCurrentSummary(int year, int year2);
}
