CREATE TABLE IF NOT EXISTS budgets(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    category_id BIGINT REFERENCES category(id),
    amount NUMERIC(15,2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);