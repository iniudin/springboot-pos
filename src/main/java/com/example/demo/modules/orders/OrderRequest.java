package com.example.demo.modules.orders;

import java.util.List;

public record OrderRequest(
  String customer,
  List<OrderItemRequest> orderItems
) {
}
