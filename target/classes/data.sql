CREATE TABLE IF NOT EXISTS debito
(id_transacao INT AUTO_INCREMENT PRIMARY KEY,
conta_id INT,
valor_debito DECIMAL,
cliente_id INT,
tipo_conta VARCHAR(30)
);

INSERT INTO debito (cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (1, 1500603806, 'contacorrente', 200.0);
INSERT INTO debito (cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (1, 1500603806, 'contacorrente', 300.0);
INSERT INTO debito (cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (1, 1500603806, 'contacorrente', 400.0);
INSERT INTO debito (cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (2, 1500500005, 'investimento', 100.0);
INSERT INTO debito (cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (2, 1500500005, 'investimento', 200.0);
INSERT INTO debito (cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (2, 1500500005, 'investimento', 200.0);

