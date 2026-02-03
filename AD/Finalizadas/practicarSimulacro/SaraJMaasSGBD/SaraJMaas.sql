-- ===========================
--     CREATE DATABASE
-- ===========================

CREATE DATABASE IF NOT EXISTS SarahJMaasDB;
USE SarahJMaasDB;

-- ===========================
--     DROP OLD TABLES
-- ===========================

DROP TABLE IF EXISTS Books;
DROP TABLE IF EXISTS Series;
DROP TABLE IF EXISTS Universe;

-- ===========================
--     CREATE TABLES
-- ===========================

CREATE TABLE Universe (
                          idUniverse INT PRIMARY KEY,
                          name VARCHAR(200) NOT NULL
);

CREATE TABLE Series (
                        idSeries INT PRIMARY KEY,
                        idUniverse INT NOT NULL,
                        title VARCHAR(200) NOT NULL,
                        FOREIGN KEY (idUniverse) REFERENCES Universe(idUniverse)
);

CREATE TABLE Books (
                       idBook INT PRIMARY KEY,
                       idSeries INT NOT NULL,
                       code INT NOT NULL,
                       title VARCHAR(300) NOT NULL,
                       FOREIGN KEY (idSeries) REFERENCES Series(idSeries)
);

-- ===========================
--     INSERT DATA
-- ===========================

-- ----- UNIVERSES -----
INSERT INTO Universe (idUniverse, name) VALUES
                                            (1, 'Throne of Glass Universe'),
                                            (2, 'A Court of Thorns and Roses Universe'),
                                            (3, 'Crescent City Universe');

-- ----- SERIES -----
INSERT INTO Series (idSeries, idUniverse, title) VALUES
                                                     (101, 1, 'Throne of Glass'),
                                                     (201, 2, 'A Court of Thorns and Roses'),
                                                     (301, 3, 'Crescent City');

-- ===========================
--           BOOKS
-- ===========================

-- ----- THRONE OF GLASS -----
INSERT INTO Books (idBook, idSeries, code, title) VALUES
                                                      (1001, 101, 1, 'The Assassin''s Blade'),
                                                      (1002, 101, 2, 'Throne of Glass'),
                                                      (1003, 101, 3, 'Crown of Midnight'),
                                                      (1004, 101, 4, 'Heir of Fire'),
                                                      (1005, 101, 5, 'Queen of Shadows'),
                                                      (1006, 101, 6, 'Empire of Storms'),
                                                      (1007, 101, 7, 'Tower of Dawn'),
                                                      (1008, 101, 8, 'Kingdom of Ash');

-- ----- ACOTAR -----
INSERT INTO Books (idBook, idSeries, code, title) VALUES
                                                      (2001, 201, 1, 'A Court of Thorns and Roses'),
                                                      (2002, 201, 2, 'A Court of Mist and Fury'),
                                                      (2003, 201, 3, 'A Court of Wings and Ruin'),
                                                      (2004, 201, 4, 'A Court of Frost and Starlight'),
                                                      (2005, 201, 5, 'A Court of Silver Flames');

-- ----- CRESCENT CITY -----
INSERT INTO Books (idBook, idSeries, code, title) VALUES
                                                      (3001, 301, 1, 'House of Earth and Blood'),
                                                      (3002, 301, 2, 'House of Sky and Breath'),
                                                      (3003, 301, 3, 'House of Flame and Shadow');
