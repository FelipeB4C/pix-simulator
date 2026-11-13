package dev.pixsim.api.web.controller;

import dev.pixsim.api.service.PixTransferService;
import dev.pixsim.api.web.dto.PixRequestDTO;
import dev.pixsim.api.web.dto.PixResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pix")
public class PixTransferController {

    private final PixTransferService service;

    public PixTransferController(PixTransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<PixResponseDTO> createPixTransfer(@RequestBody @Valid PixRequestDTO request,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        PixResponseDTO pixResponse = service.createPixTransfer(request);
        URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(pixResponse.id()).toUri();
        return ResponseEntity.created(uri).body(pixResponse);
    }

}
