CREATE TABLE pix_transfer (
                              id                 UUID PRIMARY KEY,
                              idempotency_key    UUID           NOT NULL UNIQUE,
                              request_hash       VARCHAR(64)    NOT NULL,
                              origin_account_id  UUID           NOT NULL,
                              target_account_id  UUID,
                              target_key         VARCHAR(140)   NOT NULL,
                              amount             NUMERIC(15,2)  NOT NULL CHECK (amount > 0),
                              description        VARCHAR(140),
                              status             VARCHAR(20)    NOT NULL,
                              rejection_reason   VARCHAR(40),
                              created_at         TIMESTAMPTZ    NOT NULL DEFAULT now(),
                              updated_at         TIMESTAMPTZ    NOT NULL DEFAULT now()
);
CREATE INDEX idx_pix_transfer_origin_account_created_at
    ON pix_transfer (origin_account_id, created_at DESC);
CREATE INDEX idx_pix_transfer_target_account_created_at
    ON pix_transfer (target_account_id, created_at DESC);