
DROP TABLE IF EXISTS ceps;

CREATE TABLE ceps (
    cep VARCHAR(8) PRIMARY KEY,
    cidade VARCHAR(250),
    uf VARCHAR(64),
    bairro VARCHAR(64),
    logradouro VARCHAR(250)
);

INSERT INTO ceps
    SELECT * FROM CSVREAD('classpath:ceps.csv', 'cep;cidade;uf;bairro;logradouro', 'charset=UTF-8 fieldSeparator=;');
