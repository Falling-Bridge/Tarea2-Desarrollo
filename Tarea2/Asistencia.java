package Tarea2;

import javax.lang.model.util.ElementScanner7;
import java.util.ArrayList;

public abstract class Asistencia  {
    protected ArrayList<Empleado> employeeasistencia;
    
    public Asistencia(){
    employeeasistencia = new ArrayList<Empleado>();
    }

    public ArrayList<Empleado> getAsistentes(){
        return employeeasistencia;
    }

    public void addAsistentes(Empleado emp){
        employeeasistencia.add(emp);
    }

    public int cantAsistentes(){
        return employeeasistencia.size();
    }
}
