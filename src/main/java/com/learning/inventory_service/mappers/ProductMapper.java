package com.learning.inventory_service.mappers;

import com.learning.inventory_service.dtos.products.ProductCreateRequest;
import com.learning.inventory_service.dtos.products.ProductDetailsDto;
import com.learning.inventory_service.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public ProductDetailsDto mapEntityToDetailsDto(Product product) {
    return ProductDetailsDto.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .stock(product.getStock())
        .build();
  }

  public Product mapCreateRequestToEntity(ProductCreateRequest productCreateRequest) {
    return Product.builder()
        .name(productCreateRequest.getName())
        .price(productCreateRequest.getPrice())
        .stock(productCreateRequest.getStock())
        .build();
  }
}
