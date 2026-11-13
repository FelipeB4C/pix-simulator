package dev.pixsim.api.web.mapper;

import dev.pixsim.api.web.dto.PixRequestDTO;
import dev.pixsim.api.web.dto.PixResponseDTO;
import dev.pixsim.api.domain.model.PixTransfer;
import dev.pixsim.api.domain.enums.StatusTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PixTransferMapper {


    @Mapping(source = "request.accountDestiny", target = "targetKey")
    @Mapping(source = "requestHash", target = "requestHash")
    @Mapping(source = "idempotencyKey", target = "idempotencyKey")
    PixTransfer toEntity(PixRequestDTO request, String requestHash, UUID idempotencyKey);

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    PixResponseDTO toResponseDTO(PixTransfer entity);

    @Named("uuidToString")
    default String uuidToString(UUID id) {
        return id == null ? null : id.toString();
    }

    @Named("statusToString")
    default String statusToString(StatusTransfer status) {
        return status == null ? null : status.name();
    }

}
