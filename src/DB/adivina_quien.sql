-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-06-2025 a las 04:44:43
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `adivina_quien`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugadores`
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
-- Volcado de datos para la tabla `jugadores`
--

INSERT INTO `jugadores` (`id`, `nombre_jugador`, `profile`, `victorias`, `derrotas`, `edad`, `personaje_adivinado`, `tiempo_juego`, `intentos`, `fecha_partida`, `jugador_vs`, `ranking`) VALUES
(10, 'AnaLorena', '/Imagenes/profileIcons/mike.jpg', 0, 0, 22, NULL, NULL, 0, NULL, NULL, 0),
(11, 'TaniaPaola', '/Imagenes/profileIcons/nemo.jpg', 3, 1, 19, 'Ralph', '00:00:21', 0, '2025-06-22', 'AnaLorena', 100),
(12, 'LorenzoAntonio', '/Imagenes/profileIcons/mcqueen.jpg', 0, 4, 20, 'Carl', '00:00:41', 3, '2025-06-22', 'TaniaPaola', -200),
(13, 'MarielVillalpando', '/Imagenes/profileIcons/walle.jpg', 2, 1, 23, 'Rapunzel', '00:00:37', 1, '2025-06-22', 'TaniaPaola', 50),
(14, 'JoelNarvaez', '/Imagenes/profileIcons/enojo.jpg', 0, 1, 20, 'Dash', '00:01:07', 3, '2025-06-22', 'Guest', -50),
(15, 'Guest', '/Imagenes/profileIcons/lilo.png', 1, 0, 18, 'Dash', '00:01:07', 1, '2025-06-22', 'Guest', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidas`
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
-- Volcado de datos para la tabla `partidas`
--

INSERT INTO `partidas` (`id`, `jugador1`, `jugador2`, `ganador`, `personaje_ganador`, `fecha`, `duracion`) VALUES
(38, 'Guest', 'JoelNarvaez', 'JoelNarvaez', '/Imagenes/Personajes/bella.png', '2025-06-22', '00:03:44'),
(39, 'LorenzoAntonio', 'TaniaPaola', 'TaniaPaola', '/Imagenes/Personajes/carl.png', '2025-06-22', '00:00:41'),
(40, 'TaniaPaola', 'AnaLorena', 'TaniaPaola', '/Imagenes/Personajes/ralph.png', '2025-06-22', '00:00:21'),
(41, 'LorenzoAntonio', 'JoelNarvaez', 'LorenzoAntonio', '/Imagenes/Personajes/carl.png', '2025-06-22', '00:00:46'),
(42, 'AnaLorena', 'JoelNarvaez', 'JoelNarvaez', '/Imagenes/Personajes/bella.png', '2025-06-22', '00:02:46'),
(43, 'Guest', 'TaniaPaola', 'TaniaPaola', '/Imagenes/Personajes/ralph.png', '2025-06-22', '00:01:46'),
(44, 'MarielVillalpando', 'Guest', 'MarielVillalpando', '/Imagenes/Personajes/jasmine.png', '2025-06-22', '00:01:35'),
(45, 'LorenzoAntonio', 'MarielVillalpando', 'LorenzoAntonio', '/Imagenes/Personajes/pocahontas.png', '2025-06-22', '00:01:21');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas_adivinaquien`
--

CREATE TABLE `preguntas_adivinaquien` (
  `id` int(11) NOT NULL,
  `pregunta` varchar(255) NOT NULL,
  `activa` tinyint(1) DEFAULT 1,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `preguntas_adivinaquien`
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
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `jugadores`
--
ALTER TABLE `jugadores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre_jugador` (`nombre_jugador`);

--
-- Indices de la tabla `partidas`
--
ALTER TABLE `partidas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `preguntas_adivinaquien`
--
ALTER TABLE `preguntas_adivinaquien`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `jugadores`
--
ALTER TABLE `jugadores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `partidas`
--
ALTER TABLE `partidas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `preguntas_adivinaquien`
--
ALTER TABLE `preguntas_adivinaquien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
