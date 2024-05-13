package Tarea2;

public class ReunionVirtual extends Reunion {
    private String enlace;
    public ReunionVirtual(Empleado org, tipoReunion tipo, String enlceunion){
        super(org, tipo);
        this.enlace = enlceunion;
    }
    public String getEnlace(){
        return enlace;
    }
}