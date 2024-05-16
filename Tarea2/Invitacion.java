package Tarea2;
import Tarea2.Reunion.*;
import java.time.*;
import java.util.ArrayList;

public class Invitacion implements Invitable{
    private Instant hora;
    public ArrayList<Empleado> invitado;

    public void Invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }
    public Invitacion(){
        invitado = new ArrayList<Empleado>();
    }

    public ArrayList<Empleado> getInvitados(){
        return invitado;
    }
}