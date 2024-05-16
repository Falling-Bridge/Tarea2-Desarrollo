package Tarea2;
import java.util.ArrayList;

public class Asistencia  {
    protected ArrayList<Empleado> employeeasistencia;
    
    /**
     * Constructor de la clase, donde se incializa el arreglo de las personas q asistieron a la reunion
     */
    public Asistencia(){
        employeeasistencia = new ArrayList<Empleado>();
    }

    /**
     * es un setter, donde se ingresan la personas al arreglo
     * @param emp el empleado q asistío a la reunión se ingresa por acá
     */
    public void addAsistentes(Empleado emp){
        employeeasistencia.add(emp);
    }

    /**
     * es un getter, q devuelve el arreglo con todas las personan q han asistido a la reunión
     * @return el arreglo employeeasistencia
     */
    public ArrayList<Empleado> getAsistentes(){
        return employeeasistencia;
    }
}
