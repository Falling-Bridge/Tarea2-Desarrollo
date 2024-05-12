package Tarea2.Reunion;

import Tarea2.Empleado;

public class ReunionPresencial extends Reunion{
    private String sala;
    public ReunionPresencial(Empleado org, tipoReunion tipo, String lugar){
        super(org, tipo);
        this.sala = lugar;
    }
    public String getSala(){
        return sala;
    }
}
