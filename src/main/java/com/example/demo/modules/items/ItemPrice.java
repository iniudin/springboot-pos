package com.example.demo.modules.items;

import java.time.LocalDateTime;

import com.example.demo.shared.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_prices")
public class ItemPrice extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnoreProperties("prices")
    private Item item;
    private Double price;
    private LocalDateTime effectiveDate;
}
