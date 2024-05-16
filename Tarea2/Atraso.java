package Tarea2;
import java.util.ArrayList;

public class Atraso{
    private ArrayList<Empleado> atrasados;
    
    /**
     * Cosntructor de la clase, donde se inicializa el arreglo de atrasados
     */
    public Atraso(){
        atrasados = new ArrayList<Empleado>();
    }
/**
     * es un setter, donde se ingresan la personas al arreglo
     * @param emp el empleado q llego a la reunión despues de la hora de inicio se ingresa por acá
     */
    public void addAtrasados(Empleado emp){
        atrasados.add(emp);
    }

    /**
     * es un getter, q devuelve el arreglo con todas las personan q llegaron tarde a la reunión
     * @return el arreglo atrasados
     */
    public ArrayList<Empleado> getAtrasados(){
        return atrasados;
    }
}