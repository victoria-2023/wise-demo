package com.victoria.wisedemo.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentDto(
    Long id,
    UUID userId,
    BigDecimal amount,
    String from,
    String to,
    Instant createdAt
) {}
