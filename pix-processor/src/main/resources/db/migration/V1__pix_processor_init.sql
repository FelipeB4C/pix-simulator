CREATE TABLE conta (
                       id       UUID PRIMARY KEY,
                       titular  VARCHAR(120)  NOT NULL,
                       saldo    NUMERIC(15,2) NOT NULL CHECK (saldo >= 0)
);

CREATE TABLE chave_pix (
                           chave    VARCHAR(140) PRIMARY KEY,
                           tipo     VARCHAR(12)  NOT NULL,
                           conta_id UUID         NOT NULL REFERENCES conta(id)
);

CREATE TABLE transferencia_processada (
                                          transferencia_id UUID PRIMARY KEY,
                                          resultado        VARCHAR(20) NOT NULL,
                                          processada_em    TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE lancamento (
                            id               UUID PRIMARY KEY,
                            transferencia_id UUID          NOT NULL,
                            conta_id         UUID          NOT NULL REFERENCES conta(id),
                            tipo             VARCHAR(8)    NOT NULL,
                            valor            NUMERIC(15,2) NOT NULL,
                            criado_em        TIMESTAMPTZ   NOT NULL DEFAULT now(),
                            UNIQUE (transferencia_id, tipo)
);

CREATE TABLE outbox_evento (
                               id            UUID PRIMARY KEY,
                               topico        VARCHAR(100) NOT NULL,
                               chave         VARCHAR(100) NOT NULL,
                               payload       JSONB        NOT NULL,
                               criado_em     TIMESTAMPTZ  NOT NULL DEFAULT now(),
                               publicado_em  TIMESTAMPTZ
);
CREATE INDEX idx_outbox_pendentes ON outbox_evento (criado_em) WHERE publicado_em IS NULL;