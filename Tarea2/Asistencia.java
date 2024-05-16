package Tarea2;
import java.util.ArrayList;

public class Asistencia  {
    protected ArrayList<Empleado> employeeasistencia;
    
    public Asistencia(){
        employeeasistencia = new ArrayList<Empleado>();
    }

    public void addAsistentes(Empleado emp){
        employeeasistencia.add(emp);
    }

    public ArrayList<Empleado> getAsistentes(){
        return employeeasistencia;
    }
}
