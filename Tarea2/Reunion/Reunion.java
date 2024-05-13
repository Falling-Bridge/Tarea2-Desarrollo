package Tarea2.Reunion;
import Tarea2.*;
import java.time.*;
import java.util.ArrayList;

public abstract class Reunion {
    private Atraso attendace;
    private long fecha;
    private Empleado Organizador;
    private Duration duracionPrevista;
    private Instant horaPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private Invitacion listaInvitados;
    private Invitacion listainasistencias;
    private Empleado employeereunion;
    private tipoReunion typereunionreunion;
    private ArrayList<Nota> notas;
    private ArrayList<Nota> informeReunion;

    public void agregarInvitados(Empleado emp){
        listaInvitados.invitado.add(emp);
    }

    public ArrayList<Empleado> obtenerAsistencias(){
        return attendace.getAsistentes();
    }

    public void agregarInasistencias(Empleado emp){
        if (!listaInvitados.invitado.contains(emp)) {
            listainasistencias.invitado.add(emp);
        }
    }

    public ArrayList<Empleado> obtenerInasistencias(){//list
            return listainasistencias.invitado;
    }

    public void llegadaEmpleados(Empleado em){
        em.horallegada = Instant.now();
        attendace.addAsistentes(em);//crear una exceocion ern caso de que intenten meter a un no invitado
        if(horaInicio == null){
            attendace.addAsistentes(em);
        }
        else if(em.horallegada.compareTo(horaInicio) < 0){
            attendace.addAsistentes(em);
        }
        else{
            attendace.ingresarAtrasados(em);
        }
    }

    public ArrayList<Empleado> obtenerAtraso(){
        return attendace.getAtrasados();
    }

    public void obtenerRetrasos(){//list
        //for(int i = 0; i < listaInvitados.invitado.size(); i++){
          //  listaInvitados.invitado
        //}
    }

    public int totalAsistencias(){ //int
        return attendace.cantAsistentes();
    }

    public int totalInasistencias(){
        return attendace.cantInasistentes();
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
    * */
    public Duration getDuracion(Duration duration){
        duracionPrevista = Duration.between(horaInicio, horaFin);
        return duracionPrevista;
    }
    /*
    * Al llamar a este metódo se marca la hora de inicio de la reunion
    * con una función de time.Instant
    */
    public void iniciar(){
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
    public Empleado datosOrganizador(){
        return Organizador;
    }

    public Reunion(Empleado org, tipoReunion tipo){
        attendace = new Atraso();
        notas = new ArrayList<Nota>();
        Organizador = org;
        listaInvitados = new Invitacion();
        listainasistencias = new Invitacion();
        //fecha = getDate();
        typereunionreunion = tipo;
    }
}