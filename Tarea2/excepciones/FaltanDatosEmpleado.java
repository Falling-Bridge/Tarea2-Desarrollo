package Tarea2.excepciones;

public class FaltanDatosEmpleado extends Exception {
    public FaltanDatosEmpleado() {
        super("Faltan datos del empleado");
    }
}
