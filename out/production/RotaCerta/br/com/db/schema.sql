CREATE DATABASE rotacerta;

CREATE TABLE entregadores (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    idade INTEGER CHECK (idade >= 18)
);

CREATE TABLE entregas (
    id SERIAL PRIMARY KEY,
    entregador_id INTEGER,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    cep CHAR(8),
    estado VARCHAR(50),
    status VARCHAR(20)
        CHECK (status IN ('PENDENTE', 'EM_ROTA', 'ENTREGUE')),
    data_entrega TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_entregador
        FOREIGN KEY (entregador_id)
        REFERENCES entregadores(id)
);