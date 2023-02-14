-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-02-2023 a las 22:44:57
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel`
--

-- --------------------------------------------------------
CREATE DATABASE hotel;
use hotel;

--
-- Estructura de tabla para la tabla `tblcliente`
--

CREATE TABLE `tblcliente` (
  `id_cliente` int(11) NOT NULL,
  `celular_cliente` varchar(255) NOT NULL,
  `documento_cliente` varchar(255) NOT NULL,
  `primer_apellido_cliente` varchar(255) NOT NULL,
  `segundo_apellido_cliente` varchar(255) DEFAULT NULL,
  `primer_nombre_cliente` varchar(255) NOT NULL,
  `segundo_nombre_cliente` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tblcliente`
  ADD PRIMARY KEY (`id_cliente`),
  MODIFY `id_cliente` int(6) NOT NULL AUTO_INCREMENT;

--
-- Volcado de datos para la tabla `tblcliente`
--

INSERT INTO `tblcliente` (`id_cliente`, `celular_cliente`, `documento_cliente`, `primer_apellido_cliente`,  `segundo_apellido_cliente`, `primer_nombre_cliente`,`segundo_nombre_cliente`) VALUES
(1, '789456', '789456', 'ortiz', NULL, 'yesid', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblestadoshabitacion`
--

CREATE TABLE `tblestadoshabitacion` (
  `id_estado` int(11) NOT NULL ,
  `nombre_estado` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tblestadoshabitacion` 
  add PRIMARY KEY(`id_estado`),
  MODIFY `id_estado` int(2) NOT NULL AUTO_INCREMENT;
INSERT INTO `tblestadoshabitacion` (`id_estado`, `nombre_estado`) VALUES (NULL, 'limpia'), (NULL, 'sucia'), (NULL, 'ocupada');
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbltipohabitacion`
--

CREATE TABLE `tbltipohabitacion` (
  `id_tipo_habitacion` int(11) NOT NULL ,
  `descripcion_tipo_habitacion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tbltipohabitacion` 
    ADD PRIMARY KEY(`id_tipo_habitacion`),
    MODIFY `id_tipo_habitacion` int(2) NOT NULL AUTO_INCREMENT;

INSERT INTO `tbltipohabitacion` (`id_tipo_habitacion`, `descripcion_tipo_habitacion`) VALUES (NULL, 'doble'), (NULL, 'sencilla');
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblhabitacion`
--

CREATE TABLE `tblhabitacion` (
  `id_habitacion` int(11) NOT NULL ,
  `num_habitacion` int(11) NOT NULL,
  `numero_camas` int(11) NOT NULL,
  `id_est_hab` int(11) NOT NULL,
  `tipo_habitacion` int(11) NOT NULL,
  `precio_habitacion_noche` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tblhabitacion`
  ADD PRIMARY KEY (`id_habitacion`),
  MODIFY `id_habitacion` int(11) NOT NULL AUTO_INCREMENT,
  ADD FOREIGN KEY (`id_est_hab`) REFERENCES `tblestadoshabitacion`(`id_estado`),
  ADD FOREIGN KEY (`tipo_habitacion`) REFERENCES `tbltipohabitacion` (`id_tipo_habitacion`);

INSERT INTO `tblhabitacion` (`id_habitacion`, `num_habitacion`, `numero_camas`, `id_est_hab`, `tipo_habitacion`) VALUES (NULL, '301', '2', '1', '1'), (NULL, '302', '1', '1', '2');
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblmunicipios`
--

CREATE TABLE `tblmunicipios` (
  `id_municipios` int(11) NOT NULL ,
  `nombre_mun` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tblmunicipios`
  ADD PRIMARY KEY (`id_municipios`),
  MODIFY `id_municipios` int(3) NOT NULL AUTO_INCREMENT;
INSERT INTO `tblmunicipios` (`id_municipios`, `nombre_mun`) VALUES (NULL, 'Arbelaez'), (NULL, 'Fusagasuga'), (NULL, 'San Bernardo');
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblrecepcionista`
--

CREATE TABLE `tblrecepcionista` (
  `id_recep` int(11) NOT NULL ,
  `apellidos_recep` varchar(255) NOT NULL,
  `doc_recep` varchar(255) NOT NULL,
  `nombres_recep` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tblrecepcionista`
  ADD PRIMARY KEY (`id_recep`),
  MODIFY `id_recep` int(2) NOT NULL AUTO_INCREMENT;

INSERT INTO `tblrecepcionista` (`id_recep`, `apellidos_recep`, `doc_recep`, `nombres_recep`) VALUES (NULL, 'Ortega Ordoñez', '456123', 'Maria');

CREATE TABLE `tblrecepcionista_login`(
    `id_recep_login` int(11) NOT NULL ,
    `recep` int(2) NOT NULL ,
    `nombre_usuario` varchar(255) NOT NULL,
    `contrasena` varchar(255) NOT NULL,
    `recep_estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tblrecepcionista_login`
  ADD PRIMARY KEY (`id_recep_login`),
  MODIFY `id_recep_login` int(2) NOT NULL AUTO_INCREMENT,
  ADD FOREIGN KEY (`recep`) REFERENCES `tblrecepcionista`(`id_recep`);
INSERT INTO `tblrecepcionista_login` (`id_recep_login`, `recep`, `nombre_usuario`, `contrasena`, `recep_estado`) VALUES (NULL, 1, 'mariaOrte', 'asdfasdf', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbltipopago`
--

CREATE TABLE `tbltipopago` (
  `id_pago` int(11) NOT NULL ,
  `descripcion_pago` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tbltipopago`
  ADD PRIMARY KEY (`id_pago`),
  MODIFY `id_pago` int(3) NOT NULL AUTO_INCREMENT;
INSERT INTO `tbltipopago` (`id_pago`, `descripcion_pago`) VALUES (NULL, 'debito'), (NULL, 'credito'), (NULL, 'efectivo');
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbltipotarifa`
--

CREATE TABLE `tbltipotarifa` (
  `id_tipo_tarifa` int(11) NOT NULL ,
  `descripcion_tarifa` varchar(255) NOT NULL,
  `porcentaje_tarifa` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tbltipotarifa`
  ADD PRIMARY KEY (`id_tipo_tarifa`),
  MODIFY `id_tipo_tarifa` int(3) NOT NULL AUTO_INCREMENT;

  INSERT INTO `tbltipotarifa` (`id_tipo_tarifa`, `descripcion_tarifa`) VALUES (NULL, 'Normal'), (NULL, 'Transportador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblservicio`
--

CREATE TABLE `tblservicio` (
  `id_servicio` int(11) NOT NULL ,
  `fecha_entrada` datetime NOT NULL,
  `fecha_salida` datetime NOT NULL,
  `pago` int(11) NOT NULL,
  `cli_destino` int(11) NOT NULL,
  `cli_procedencia` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_habitacion` int(11) NOT NULL,
  `id_recep` int(11) NOT NULL,
  `id_tipo_pago` int(11) NOT NULL,
  `id_tipo_tarifa` int(11) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE `tblservicio`
  ADD PRIMARY KEY (`id_servicio`),
  MODIFY `id_servicio` int(10) NOT NULL AUTO_INCREMENT;
  ALTER TABLE `tblservicio`ADD FOREIGN KEY (`id_recep`) REFERENCES `tblrecepcionista` (`id_recep`);
  ALTER TABLE `tblservicio`ADD FOREIGN KEY (`id_cliente`) REFERENCES `tblcliente` (`id_cliente`);
  ALTER TABLE `tblservicio` ADD FOREIGN KEY (`id_habitacion`) REFERENCES `tblhabitacion` (`id_habitacion`);
  ALTER TABLE `tblservicio` ADD FOREIGN KEY (`id_tipo_tarifa`) REFERENCES `tbltipotarifa` (`id_tipo_tarifa`);
  ALTER TABLE `tblservicio` ADD FOREIGN KEY (`cli_procedencia`) REFERENCES `tblmunicipios` (`id_municipios`);
  ALTER TABLE `tblservicio` ADD FOREIGN KEY (`cli_destino`) REFERENCES `tblmunicipios` (`id_municipios`);
  ALTER TABLE `tblservicio` ADD FOREIGN KEY (`id_tipo_pago`) REFERENCES `tbltipopago` (`id_pago`);
