package Tarea2;
import java.time.Instant;
import java.util.Date;

public class Empleado implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;
    public Instant horallegada;
    public int EstadodeLlegada; //0 es llego a la hora, 1 es llego atrasado, y 2 es no llego

    @Override
    public void invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }

    public Empleado(String x, String nom, String cor, String ap){
        apellidos = ap;
        nombre = nom;
        correo = cor;
        id = x;
    }
    public String Datos(){
        return id + " " + apellidos + " " + nombre + " " + correo;
    }
}
