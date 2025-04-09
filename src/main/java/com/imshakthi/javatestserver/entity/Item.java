package com.imshakthi.javatestserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Item {
  @Id private UUID id;

  private String name;

  // optional: reverse mapping
  @OneToMany(mappedBy = "item")
  private List<ProductBom> productBoms;
}
