package Tarea2;
import javax.lang.model.util.ElementScanner7;
import java.util.ArrayList;

public abstract class Asistencia  {
    protected ArrayList<Empleado> employeeasistencia;
    protected ArrayList<Empleado> employeeinasistencia;
    
    public Asistencia(){
        employeeasistencia = new ArrayList<Empleado>();
        employeeinasistencia = new ArrayList<Empleado>();
    }

    public void addAsistentes(Empleado emp){
        employeeasistencia.add(emp);
    }

    public ArrayList<Empleado> getAsistentes(){
        return employeeasistencia;
    }

    public void addInasistentes(Empleado emp){
        employeeinasistencia.add(emp);
    }

    public ArrayList<Empleado> getInasistentes(){
        return employeeinasistencia;
    }

    public int cantAsistentes(){
        return employeeasistencia.size();
    }

    public int cantInasistentes(){
        return employeeinasistencia.size();
    }
}
