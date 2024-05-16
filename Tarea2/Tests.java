package Tarea2;
import Tarea2.Reunion.*;
import org.junit.jupiter.api.*;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test   @DisplayName("test01")//prueba la creación de empleados (no se agrega a la lista)
    public void testCrearEmpleado() {
        Empleado empleado1 = new Empleado("001", "Alvaro","Flar", "alvaro@example.com");
        String datosEsperados = "id: 001; nombre: Alvaro; Apellidos: Flar; correo: alvaro@example.com";
        assertEquals(datosEsperados, empleado1.Datos(), "Falla en test01:");
    }

    @Test   @DisplayName("test02")//agregación de los empleados a la lista de empleados
    public void AgregarEmpleadoALista() {
        Departamento departamento = new Departamento(0);
        Empleado empleado1 = new Empleado("001", "Juan", "Pérez","juan@example.com" );
        Empleado empleado2 = new Empleado("002", "María", "López", "maria@example.com");
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);

        assertEquals(2, departamento.ObtenerCantidadEmpleados(), "El número de empleados en el departamento debe ser 3");
    }

    @Test   @DisplayName("test03") //prueba que no hayan empleados repetidos
    public void noHayEmpleadosDuplicados() {
        Departamento departamento = new Departamento(0);
        Empleado empleado1 = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado2 = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado3 = new Empleado("002", "María", "López", "maria@example.com");

        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado3);
        departamento.AgregarEmpleado(empleado2);

        assertEquals(2, departamento.ObtenerCantidadEmpleados(), "El número de empleados debe ser 3");
    }

    @Test   @DisplayName("test 04") //creación de reunión presencial con tiempo
    public void CrearReunionespresenciales() {
        Departamento departamento = new Departamento(0);
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        departamento.AgregarEmpleado(organizador);
        Duration duration = Duration.ofSeconds(10);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202", duration);

        assertNotNull(reunionpres, "La reunión presencial no se creó correctamente");
        assertEquals(organizador, departamento.Organizadorreunion(), "El organizador de la reunión no coincide");
        assertEquals(tipoReunion.MARKETING, reunionpres.getType(), "El tipo de reunión no coincide");
        assertEquals("Sala 202", reunionpres.getSala(), "El lugar de la reunión no coincide");
    }

    @Test   @DisplayName("test 05") //creación de reunión virtual con tiempo
    public void CrearReunionesvirtuales() {
        Departamento departamento = new Departamento(0);
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        departamento.AgregarEmpleado(organizador);
        Duration duration = Duration.ofSeconds(10);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.MARKETING, "https://www.youtube.com/watch?v=xvFZjo5PgG0", duration);

        assertNotNull(reunionvir, "La reunión virtual no se creó correctamente");
        assertEquals(organizador, departamento.Organizadorreunion(), "El organizador de la reunión no coincide");
        assertEquals(tipoReunion.MARKETING, reunionvir.getType(), "El tipo de reunión no coincide");
        assertEquals("https://www.youtube.com/watch?v=xvFZjo5PgG0", reunionvir.getEnlace(), "El lugar de la reunión no coincide");
    }

    @Test   @DisplayName("test 06") //creación de reunion presencial con personas y tiempo
    public void CrearReunionespresencialesconempleados() throws Exception { //reunion presencial con tiempos y personas
        Departamento departamento = new Departamento(0);
        Invitacion invitation = new Invitacion();
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        Duration duracionreunion = Duration.ofSeconds(10);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202", duracionreunion);

        invitation.Invitar(reunionpres, organizador);
        invitation.Invitar(reunionpres, empleado1);
        invitation.Invitar(reunionpres, empleado2);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(empleado2);
        reunionpres.obtenerFechayHoraReunion();
        reunionpres.iniciar();
        reunionpres.finalizar();

        assertTrue(reunionpres.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado2), "El empleado 2 no está registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(3, reunionpres.obtenerAsistencias().size(), "El número total de asistentes no es correcto");
    }

    @Test   @DisplayName("test 07") //creación de reunion virtual con personas y tiempo
    public void CrearReunionesvirtualesconempleados() throws Exception { //reunion presencial con tiempos y personas
        Departamento departamento = new Departamento(0);
        Invitacion invitation = new Invitacion();
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        Duration duracionreunion = Duration.ofSeconds(10);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.MARKETING, "\"https://www.youtube.com/watch?v=xvFZjo5PgG0\"", duracionreunion);

        invitation.Invitar(reunionvir, organizador);
        invitation.Invitar(reunionvir, empleado1);
        invitation.Invitar(reunionvir, empleado2);
        reunionvir.llegadaEmpleados(empleado1);
        reunionvir.llegadaEmpleados(empleado2);
        reunionvir.llegadaEmpleados(organizador);
        reunionvir.obtenerHoraInicioyFin();
        reunionvir.iniciar();
        reunionvir.finalizar();

        assertTrue(reunionvir.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(empleado2), "El empleado 2 no está registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(3, reunionvir.obtenerAsistencias().size(), "El número total de asistentes no es correcto");
    }

    @Test   @DisplayName("test 08") //creación de reuniones presenciales con personas, inasistencias y tiempo
    public void Crearreunionespresencialesconinasistencias() throws Exception {
        Departamento departamento = new Departamento(0);
        Invitacion invitation = new Invitacion();
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        Duration duracionreunion = Duration.ofSeconds(10);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202", duracionreunion);

        invitation.Invitar(reunionpres, organizador);
        invitation.Invitar(reunionpres, empleado1);
        invitation.Invitar(reunionpres, empleado2);
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.obtenerFechayHoraReunion();
        reunionpres.iniciar();
        reunionpres.finalizar();
        reunionpres.llegadaEmpleados(empleado2);

        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionpres.obtenerAusencias().contains(empleado2), "El empleado 2 no está registrado con ausencia");
        assertEquals(2, reunionpres.obtenerAsistencias().size(), "El número total de asistentes no es correcto");
        assertEquals(1, reunionpres.obtenerAusencias().size(), "El número total de ausentes no es correcto");
    }

    @Test   @DisplayName("test 09") //creación de reuniones virtuales con personas, inasistencias y tiempo
    public void Crearreunionesvirtualesesconinasistencias() throws Exception {
        Departamento departamento = new Departamento(0);
        Invitacion invitation = new Invitacion();
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        Duration duracionreunion = Duration.ofSeconds(10);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.MARKETING, "\"https://www.youtube.com/watch?v=xvFZjo5PgG0\"", duracionreunion);

        invitation.Invitar(reunionvir, organizador);
        invitation.Invitar(reunionvir, empleado1);
        invitation.Invitar(reunionvir, empleado2);
        reunionvir.llegadaEmpleados(empleado1);
        reunionvir.llegadaEmpleados(organizador);
        reunionvir.iniciar();
        reunionvir.finalizar();
        reunionvir.llegadaEmpleados(empleado2);

        assertTrue(reunionvir.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionvir.obtenerAusencias().contains(empleado2), "El empleado 2 no está registrado como inasistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(2, reunionvir.obtenerAsistencias().size(), "El número total de asistentes no es correcto");
        assertEquals(1, reunionvir.obtenerAusencias().size(), "El número total de ausentes no es correcto");
    }

    @Test   @DisplayName("test10")//creacion de reuniones presenciales con asistencias, retrasos y ausencias
    public void Reunionespresencialescontiempoasistenciayretraso() throws Exception {
        Departamento departamento = new Departamento(0);
        Invitacion invitation = new Invitacion();
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        Duration duracionreunion = Duration.ofSeconds(10);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202", duracionreunion);

        invitation.Invitar(reunionpres, organizador);
        invitation.Invitar(reunionpres, empleado1);
        invitation.Invitar(reunionpres, empleado2);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.iniciar();
        Thread.sleep(500);
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(empleado2);
        reunionpres.finalizar();

        assertTrue(reunionpres.obtenerAsistencias().contains(organizador),"El organizador no está registrado como asistente");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado1),"El empleado1 no está registrado con atraso");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado2),"El empleado2 no está registrado con atraso");
        assertEquals(2,reunionpres.obtenerAtraso().size(), "El número de retrasos no es correcto");
        assertEquals(3,reunionpres.obtenerAsistencias().size(),"El número de asistencias no es correcto");
    }

    @Test   @DisplayName("test 11") //creación de reuniones presenciales con asistencias, retrasos y ausencias
    public void Reunionespresencialesconasistenciasinasistenciasretrasos() throws Exception {
        Departamento departamento = new Departamento(0);
        Invitacion invitation = new Invitacion();
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        Duration duracionreunion = Duration.ofSeconds(10);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.TECNICA, "Sala 202", duracionreunion);

        invitation.Invitar(reunionpres, organizador);
        invitation.Invitar(reunionpres, empleado1);
        invitation.Invitar(reunionpres, empleado2);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.iniciar();
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.finalizar();
        reunionpres.llegadaEmpleados(empleado2);

        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado1), "El empleado1 no está registrado con atraso");
        assertTrue(reunionpres.obtenerAusencias().contains(empleado2), "El empleado2 no está registrado como ausente");
        assertEquals(2,reunionpres.obtenerAsistencias().size(),"El número de asistentes no es correcto");
        assertEquals(1,reunionpres.obtenerAtraso().size(),"El número de retrasos no es correcto");
        assertEquals(1,reunionpres.obtenerAusencias().size(),"El número de ausentes no es correcto");
    }

    @Test   @DisplayName("test 12") //creación de reuniones virtuales con asistencias, retrasos y ausencias
    public void Reunionesvirtualesconasistenciasinasistenciasretrasos() throws Exception {
        Departamento departamento = new Departamento(0);
        Invitacion invitation = new Invitacion();
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        Duration duracionreunion = Duration.ofSeconds(10);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.TECNICA, "\"https://www.youtube.com/watch?v=xvFZjo5PgG0\"", duracionreunion);

        invitation.Invitar(reunionvir, organizador);
        invitation.Invitar(reunionvir, empleado1);
        invitation.Invitar(reunionvir, empleado2);
        reunionvir.llegadaEmpleados(organizador);
        reunionvir.iniciar();
        Thread.sleep(500);
        reunionvir.llegadaEmpleados(empleado1);
        reunionvir.finalizar();
        reunionvir.llegadaEmpleados(empleado2);

        assertTrue(reunionvir.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertTrue(reunionvir.obtenerAtraso().contains(empleado1), "El empleado1 no está registrado con atraso");
        assertTrue(reunionvir.obtenerAusencias().contains(empleado2), "El empleado2 no está registrado como ausente");
        assertEquals(2,reunionvir.obtenerAsistencias().size(),"El número de asistentes no es correcto");
        assertEquals(1,reunionvir.obtenerAtraso().size(),"El número de retrasos no es correcto");
        assertEquals(1,reunionvir.obtenerAusencias().size(),"El número de ausentes no es correcto");
    }

    @Test   @DisplayName("test 13") //creacion de informe de reunion presencial, con:
                                    //fecha - hora de la hora (inicio, fin) - duracion de la reunion - tema de la reunion (tipo reunion)
                                    //nota de lo que se hablò - tipo de reunion (presencial o virtual) - lista invitados, asistentes, atrasados, ausentes
    public void CreaciondeInforme() throws Exception {
        Departamento departamento = new Departamento(0);
        Invitacion invitation = new Invitacion();
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado2 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado3 = new Empleado("003", "Pedro", "González", "pedro@example.com");
        Empleado empleado4 = new Empleado("004", "Ana", "Martínez", "ana@example.com");
        Empleado empleado5 = new Empleado("005", "Carlos", "Rodríguez", "carlos@example.com");
        Empleado empleado6 = new Empleado("006", "Sofía", "Hernández", "sofia@example.com");
        Empleado empleado7 = new Empleado("007", "Luis", "Díaz", "luis@example.com");
        Empleado empleado8 = new Empleado("008", "Laura", "García", "laura@example.com");

        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        Duration duracionreunion = Duration.ofSeconds(10);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.TECNICA, "Sala 202", duracionreunion);

        invitation.Invitar(reunionpres, organizador);
        invitation.Invitar(reunionpres, empleado1);
        invitation.Invitar(reunionpres, empleado2);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.iniciar();
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.finalizar();
        reunionpres.llegadaEmpleados(empleado2);

        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado1), "El empleado1 no está registrado con atraso");
        assertTrue(reunionpres.obtenerAusencias().contains(empleado2), "El empleado2 no está registrado como ausente");
        assertEquals(2,reunionpres.obtenerAsistencias().size(),"El número de asistentes no es correcto");
        assertEquals(1,reunionpres.obtenerAtraso().size(),"El número de retrasos no es correcto");
        assertEquals(1,reunionpres.obtenerAusencias().size(),"El número de ausentes no es correcto");
    }
}