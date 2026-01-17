package com.example.demo.modules.items;

import java.util.List;

import lombok.Builder;

@Builder
public record ItemResponse(
  Long id,
  String name,
  String description,
  List<ItemPriceResponse> prices
) {

  public static ItemResponse fromEntity(Item item) {
    return ItemResponse.builder()
      .id(item.getId())
      .name(item.getName())
      .description(item.getDescription())
      .prices(item.getPrices()
        .stream()
        .map(ItemPriceResponse::fromEntity)
        .toList())
      .build();
  }
}