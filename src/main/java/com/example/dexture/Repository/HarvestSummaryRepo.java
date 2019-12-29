package com.example.dexture.Repository;

import com.example.dexture.model.HarvestSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestSummaryRepo extends CrudRepository<HarvestSummary, Integer> {
    boolean existsByTypeAndYear(String type, int year);

    HarvestSummary getByTypeAndYear(String type, int year);

    List<HarvestSummary> getAllByYearOrderByType(int year);

    @Query("select distinct summary.type from HarvestSummary summary")
    List<String> getAllTypes();

    List<HarvestSummary> getAllByType(String type);

    /*@Query(value = "select harvest_summary.total_quantity as totalHarvest, future_cultivation_summary.total_quantity as totalFuture, " +
            "harvest_summary.type as harvest, future_cultivation_summary.type as future " +
            "from harvest_summary full join future_cultivation_summary on future_cultivation_summary.type = harvest_summary.type " +
            "where h.year = ?1 and f.year = ?2", nativeQuery = true)
    List joinSummary(int year1, int year2);*/

    /*@Query(value = "select total_quantity, total_quantity, type " +
            "from harvest_summary full join future_cultivation_summary on future_cultivation_summary.type = harvest_summary.type " +
            "where h.year = ?1 and f.year = ?2", nativeQuery = true)
    List joinSummary(int year1, int year2);*/
}
