package Tarea2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test    @DisplayName("test01:")
    public void testCrearEmpleado() { //prueba la creación de empleados y obtención de datos
        String id = "123";
        String nombre = "Juan";
        String correo = "juan@example.com";
        String apellidos = "Pérez";

        Empleado empleado = new Empleado(id, nombre, correo, apellidos);

        String datosEsperados = "id: 123  nombre: Juan  Apellidos: Pérez    correo: juan@example.com\n";
        assertEquals(datosEsperados, empleado.getDatos(), "Falla en test01:");
    }
    @Test    @DisplayName("test02:")
    public void Agregarempleadoalista() { //ingresa los empleados al registro
        String id = "123";
        String nombre = "Juan";
        String correo = "juan@example.com";
        String apellidos = "Pérez";

        Empleado empleado = new Empleado(id, nombre, correo, apellidos);

        String datosEsperados = "id: 123  nombre: Juan  Apellidos: Pérez    correo: juan@example.com\n";
        assertEquals(datosEsperados, empleado.getDatos(), "Falla en test02:");
    }
}
