package com.furkan.orderservice.infrastructure.adapter.out.kafka;

import com.furkan.orderservice.domain.model.Order;
import com.furkan.orderservice.domain.port.out.NotificationPublisherPort;
import com.furkan.orderservice.infrastructure.adapter.out.kafka.event.OrderCreatedEvent;
import com.furkan.orderservice.infrastructure.config.KafkaTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderNotificationKafkaAdapter implements NotificationPublisherPort {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Override
    public void publishOrderCreated(Order order) {

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                order.getId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getCreatedBy(),
                order.getCreatedAt()
        );

        kafkaTemplate.send(
                KafkaTopics.ORDER_CREATED,
                order.getId().toString(),
                orderCreatedEvent
        );
    }
}
