package Tarea2;

import java.util.ArrayList;

public class Departamento implements Invitable {
    private String nombre;
    private ArrayList<Empleado> employeedepartamento;
    private int numEmpleados;

    public int ObtenerCantidadEmpleados(){
        return numEmpleados;
    }

    public void invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }

    public Departamento(int num){
        numEmpleados = num;
    }

}
