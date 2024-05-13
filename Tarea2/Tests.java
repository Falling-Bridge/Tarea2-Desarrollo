package Tarea2;
import Tarea2.Reunion.*;
import Tarea2.Reunion.ReunionPresencial;
import Tarea2.Reunion.tipoReunion;
import org.junit.jupiter.api.*;
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

    @Test   @DisplayName("test 04") //creación de reunión presencial
    public void CrearReunionespresenciales() {
        Departamento departamento = new Departamento(0);
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        departamento.AgregarEmpleado(organizador);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202");

        assertNotNull(reunionpres, "La reunión presencial no se creó correctamente");
        assertEquals(organizador, departamento.getOrganizador(), "El organizador de la reunión no coincide");
        assertEquals(tipoReunion.MARKETING, reunionpres.getType(), "El tipo de reunión no coincide");
        assertEquals("Sala 202", reunionpres.getSala(), "El lugar de la reunión no coincide");
    }

    @Test   @DisplayName("test 05") //creación de reunión presencial
    public void CrearReunionesvirtuales() {
        Departamento departamento = new Departamento(0);
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        departamento.AgregarEmpleado(organizador);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.MARKETING, "https://www.youtube.com/watch?v=xvFZjo5PgG0");

        assertNotNull(reunionvir, "La reunión virtual no se creó correctamente");
        assertEquals(organizador, departamento.getOrganizador(), "El organizador de la reunión no coincide");
        assertEquals(tipoReunion.MARKETING, reunionvir.getType(), "El tipo de reunión no coincide");
        assertEquals("https://www.youtube.com/watch?v=xvFZjo5PgG0", reunionvir.getEnlace(), "El lugar de la reunión no coincide");
    }

    @Test   @DisplayName("test 06")
    public void CrearReunionespresencialesconempleados() { //reunion presencial con tiempos
        Departamento departamento = new Departamento(0);
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202");
        reunionpres.getDuracion();
        reunionpres.iniciar();
        reunionpres.finalizar();
    }
}