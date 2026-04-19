-- 2. Inserción de los registros usando solo strings para las fechas (formato DD-MM-YYYY)
INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('01-04-2026', 101, 'Angelo Benvenuto', '15-04-2026', '20-04-2026');

INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('02-04-2026', 102, 'María Silva', '16-04-2026', '18-04-2026');

INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('03-04-2026', 201, 'Carlos Gómez', '17-04-2026', '22-04-2026');

INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('04-04-2026', 202, 'Ana Rojas', '18-04-2026', '25-04-2026');

INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('05-04-2026', 301, 'Luis Martínez', '19-04-2026', '21-04-2026');

INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('06-04-2026', 302, 'Camila Soto', '20-04-2026', '23-04-2026');

INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('07-04-2026', 401, 'Juan Pérez', '21-04-2026', '24-04-2026');

INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('08-04-2026', 402, 'María Quilobran', '22-04-2026', '26-04-2026');

INSERT INTO reservas (fecha_reserva, numero_habitacion, nombre_cliente, fecha_ingreso, fecha_salida) 
VALUES ('10-04-2026', 502, 'Miguel Molina', '24-04-2026', '27-04-2026');

-- 3. Confirmar la transacción
COMMIT;