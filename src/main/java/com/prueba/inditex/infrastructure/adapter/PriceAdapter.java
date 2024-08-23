package com.prueba.inditex.infrastructure.adapter;

import com.prueba.inditex.application.port.PricePort;
import com.prueba.inditex.domain.model.Price;
import com.prueba.inditex.infrastructure.mapper.PriceMapper;
import com.prueba.inditex.infrastructure.model.PriceEntity;
import com.prueba.inditex.infrastructure.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceAdapter implements PricePort {

  private final PriceRepository priceRepository;
  private final PriceMapper mapper;

  @Autowired
  public PriceAdapter(PriceRepository priceRepository, PriceMapper mapper) {
    this.priceRepository = priceRepository;
    this.mapper = mapper;
  }

  @Override
  @Transactional
  public List<Price> findByProductIdAndBrandIdAndDate(Long productId, LocalDateTime date, Long brandId) {
    List<PriceEntity> prices = priceRepository.findByProductIdAndBrandIdAndDate(productId, date, brandId);
    return mapper.toDomain(prices);
  }
}
