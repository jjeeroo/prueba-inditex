package com.prueba.inditex.infrastructure.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(Long productId, Long brandId, Long priceId, LocalDateTime startDate, LocalDateTime endDate,
                            BigDecimal price) {
}
