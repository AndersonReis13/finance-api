CREATE TYPE account_type_enum AS ENUM('corrente', 'poupança', 'carteira digital', 'dinheiro fisico')

CREATE TABLE IF NOT EXISTS accounts(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) DELETE ON CASCADE,
    name VARCHAR(155) NOT NULL,
    account_type account_type_enum NOT NULL,
    balance NUMERIC(15, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP
);