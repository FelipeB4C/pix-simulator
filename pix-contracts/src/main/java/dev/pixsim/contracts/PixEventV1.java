package dev.pixsim.contracts;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PixEventV1(
        UUID eventId,
        PixEventType type,
        UUID transferId,
        UUID accountOriginId,
        UUID accountTargetId,
        BigDecimal value,
        RejectionMotive motive,
        Instant occurredAt
        ) {
}
