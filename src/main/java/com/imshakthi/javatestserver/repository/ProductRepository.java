package com.imshakthi.javatestserver.repository;

import com.imshakthi.javatestserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  Product findByName(final String name);
}
