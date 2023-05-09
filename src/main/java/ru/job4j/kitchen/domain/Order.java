package ru.job4j.kitchen.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.job4j.kitchen.dto.Status;

import javax.persistence.*;

@Entity
@Table(name = "job4j_orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    @Column(name = "dishid")
    private int dishId;
    private Status status;
}
