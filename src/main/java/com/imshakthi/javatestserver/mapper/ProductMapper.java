package com.imshakthi.javatestserver.mapper;

import com.imshakthi.javatestserver.entity.Product;
import com.imshakthi.javatestserver.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  @Mapping(target = "name", source = "name")
  ProductResponse toProductResponse(final Product product);
}
