INSERT INTO account (id, holder, balance) VALUES
                                           ('11111111-1111-1111-1111-111111111111', 'Ana Souza',   5000.00),
                                           ('22222222-2222-2222-2222-222222222222', 'Bruno Lima',  1000.00),
                                           ('33333333-3333-3333-3333-333333333333', 'Maria Silva',  300.00);

INSERT INTO pix_key (key_value, key_type, account_id) VALUES
                                                         ('529.982.247-25',                 'CPF',      '11111111-1111-1111-1111-111111111111'),
                                                         ('ana@exemplo.com',                'EMAIL',    '11111111-1111-1111-1111-111111111111'),
                                                         ('+5561999990000',                 'TELEFONE', '22222222-2222-2222-2222-222222222222'),
                                                         ('maria@exemplo.com',              'EMAIL',    '33333333-3333-3333-3333-333333333333'),
                                                         ('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa', 'EVP', '33333333-3333-3333-3333-333333333333');
