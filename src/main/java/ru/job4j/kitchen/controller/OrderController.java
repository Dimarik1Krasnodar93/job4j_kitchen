package ru.job4j.kitchen.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.job4j.kitchen.domain.Order;
import ru.job4j.kitchen.service.KitchenService;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    KitchenService kitchenService;
    @PostMapping("/create")
    public void create(@RequestBody Order order) {
        //kitchenService.receiveOrder(order);
    }

}
