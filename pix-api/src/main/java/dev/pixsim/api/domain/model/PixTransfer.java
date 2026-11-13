package dev.pixsim.api.domain.model;

import dev.pixsim.api.domain.enums.RejectionReason;
import dev.pixsim.api.domain.enums.StatusTransfer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(
        name = "pix_transfer",
        indexes = {
                @Index(name = "idx_pix_transfer_origin_account_created_at", columnList = "origin_account_id, created_at"),
                @Index(name = "idx_pix_transfer_target_account_created_at", columnList = "target_account_id, created_at")
        }
)
public class PixTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "idempotency_key", nullable = false, unique = true)
    private UUID idempotencyKey;

    @Column(name = "request_hash", nullable = false, length = 64)
    private String requestHash;

    @Column(name = "origin_account_id", nullable = false)
    private UUID originAccountId;

    @Column(name = "target_account_id")
    private UUID targetAccountId;

    @Column(name = "target_key", nullable = false, length = 140)
    private String targetKey;

    @Positive
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(length = 140)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusTransfer status;

    @Enumerated(EnumType.STRING)
    @Column(name = "rejection_reason", length = 40)
    private RejectionReason rejectionReason;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    private void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public void changeStatus(StatusTransfer status) {
        this.status = status;
    }
}
