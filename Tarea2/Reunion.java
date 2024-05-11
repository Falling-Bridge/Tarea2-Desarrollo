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
    private Atraso attendace;
    private Invitacion listaInvitados;
    private ArrayList<Nota> notas;
    private Empleado employeereunion;
    private tipoReunion typereunionreunion;
    private ArrayList<Nota> informeReunion;

    public void agregarInvitados(Empleado emp){
        listaInvitados.invitado.add(emp);
    }

    public ArrayList<Empleado> obtenerAsistencias(){
        return attendace.getAsistentes();
    }

    public ArrayList<Empleado> obtenerAtraso(){
        return attendace.getAtrasados();
    }

    public void obtenerAusencias(){//list
        int asistencias = 0;
        ArrayList<Empleado> Faltantes = new ArrayList<Empleado>();
       // for(int i = 0; i < listaInvitados.invitado.size(); i++){
        //    if(listaInvitados.invitado.)
        //}
    }

    public void llegadaEmpleados(Empleado em){
        em.horallegada = Instant.now();
        attendace.addAsistentes(em);//crear una exceocion ern caso de que intenten meter a un no invitado
        if(horaInicio == null){
            em.EstadodeLlegada = 0;
        }
        else if(em.horallegada.compareTo(horaInicio) < 0){
            em.EstadodeLlegada = 0;
        }
        else{
            em.EstadodeLlegada = 1;
            attendace.ingresarAtrasados(em);
        }
    }

    public void obtenerRetrasos(){//list
        //for(int i = 0; i < listaInvitados.invitado.size(); i++){
          //  listaInvitados.invitado
        //}
    }

    public int totalAsistencias(){ //int
        return attendace.cantAsistentes();
    }

    public float obtenerPorcentajeAsistencia(){ //float
        return (float) listaInvitados.invitado.size() / (attendace.cantAsistentes()) * 100;
    }
    /*
    * Este método genera y crea una nota, la cual se guarda en un Arraylist de Notas, para que luego se puedan revisar en el informe
    * Se le tiene q ingresar un string, con el contenido de la nota
    * */
    public void generarNota(String texto){
        Nota nota = new Nota(texto);
        notas.add(nota);
    }

    /*
    * Este getter se crea para poder ingresar la infomación de las notas al informe, y se usa en la clase informe
    * */
    public ArrayList getNoptas(){
        return notas;
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
* */
public void finalizar(){
        horaFin = Instant.now();
    }

/*
* Metodo que se usa en Nota para entregar mas informción acerca del organizador de la reunion
* */
    public String datosOrganizador(){
        return Organizador.Datos();
    }

    public Reunion(Empleado org, tipoReunion tipo){
        attendace = new Atraso();
        notas = new ArrayList<Nota>();
        Organizador = org;
        //fecha = getDate();
        typereunionreunion = tipo;
    }
}