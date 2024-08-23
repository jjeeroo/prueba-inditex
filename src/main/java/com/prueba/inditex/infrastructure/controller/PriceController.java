package com.prueba.inditex.infrastructure.controller;

import com.prueba.inditex.domain.model.Price;
import com.prueba.inditex.domain.usecases.FindPriceUseCase;
import com.prueba.inditex.infrastructure.controller.dto.PriceResponse;
import com.prueba.inditex.infrastructure.mapper.PriceMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/api/prices")
@Api(value = "Prices operations")
public class PriceController {

  private final FindPriceUseCase findPriceUseCase;
  private final PriceMapper priceMapper;

  @Autowired
  public PriceController(FindPriceUseCase findPriceUseCase, PriceMapper priceMapper) {
    this.findPriceUseCase = findPriceUseCase;
    this.priceMapper = priceMapper;
  }

  @ApiOperation(value = "Retrieve a price by productId, brandId and date", response = PriceResponse.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Price retrieved successfully"),
      @ApiResponse(code = 400, message = "Incorrect parameters"), @ApiResponse(code = 404, message = "Price not found"),
      @ApiResponse(code = 500, message = "Internal server error")})
  @GetMapping
  public ResponseEntity<PriceResponse> getPricesByProducIdAndDateAndBrandId(
      @ApiParam(value = "Product Id", required = true) @RequestParam(name = "productId") Long productId,
      @ApiParam(value = "Date", required = true) @RequestParam(name = "date") @DateTimeFormat(
          pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date,
      @ApiParam(value = "Brand Id", required = true) @RequestParam(name = "brandId") Long brandId) {
    Price result = findPriceUseCase.findByProductIdAndBrandIdAndDate(productId, date, brandId);
    return ResponseEntity.ok(priceMapper.toResponse(result));
  }
}
