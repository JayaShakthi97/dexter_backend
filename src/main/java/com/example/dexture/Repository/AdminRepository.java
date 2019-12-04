package com.example.dexture.Repository;

import com.example.dexture.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
    boolean existsAdminByEmailAndPassword(String email, String pw);

    Admin getByEmail(String email);
}
