package dev.pixsim.api.service;

public record CanonicalPixRequest(
        String originAccountId,
        String accountDestiny,
        String amount,
        String description
) {
}
