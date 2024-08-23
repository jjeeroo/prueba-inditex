package com.prueba.inditex.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.inditex.infrastructure.controller.dto.PriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void test1() throws Exception {
    Long productId = 35455L;
    Long brandId = 1L;
    String date = "2020-06-14T10:00:00";

    PriceResponse expectedObject = new PriceResponse(productId, brandId, 1L, LocalDateTime.parse("2020-06-14T00:00:00"),
        LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(35.50));

    performRequestAndVerify(productId, brandId, date, expectedObject);
  }

  @Test
  void test2() throws Exception {
    Long productId = 35455L;
    Long brandId = 1L;
    String date = "2020-06-14T16:00:00";

    PriceResponse expectedObject = new PriceResponse(productId, brandId, 2L, LocalDateTime.parse("2020-06-14T15:00:00"),
        LocalDateTime.parse("2020-06-14T18:30:00"), BigDecimal.valueOf(25.45));

    performRequestAndVerify(productId, brandId, date, expectedObject);
  }

  @Test
  void test3() throws Exception {
    Long productId = 35455L;
    Long brandId = 1L;
    String date = "2020-06-14T21:00:00";

    PriceResponse expectedObject = new PriceResponse(productId, brandId, 1L, LocalDateTime.parse("2020-06-14T00:00:00"),
        LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(35.50));

    performRequestAndVerify(productId, brandId, date, expectedObject);
  }

  @Test
  void test4() throws Exception {
    Long productId = 35455L;
    Long brandId = 1L;
    String date = "2020-06-15T10:00:00";

    PriceResponse expectedObject = new PriceResponse(productId, brandId, 3L, LocalDateTime.parse("2020-06-15T00:00:00"),
        LocalDateTime.parse("2020-06-15T11:00:00"), BigDecimal.valueOf(30.50));

    performRequestAndVerify(productId, brandId, date, expectedObject);
  }

  @Test
  void test5() throws Exception {
    Long productId = 35455L;
    Long brandId = 1L;
    String date = "2020-06-16T21:00:00";

    PriceResponse expectedObject = new PriceResponse(productId, brandId, 4L, LocalDateTime.parse("2020-06-15T16:00:00"),
        LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(38.95));

    performRequestAndVerify(productId, brandId, date, expectedObject);
  }

  private void performRequestAndVerify(Long productId, Long brandId, String date, PriceResponse expectedObject)
      throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/prices").param("productId", String.valueOf(productId))
            .param("brandId", String.valueOf(brandId)).param("date", date)).andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedObject)));
  }
}
