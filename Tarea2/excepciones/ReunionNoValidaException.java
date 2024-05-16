package Tarea2.excepciones;

public class ReunionNoValidaException extends Exception {
    public ReunionNoValidaException(String s) {//se usa en Informe, y es para cuando la reunión no está correctamente definida
        super(s);
    }
}