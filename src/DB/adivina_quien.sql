-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2025 at 06:51 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `adivina_quien`
--

-- --------------------------------------------------------

--
-- Table structure for table `jugadores`
--

CREATE TABLE `jugadores` (
  `id` int(11) NOT NULL,
  `nombre_jugador` varchar(50) NOT NULL,
  `profile` varchar(255) NOT NULL,
  `victorias` int(11) DEFAULT 0,
  `derrotas` int(11) DEFAULT 0,
  `edad` int(11) NOT NULL,
  `personaje_adivinado` varchar(100) DEFAULT NULL,
  `tiempo_juego` time DEFAULT NULL,
  `intentos` int(11) DEFAULT 0,
  `fecha_partida` date DEFAULT NULL,
  `jugador_vs` varchar(50) DEFAULT NULL,
  `ranking` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jugadores`
--

INSERT INTO `jugadores` (`id`, `nombre_jugador`, `profile`, `victorias`, `derrotas`, `edad`, `personaje_adivinado`, `tiempo_juego`, `intentos`, `fecha_partida`, `jugador_vs`, `ranking`) VALUES
(10, 'AnaLorena', '/Imagenes/profileIcons/mike.jpg', 0, 0, 22, NULL, NULL, 0, NULL, NULL, 0),
(11, 'TaniaPaola', '/Imagenes/profileIcons/nemo.jpg', 0, 0, 19, NULL, NULL, 0, NULL, NULL, 0),
(12, 'LorenzoAntonio', '/Imagenes/profileIcons/mcqueen.jpg', 0, 0, 20, NULL, NULL, 0, NULL, NULL, 0),
(13, 'MarielVillalpando', '/Imagenes/profileIcons/walle.jpg', 0, 0, 23, NULL, NULL, 0, NULL, NULL, 0),
(14, 'JoelNarvaez', '/Imagenes/profileIcons/enojo.jpg', 0, 0, 20, NULL, NULL, 0, NULL, NULL, 0),
(15, 'Guest', '/Imagenes/profileIcons/lilo.png', 0, 0, 18, NULL, NULL, 0, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `partidas`
--

CREATE TABLE `partidas` (
  `id` int(11) NOT NULL,
  `jugador1` varchar(100) DEFAULT NULL,
  `jugador2` varchar(100) DEFAULT NULL,
  `ganador` varchar(100) DEFAULT NULL,
  `personaje_ganador` varchar(100) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `duracion` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `partidas`
--

INSERT INTO `partidas` (`id`, `jugador1`, `jugador2`, `ganador`, `personaje_ganador`, `fecha`, `duracion`) VALUES
(1, 'MarielVillalpando', 'JoelNarvaez', 'JoelNarvaez', '/Imagenes/Personajes/aladdin.png', '2025-06-17', '00:11:52'),
(2, 'AnaLorena', 'TaniaPaola', 'AnaLorena', '/Imagenes/Personajes/carl.png', '2025-06-17', '00:24:52'),
(3, 'JoelNarvaez', 'TaniaPaola', 'JoelNarvaez', '/Imagenes/Personajes/dash.png', '2025-06-17', '00:26:28'),
(4, 'AnaLorena', 'JoelNarvaez', 'JoelNarvaez', '/Imagenes/Personajes/lilo.png', '2025-06-17', '00:09:42');

-- --------------------------------------------------------

--
-- Table structure for table `preguntas_adivinaquien`
--

CREATE TABLE `preguntas_adivinaquien` (
  `id` int(11) NOT NULL,
  `pregunta` varchar(255) NOT NULL,
  `activa` tinyint(1) DEFAULT 1,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `preguntas_adivinaquien`
--

INSERT INTO `preguntas_adivinaquien` (`id`, `pregunta`, `activa`, `fecha_registro`) VALUES
(1, '¿Es hombre?', 1, '2025-06-12 17:00:49'),
(2, '¿Es mujer?', 1, '2025-06-12 17:00:49'),
(3, '¿Tiene cabello rubio?', 1, '2025-06-12 17:00:49'),
(4, '¿Tiene cabello oscuro?', 1, '2025-06-12 17:00:49'),
(5, '¿Usa lentes?', 1, '2025-06-12 17:00:49'),
(6, '¿Lleva sombrero o gorra?', 1, '2025-06-12 17:00:49'),
(7, '¿Tiene cabello largo?', 1, '2025-06-12 17:00:49'),
(8, '¿Tiene barba o bigote?', 1, '2025-06-12 17:00:49'),
(9, '¿Está sonriendo?', 1, '2025-06-12 17:00:49'),
(10, '¿Tiene ojos claros?', 1, '2025-06-12 17:00:49'),
(11, '¿Tiene cabello rizado?', 1, '2025-06-12 17:00:49'),
(12, '¿Usa accesorios?', 1, '2025-06-12 17:00:49'),
(13, '¿Parece joven?', 1, '2025-06-12 17:00:49'),
(14, '¿Parece mayor?', 1, '2025-06-12 17:00:49'),
(15, '¿Tiene piel clara?', 1, '2025-06-12 17:00:49'),
(16, '¿Tiene piel oscura?', 1, '2025-06-12 17:00:49'),
(17, '¿Viste ropa brillante?', 1, '2025-06-12 17:00:49'),
(18, '¿Tiene cejas gruesas?', 1, '2025-06-12 17:00:49'),
(19, '¿Tiene expresión seria?', 1, '2025-06-12 17:00:49'),
(20, '¿Tiene pecas o marcas?', 1, '2025-06-12 17:00:49');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jugadores`
--
ALTER TABLE `jugadores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre_jugador` (`nombre_jugador`);

--
-- Indexes for table `partidas`
--
ALTER TABLE `partidas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `preguntas_adivinaquien`
--
ALTER TABLE `preguntas_adivinaquien`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jugadores`
--
ALTER TABLE `jugadores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `partidas`
--
ALTER TABLE `partidas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `preguntas_adivinaquien`
--
ALTER TABLE `preguntas_adivinaquien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
