-- 1. Inserir Famílias (Tabela: familia)
INSERT INTO familia (nome) VALUES ('Cordas');
INSERT INTO familia (nome) VALUES ('Sopro');
INSERT INTO familia (nome) VALUES ('Percussão');

-- 2. Inserir Marcas (Tabela: tb_marca)
INSERT INTO tb_marca (nome, pais_origem) VALUES ('Fender', 'EUA');
INSERT INTO tb_marca (nome, pais_origem) VALUES ('Yamaha', 'Japão');
INSERT INTO tb_marca (nome, pais_origem) VALUES ('Giannini', 'Brasil');

-- 3. Inserir Luthiers (Tabela: tb_luthier)
INSERT INTO tb_luthier (nome, especialidade, cidade, email, telefone) 
VALUES ('Antônio de Pádua', 'Violões Clássicos', 'São Paulo', 'antonio@luthier.com', '(11) 99999-1111');

INSERT INTO tb_luthier (nome, especialidade, cidade, email, telefone) 
VALUES ('Carlos Silva', 'Guitarras e Baixos', 'Rio de Janeiro', 'carlos@luthier.com', '(21) 98888-2222');

-- 4. Inserir Instrumentos (Tabela: tb_instrumento)
INSERT INTO tb_instrumento (nome_modelo, ano_fabricacao, descricao, id_familia, id_marca, id_luthier) 
VALUES ('Violão Folk NV-18', '2010-01-01', 'Violão acoplado de nylon', 1, 3, 1);

INSERT INTO tb_instrumento (nome_modelo, ano_fabricacao, descricao, id_familia, id_marca, id_luthier) 
VALUES ('Stratocaster American', '2021-05-10', 'Guitarra elétrica clássica', 1, 1, 2);

INSERT INTO tb_instrumento (nome_modelo, ano_fabricacao, descricao, id_familia, id_marca, id_luthier) 
VALUES ('Flauta Transversal YFL-212', '2019-11-20', 'Flauta em dó em excelente estado', 2, 2, NULL);