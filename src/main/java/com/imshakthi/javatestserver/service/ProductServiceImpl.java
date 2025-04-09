package com.imshakthi.javatestserver.service;

import com.imshakthi.javatestserver.entity.Product;
import com.imshakthi.javatestserver.repository.ProductRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product findByName(final String name) {
    final var product = productRepository.findByName(name);

    Optional.ofNullable(product)
        .map(Product::getProductBoms)
        .ifPresent(
            boms ->
                boms.forEach(
                    bom ->
                        log.info(
                            "bom: {} - {}", bom.getItem().getName(), bom.getRequiredItemUnits())));

    return product;
  }
}
