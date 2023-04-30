package ru.job4j.kitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.kitchen.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
