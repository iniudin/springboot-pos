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

@RestController
@RequestMapping("/items")
public class ItemController {
  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping
  public List<ItemResponse> getItems() {
    return itemService.getItems();
  }

  @GetMapping("/{id}")
  public ItemResponse getItemById(@PathVariable Long id) {
    return itemService.getItemById(id);
  }

  @PostMapping
  public ItemResponse createItem(@RequestBody ItemRequest itemRequest) {
    return itemService.createItem(itemRequest);
  }

  @PutMapping("/{id}")
  public ItemResponse updateItem(@PathVariable Long id, @RequestBody ItemRequest itemRequest) {
    return itemService.updateItem(id, itemRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteItem(@PathVariable Long id) {
    itemService.deleteItem(id);
  }
}
