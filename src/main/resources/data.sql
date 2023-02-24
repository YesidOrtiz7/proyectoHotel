--
-- Volcado de datos para la tabla `tblcliente`
--
INSERT INTO `tblcliente` (`id_cliente`, `celular_cliente`, `documento_cliente`, `primer_apellido_cliente`, `primer_nombre_cliente`, `segundo_apellido_cliente`, `segundo_nombre_cliente`) VALUES
(1, '111111', '1111', 'apellido cliente 1', 'nombre cliente 1', NULL, NULL),
(2, '222222', '2222', 'apellido cliente 2', 'nombre cliente 2', NULL, NULL),
(3, '333333', '3333', 'apellido cliente 3', 'nombre cliente 3', NULL, NULL),
(4, '444444', '4444', 'apellido cliente 4', 'nombre cliente 4', NULL, NULL);

-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `tblestadoshabitacion`
--
INSERT INTO `tblestadoshabitacion` (`id_estado`, `nombre_estado`) VALUES
(1, 'limpia'),
(2, 'sucia'),
(3, 'ocupada');

-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `tblhabitacion`
--

INSERT INTO `tblhabitacion` (`id_habitacion`, `num_habitacion`, `numero_camas`, `precio_habitacion_noche`, `id_est_hab`, `tipo_habitacion`) VALUES
(1, 11, 1, 111, 3, 1),
(2, 12, 1, 111, 3, 1),
(3, 13, 1, 111, 1, 1),
(4, 14, 1, 111, 3, 1);

-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `tblmunicipios`
--

INSERT INTO `tblmunicipios` (`id_municipios`, `nombre_mun`) VALUES
(1, 'municipio 1'),
(2, 'municipio 2'),
(3, 'municipio 3'),
(4, 'municipio 4'),
(5, 'municipio 5');

-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `tblrecepcionista`
--

INSERT INTO `tblrecepcionista` (`id_recep`, `apellidos_recep`, `doc_recep`, `nombres_recep`) VALUES
(1, 'recepcionista apellidos 1', '1111', 'recepcionista nombre 1'),
(2, 'recepcionista apellidos 2', '2222', 'recepcionista nombre 2'),
(3, 'recepcionista apellidos 3', '3333', 'recepcionista nombre 3'),
(4, 'recepcionista apellidos 4', '4444', 'recepcionista nombre 4'),
(5, 'recepcionista apellidos 5', '5555', 'recepcionista nombre 5');

-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `tblservicio`
--

INSERT INTO `tblservicio` (`id_servicio`, `estado`, `fecha_entrada`, `fecha_salida`, `pago`, `cli_destino`, `cli_procedencia`, `id_cliente`, `id_habitacion`, `id_recep`, `id_tipo_pago`, `id_tipo_tarifa`) VALUES
(1, 1, '2023-02-21 11:11:06', '2023-02-22 11:11:06', 0, 5, 1, 1, 2, 2, 1, 1),
(2, 1, '2023-02-21 11:27:25', '2023-02-22 11:27:25', 0, 5, 1, 3, 1, 2, 1, 2),
(3, 1, '2023-02-21 11:32:14', '2023-02-26 11:33:39', 0, 5, 1, 4, 4, 4, 2, 3);

-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `tbltipohabitacion`
--

INSERT INTO `tbltipohabitacion` (`id_tipo_habitacion`, `descripcion_tipo_habitacion`) VALUES
(1, 'tipo habitacion 1'),
(2, 'tipo habitacion 2'),
(3, 'tipo habitacion 3'),
(4, 'tipo habitacion 4');

-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `tbltipopago`
--

INSERT INTO `tbltipopago` (`id_pago`, `descripcion_pago`) VALUES
(1, 'debito'),
(2, 'credito'),
(3, 'efectivo');

-- --------------------------------------------------------
--
-- Volcado de datos para la tabla `tbltipotarifa`
--

INSERT INTO `tbltipotarifa` (`id_tipo_tarifa`, `descripcion_tarifa`, `porcentaje_tarifa`) VALUES
(1, 'tarifa 1', 10),
(2, 'tarifa 2', 20),
(3, 'tarifa 3', 30);
