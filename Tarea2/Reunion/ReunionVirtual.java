package Tarea2.Reunion;

import Tarea2.Departamento;
import Tarea2.Empleado;

public class ReunionVirtual extends Reunion{
    private String enlace;
    public ReunionVirtual(Empleado org, tipoReunion tipo, String enlceunion){
        super(org, tipo);
        this.enlace = enlceunion;
    }
    public String getEnlace(){
        return enlace;
    }

}