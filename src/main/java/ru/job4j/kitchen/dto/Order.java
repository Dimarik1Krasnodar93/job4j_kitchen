package ru.job4j.kitchen.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private int id;
    private int dishId;
    private String name;
    private Status status;
    private boolean cancelled;
}
