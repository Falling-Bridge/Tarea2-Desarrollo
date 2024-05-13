package Tarea2;
import Tarea2.Reunion.*;
import Tarea2.Reunion.ReunionPresencial;
import Tarea2.Reunion.tipoReunion;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test   @DisplayName("test01")//prueba la creación de empleados (no se agrega a la lista)
    public void testCrearEmpleado() {
        Empleado empleado1 = new Empleado("001", "Alvaro","Flar", "alvaro@example.com");
        String datosEsperados = "id: 001; nombre: Alvaro; Apellidos: Flar; correo: alvaro@example.com";
        assertEquals(datosEsperados, empleado1.getDatos(), "Falla en test01:");
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
        assertEquals(organizador, departamento.getOrganizador(), "El organizador de la reunión no coincide");
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
        assertEquals(organizador, departamento.getOrganizador(), "El organizador de la reunión no coincide");
        assertEquals(tipoReunion.MARKETING, reunionvir.getType(), "El tipo de reunión no coincide");
        assertEquals("https://www.youtube.com/watch?v=xvFZjo5PgG0", reunionvir.getEnlace(), "El lugar de la reunión no coincide");
    }

    @Test   @DisplayName("test 06") //creación de reunion presencial con personas y tiempo
    public void CrearReunionespresencialesconempleados() { //reunion presencial con tiempos y personas
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

        invitation.invitar(reunionpres, organizador);
        invitation.invitar(reunionpres, empleado1);
        invitation.invitar(reunionpres, empleado2);
        reunionpres.iniciar();
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(empleado2);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.finalizar();

        assertTrue(reunionpres.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado2), "El empleado 2 no está registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(3, reunionpres.totalAsistencias(), "El número total de asistentes no es correcto");
    }

    @Test   @DisplayName("test 07") //creación de reunion virtual con personas y tiempo
    public void CrearReunionesvirtualesconempleados() { //reunion presencial con tiempos y personas
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

        invitation.invitar(reunionvir, organizador);
        invitation.invitar(reunionvir, empleado1);
        invitation.invitar(reunionvir, empleado2);
        reunionvir.iniciar();
        reunionvir.llegadaEmpleados(empleado1);
        reunionvir.llegadaEmpleados(empleado2);
        reunionvir.llegadaEmpleados(organizador);
        reunionvir.finalizar();

        assertTrue(reunionvir.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(empleado2), "El empleado 2 no está registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(3, reunionvir.totalAsistencias(), "El número total de asistentes no es correcto");
    }

    @Test   @DisplayName("test 08") //creación de reuniones presenciales con personas, inasistencias y tiempo
    public void Crearreunionespresencialesconinasistencias(){
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

        invitation.invitar(reunionpres, organizador);
        invitation.invitar(reunionpres, empleado1);
        invitation.invitar(reunionpres, empleado2);
        reunionpres.iniciar();
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.finalizar();
        reunionpres.agregarInasistencias(empleado2);

        assertTrue(reunionpres.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionpres.obtenerInasistencias().contains(empleado2), "El empleado 2 no está registrado como inasistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(2, reunionpres.totalAsistencias(), "El número total de asistentes no es correcto");
        assertEquals(1, reunionpres.totalInasistencias(), "El número total de asistentes no es correcto");
    }
}