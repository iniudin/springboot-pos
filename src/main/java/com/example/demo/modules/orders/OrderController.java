package com.example.demo.modules.orders;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public List<OrderResponse> getOrders() {
    return orderService.getOrders();
  }

  @GetMapping("/{id}")
  public OrderResponse getOrderById(@PathVariable Long id) {
    return orderService.getOrderById(id);
  }

  @PostMapping
  public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
    return orderService.createOrder(orderRequest);
  }

  @PutMapping("/{id}")
  public OrderResponse updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
    return orderService.updateOrder(id, orderRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderService.deleteOrder(id);
  }
}
