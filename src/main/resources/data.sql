INSERT INTO tblestadoshabitacion (id_estado, nombre_estado,
visible_en_seleccion, predeterminado_para_cierre) VALUES
(1, 'limpia',1,0),
(2, 'ocupada',1,0),
(3, 'sucia',0,1)
ON DUPLICATE KEY UPDATE id_estado = VALUES(id_estado);

INSERT INTO tbltipopago (id_pago, descripcion_pago) VALUES
(1, 'efectivo'),
(2, 'credito'),
(3, 'debito')
ON DUPLICATE KEY UPDATE id_pago = VALUES(id_pago);