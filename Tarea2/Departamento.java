package Tarea2;
import Tarea2.Reunion.*;
import java.util.*;

public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> employeedepartamento; // Cambiamos ArrayList a Set
    private int numEmpleados;

    public Empleado getOrganizador(){
        return employeedepartamento.getFirst();
    }

    public int ObtenerCantidadEmpleados(){
        for (Empleado empleado : employeedepartamento) {
            System.out.println(empleado.getDatos());
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

    public void invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }

    public Departamento(int num){
        numEmpleados = num;
        employeedepartamento = new ArrayList<>();
    }
}
