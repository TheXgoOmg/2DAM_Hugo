DROP DATABASE IF EXISTS sanderson_universos;
CREATE DATABASE sanderson_universos;
USE sanderson_universos;

-- TABLA UNIVERSO
CREATE TABLE universo (
    idUniverso INT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL
);

-- TABLA SAGA
CREATE TABLE saga (
    idSaga INT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    idUniverso INT NOT NULL,
    FOREIGN KEY (idUniverso) REFERENCES universo(idUniverso)
);

-- TABLA LIBRO
CREATE TABLE libro (
    codigo INT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    idSaga INT NOT NULL,
    FOREIGN KEY (idSaga) REFERENCES saga(idSaga)
);

-- UNIVERSOS
INSERT INTO universo VALUES
(1, 'Cosmere'),
(2, 'No Cosmere');

-- SAGAS COSMERE
INSERT INTO saga VALUES
(101, 'El Archivo de las Tormentas', 1),
(102, 'Nacidos de la Bruma - Era 1', 1),
(103, 'Nacidos de la Bruma - Era 2', 1),
(104, 'Elantris', 1),
(105, 'Warbreaker', 1),
(106, 'Secret Projects (Cosmere)', 1);

-- LIBROS COSMERE
INSERT INTO libro VALUES
(1, 'El Camino de los Reyes', 101),
(2, 'Palabras Radiantes', 101),
(3, 'Juramentada', 101),
(4, 'El Ritmo de la Guerra', 101),
(5, 'Wind and Truth', 101),

(11, 'El Imperio Final', 102),
(12, 'El Pozo de la Ascensión', 102),
(13, 'El Héroe de las Eras', 102),

(21, 'Aleación de Ley', 103),
(22, 'Sombras de Identidad', 103),
(23, 'Brazales de Duelo', 103),
(24, 'El Metal Perdido', 103),

(31, 'Elantris', 104),
(32, 'El Alma del Emperador', 104),

(41, 'El Aliento de los Dioses', 105),

(51, 'Tress of the Emerald Sea', 106),
(52, 'Yumi and the Nightmare Painter', 106),
(53, 'The Sunlit Man', 106);

-- SAGAS NO COSMERE
INSERT INTO saga VALUES
(201, 'Skyward (Cytoverse)', 2),
(202, 'The Reckoners', 2),
(203, 'Alcatraz vs. los Bibliotecarios Malvados', 2),
(204, 'Legion', 2),
(205, 'Rithmatist', 2),
(206, 'Secret Projects (No Cosmere)', 2);

-- LIBROS NO COSMERE
INSERT INTO libro VALUES
(1011, 'Skyward', 201),
(1012, 'Starsight', 201),
(1013, 'Cytonic', 201),
(1014, 'Defiant', 201),

(1021, 'Steelheart', 202),
(1022, 'Firefight', 202),
(1023, 'Calamity', 202),

(1031, 'Alcatraz Versus the Evil Librarians', 203),
(1032, 'The Scrivener’s Bones', 203),
(1033, 'The Knights of Crystallia', 203),
(1034, 'The Shattered Lens', 203),
(1035, 'The Dark Talent', 203),
(1036, 'Bastille vs. the Evil Librarians', 203),

(1041, 'Legion', 204),
(1042, 'Skin Deep', 204),
(1043, 'Lies of the Beholder', 204),

(1051, 'El Rithmatista', 205),

(1061, 'The Frugal Wizard’s Handbook for Surviving Medieval England', 206);

