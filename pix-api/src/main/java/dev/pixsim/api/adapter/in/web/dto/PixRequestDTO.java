package dev.pixsim.api.adapter.in.web.dto;

import java.math.BigDecimal;

public record PixRequestDTO(
        String originAccountId,
        String accountDestiny,
        BigDecimal amount,
        String description
) {
}
