package com.example.demo.modules.items;

import java.time.LocalDateTime;

public record ItemPriceRequest(
        Double price,
        LocalDateTime effectiveDate) {
}
