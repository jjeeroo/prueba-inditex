package com.prueba.inditex.infrastructure.mapper;

import com.prueba.inditex.domain.model.Price;
import com.prueba.inditex.infrastructure.controller.dto.PriceResponse;
import com.prueba.inditex.infrastructure.model.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  Price toDomain(PriceEntity entity);

  List<Price> toDomain(List<PriceEntity> entities);

  PriceResponse toResponse(Price price);
}
