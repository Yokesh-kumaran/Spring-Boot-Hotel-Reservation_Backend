package com.restapi.repository;

import com.restapi.model.Amenity;
import com.restapi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    @Query("select a from AppUser a inner join a.roles r where r.id=1")
    List<AppUser> findAllUser();
}
