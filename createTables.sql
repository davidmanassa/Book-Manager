CREATE TABLE Utilizador (
    ID_Utilizador INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,  INCREMENT BY 1),
    Nome_Utilizador varchar(255) NOT NULL,
    Salt varchar(255),
    Password varchar(255),
    PRIMARY KEY (ID_Utilizador)
);


CREATE TABLE Livro (
    ID_Livro INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,  INCREMENT BY 1),
    Nome_Livro varchar(255) NOT NULL,
    Autor_Livro varchar(255),
    Edicao_Livro INTEGER,
    PRIMARY KEY (ID_Livro)
);



CREATE TABLE Pedido (
    ID_Pedido INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,  INCREMENT BY 1),
    ID_Utilizador INTEGER,
    ID_Livro INTEGER,
    Date INTEGER,

    PRIMARY KEY (ID_Pedido),

    FOREIGN KEY (ID_Utilizador) REFERENCES Utilizador(ID_Utilizador),
    FOREIGN KEY (ID_Livro) REFERENCES Livro(ID_Livro)

);
