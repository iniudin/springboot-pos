package com.example.demo.modules.orders;

import com.example.demo.modules.items.ItemResponse;

import lombok.Builder;

@Builder
public record OrderItemResponse(
        Long id,
        ItemResponse item,
        Integer quantity) {

    public static OrderItemResponse fromEntity(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .item(ItemResponse.fromEntity(orderItem.getItem()))
                .quantity(orderItem.getQuantity())
                .build();
    }
}
