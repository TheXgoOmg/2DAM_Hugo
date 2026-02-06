USE Concesionario;
DROP TABLE IF EXISTS `fabricante`;
CREATE TABLE `fabricante` (
  `id_fabricante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `pais_origen` varchar(50) DEFAULT NULL,
  `anyo_fundacion` int DEFAULT NULL,
  PRIMARY KEY (`id_fabricante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO `fabricante` (`id_fabricante`, `nombre`, `pais_origen`, `anyo_fundacion`) VALUES
(1, 'Toyota', 'Japón', 1937),
(2, 'Volkswagen', 'Alemania', 1937),
(3, 'Ford', 'Estados Unidos', 1903),
(4, 'BMW', 'Alemania', 1916),
(5, 'Mercedes-Benz', 'Alemania', 1926),
(6, 'Renault', 'Francia', 1899),
(7, 'Ferrari', 'Italia', 1939),
(8, 'Tesla', 'Estados Unidos', 2003),
(9, 'Hyundai', 'Corea del Sur', 1967),
(10, 'Volvo', 'Suecia', 1927);



DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `cliente` (`id_cliente`, `dni`, `nombre`, `apellidos`, `telefono`, `fecha_registro`) VALUES
(1, '12345678A', 'Carlos', 'García López', '611222333', '2023-01-15'),
(2, '87654321B', 'Ana', 'Martínez Ruiz', '644555666', '2023-02-20'),
(3, '23456789C', 'Luis', 'Fernández Gómez', '677888999', '2023-03-10'),
(4, '98765432D', 'Marta', 'Rodríguez Santos', '600111222', '2023-04-05'),
(5, '34567890E', 'Javier', 'Pérez Díaz', '633444555', '2023-05-12'),
(6, '45678901F', 'Sofía', 'Sánchez Martín', '666777888', '2023-06-18'),
(7, '56789012G', 'David', 'González Castro', '699000111', '2023-07-22'),
(8, '67890123H', 'Elena', 'Torres Navarro', '622333444', '2023-08-30');



DROP TABLE IF EXISTS `vehiculo`;
CREATE TABLE `vehiculo` (
  `id_vehiculo` int NOT NULL AUTO_INCREMENT,
  `id_fabricante` int NOT NULL,
  `id_cliente` int DEFAULT NULL,
  `matricula` varchar(10) NOT NULL,
  `modelo` varchar(100) NOT NULL,
  `color` varchar(30) DEFAULT NULL,
  `kilometros` int DEFAULT 0,
  `anyo_fabricacion` int DEFAULT NULL,
  `tipo` enum('SEDAN','SUV','COUPE','HATCHBACK','DEPORTIVO','FAMILIAR','ELECTRICO') DEFAULT 'SEDAN',
  `estado` enum('DISPONIBLE','VENDIDO','RESERVADO','REPARACION') DEFAULT 'DISPONIBLE',
  `precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_vehiculo`),
  UNIQUE KEY `matricula` (`matricula`),
  KEY `idx_vehiculo_fabricante` (`id_fabricante`),
  KEY `idx_vehiculo_cliente` (`id_cliente`),
  CONSTRAINT `fk_vehiculo_fabricante` FOREIGN KEY (`id_fabricante`) REFERENCES `fabricante` (`id_fabricante`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_vehiculo_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO `vehiculo` (`id_vehiculo`, `id_fabricante`, `id_cliente`, `matricula`, `modelo`, `color`, `kilometros`, `anyo_fabricacion`, `tipo`, `estado`, `precio`) VALUES
(1, 1, 1, '1234ABC', 'Corolla', 'Blanco', 15000, 2023, 'SEDAN', 'VENDIDO', 25000.00),
(2, 1, 2, '5678DEF', 'RAV4', 'Negro', 45000, 2021, 'SUV', 'VENDIDO', 32000.00),
(3, 1, NULL, '9012GHI', 'Corolla', 'Gris', 8000, 2024, 'SEDAN', 'DISPONIBLE', 26000.00),
(4, 2, 3, '3456JKL', 'Golf', 'Rojo', 30000, 2022, 'HATCHBACK', 'VENDIDO', 28000.00),
(5, 2, NULL, '7890MNO', 'Tiguan', 'Azul', 120000, 2019, 'SUV', 'DISPONIBLE', 15000.00),
(6, 3, 4, '1122PQR', 'Mustang', 'Amarillo', 5000, 2024, 'COUPE', 'REPARACION', 55000.00),
(7, 3, NULL, '3344STU', 'Mustang', 'Negro', 20000, 2023, 'COUPE', 'DISPONIBLE', 52000.00),
(8, 4, 5, '5566VWX', 'Serie 3', 'Blanco', 25000, 2022, 'SEDAN', 'VENDIDO', 42000.00),
(9, 4, NULL, '7788YZA', 'Serie 5', 'Gris', 60000, 2021, 'SEDAN', 'DISPONIBLE', 38000.00),
(10, 5, 6, '9900BCD', 'Clase C', 'Negro', 35000, 2023, 'SEDAN', 'VENDIDO', 45000.00),
(11, 6, 1, '2233EFG', 'Clio', 'Blanco', 2000, 2024, 'HATCHBACK', 'VENDIDO', 18000.00),
(12, 6, NULL, '4456HIJ', 'Megane', 'Rojo', 80000, 2020, 'HATCHBACK', 'RESERVADO', 12000.00),
(13, 7, 2, '6678KLM', 'F8 Tributo', 'Rojo', 3000, 2023, 'DEPORTIVO', 'VENDIDO', 250000.00),
(14, 8, 3, '8890NOP', 'Model 3', 'Blanco', 10000, 2023, 'ELECTRICO', 'VENDIDO', 42000.00),
(15, 8, NULL, '0012QRS', 'Model Y', 'Negro', 500, 2024, 'ELECTRICO', 'DISPONIBLE', 45000.00),
(16, 9, 4, '2234TUV', 'Tucson', 'Gris', 40000, 2022, 'SUV', 'VENDIDO', 32000.00),
(17, 9, NULL, '4456WXY', 'Santa Fe', 'Azul', 25000, 2023, 'SUV', 'REPARACION', 34000.00),
(18, 10, 5, '6678ZAB', 'XC60', 'Negro', 18000, 2023, 'SUV', 'VENDIDO', 52000.00),
(19, 10, NULL, '8890CDE', 'XC90', 'Blanco', 70000, 2021, 'SUV', 'RESERVADO', 45000.00),
(20, 1, 6, '0012FGH', 'RAV4', 'Plateado', 22000, 2023, 'SUV', 'VENDIDO', 38000.00),
(21, 2, 7, '1123IJK', 'Passat', 'Negro', 55000, 2022, 'SEDAN', 'VENDIDO', 28000.00),
(22, 3, 8, '2234LMN', 'Focus', 'Rojo', 35000, 2023, 'HATCHBACK', 'VENDIDO', 22000.00),
(23, 1, NULL, '3345OPQ', 'Yaris', 'Azul', 15000, 2023, 'HATCHBACK', 'DISPONIBLE', 19000.00),
(24, 4, NULL, '4456RST', 'X5', 'Negro', 30000, 2022, 'SUV', 'DISPONIBLE', 65000.00),
(25, 5, 1, '5567UVW', 'Clase A', 'Blanco', 10000, 2024, 'SEDAN', 'VENDIDO', 35000.00);
