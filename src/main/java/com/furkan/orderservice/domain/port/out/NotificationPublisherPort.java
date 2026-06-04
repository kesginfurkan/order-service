package com.furkan.orderservice.domain.port.out;

import com.furkan.orderservice.domain.model.Order;

public interface NotificationPublisherPort {
    void publishOrderCreated(Order order);
}
