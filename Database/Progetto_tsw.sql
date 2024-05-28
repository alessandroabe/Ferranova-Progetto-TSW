drop
    database if exists Progetto_tsw;
create database Progetto_tsw;
use Progetto_tsw;
CREATE TABLE Utente
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    nome         VARCHAR(30)  NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    passwordhash VARCHAR(100)  NOT NULL,
    telefono     VARCHAR(13)  NOT NULL,
    is_admin     BOOLEAN      NOT NULL DEFAULT FALSE
    /* potrebbe essere sostituita con enumerazione se si dovessero aggiungere altri ruoli*/
);
CREATE TABLE Indirizzo
(
    città    varchar(30) not null,
    n_civico varchar(30) not null,
    via      varchar(30) not null,
    cap      varchar(30) not null,
    PRIMARY KEY (città, n_civico, via, cap)
);

CREATE TABLE Utente_Indirizzo
(
    id_utente  INT         NOT NULL,
    is_default boolean     not null default false,
    città      varchar(30) not null,
    n_civico   varchar(30) not null,
    via        varchar(30) not null,
    cap        varchar(30) not null,
    PRIMARY KEY (
                 città, n_civico, via, cap, id_utente
        ),
    FOREIGN KEY (città, n_civico, via, cap) references Indirizzo (città, n_civico, via, cap),
    FOREIGN KEY (id_utente) references Utente (id)
);
CREATE TABLE Promozione
(
    id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    titolo      VARCHAR(255)    NOT NULL,
    descrizione text,
    sconto      int unsigned    NOT NULL,
    CHECK (sconto <= 100)
);
CREATE TABLE Categoria
(
    sigla       varchar(30) PRIMARY KEY NOT NULL,
    descrizione text
);
CREATE TABLE Prodotto
(
    id            INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    titolo        VARCHAR(255)    NOT NULL,
    descrizione   TEXT,
    quantità      INT UNSIGNED    NOT NULL,
    id_promozione INT,
    categoria     VARCHAR(30)     NOT NULL,
    prezzo        DECIMAL(13, 2)  NOT NULL,
    FOREIGN KEY (id_promozione)
        REFERENCES Promozione (id),
    FOREIGN KEY (categoria)
        REFERENCES Categoria (sigla)
);

/* forse sul carrello il prezzo finale è un pò overhead ma soprattutto confisionario,
 perchè alla fine il prodotto non è ancora stato aquistato
 quindi il prezzo dovrebbe essere il linea con quello del prodotto con le promozioni attuali*/
CREATE TABLE Carrello
(
    id_utente     INT            NOT NULL,
    id_prodotto   INT            NOT NULL,
    quantità      int unsigned   not null,
    prezzo_finale decimal(13, 2) not null,
    PRIMARY KEY (id_utente, id_prodotto),
    FOREIGN KEY (id_utente) references Utente (id),
    FOREIGN KEY (id_prodotto) references Prodotto (id)
);
CREATE TABLE Ordine
(
    id               INT primary key                                        NOT NULL AUTO_INCREMENT,
    id_utente        INT                                                    NOT NULL,
    stato_ordine     enum ( 'consegnato', 'spedito', 'disperso','ordinato') not null default 'ordinato',
    prezzo_totale    decimal(13, 2)                                         not null,
    spese_spedizione decimal(13, 2)                                         not null default 0.00,
    data_ordine      date                                                   not null,
    data_spedizione  date,
    data_consegna    date,
    tipo_pagamento   varchar(40)                                            not null default 'carta di credito',

    /* su questo non sono deciso, male che va facciamo come nell'esercizio birre,
       cioè che c'è un credito interno all'applicazione per utente, un pò rustica ma efficace  */
    FOREIGN KEY (id_utente) references Utente (id)
);
CREATE TABLE Ordine_Prodotto
(
    id_ordine     INT            NOT NULL,
    id_prodotto   INT            NOT NULL,
    quantità      int unsigned   not null,
    prezzo_finale decimal(13, 2) not null,
    PRIMARY KEY (id_ordine, id_prodotto),
    FOREIGN KEY (id_ordine) references Ordine (id),
    FOREIGN KEY (id_prodotto) references Prodotto (id)
);
CREATE TABLE Recensione
(
    id_utente   INT          NOT NULL,
    id_prodotto INT          NOT NULL,
    voto        int unsigned NOT NULL,
    CHECK (voto <= 10),
    descrizione text         not null,
    PRIMARY KEY (id_utente, id_prodotto),
    FOREIGN KEY (id_utente) references Utente (id),
    FOREIGN KEY (id_prodotto) references Prodotto (id)
);


INSERT INTO Utente (nome, email, passwordhash, telefono, is_admin)
VALUES ('Domenico', 'Domenico.admin@unisa.com', sha1('c\'è poco da dire'), '1234567890', TRUE),
       ('Alesssandro', 'Alessandro.admin@unisa.com', sha1('c\'è tanto da fare'), '3210987654', TRUE),
       ('Christian', 'Christian.admin@unisa.com', sha1('bullo'), '3210987654', TRUE),
       ('Domenico', 'Domenico.user@unisa.com', sha1('c\'è poco da dire'), '1234567890', FALSE),
       ('Alesssandro', 'Alessandro.user@unisa.com', sha1('c\'è tanto da fare'), '3210987654', FALSE),
       ('Christian', 'Christian.user@unisa.com', sha1('bullo'), '3210987654', FALSE);


INSERT INTO Indirizzo (città, n_civico, via, cap)
VALUES ('Roma', '10', 'Via Roma', '00187'),
       ('Milano', '25', 'Via Milano', '20121');

INSERT INTO Utente_Indirizzo (id_utente, is_default, città, n_civico, via, cap)
VALUES (4, TRUE, 'Roma', '10', 'Via Roma', '00187'),
       (5, TRUE, 'Roma', '10', 'Via Roma', '00187'),
       (6, TRUE, 'Roma', '10', 'Via Roma', '00187');

INSERT INTO Promozione (titolo, descrizione, sconto)
VALUES ('Natale 2023', 'Sconto del 10% su tutti i prodotti', 10),
       ('Sconto Invernale', 'Sconto del 15% su abbigliamento', 15),
       ('Agratis', 'Sconto del 100% su qualsiasi prodotto', 15);


INSERT INTO Categoria (sigla, descrizione)
VALUES ('Agricoltura', 'Utensili e prodotti agricoli'),
       ('Falegnameria', 'Utensili e prodotti per la lavorazione del legno'),
       ('Ferramenta', 'Utensili e prodotti di ferramanta'),
       ('Edilizia', 'Utensili e prodotti per la costruzione di case, pavimentazioni e infissi'),
       ('Energia', 'Pannelli solari, pale eoliche domestiche e condensatori');


INSERT INTO Prodotto (titolo, descrizione, quantità, id_promozione, categoria, prezzo)
VALUES ('Cacciavite a stella', 'Cacciavite per viti a stella', 2, NULL, 'Ferramenta', 19.90),
       ('Trapano a percussione', 'Trapano a percussione con kit di punte', 50, NULL, 'Ferramenta', 499.00);

INSERT INTO Carrello (id_utente, id_prodotto, quantità, prezzo_finale)
VALUES (4, 1, 3, 19.90),
       (4, 2, 1, 499.00);

INSERT INTO Ordine (id_utente, stato_ordine, prezzo_totale, data_ordine)
VALUES (4, 'ordinato', 558.7, '2024-05-14');

INSERT INTO Ordine_Prodotto (id_ordine, id_prodotto, quantità, prezzo_finale)
VALUES (1, 1, 3, 39.80),
       (1, 2, 1, 499.00);



INSERT INTO Recensione (id_utente, id_prodotto, voto, descrizione)
VALUES (4, 1, 5, 'Ottimo prodotto! Spedizione veloce e prodotto conforme alla descrizione.'),
       (4, 2, 4, 'Il prodotto è arrivato con un leggero ritardo, ma per il resto sono soddisfatto.');
