package dev.pixsim.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pixsim.api.domain.enums.StatusTransfer;
import dev.pixsim.api.domain.model.PixTransfer;
import dev.pixsim.api.exception.IdempotencyKeyConflictException;
import dev.pixsim.api.repository.PixTransferRepository;
import dev.pixsim.api.web.dto.PixRequestDTO;
import dev.pixsim.api.web.dto.PixResponseDTO;
import dev.pixsim.api.web.mapper.PixTransferMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

@Service
public class PixTransferService {

    private final PixTransferRepository repository;
    private final PixTransferMapper mapper;
    private final ObjectMapper objectMapper;

    public PixTransferService(
            PixTransferRepository repository,
            PixTransferMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.objectMapper = new ObjectMapper();
    }

    @Transactional
    public PixResponseDTO createPixTransfer(PixRequestDTO request, UUID idempotencyKey) {
        String hashRequestBody = hashRequest(request);

        var existingTransfer = repository.findByIdempotencyKey(idempotencyKey);
        if (existingTransfer.isPresent()) {
            PixTransfer pix = existingTransfer.get();
            if (!pix.getRequestHash().equals(hashRequestBody)) {
                throw new IdempotencyKeyConflictException(
                        "Idempotency Key already used with different body"
                );
            }
            return mapper.toResponseDTO(pix);
        }

        PixTransfer pixEntity = mapper.toEntity(request, hashRequestBody, idempotencyKey);
        pixEntity.changeStatus(StatusTransfer.PROCESSING);
        PixTransfer pixCreated = repository.save(pixEntity);
        return mapper.toResponseDTO(pixCreated);
    }

    @Transactional(readOnly = true)
    public PixResponseDTO getPixStatusTransfer(UUID transferId) {
        PixTransfer pix = repository.findById(transferId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "PIX transfer not found: " + transferId
                ));
        return mapper.toResponseDTO(pix);
    }

    private String hashRequest(PixRequestDTO request) {
        CanonicalPixRequest canonicalRequest = new CanonicalPixRequest(
                request.originAccountId().trim(),
                request.accountDestiny().trim(),
                canonicalAmount(request.amount()),
                request.description()
        );

        try {
            byte[] content = objectMapper.writeValueAsBytes(canonicalRequest);
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(content);
            return HexFormat.of().formatHex(hash);
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not generate the PIX request hash", e);
        }
    }

    private String canonicalAmount(BigDecimal amount) {
        return amount.stripTrailingZeros().toPlainString();
    }
}
