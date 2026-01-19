package com.example.demo.modules.items;

import lombok.Builder;

@Builder
public record ItemResponse(
        Long id,
        String name,
        String description,
        ItemPriceResponse price) {

    public static ItemResponse fromEntity(Item item) {
        ItemPrice currentPrice = item.getCurrentPrice();
        ItemPriceResponse priceResponse = currentPrice != null ? ItemPriceResponse.fromEntity(currentPrice) : null;
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(priceResponse)
                .build();
    }
}
