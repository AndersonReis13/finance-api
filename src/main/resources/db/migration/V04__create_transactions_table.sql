CREATE TYPE transactions_type_enum AS ENUM('receita', 'despesa', 'transferência')

CREATE TABLE IF NOT EXISTS transactions(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) DELETE ON CASCADE,
    category_id BIGINT REFERENCES categories(id),
    amount NUMERIC(15,2) NOT NULL CHECK (amount > 0),
    transactions_type transactions_type_enum NOT NULL,
    description TEXT,
    transactions_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
 );