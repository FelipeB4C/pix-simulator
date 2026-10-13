package dev.pixsim.contracts;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PixCommandProcess(
        UUID transferId,
        UUID accountOriginId,
        String targetKey,
        BigDecimal value,
        String description,
        Instant requestedAt
) {
}
