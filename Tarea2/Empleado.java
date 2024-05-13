package Tarea2;
import java.time.Instant;
import java.util.Objects;

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

    public Empleado(String x, String nom, String ap, String cor){
        this.apellidos = ap;
        this.nombre = nom;
        this.correo = cor;
        this.id = x;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String Datos(){
        return "id: " + id + "; nombre: " + nombre + "; Apellidos: " + apellidos + "; correo: " + correo ;
    }
}