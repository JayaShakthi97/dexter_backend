package com.example.dexture.Repository;

import com.example.dexture.model.Summary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummaryRepository extends CrudRepository<Summary,Integer> {

   List<Summary> findAllByYearBetweenOrderByYear(int startYear, int endYear);

   List<Summary> findAllByYearOrderByPercentage(int year);

   Boolean existsByYear(int year);

   @Query("select summary.type from Summary summary group by summary.type")
   List<String> getAllTypes();

   List<Summary> getAllByType(String type);
}
