package com.example.demo.modules.orders;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record OrderResponse(
  Long id,
  String customer,
  List<OrderItemResponse> orderItems
) {

  public static OrderResponse fromEntity(Order order) {
    return OrderResponse.builder()
      .id(order.getId())
      .customer(order.getCustomer())
      .orderItems(order.getOrderItems().stream()
        .map(OrderItemResponse::fromEntity)
        .collect(Collectors.toList()))
      .build();
  }
}