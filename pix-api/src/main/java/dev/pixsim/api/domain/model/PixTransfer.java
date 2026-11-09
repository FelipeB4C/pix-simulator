package dev.pixsim.api.domain.model;

import dev.pixsim.api.domain.enums.RejectionReason;
import dev.pixsim.api.domain.enums.StatusTransfer;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class PixTransfer {

    private UUID id;
    private UUID idempotencyKey;
    private String requestHash;
    private UUID originAccountId;
    private UUID targetAccountId;
    private String targetKey;
    private BigDecimal amount;
    private String description;
    private StatusTransfer status;
    private RejectionReason rejectionReason;
    private Instant createdAt;
    private Instant updatedAt;

    public PixTransfer(UUID id, UUID idempotencyKey, String requestHash, UUID originAccountId, UUID targetAccountId,
                       String targetKey, BigDecimal amount, String description, StatusTransfer status,
                       RejectionReason rejectionReason, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.idempotencyKey = idempotencyKey;
        this.requestHash = requestHash;
        this.originAccountId = originAccountId;
        this.targetAccountId = targetAccountId;
        this.targetKey = targetKey;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getRequestHash() {
        return requestHash;
    }

    public UUID getOriginAccountId() {
        return originAccountId;
    }

    public UUID getTargetAccountId() {
        return targetAccountId;
    }

    public String getTargetKey() {
        return targetKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public StatusTransfer getStatus() {
        return status;
    }

    public RejectionReason getRejectionReason() {
        return rejectionReason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
