package ru.job4j.kitchen.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "job4j_orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    @Column(name = "dishid")
    private int dishId;
}
