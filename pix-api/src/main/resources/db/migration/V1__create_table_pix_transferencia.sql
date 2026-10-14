CREATE TABLE pix_transferencia (
                                   id                UUID PRIMARY KEY,
                                   idempotency_key   UUID           NOT NULL UNIQUE,
                                   hash_requisicao   VARCHAR(64)    NOT NULL,
                                   conta_origem_id   UUID           NOT NULL,
                                   conta_destino_id  UUID,
                                   chave_destino     VARCHAR(140)   NOT NULL,
                                   valor             NUMERIC(15,2)  NOT NULL CHECK (valor > 0),
                                   descricao         VARCHAR(140),
                                   status            VARCHAR(20)    NOT NULL,
                                   motivo_rejeicao   VARCHAR(40),
                                   criado_em         TIMESTAMPTZ    NOT NULL DEFAULT now(),
                                   atualizado_em     TIMESTAMPTZ    NOT NULL DEFAULT now()
);
CREATE INDEX idx_transf_origem  ON pix_transferencia (conta_origem_id, criado_em DESC);
CREATE INDEX idx_transf_destino ON pix_transferencia (conta_destino_id, criado_em DESC);