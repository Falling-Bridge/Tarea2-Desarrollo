package Tarea2.Reunion;
import Tarea2.*;

public class ReunionPresencial extends Reunion{
    private String sala;

    /**
     * Es el constructor de la clase, y define el tipo de reunion, y la sala
     * @param org es el organizador, el mismo q en la clase superior
     * @param tipo al igual q en Reunión es el tipo de reunión q se va a realizar, de clase enum 
     * @param lugar es un string aparte y diferente a la clase Reunión, y es la sala donde se va a realizar la reunión presencial
     */
    public ReunionPresencial(Empleado org, tipoReunion tipo, String lugar){
        super(org, tipo);
        this.sala = lugar;
    }
    /**
     * Es un getter para obtener la sala de la run
     * @return un String con la variable private sala
     */
    public String getSala(){
        return sala;
    }
}