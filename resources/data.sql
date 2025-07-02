CREATE TABLE cliente
(
    idcliente BIGINT AUTO_INCREMENT NOT NULL,
    email     VARCHAR(255)          NOT NULL,
    login     VARCHAR(255)          NOT NULL,
    nome      VARCHAR(255)          NOT NULL,
    senha     VARCHAR(255)          NOT NULL,
    telefone  VARCHAR(255)          NOT NULL,
    cpf       VARCHAR(255)          NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (idcliente)
);

CREATE TABLE estadias
(
    id_estadia     INT AUTO_INCREMENT NOT NULL,
    data_entrada   date               NOT NULL,
    data_saida     date               NOT NULL,
    valor_total    DECIMAL(10, 2)     NOT NULL,
    cliente_codigo BIGINT             NOT NULL,
    quarto_codigo  INT                NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id_estadia)
);

CREATE TABLE quarto
(
    idquarto  INT AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(255)       NOT NULL,
    imagem    VARCHAR(255)       NULL,
    nome      VARCHAR(255)       NOT NULL,
    preco     DECIMAL(10, 2)     NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (idquarto)
);

CREATE TABLE usuario
(
    id_user        BIGINT AUTO_INCREMENT NOT NULL,
    login_user     VARCHAR(255)          NOT NULL,
    perfil         ENUM                  NOT NULL,
    senha          VARCHAR(255)          NOT NULL,
    cliente_codigo BIGINT                NULL,
    cpf            VARCHAR(255)          NOT NULL,
    email          VARCHAR(255)          NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id_user)
);

ALTER TABLE usuario
    ADD CONSTRAINT UK5171l57faosmj8myawaucatdw UNIQUE (email);

ALTER TABLE usuario
    ADD CONSTRAINT UK692bsnqxa8m9fmx7m1yc6hsui UNIQUE (cpf);

ALTER TABLE cliente
    ADD CONSTRAINT UKcmxo70m08n43599l3h0h07cc6 UNIQUE (email);

ALTER TABLE usuario
    ADD CONSTRAINT UKjkcswvcwiig50ipk8uk37eg3t UNIQUE (login_user);

ALTER TABLE usuario
    ADD CONSTRAINT UKnq2vyfy2p9j0tdkdh4u6udnpg UNIQUE (cliente_codigo);

ALTER TABLE cliente
    ADD CONSTRAINT UKp7phhm1xt3yxj5bl1rinpow1h UNIQUE (login);

ALTER TABLE usuario
    ADD CONSTRAINT FK4v3999g3mpc15yvo4s2j76ps2 FOREIGN KEY (cliente_codigo) REFERENCES cliente (idcliente) ON DELETE NO ACTION;

ALTER TABLE estadias
    ADD CONSTRAINT FK6s0lvlsmi5c9gx8vvujhdodsj FOREIGN KEY (cliente_codigo) REFERENCES cliente (idcliente) ON DELETE NO ACTION;

CREATE INDEX FK6s0lvlsmi5c9gx8vvujhdodsj ON estadias (cliente_codigo);

ALTER TABLE estadias
    ADD CONSTRAINT FKcih8msxa44wsh69hhpg87wd8l FOREIGN KEY (quarto_codigo) REFERENCES quarto (idquarto) ON DELETE NO ACTION;

CREATE INDEX FKcih8msxa44wsh69hhpg87wd8l ON estadias (quarto_codigo);