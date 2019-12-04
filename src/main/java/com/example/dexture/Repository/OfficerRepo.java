package com.example.dexture.Repository;

import com.example.dexture.model.Officer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficerRepo extends CrudRepository<Officer, Integer> {
}
