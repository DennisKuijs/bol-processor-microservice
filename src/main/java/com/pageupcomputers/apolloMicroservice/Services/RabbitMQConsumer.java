package com.pageupcomputers.apolloMicroservice.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pageupcomputers.apolloMicroservice.DTO.OrderDTO;

@Service
public class RabbitMQConsumer {
    
    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    /**
     * RabbitMQ Listener that processed messages that are being pushed to the queue.
     * @param orderMessage
     */
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeOrder(OrderDTO orderMessage) {
        try {
            logger.info(String.format("Received order %s", orderMessage.getOrderId()));
            OrderDTO order = orderService.processOrder(orderMessage);
            logger.info(String.format("Order %s successfully processed", order.getOrderId()));
        } 
        catch (Exception e) {
            logger.error(String.format(e.getMessage() + "Order %s", orderMessage.getOrderId()));
        }
    }
}
