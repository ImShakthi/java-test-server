package com.imshakthi.javatestserver.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {
  @Id private UUID id;
  private int availableUnits;

  @JoinColumn(name = "item_id")
  @OneToOne(cascade = CascadeType.ALL)
  private Item item;
}
