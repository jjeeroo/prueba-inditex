package com.prueba.inditex.application.usecases;

import com.prueba.inditex.application.port.PricePort;
import com.prueba.inditex.domain.exceptions.PriceNotFoundException;
import com.prueba.inditex.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPriceUseCaseTest {

  @Mock
  private PricePort pricePort;

  @InjectMocks
  private FindPriceUseCaseImpl findPriceUseCase;

  private Long productId;
  private Long brandId;
  private LocalDateTime date;

  @BeforeEach
  void setUp() {
    productId = 35455L;
    brandId = 1L;
    date = LocalDateTime.of(2020, 6, 14, 16, 0);
  }

  @Test
  void testFindByProductIdAndBrandIdAndDate_PriceFound() {
    Price price1 =
        new Price(brandId, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), 1L,
            productId, 0L, BigDecimal.valueOf(35.50), "EUR");
    Price price2 =
        new Price(brandId, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30), 2L, productId,
            1L, BigDecimal.valueOf(25.45), "EUR");
    List<Price> prices = Arrays.asList(price1, price2);

    when(pricePort.findByProductIdAndBrandIdAndDate(productId, date, brandId)).thenReturn(prices);

    Price result = findPriceUseCase.findByProductIdAndBrandIdAndDate(productId, date, brandId);

    assertNotNull(result);
    assertEquals(price2, result);
  }

  @Test
  void testFindByProductIdAndBrandIdAndDate_PriceNotFound() {
    when(pricePort.findByProductIdAndBrandIdAndDate(productId, date, brandId)).thenReturn(Collections.emptyList());

    assertThrows(PriceNotFoundException.class, () -> {
      findPriceUseCase.findByProductIdAndBrandIdAndDate(productId, date, brandId);
    });
  }
}
