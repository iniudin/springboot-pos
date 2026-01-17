package com.example.demo.modules.items;

import java.time.LocalDateTime;

public record ItemRequest(
  String name,
  String description,
  Double price,
  LocalDateTime effectiveDate
) {

}