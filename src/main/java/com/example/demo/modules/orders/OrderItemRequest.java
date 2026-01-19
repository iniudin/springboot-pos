package com.example.demo.modules.orders;

public record OrderItemRequest(
        Long itemId,
        Integer quantity) {
}
