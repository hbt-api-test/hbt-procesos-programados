--INSERT INTO movie(title, description,director,country) VALUES('The Lord Of The Ring: The Fellowshipt of the ring', 'In the second age of middle-earth, the lord of elves...','Peter JAckson','New Zealand - USA');


INSERT INTO DIAS_SEMANA(descripcion) VALUES('Lunes');
INSERT INTO DIAS_SEMANA(descripcion) VALUES('Martes');
INSERT INTO DIAS_SEMANA(descripcion) VALUES('Miercoles');
INSERT INTO DIAS_SEMANA(descripcion) VALUES('Jueves');
INSERT INTO DIAS_SEMANA(descripcion) VALUES('Viernes');
INSERT INTO DIAS_SEMANA(descripcion) VALUES('Sabado');
INSERT INTO DIAS_SEMANA(descripcion) VALUES('Domingo');

INSERT INTO MESES(mes) VALUES('JANUARY');
INSERT INTO MESES(mes) VALUES('FEBRUARY');
INSERT INTO MESES(mes) VALUES('MARCH');
INSERT INTO MESES(mes) VALUES('APRIL');
INSERT INTO MESES(mes) VALUES('MAY');
INSERT INTO MESES(mes) VALUES('JUNE');
INSERT INTO MESES(mes) VALUES('JULY');
INSERT INTO MESES(mes) VALUES('AUGUST');
INSERT INTO MESES(mes) VALUES('SEPTEMBER');
INSERT INTO MESES(mes) VALUES('OCTOBER');
INSERT INTO MESES(mes) VALUES('NOVEMBER');
INSERT INTO MESES(mes) VALUES('DECEMBER');


INSERT INTO scheduled_configuration(dayOfMonth,frecuency,hour,minutes,cronExpresion) VALUES(0,2,99,99,null);

INSERT INTO PROCESOPROGRAMADO(clases, nombreProcesoProgramado, numeroReintentos, statusScheduledProcess, scheduledConfiguration_id) VALUES('Task1.class','proceso 1',2,2, 1);

INSERT INTO PARAMETROPROCESO(nombreParametroProceso,requerido,valorParametroProceso) VALUES('P1',FALSE,'');
INSERT INTO PARAMETROPROCESO(nombreParametroProceso,requerido,valorParametroProceso) VALUES('P2',FALSE,'');

INSERT INTO PROCESOPROGRAMADO_PARAMETROPROCESO(ScheduledProcesses_idProcesoProgramado, processProperty_idParametroProceso) VALUES(1,1);
INSERT INTO PROCESOPROGRAMADO_PARAMETROPROCESO(ScheduledProcesses_idProcesoProgramado, processProperty_idParametroProceso) VALUES(1,2);

INSERT INTO weekdays_scheduled(scheduled_id,weekdays_id) VALUES(1,1);
INSERT INTO weekdays_scheduled(scheduled_id,weekdays_id) VALUES(1,2);

INSERT INTO months_scheduled(scheduled_id, months_id) VALUES(1, 1);
INSERT INTO months_scheduled(scheduled_id, months_id) VALUES(1, 2);