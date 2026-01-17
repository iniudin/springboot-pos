package com.example.demo.modules.items;

import java.time.LocalDateTime;

import com.example.demo.shared.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "item_prices")
public class ItemPrice extends BaseEntity {
  private Item item;
  private Double price;
  private LocalDateTime effectiveDate;

  public ItemPrice() {
    this.effectiveDate = LocalDateTime.now();
  }

  public ItemPrice(Item item, Double price, LocalDateTime effectiveDate) {
    this.item = item;
    this.price = price;
    this.effectiveDate = effectiveDate;
  }
}
