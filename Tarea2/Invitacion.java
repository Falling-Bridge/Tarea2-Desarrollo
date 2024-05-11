package Tarea2;
import java.time.*;
import java.util.ArrayList;

public class Invitacion implements Invitable{
    private Instant hora;
    public ArrayList<Empleado> invitado;

    public void invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }
    public Invitacion(){
        invitado = new ArrayList<Empleado>();
    }
}
