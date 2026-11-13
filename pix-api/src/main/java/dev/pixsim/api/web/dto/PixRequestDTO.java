package dev.pixsim.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PixRequestDTO(
        @NotBlank String originAccountId,
        @NotBlank String accountDestiny,
        @Positive BigDecimal amount,
        String description
) {
}
