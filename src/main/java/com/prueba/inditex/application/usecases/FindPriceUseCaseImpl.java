package com.prueba.inditex.application.usecases;

import com.prueba.inditex.application.port.PricePort;
import com.prueba.inditex.domain.exceptions.PriceNotFoundException;
import com.prueba.inditex.domain.model.Price;
import com.prueba.inditex.domain.usecases.FindPriceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FindPriceUseCaseImpl implements FindPriceUseCase {

  private final PricePort pricePort;

  @Autowired
  public FindPriceUseCaseImpl(PricePort pricePort) {
    this.pricePort = pricePort;
  }

  @Override
  @Transactional
  public Price findByProductIdAndBrandIdAndDate(Long productId, LocalDateTime date, Long brandId) {
    List<Price> prices = pricePort.findByProductIdAndBrandIdAndDate(productId, date, brandId);
    Optional<Price> finalPrice = prices.stream().max(Comparator.comparingLong(Price::priority));

    if (finalPrice.isEmpty()) {
      throw new PriceNotFoundException("No price found for this product.");
    }

    return finalPrice.get();
  }
}
