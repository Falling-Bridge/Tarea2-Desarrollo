package Tarea2;
import java.util.*;
import Tarea2.Reunion.*;

public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> employeedepartamento; // Cambiamos ArrayList a Set
    private ArrayList<Reunion> empleadosreunion;
    private int numEmpleados;
    private String organizacion;

    public int ObtenerCantidadEmpleados(){
        for (Empleado empleado : employeedepartamento) {
            System.out.println(empleado.Datos());
        }
        return numEmpleados;
    }

    public boolean AgregarEmpleado(Empleado empleado) {
        if (employeedepartamento.contains(empleado)) {
            return false;
        } else {
            employeedepartamento.add(empleado);
            numEmpleados++;
            return true;
        }
    }

    public void Invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }

    public Empleado Organizadorreunion(){
        return employeedepartamento.getFirst();
    }

    public Departamento(int num){
        numEmpleados = num;
        employeedepartamento = new ArrayList<>();
        empleadosreunion = new ArrayList<>();
    }
}
