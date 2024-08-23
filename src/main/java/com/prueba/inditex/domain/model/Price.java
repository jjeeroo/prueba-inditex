package com.prueba.inditex.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(Long brandId, LocalDateTime startDate, LocalDateTime endDate, Long priceId, Long productId,
                    Long priority, BigDecimal price, String curr) {
}
