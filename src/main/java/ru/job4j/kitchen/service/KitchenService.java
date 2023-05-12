package ru.job4j.kitchen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.domain.Order;
import ru.job4j.kitchen.dto.Status;
import ru.job4j.kitchen.repository.OrderRepository;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class KitchenService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    OrderRepository orderRepository;
    private static final int DISH_TO_CANCEL = 6;
    @KafkaListener(topics = "job4j_orders_2")
    public void receiveOrder(ru.job4j.kitchen.dto.Order orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setDishId(orderDto.getDishId());
        List<Order> listOrder = orderRepository.findOrdersByDishId(order.getDishId());
        if (listOrder.size() == 0) {
            order.setStatus(Status.MADE);
            orderRepository.save(order);
        } else {
            if (order.getDishId() == DISH_TO_CANCEL) {
                order.setStatus(Status.CANCEL);
            } else {
                order.setStatus(Status.CREATED);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            kafkaTemplate.send("cooked_order", objectMapper.writeValueAsString(order));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
