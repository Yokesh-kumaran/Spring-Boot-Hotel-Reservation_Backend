package com.restapi.repository;

import com.restapi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restapi.model.Room;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByPrice(Double price);

    Optional<List<Room>> findByCategoryId(Long categoryId);
}
