package com.victoria.wisedemo.api;

import com.victoria.wisedemo.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentService svc;

    @Test
    void postCreate_returns201AndJson() throws Exception {
        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        when(svc.create(any(CreatePaymentRequest.class)))
            .thenReturn(new PaymentDto(55L, userId, new BigDecimal("12.34"), "USD", "EUR", Instant.now()));

        mvc.perform(post("/api/payments")
               .contentType(MediaType.APPLICATION_JSON)
               .content("""
                   {"userId":"123e4567-e89b-12d3-a456-426614174000",
                    "amount":12.34,"from":"USD","to":"EUR"}
               """))
           .andExpect(status().isCreated())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.id").value(55))
           .andExpect(jsonPath("$.from").value("USD"));
    }

    @Test
    void getByUser_returns200AndArray() throws Exception {
        UUID userId = UUID.randomUUID();
        when(svc.findByUser(userId))
            .thenReturn(List.of(new PaymentDto(1L, userId, BigDecimal.ONE, "A", "B", Instant.now())));

        mvc.perform(get("/api/payments/user/{userId}", userId))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].userId").value(userId.toString()));
    }

    @Test
    void getAll_returns200AndArray() throws Exception {
        when(svc.findAll())
            .thenReturn(List.of(new PaymentDto(2L, UUID.randomUUID(), BigDecimal.ONE, "X", "Y", Instant.now())));

        mvc.perform(get("/api/payments"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].id").value(2));
    }
}
