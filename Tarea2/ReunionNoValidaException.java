package Tarea2;

public class ReunionNoValidaException extends Exception{
    public ReunionNoValidaException(){
        super("La reunión no se a creado correctamente, intentar nuevamente o con otra reunión");
    }
}
