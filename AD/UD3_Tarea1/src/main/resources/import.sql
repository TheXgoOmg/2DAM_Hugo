-- Insertar motores de BMW míticos
INSERT INTO Motor (codigo_motor, tipo, cilindrada, anio_fabricacion, potencia_hp)
VALUES
('M10B18', 'Gasolina', 1.8, 1973, 170),    -- BMW 2002 Turbo
('S14B23', 'Gasolina', 2.3, 1986, 192),    -- BMW E30 M3
('S54B32', 'Gasolina', 3.2, 2001, 343),    -- BMW E46 M3
('V8L68', 'Gasolina', 4.8, 1956, 150),     -- BMW 507
('M88/1', 'Gasolina', 3.5, 1978, 273);     -- BMW M1

-- Insertar mecánicos legendarios
INSERT INTO Mecanico (nombre, experiencia_anios, taller)
VALUES
('Maximilian Schwarz', 40, 'Classic BMW Restoration'),
('Dieter Braun', 25, 'Bavaria Motors'),
('Stefan Weber', 35, 'Autobahn Garage'),
('Fritz Müller', 50, 'München Performance');

-- Insertar chasis y modelos míticos
INSERT INTO Chasis (numero_chasis, modelo, serie, anio, color_original, id_motor, id_mecanico)
VALUES
('CHS1973001', 'BMW 2002 Turbo', '02', 1973, 'Blanco Alpino', 1, 1),
('CHS1986023', 'BMW E30 M3', 'E30', 1986, 'Brillante Rot', 2, 2),
('CHS2001030', 'BMW E46 M3', 'E46', 2001, 'Interlagos Blue', 3, 3),
('CHS1956007', 'BMW 507', '507', 1956, 'Blanco Marfil', 4, 4),
('CHS1978002', 'BMW M1', 'M1', 1978, 'Naranja', 5, 5);

-- Relación N:M: mecánicos certificados en motores
INSERT INTO Mecanico_Motor (id_mecanico, id_motor, fecha_certificacion)
VALUES
(1, 1, '2010-08-01'),
(2, 2, '2015-09-15'),
(3, 3, '2018-06-20'),
(4, 4, '2020-11-30'),
(5, 5, '2022-03-05');
