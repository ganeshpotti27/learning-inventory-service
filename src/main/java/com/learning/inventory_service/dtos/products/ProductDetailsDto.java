package com.learning.inventory_service.dtos.products;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailsDto {
  Long id;

  String name;

  Double price;

  Integer stock;
}
