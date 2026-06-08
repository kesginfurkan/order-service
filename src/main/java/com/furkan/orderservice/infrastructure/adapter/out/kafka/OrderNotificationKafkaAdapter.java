package com.furkan.orderservice.infrastructure.adapter.out.kafka;

import com.furkan.orderservice.domain.model.Order;
import com.furkan.orderservice.domain.port.out.NotificationPublisherPort;
import com.furkan.orderservice.infrastructure.adapter.out.kafka.event.OrderCreatedEvent;
import com.furkan.orderservice.infrastructure.config.KafkaTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
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

        try {

            var result = kafkaTemplate.send(
                    KafkaTopics.ORDER_CREATED,
                    order.getId().toString(),
                    orderCreatedEvent
            ).get(5, TimeUnit.SECONDS);

            log.info(
                    "Order created event published. topic={}, partition={}, offset={}, orderId={}",
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset(),
                    order.getId()
            );

        } catch (Exception ex) {
            log.error("Failed to publish order created event. orderId={}", order.getId(), ex);
            throw new RuntimeException("Order created event could not be published", ex);
        }
    }
}
