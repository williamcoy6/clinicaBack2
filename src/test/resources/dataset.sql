-- | codigo | contrasenia | correo |
INSERT INTO administrador VALUES (1, '31drg354', 'admin1@example.com');
INSERT INTO administrador VALUES (2, 'f8hj92s', 'admin2@example.com');
INSERT INTO administrador VALUES (3, 'k5p7q1r', 'admin3@example.com');
INSERT INTO administrador VALUES (4, 'g3t2y6o', 'admin4@example.com');
INSERT INTO administrador VALUES (5, 'd9a4s7g', 'admin5@example.com');
INSERT INTO administrador VALUES (6, 'p2l6o8t', 'admin6@example.com');

-- | codigo | contrasena | correo | cedula | celular | ciudad | direccion | nombre | url_foto | alergias | eps | fecha_nacimiento | tipo_sancgre |
INSERT INTO paciente VALUES (1, 'p@ssw0rd1', 'usuario1@example.com', '1234567890', '1234567890', 1, 'Calle 123', 'Usuario 1', 'url1.jpg', 'Ninguna', 1, '1990-01-01', 1);
INSERT INTO paciente VALUES (2, 'p@ssw0rd2', 'usuario2@example.com', '9876543210', '9876543210', 2, 'Avenida 456', 'Usuario 2', 'url2.jpg', 'Polvo', 2, '1985-05-15', 2);
INSERT INTO paciente VALUES (3, 'p@ssw0rd3', 'usuario3@example.com', '5678901234', '5678901234', 3, 'Carrera 789', 'Usuario 3', 'url3.jpg', 'Cacahuetes', 2, '2000-10-22', 3);
INSERT INTO paciente VALUES (4, 'p@ssw0rd4', 'usuario4@example.com', '0123456789', '0123456789', 3, 'Plaza 101', 'Usuario 4', 'url4.jpg', 'Leche', 3, '1988-12-05', 4);
INSERT INTO paciente VALUES (5, 'p@ssw0rd5', 'usuario5@example.com', '5432109876', '5432109876', 1, 'Bulevar 555', 'Usuario 5', 'url5.jpg', 'Polen', 2, '1995-07-18', 1);

-- | codigo | contrasena | correo | cedula | celular | cuidad | direccion | nombre | url_foto | especiañizacion | estado_medico | hora_fin | hora_inicio |
INSERT INTO medico VALUES (1, 'm@p@ss1', 'medico1@example.com', '1234567890', '1234567890', 1, 'Calle 111', 'Medico 1', 'url1.jpg', 1, 1, '18:00:00', '09:00:00');
INSERT INTO medico VALUES (2, 'm@p@ss2', 'medico2@example.com', '9876543210', '9876543210', 2, 'Avenida 222', 'Medico 2', 'url2.jpg', 2, 1, '17:30:00', '08:30:00');
INSERT INTO medico VALUES (3, 'm@p@ss3', 'medico3@example.com', '5678901234', '5678901234', 3, 'Carrera 333', 'Medico 3', 'url3.jpg', 3, 1, '18:45:00', '10:00:00');
INSERT INTO medico VALUES (4, 'm@p@ss4', 'medico4@example.com', '0123456789', '0123456789', 4, 'Plaza 444', 'Medico 4', 'url4.jpg', 4, 1, '17:00:00', '09:30:00');
INSERT INTO medico VALUES (5, 'm@p@ss5', 'medico5@example.com', '5432109876', '5432109876', 2, 'Bulevar 555', 'Medico 5', 'url5.jpg', 5, 1, '18:30:00', '09:00:00');

-- | codigo | estado_cita | fecha_cita | fecha_creacion | motivo | codigo_medico | codigo_paciente |
INSERT INTO cita VALUES (1, 1, '2023-11-01 08:00:00', '2023-10-30 15:30:00', 'Consulta general', 1, 1);
INSERT INTO cita VALUES (2, 2, '2023-11-02 09:30:00', '2023-10-31 14:45:00', 'Control de rutina', 2, 2);
INSERT INTO cita VALUES (3, 1, '2023-11-03 11:15:00', '2023-11-01 10:00:00', 'Dolor de cabeza', 2, 3);
INSERT INTO cita VALUES (4, 3, '2023-11-04 13:45:00', '2023-11-02 12:30:00', 'Examen físico', 3, 4);
INSERT INTO cita VALUES (5, 2, '2023-11-05 15:30:00', '2023-11-03 14:15:00', 'Consulta especializada', 4, 5);

-- | codigo | diagnostico | fecha | notas_medicas | sintomas | tratamiento | codigo_cita |
INSERT INTO atencion VALUES (1, 'Dolor de cabeza', '2023-11-01 09:30:00', 'Recetado paracetamol', 'Mareo', 'Descanso', 1);
INSERT INTO atencion VALUES (2, 'Control de rutina', '2023-11-02 10:45:00', 'Ninguno', 'Sin síntomas', 'Seguimiento', 2);
INSERT INTO atencion VALUES (3, 'Consulta general', '2023-11-03 12:15:00', 'Ninguno', 'Fiebre', 'Recetado antibiótico', 3);
INSERT INTO atencion VALUES (4, 'Examen físico', '2023-11-04 14:30:00', 'Requiere pruebas adicionales', 'Dolor en el pecho', 'Pruebas cardíacas', 4);
INSERT INTO atencion VALUES (5, 'Consulta especializada', '2023-11-05 16:00:00', 'Ninguno', 'Dolor crónico', 'Recomendado fisioterapia', 5);

-- | codigo | fecha | medico_codigo
INSERT INTO dia_libre VALUES (1, '2023-11-06', 1);
INSERT INTO dia_libre VALUES (2, '2023-11-07', 2);
INSERT INTO dia_libre VALUES (3, '2023-11-08', 3);
INSERT INTO dia_libre VALUES (4, '2023-11-09', 4);
INSERT INTO dia_libre VALUES (5, '2023-11-10', 5);

-- | codigo | dia | hora_fin | |hora_inicio | medico_codigo |
INSERT INTO horario VALUES (1, '2023-11-06', '2023-11-06 18:00:00', '2023-11-06 09:00:00', 1);
INSERT INTO horario VALUES (2, '2023-11-07', '2023-11-07 17:30:00', '2023-11-07 08:30:00', 2);
INSERT INTO horario VALUES (3, '2023-11-08', '2023-11-08 18:45:00', '2023-11-08 10:00:00', 3);
INSERT INTO horario VALUES (4, '2023-11-09', '2023-11-09 17:00:00', '2023-11-09 09:30:00', 4);
INSERT INTO horario VALUES (5, '2023-11-10', '2023-11-10 18:30:00', '2023-11-10 09:00:00', 5);

-- | codigo | estado_pqrs | fecha_creacion | motivo | tipo | cita_codigo
INSERT INTO pqrs VALUES (1, 1, '2023-11-11 10:00:00', 'Solicitud de información', 'Queja', 1);
INSERT INTO pqrs VALUES (2, 2, '2023-11-12 11:30:00', 'Problema con la atención médica', 'Reclamo', 2);
INSERT INTO pqrs VALUES (3, 1, '2023-11-13 13:15:00', 'Sugerencia para mejorar servicios', 'Sugerencia', 3);
INSERT INTO pqrs VALUES (4, 3, '2023-11-14 15:45:00', 'Error en la factura', 'Reclamo', 4);
INSERT INTO pqrs VALUES (5, 2, '2023-11-15 17:30:00', 'Problema con la cita médica', 'Reclamo', 5);

-- | id | fecha | mensaje | admin_codigo | pqrs_codigo |
INSERT INTO respuesta_admin VALUES (1, '2023-11-16 10:30:00', 'Gracias por su comentario. Estamos trabajando para mejorar nuestros servicios.', 1, 1);
INSERT INTO respuesta_admin VALUES (2, '2023-11-17 12:00:00', 'Lamentamos los inconvenientes. Estamos investigando su caso y nos comunicaremos con usted pronto.', 1, 2);
INSERT INTO respuesta_admin VALUES (3, '2023-11-18 14:15:00', 'Agradecemos su sugerencia. Estamos evaluando la implementación de mejoras sugeridas.', 3, 3);
INSERT INTO respuesta_admin VALUES (4, '2023-11-19 16:45:00', 'Sentimos la confusión. Revisaremos su factura y corregiremos cualquier error.', 4, 4);
INSERT INTO respuesta_admin VALUES (5, '2023-11-20 18:30:00', 'Lamentamos la situación. Investigaremos y tomaremos medidas para mejorar la gestión de citas.', 5, 5);

-- | id | fecha | mensaje | paciente_codigo | pqrs_codigo | respuesta_admin_id |
INSERT INTO respuesta_paciente VALUES (1, '2023-11-21 10:00:00', 'Agradezco su respuesta. Estaré atento a las mejoras implementadas.', 1, 1, 1);
INSERT INTO respuesta_paciente VALUES (2, '2023-11-22 11:30:00', 'Espero una pronta solución. Gracias por su atención.', 2, 2, 2);
INSERT INTO respuesta_paciente VALUES (3, '2023-11-23 13:15:00', 'Gracias por considerar mi sugerencia. Quedo a la espera de futuras mejoras.', 3, 3, 3);
INSERT INTO respuesta_paciente VALUES (4, '2023-11-24 15:45:00', 'Espero la corrección en mi factura. Gracias por su pronta acción.', 4, 4, 4);
INSERT INTO respuesta_paciente VALUES (5, '2023-11-25 17:30:00', 'Aprecio su respuesta. Confío en que mejorarán la gestión de citas.', 5, 5, 5);

