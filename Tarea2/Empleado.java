package Tarea2;
import Tarea2.Reunion.*;
import java.util.Objects;

public class Empleado implements Invitable {
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    @Override
    /**
     * Este es un metodo de la interfaz Invitable, q permite invitar a un empleado a una reunión
     * @param reu es la reunión a la q se esta invitado un empleado
     * @param emp es el empleado q se quiere invitar
     */
    public void Invitar(Reunion reu, Empleado emp){
        reu.agregarInvitados(emp);
    }

    /**
     * Es el constructor de la clase donde se de declaran las variables privadas del empleado
     * @param x es la id del empleado
     * @param nom es el nombre del empleado
     * @param ap es el apellido del empleado
     * @param cor es el correo del empleado
     */    
    public Empleado(String x, String nom, String ap, String cor){
        this.apellidos = ap;
        this.nombre = nom;
        this.correo = cor;
        this.id = x;
    }

    @Override
    /**
     * Este es un método q se usa para saber si un empleado tiene algun doble con los mismo valores, lo cual no debe ser posible
     * @param o es el empleado q se quiere comaprar con los demas
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id);
    }

    /**
     * Es un getter q retorna todos las variables privadas del empleado
     * @return un string con la suceción de los datos del empleado
     */
    public String Datos(){
        return "id: " + id + "; nombre: " + nombre + "; Apellidos: " + apellidos + "; correo: " + correo ;
    }
}