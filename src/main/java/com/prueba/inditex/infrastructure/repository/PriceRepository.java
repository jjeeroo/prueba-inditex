package com.prueba.inditex.infrastructure.repository;

import com.prueba.inditex.infrastructure.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
  @Query(
      "SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN p.startDate AND p.endDate")
  List<PriceEntity> findByProductIdAndBrandIdAndDate(@Param("productId") Long productId,
      @Param("date") LocalDateTime date, @Param("brandId") Long brandId);
}
