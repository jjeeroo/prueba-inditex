package com.prueba.inditex.domain.usecases;

import com.prueba.inditex.domain.model.Price;

import java.time.LocalDateTime;

public interface FindPriceUseCase {
  Price findByProductIdAndBrandIdAndDate(Long productId, LocalDateTime date, Long brandId);
}
