package com.learning.inventory_service.services;

import com.learning.inventory_service.dtos.orders.OrderRequestDto;
import com.learning.inventory_service.dtos.orders.OrderRequestItemDto;
import com.learning.inventory_service.dtos.products.ProductCreateRequest;
import com.learning.inventory_service.dtos.products.ProductDetailsDto;
import com.learning.inventory_service.entities.Product;
import com.learning.inventory_service.mappers.ProductMapper;
import com.learning.inventory_service.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductService {

  ProductRepository productRepository;

  ProductMapper productMapper;

  public List<ProductDetailsDto> getAllInventory() {
    log.info("fetching all inventory items");
    List<Product> products = productRepository.findAll();
    return products.stream().map(productMapper::mapEntityToDetailsDto).collect(Collectors.toList());
  }

  public ProductDetailsDto getProductById(Long productId) {
    log.info("fetching product with id: {}", productId);
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found with id: " + productId));
    return productMapper.mapEntityToDetailsDto(product);
  }

  public ProductDetailsDto createProduct(ProductCreateRequest productCreateRequest) {
    log.info("creating product");
    Product product = productMapper.mapCreateRequestToEntity(productCreateRequest);
    product = productRepository.save(product);
    return productMapper.mapEntityToDetailsDto(product);
  }

  @Transactional
  public Double reduceStocks(OrderRequestDto orderRequestDto) {
    Double totalPrice = 0.0;
    for (OrderRequestItemDto orderRequestItemDto : orderRequestDto.getItems()) {
      Long productId = orderRequestItemDto.getProductId();
      Integer quantity = orderRequestItemDto.getQuantity();

      Product product =
          productRepository
              .findById(productId)
              .orElseThrow(
                  () ->
                      new ResponseStatusException(
                          HttpStatus.NOT_FOUND, "product not found with Id: " + productId));

      if (product.getStock() < quantity)
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Requested product quantity insufficient for product Id: " + productId);

      product.setStock(product.getStock() - quantity);
      totalPrice += quantity * product.getPrice();
    }
    return totalPrice;
  }
}
