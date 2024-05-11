package Tarea2;
import java.time.*;
import java.util.ArrayList;

public class Atraso extends Asistencia{
    private Instant hora;
    private ArrayList<Empleado> atrasados;
    public Atraso(){
        super();
        atrasados = new ArrayList<Empleado>();
    }

    public void ingresarAtrasados(Empleado emp){
        atrasados.add(emp);
    }

    public ArrayList<Empleado> getAtrasados(){
        return atrasados;
    }


    
}
