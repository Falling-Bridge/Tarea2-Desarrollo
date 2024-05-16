package Tarea2.excepciones;

public class EmpleadoNoInvitadoException extends Exception{
    public EmpleadoNoInvitadoException(String s) {//excepción para cuando un empleado no invitado a cierta reuniómn intenta entrar de igual manera 
        super(s);
    }
}
