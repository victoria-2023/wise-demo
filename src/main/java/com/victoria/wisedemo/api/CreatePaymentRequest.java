package com.victoria.wisedemo.api;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentRequest(
    @NotNull UUID userId,
    @NotNull @DecimalMin("0.01") BigDecimal amount,
    @NotBlank String from,
    @NotBlank String to
) {}
