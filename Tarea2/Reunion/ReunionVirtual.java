package Tarea2.Reunion;
import Tarea2.*;

public class ReunionVirtual extends Reunion{
    private String enlace;

    /**
     * Es el constructor de la clase, y define el tipo de reunion, y el enlace
     * @param org es el organizador, el mismo q en la clase superior
     * @param tipo al igual q en Reunión es el tipo de reunión q se va a realizar, de clase enum 
     * @param enlacereunion es un string aparte y diferente a la clase Reunión, y es el link en donde se va a realizar la reunion online
     */
    public ReunionVirtual(Empleado org, tipoReunion tipo, String enlacereunion){
        super(org, tipo);
        this.enlace = enlacereunion;
    }

    /**
     * Es un getter q se usa para obtener el enlace de la reunion
     * @return el String de la variable private enlace
     */
    public String getEnlace(){
        return enlace;
    }
}