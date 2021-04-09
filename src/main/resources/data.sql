INSERT INTO CARACTERISTICA(nome, descricao) values ('Liso', 'Produto com estampa lisa');
INSERT INTO CARACTERISTICA(nome, descricao) values ('Preto', 'Produto na cor preta');
INSERT INTO CARACTERISTICA(nome, descricao) values ('Silicone', 'Produto feito de silicone');

INSERT INTO CARACTERISTICA(nome, descricao) values ('Memória interna de 128GB', 'Memória interna de 128GB. Adequado para cartão SD de 512GB');
INSERT INTO CARACTERISTICA(nome, descricao) values ('Câmera frontal', 'Câmera frontal de 10Mpx');

INSERT INTO CATEGORIA(nome) values ('Capinhas celular');
INSERT INTO CATEGORIA(nome) values ('Celular');

INSERT INTO USUARIO(login, senha) values('kmlporto1@gmail.com', '123456');
INSERT INTO USUARIO(login, senha) values('kmlporto2@gmail.com', '234567');
INSERT INTO USUARIO(login, senha) values('kmlporto3@gmail.com', '345678');

INSERT INTO PRODUTO(nome, descricao, valor, quantidade_disponivel, categoria_id, dono_id, data_criacao) values ('Capinha celular', 'uma capinha muito bonitinha', 35.00, 30, 1, 1, '2022-12-18');
INSERT INTO PRODUTO_CARACTERISTICAS(produto_id, caracteristicas_id) values(1, 1);
INSERT INTO PRODUTO_CARACTERISTICAS(produto_id, caracteristicas_id) values(1, 2);
INSERT INTO PRODUTO_CARACTERISTICAS(produto_id, caracteristicas_id) values(1, 3);

INSERT INTO PRODUTO(nome, descricao, valor, quantidade_disponivel, categoria_id, dono_id, data_criacao) values ('Celular S10', 'um celular bem top', 2500.00, 5, 2, 1, '2022-12-19');
INSERT INTO PRODUTO_CARACTERISTICAS(produto_id, caracteristicas_id) values(2, 2);
INSERT INTO PRODUTO_CARACTERISTICAS(produto_id, caracteristicas_id) values(2, 4);
INSERT INTO PRODUTO_CARACTERISTICAS(produto_id, caracteristicas_id) values(2, 5);

INSERT INTO OPINIAO(titulo, nota, descricao, usuario_id, produto_id) values('Muito bom', 5.0, 'o celular superou minhas expectativas', 2, 1);
INSERT INTO PERGUNTA(titulo, usuario_id, produto_id, data_criacao) values('Quanto tempo dura a bateria?', 3, 2, '2022-12-20');
INSERT INTO PERGUNTA(titulo, usuario_id, produto_id, data_criacao) values('Capinha é fechada em baixo?', 3, 1, '2022-12-20');