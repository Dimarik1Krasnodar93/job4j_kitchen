package ru.job4j.kitchen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.domain.Order;
import ru.job4j.kitchen.repository.OrderRepository;

@AllArgsConstructor
@Service
@Slf4j
public class KitchenService {
    OrderRepository orderRepository;
    @KafkaListener(topics = "job4j_orders2")
    public void receiveOrder(String orderStr) {
        Gson gson = new GsonBuilder().create();
        Order order = gson.fromJson(orderStr, Order.class);
        orderRepository.save(order);
    }
}
