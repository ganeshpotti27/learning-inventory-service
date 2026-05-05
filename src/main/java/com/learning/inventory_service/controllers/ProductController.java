package com.learning.inventory_service.controllers;

import com.learning.inventory_service.dtos.orders.OrderRequestDto;
import com.learning.inventory_service.dtos.products.ProductCreateRequest;
import com.learning.inventory_service.dtos.products.ProductDetailsDto;
import com.learning.inventory_service.services.ProductService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

  ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductDetailsDto>> getAllInventory() {
    List<ProductDetailsDto> productDetailsDtoList = productService.getAllInventory();
    return ResponseEntity.ok(productDetailsDtoList);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductDetailsDto> getAllInventory(@PathVariable Long productId) {
    ProductDetailsDto productDetailsDto = productService.getProductById(productId);
    return ResponseEntity.ok(productDetailsDto);
  }

  @PostMapping
  public ResponseEntity<ProductDetailsDto> createProduct(
      @RequestBody ProductCreateRequest productCreateRequest) {
    ProductDetailsDto productDetailsDto = productService.createProduct(productCreateRequest);
    return ResponseEntity.ok(productDetailsDto);
  }

  @PutMapping("/reduce-stocks")
  public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDto orderRequestDto) {
    Double totalPrice = productService.reduceStocks(orderRequestDto);
    return ResponseEntity.ok(totalPrice);
  }
}
