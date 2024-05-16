package Tarea2.excepciones;

public class SinContenidoException extends Exception {
    public SinContenidoException(String s) {//es un excepción para cuando los arreglos no contienen elementos pero modificada para este contexto, recibe un mensaje q es el q se imprime
        super(s);
    }
}