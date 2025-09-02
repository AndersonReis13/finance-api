CREATE TYPE notification_type AS ENUM('aviso', 'lembrete', 'orçamento estourado')

CREATE TABLE IF NOT EXISTS notifications(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) DELETE ON CASCADE,
    title VARCHAR(80) NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN,
    created_at TIMESTAMP DEFAULT NOW()
);