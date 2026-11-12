package dev.pixsim.api.web.dto;

import java.math.BigDecimal;

public record PixRequestDTO(
        String originAccountId,
        String accountDestiny,
        BigDecimal amount,
        String description
) {
}
