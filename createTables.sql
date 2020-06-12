CREATE DATABASE BookManager;

USE BookManager;

CREATE TABLE Utilizador (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    Username varchar(30) NOT NULL,
    Password varchar(255),
    
    PRIMARY KEY (ID)
);

CREATE TABLE Livro (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    Name varchar(255) NOT NULL,
    Author varchar(255),
    Edition varchar(255),
    
    PRIMARY KEY (ID)
);

CREATE TABLE Pedido (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    UserID INTEGER NOT NULL,
    BookID INTEGER NOT NULL,
    Date BIGINT,
    ReturnDate BIGINT DEFAULT NULL,
    
    PRIMARY KEY (ID),

    FOREIGN KEY (UserID) REFERENCES Utilizador(ID),
    FOREIGN KEY (BookID) REFERENCES Livro(ID)

);