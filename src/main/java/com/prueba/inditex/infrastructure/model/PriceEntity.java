package com.prueba.inditex.infrastructure.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PRICE_ID")
  private Long priceId;

  @Column(name = "BRAND_ID")
  private Long brandId;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

  @Column(name = "PRODUCT_ID")
  private Long productId;

  @Column(name = "PRIORITY")
  private Long priority;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "CURR")
  private String curr;
}
