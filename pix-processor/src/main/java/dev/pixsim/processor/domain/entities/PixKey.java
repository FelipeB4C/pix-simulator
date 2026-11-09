package dev.pixsim.processor.domain.entities;

import dev.pixsim.processor.domain.enums.PixKeyType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "pix_key")
public class PixKey {

    @Id
    @Column(name = "key_value", length = 140)
    private String keyValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "key_type", nullable = false, length = 12)
    private PixKeyType keyType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
