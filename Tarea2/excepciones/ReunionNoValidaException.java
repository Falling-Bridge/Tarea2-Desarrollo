package Tarea2.excepciones;

public class ReunionNoValidaException extends Exception{
    public ReunionNoValidaException(){
        super("La reunión no se ha creado correctamente, intentar nuevamente o con otra reunión");
    }
}
