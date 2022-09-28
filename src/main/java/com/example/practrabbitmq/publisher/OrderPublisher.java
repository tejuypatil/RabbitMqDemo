package com.example.practrabbitmq.publisher;

import com.example.practrabbitmq.config.MessagingConfig;
import com.example.practrabbitmq.dto.Order;
import com.example.practrabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order,"Process","order placed successfully in" +restaurantName);
       template.convertAndSend("EXCHANGE1","ROUTING_KEY1",orderStatus);
        return "Success !!";
    }
}
