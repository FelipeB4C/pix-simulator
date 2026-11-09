package dev.pixsim.processor.domain.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "processed_transfer")
public class ProcessedTransfer {

    @Id
    @Column(name = "transfer_id", nullable = false)
    private UUID transferId;

    @Column(nullable = false, length = 20)
    private String result;

    @Column(name = "processed_at", nullable = false)
    private Instant processedAt;

    @PrePersist
    private void onCreate() {
        this.processedAt = Instant.now();
    }

}
