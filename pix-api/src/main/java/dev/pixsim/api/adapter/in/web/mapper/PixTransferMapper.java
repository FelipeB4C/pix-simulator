package dev.pixsim.api.adapter.in.web.mapper;

import dev.pixsim.api.adapter.in.web.dto.PixRequestDTO;
import dev.pixsim.api.adapter.in.web.dto.PixResponseDTO;
import dev.pixsim.api.adapter.out.persistence.PixTransferEntity;
import dev.pixsim.api.domain.enums.StatusTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PixTransferMapper {


    @Mapping(source = "accountDestiny", target = "targetKey")
    PixTransferEntity toEntity(PixRequestDTO request);

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    PixResponseDTO toResponseDTO(PixTransferEntity entity);

    @Named("uuidToString")
    default String uuidToString(UUID id) {
        return id == null ? null : id.toString();
    }

    @Named("statusToString")
    default String statusToString(StatusTransfer status) {
        return status == null ? null : status.name();
    }

}
