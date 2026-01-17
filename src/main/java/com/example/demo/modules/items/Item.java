package com.example.demo.modules.items;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.example.demo.shared.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "items")
@SQLDelete(sql = "UPDATE items SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Item extends BaseEntity {
  private String name;
  private String description;
  
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ItemPrice> prices = new ArrayList<>();

  public Item() {
  }

  public Item(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public void addPrice(ItemPrice price) {
    prices.add(price);
  }

  public void removePrice(ItemPrice price) {
    prices.remove(price);
  }
}
