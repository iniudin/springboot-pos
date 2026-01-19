package com.example.demo.modules.items;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemResponse> getItems() {
        return itemRepository
                .findAll()
                .stream()
                .map(ItemResponse::fromEntity)
                .toList();
    }

    public ItemResponse getItemById(Long id) {
        return itemRepository
                .findById(id)
                .map(ItemResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public ItemResponse createItem(ItemRequest itemRequest) {
        Item item = Item.builder()
                .name(itemRequest.name())
                .description(itemRequest.description())
                .build();

        ItemPrice itemPrice = ItemPrice.builder()
                .item(item)
                .price(itemRequest.price())
                .effectiveDate(LocalDateTime.now())
                .build();
        item.addPrice(itemPrice);
        Item savedItem = itemRepository.save(item);
        return ItemResponse.fromEntity(savedItem);
    }

    public ItemResponse updateItem(Long id, ItemRequest itemRequest) {
        Item item = itemRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setName(itemRequest.name());
        item.setDescription(itemRequest.description());

        Item updatedItem = itemRepository.save(item);

        return ItemResponse.fromEntity(updatedItem);
    }

    public ItemResponse updateItemPrice(Long id, ItemPriceRequest itemPriceRequest) {
        Item item = itemRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        ItemPrice itemPrice = ItemPrice.builder()
                .item(item)
                .price(itemPriceRequest.price())
                .effectiveDate(itemPriceRequest.effectiveDate())
                .build();
        item.addPrice(itemPrice);

        Item updatedItem = itemRepository.save(item);

        return ItemResponse.fromEntity(updatedItem);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
