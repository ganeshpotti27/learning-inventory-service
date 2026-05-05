package com.learning.inventory_service.dtos.orders;

import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {
  Long id;
  List<OrderRequestItemDto> items;
  Double totalPrice;
}
