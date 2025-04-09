package com.imshakthi.javatestserver.controller;

import com.imshakthi.javatestserver.mapper.ProductMapper;
import com.imshakthi.javatestserver.model.response.ProductResponse;
import com.imshakthi.javatestserver.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {
  private final ProductService productService;
  private final ProductMapper productMapper;

  public ProductController(final ProductService productService, ProductMapper productMapper) {
    this.productService = productService;
    this.productMapper = productMapper;
  }

  @GetMapping("/{product-name}")
  public ResponseEntity<ProductResponse> getProduct(
      final @PathVariable("product-name") String productName) {

    final var product = productService.findByName(productName);

    log.info("product: {} ", product);

    return ResponseEntity.ok(productMapper.toProductResponse(product));
  }
}
