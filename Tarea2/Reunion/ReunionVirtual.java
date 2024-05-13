package Tarea2.Reunion;

import Tarea2.Departamento;
import Tarea2.Empleado;

import java.time.Duration;

public class ReunionVirtual extends Reunion{
    private String enlace;
    private tipoReunion typereunion;
    public ReunionVirtual(Empleado org, tipoReunion tipo, String enlceunion, Duration duration){
        super(org, tipo);
        this.enlace = enlceunion;
        typereunion = tipo;
    }
    public String getEnlace(){

        return enlace;
    }

    public tipoReunion getType(){
        return typereunion;
    }
}