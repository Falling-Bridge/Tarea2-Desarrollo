package Tarea2.Reunion;
import Tarea2.Departamento;
import Tarea2.Empleado;

public class ReunionPresencial extends Reunion{
    private String sala;
    private tipoReunion typereunion;

    public ReunionPresencial(Empleado org, tipoReunion tipo, String lugar){
        super(org, tipo);
        this.sala = lugar;
        typereunion = tipo;
    }

    public tipoReunion getType(){
        return typereunion;
    }

    public String getSala(){

        return sala;
    }
}