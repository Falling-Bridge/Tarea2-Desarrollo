package Tarea2; 
import java.time.*;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

public abstract class Reunion {
    private long fecha;
    private Instant horaPrevista;
    private Empleado Organizador;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private ArrayList<Empleado> attendace;
    private Invitacion listaInvitados;
    private ArrayList<Nota> notas;
    private Empleado employeereunion;
    private tipoReunion typereunionreunion;

    public void agregarInvitados(Empleado emp){
        listaInvitados.invitado.add(emp);
    }

    public ArrayList obtenerAsistencias(){
        return attendace;
    }

    public String obtenerAusencias(){//list
        int asistencias = 0;
        ArrayList<Empleado> Faltantes = new ArrayList<Empleado>();
        for(int i = 0; i < listaInvitados.invitado.size(); i++){
            if(listaInvitados.invitado.)
        }
    }

    public void llegadaEmpleados(Empleado em){
        em.horallegada = Instant.now();
        attendace.add(em);//crear una exceocion ern caso de que intenten metar a un no invitado
        if(em.horallegada.compareTo(horaInicio) > 0){
            em.EstadodeLlegada = 1;
        }
        else{
            em.EstadodeLlegada = 0;
        }
    }

    public void obtenerRetrasos(){//list
        for(int i = 0; i < listaInvitados.invitado.size(); i++){
            listaInvitados.invitado
        }
    }

    public int totalAsistencias(){ //int
        return attendace.size();
    }

    public float obtenerPorcentajeAsistencia(){ //float
        return (float) listaInvitados.invitado.size() / (attendace.size()) * 100;
    }
/*
* Calcula el tiempo que dura la reunion considerando la hora de inicio y la hora de finalización
* */public Duration getDuracion(){
        duracionPrevista = Duration.between(horaInicio, horaFin);
        return duracionPrevista;
    }
/*
* Al llamar a este metódo se marca la hora de inicio de la reunion
* con una función de time.Instant
* */public void iniciar(){
        horaInicio = Instant.now();
    }
/*
*Al llamar este metódo se marca la hora en que se finaliza la reunión
* */public void finalizar(){
        horaFin = Instant.now();
    }

    public Reunion(Empleado org, tipoReunion tipo){
        attendace = new ArrayList<Empleado>();
        Organizador = org;
        fecha = getDate();
        typereunionreunion = tipo;
    }
}