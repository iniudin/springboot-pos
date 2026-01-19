package com.example.demo.modules.items;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.example.demo.shared.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
@SQLDelete(sql = "UPDATE items SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Item extends BaseEntity {
    private String name;
    private String description;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonIgnoreProperties("item")
    private List<ItemPrice> prices = new ArrayList<>();

    public ItemPrice getCurrentPrice() {
        LocalDateTime now = LocalDateTime.now();
        return prices.stream()
                .filter(price -> !price.getEffectiveDate().isAfter(now))
                .findFirst().orElse(null);
    }

    public void addPrice(ItemPrice price) {
        prices.add(price);
        price.setItem(this);
    }

    public void removePrice(ItemPrice price) {
        prices.remove(price);
        price.setItem(null);
    }
}
