package com.example.demo.modules.items;

import java.time.LocalDateTime;

public record ItemPriceRequest(
  Long itemId,
  Double price,
  LocalDateTime effectiveDate
) {
  
}
