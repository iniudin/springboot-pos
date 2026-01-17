package com.example.demo.modules.orders;

import com.example.demo.modules.items.Item;
import com.example.demo.modules.items.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final ItemRepository itemRepository;

  public OrderService(
    OrderRepository orderRepository,
    ItemRepository itemRepository
  ) {
    this.orderRepository = orderRepository;
    this.itemRepository = itemRepository;
  }

  public OrderResponse getOrderById(Long id) {
    Order order = orderRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    
    return OrderResponse.fromEntity(order);
  }

  public List<OrderResponse> getOrders() {
    List<Order> orders = orderRepository.findAll();
    
    return orders.stream()
      .map(OrderResponse::fromEntity)
      .collect(Collectors.toList());
  }

  public OrderResponse createOrder(OrderRequest orderRequest) {
    Order order = Order.builder()
      .customer(orderRequest.customer())
      .build();

    orderRequest.orderItems().forEach(orderItemRequest -> {
      Item item = itemRepository
        .findById(orderItemRequest.itemId())
        .orElseThrow(() -> new RuntimeException("Item not found: " + orderItemRequest.itemId()));
      
      order.addOrderItem(
        OrderItem.builder()
          .order(order)
          .item(item)
          .quantity(orderItemRequest.quantity())
          .build()
      );
    });

    Order savedOrder = orderRepository.save(order);
    return OrderResponse.fromEntity(savedOrder);
  }

  public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
    Order order = orderRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    
    order.setCustomer(orderRequest.customer());
    processOrderItems(order, orderRequest.orderItems());

    Order updatedOrder = orderRepository.save(order);
    return OrderResponse.fromEntity(updatedOrder);
  }

  public void deleteOrder(Long id) {
    orderRepository.deleteById(id);  
  }

  private void processOrderItems(
    Order order,
    List<OrderItemRequest> orderItems
  ) {
    order.getOrderItems().clear();
    
    orderItems.forEach(orderItemRequest -> {
      Item item = itemRepository
        .findById(orderItemRequest.itemId())
        .orElseThrow(() -> new RuntimeException("Item not found: " + orderItemRequest.itemId()));
      
      order.addOrderItem(
        OrderItem.builder()
          .order(order)
          .item(item)
          .quantity(orderItemRequest.quantity())
          .build()
      );
    });
  }
}