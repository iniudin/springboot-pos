package com.example.demo.modules.items;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/items")
@Tag(name = "Items", description = "Items management")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "Get all items")
    @GetMapping
    public List<ItemResponse> getItems() {
        return itemService.getItems();
    }

    @Operation(summary = "Get item by id")
    @GetMapping("/{id}")
    public ItemResponse getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @Operation(summary = "Create item")
    @PostMapping
    public ItemResponse createItem(@RequestBody ItemRequest itemRequest) {
        return itemService.createItem(itemRequest);
    }

    @Operation(summary = "Update item")
    @PutMapping("/{id}")
    public ItemResponse updateItem(@PathVariable Long id, @RequestBody ItemRequest itemRequest) {
        return itemService.updateItem(id, itemRequest);
    }

    @Operation(summary = "Update item price")
    @PutMapping("/{id}/price")
    public ItemResponse updateItemPrice(@PathVariable Long id, @RequestBody ItemPriceRequest itemPriceRequest) {
        return itemService.updateItemPrice(id, itemPriceRequest);
    }

    @Operation(summary = "Delete item")
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
