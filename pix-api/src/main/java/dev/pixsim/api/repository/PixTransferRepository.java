package dev.pixsim.api.repository;

import dev.pixsim.api.domain.model.PixTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PixTransferRepository extends JpaRepository<PixTransfer, UUID> {
}
