package dev.pixsim.api.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PixTransferRepository extends JpaRepository<UUID, PixTransferEntity> {
}
