package com.imshakthi.javatestserver.service;

import com.imshakthi.javatestserver.entity.Product;

public interface ProductService {
  Product findByName(final String name);
}
