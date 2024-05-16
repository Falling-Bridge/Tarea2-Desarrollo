package Tarea2;
import java.util.*;
import Tarea2.Reunion.*;

public class Departamento implements Invitable {
    private ArrayList<Empleado> employeedepartamento;
    private int numEmpleados;
    private String nombredepartamento;

    /**
     * Es un getter q devuelve el numero de empleados y todos sus datos
     * @return imprime los datos de los empleados y retorna el numero de empleados q hay
     */
    public int ObtenerCantidadEmpleados(){
        for (Empleado empleado : employeedepartamento) {
            System.out.println(empleado.Datos());
        }
        return numEmpleados;
    }

    /**
     * Es un setter q ingresa los empleados al arreglo de empleados del departamento, y si este ya esta pues no ingresa nada
     * @param empleado es el empleado q se intenta ingresar
     * @return retorna true si el empleado se ingreso correctamente, y false si el empleado ya estaba en el departamento
     */
    public boolean AgregarEmpleado(Empleado empleado) {
        if (employeedepartamento.contains(empleado)) {
            return false;
        } else {
            employeedepartamento.add(empleado);
            numEmpleados++;
            return true;
        }
    }

    /**
     * Metodo q permite a un departamento invitar a empleados a una reunión
     * @param reu es la reunión a la q se está invitando a participar
     * @param emp el empleado el cual el departamento invita
     */
    public void Invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }

    /**
     * Este metodo define como organizador de una reunión creada por un departamento a quien sea q se encuantre primero entre los mimbros de dicha instancia
     * @return retorna el primer empleado en el arreglo del departamento
     */
    public Empleado Organizadorreunion(){
        return employeedepartamento.getFirst();
    }
    
    /**
     * Es un getter q entrega a todos los empleados q pertenecen al departamento 
     * @return retorna el arreglo employeedepartamento con los miembros del departamento
     */
    public ArrayList<Empleado> getEmpleados(){
        return employeedepartamento;
    }

    /**
     * Es el constructora de la clase donde se inicializa el arreglo de los empleados q pertenecen a este, y la cantidad q va a tener
     * @param num es la cantidad de empleados aq van a pertenecer
     * @param name es el nombre del departamento que es un string 
     */
    public Departamento(int num, String name){
        numEmpleados = num;
        employeedepartamento = new ArrayList<>();
        nombredepartamento = name;
    }
}