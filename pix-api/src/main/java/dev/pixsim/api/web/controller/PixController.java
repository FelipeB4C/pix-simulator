package dev.pixsim.api.web.controller;

import dev.pixsim.api.service.PixTransferService;
import dev.pixsim.api.web.dto.PixRequestDTO;
import dev.pixsim.api.web.dto.PixResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/pix")
public class PixController {

    private final PixTransferService service;

    public PixController(PixTransferService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PixResponseDTO> createPixTransfer(@RequestBody @Valid PixRequestDTO request,
                                                            @RequestHeader("Idempotency-Key") UUID idempotencyKey,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        PixResponseDTO pixResponse = service.createPixTransfer(request, idempotencyKey);
        URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(pixResponse.id()).toUri();
        return ResponseEntity.created(uri).body(pixResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PixResponseDTO> consultPixStatusTransfer(@PathVariable UUID id){
        return ResponseEntity.ok(service.getPixStatusTransfer(id));
    }

}
