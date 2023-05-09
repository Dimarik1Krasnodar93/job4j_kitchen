package ru.job4j.kitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.kitchen.domain.Order;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer>  {

    Order findByDishId(int dishId);
   // @Query(value = "select * from job4j_orders as o where o.dishId = :dishId",
     //       nativeQuery = true)
    @Query(value = "select O from Order as O where O.dishId = :dishId")
    List<Order> findOrdersByDishId(@Param("dishId") int dishId);
}
