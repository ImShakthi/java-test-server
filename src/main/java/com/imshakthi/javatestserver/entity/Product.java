package com.imshakthi.javatestserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
  @Id private UUID id;

  private String name;

  // optional: reverse mapping
  @OneToMany(mappedBy = "product")
  private List<ProductBom> productBoms;
}
