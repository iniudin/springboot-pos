package com.example.demo.modules.items;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ItemPriceResponse(
  Long id,
  Double price,
  LocalDateTime effectiveDate
) {
  public static ItemPriceResponse fromEntity(ItemPrice itemPrice) {
    return ItemPriceResponse.builder()
      .id(itemPrice.getId())
      .price(itemPrice.getPrice())
      .effectiveDate(itemPrice.getEffectiveDate())
      .build();
  }
}
