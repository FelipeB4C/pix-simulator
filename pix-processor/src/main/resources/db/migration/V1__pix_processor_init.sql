CREATE TABLE account (
                         id      UUID PRIMARY KEY,
                         holder  VARCHAR(120)  NOT NULL,
                         balance NUMERIC(15,2) NOT NULL CHECK (balance >= 0)
);

CREATE TABLE pix_key (
                         key_value VARCHAR(140) PRIMARY KEY,
                         key_type  VARCHAR(12)  NOT NULL,
                         account_id UUID       NOT NULL REFERENCES account(id)
);

CREATE TABLE processed_transfer (
                                    transfer_id UUID PRIMARY KEY,
                                    result      VARCHAR(20) NOT NULL,
                                    processed_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE ledger_entry (
                              id         UUID PRIMARY KEY,
                              transfer_id UUID          NOT NULL,
                              account_id UUID          NOT NULL REFERENCES account(id),
                              entry_type VARCHAR(8)    NOT NULL,
                              amount     NUMERIC(15,2) NOT NULL,
                              created_at TIMESTAMPTZ   NOT NULL DEFAULT now(),
                              UNIQUE (transfer_id, entry_type)
);

CREATE TABLE outbox_event (
                              id           UUID PRIMARY KEY,
                              topic        VARCHAR(100) NOT NULL,
                              message_key  VARCHAR(100) NOT NULL,
                              payload      JSONB        NOT NULL,
                              created_at   TIMESTAMPTZ  NOT NULL DEFAULT now(),
                              published_at TIMESTAMPTZ
);
CREATE INDEX idx_outbox_event_unpublished_created_at
    ON outbox_event (created_at) WHERE published_at IS NULL;