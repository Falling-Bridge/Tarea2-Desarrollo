package Tarea2.excepciones;

public class NotasNullException extends Exception {
    public NotasNullException() {
        super("No se creo ninguna nota durante la reunion");
    }
}
