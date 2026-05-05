package com.learning.inventory_service.dtos.orders;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestItemDto {
  Long id;
  Long productId;
  Integer quantity;
}
