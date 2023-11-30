package com.restapi.repository;

import com.restapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o inner join o.appUser a where a.id=?1")
    Optional<List<Order>> findUserOrder(Long userId);

    @Query(value = "SELECT o FROM Order o " +
            "LEFT JOIN room r ON r.id = o.room_id " +
            "WHERE (o.check_in_date > :end_date OR o.check_out_date < :start_date)",
            nativeQuery = true)
    List<Order> findByAvailablity(@Param("start_date") Date check_in_date, @Param("end_date") Date check_out_date);
}
