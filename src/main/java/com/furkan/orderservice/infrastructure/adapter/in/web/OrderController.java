package com.furkan.orderservice.infrastructure.adapter.in.web;

import com.furkan.orderservice.domain.command.CreateOrderCommand;
import com.furkan.orderservice.domain.model.Order;
import com.furkan.orderservice.domain.port.in.CreateOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            Authentication auth
    ) {
        CreateOrderCommand command = new CreateOrderCommand(
                request.productName(),
                request.quantity(),
                request.price(),
                auth.getName()
        );

        Order order = createOrderUseCase.createOrder(command);

        return new OrderResponse(
                order.getId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getCreatedBy(),
                order.getCreatedAt()
        );
    }
}
