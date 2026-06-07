package com.furkan.orderservice.infrastructure.adapter.out.persistence;

import com.furkan.orderservice.domain.model.Order;
import com.furkan.orderservice.domain.port.out.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        OrderEntity savedEntity = orderRepository.save(entity);
        return OrderMapper.toDomain(savedEntity);
    }
}
