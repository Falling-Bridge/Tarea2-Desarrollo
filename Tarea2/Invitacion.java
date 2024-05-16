package Tarea2;
import Tarea2.Reunion.*;
import java.util.ArrayList;

public class Invitacion implements Invitable{
    public ArrayList<Empleado> invitado;

    /**
     * Método de la interfaz Invitable, q se usa para agregar invitados a una reunión
     * @param reu la reu a la q se esta invitando
     * @param emp el emppleado q se invita a participar
     */
    public void Invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }

    /**
     * Constructor de la clase, q inicializa el arreglo de empleados
     */
    public Invitacion(){
        invitado = new ArrayList<Empleado>();
    }

    /**
     * Es un getter q devuelve el arreglo con todos los invitados
     * @return retorna el arreglo con los invitados
     */
    public ArrayList<Empleado> getInvitados(){
        return invitado;
    }
}