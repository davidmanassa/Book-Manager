CREATE TABLE Utilizador (
    ID_Utilizador int NOT NULL AUTO_INCREMENT,
    Nome_Utilizador varchar(255) NOT NULL,
    Salt varchar(255),
    Password varchar(255),
    PRIMARY KEY (ID_Utilizador)
);


CREATE TABLE Livro (
    ID_Livro int NOT NULL AUTO_INCREMENT,
    Nome_Livro varchar(255) NOT NULL,
    Autor_Livro varchar(255),
    Edicao_Livro int,
    PRIMARY KEY (ID_Livro)
);



CREATE TABLE Pedido (
    ID_Pedido int NOT NULL AUTO_INCREMENT,
    ID_Utilizador int,
    ID_Livro int,
    Date int,

    PRIMARY KEY (ID_pedido),

    FOREIGN KEY (ID_Utilizador) REFERENCES Utilizador(ID_Utilizador),
    FOREIGN KEY (ID_Livro) REFERENCES Livro(ID_Livro)

);


