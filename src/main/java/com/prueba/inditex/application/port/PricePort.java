package com.prueba.inditex.application.port;

import com.prueba.inditex.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePort {
  List<Price> findByProductIdAndBrandIdAndDate(Long productId, LocalDateTime date, Long brandId);
}
