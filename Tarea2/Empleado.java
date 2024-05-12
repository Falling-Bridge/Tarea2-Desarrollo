package Tarea2;
import Tarea2.Reunion.*;
import java.time.Instant;

public class Empleado implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;
    public Instant horallegada;

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
        return "id: " + id + "\nnombre: " + nombre + "\nAapellidos: " + apellidos + "\ncorreo: " + correo;
    }
}
