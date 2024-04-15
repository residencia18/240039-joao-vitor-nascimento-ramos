-- Adiciona a coluna "activated" à tabela "doctors" como TINYINT
ALTER TABLE doctors ADD activated TINYINT;

-- Define o valor padrão da coluna "activated" como 1
UPDATE doctors SET activated = 1;

-- Altera a coluna "activated" para ser NOT NULL
ALTER TABLE doctors MODIFY activated TINYINT NOT NULL;
