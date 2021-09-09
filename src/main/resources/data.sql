CREATE TABLE DEBITO (id VARCHAR(36) AUTO_INCREMENT PRIMARY KEY,
cliente_id INT(10),
conta_id INT(10),
tipo_conta VARCHAR(30),
valor_debito decimal);

INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 1, 1, 'investimento', -100.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 1, 2, 'contacorrente', -20.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 2, 3, 'investimento', -50.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 2, 4, 'contacorrente', -100.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 3, 5, 'investimento', -20.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 4, 6, 'contacorrente', -10.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 4, 6, 'contacorrente', -20.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 4, 6, 'contacorrente', -50.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 6, 7, 'investimento', -100.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 6, 7, 'investimento', -20.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 6, 7, 'investimento', -200.0);
INSERT INTO DEBITO (id, cliente_id ,conta_id, tipo_conta, valor_debito) VALUES (null, 7, 8, 'contacorrente', -200.0);
