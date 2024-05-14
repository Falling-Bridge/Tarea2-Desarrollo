package Tarea2;
import java.time.*;
import java.util.Date;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Reunion {
    private Date fecha;
    private LocalTime horadeCreacion;
    private Empleado Organizador;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private Atraso attendace;
    private Invitacion listaInvitados;
    private ArrayList<Nota> notas;
    private tipoReunion typereunionreunion;

    public void agregarInvitados(Empleado emp){
        listaInvitados.invitado.add(emp);
    }

    public ArrayList<Empleado> obtenerAsistencias(){
        return attendace.getAsistentes();
    }

    public ArrayList<Empleado> obtenerAtraso(){
        return attendace.getAtrasados();
    }

    public tipoReunion obtenerTipoReunion(){
        return typereunionreunion;
    }
    public Object[] obtenerFechayHoraReunion(){
        return new Object[]{fecha, horadeCreacion};
    }

    public Instant[] obtenerHoraInicioyFin(){
        return new Instant[]{horaInicio, horaFin};
    }

    public ArrayList<Empleado> obtenerAusencias(){//list
        ArrayList<Empleado> ausentes = new ArrayList<Empleado>();
        for (Empleado invitado : listaInvitados.getInvitados()) {
            boolean asistio = false;
            for (Empleado asistente : attendace.getAsistentes()) {
                if (asistente == invitado) {
                    asistio = true;
                    break;
                }
            }
            if (!asistio) {
                ausentes.add(invitado);
            }
        }
        return ausentes;
    }

    public void llegadaEmpleados(Empleado em) throws Exception{
        if(listaInvitados.getInvitados().contains(em)){
            em.horallegada = Instant.now();
            if (horaInicio == null) {
                attendace.addAsistentes(em);
            } else if (em.horallegada.compareTo(horaInicio) < 0) {
                attendace.addAsistentes(em);
            } else {
                attendace.ingresarAtrasados(em);
            }
        }
        else {
            throw new EmpleadoNoInvitadoException();
        }
    }

    public ArrayList<Empleado> obtenerRetrasos(){
        return attendace.getAtrasados();
    }

    public int totalAsistencias(){ //int
        return attendace.cantAsistentes();
    }

    public float obtenerPorcentajeAsistencia(){ //float
        return (float) (attendace.cantAsistentes()) / listaInvitados.invitado.size();
    }

    /*
     * Este método genera y crea una nota, la cual se guarda en un Arraylist de Notas, para que luego se puedan revisar en el informe
     * Se le tiene q ingresar un string, con el contenido de la nota
     * */
    public void generarNota(String textonota){
        Nota nota = new Nota(textonota);
        notas.add(nota);
    }

    /*
     * Este getter se crea para poder ingresar la infomación de las notas al informe, y se usa en la clase informe
     * */
    public ArrayList getNotas(){
        return notas;
    }
    /*
     * Calcula el tiempo que dura la reunion considerando la hora de inicio y la hora de finalización
     * */
    public Duration getDuracion(){
        duracionPrevista = Duration.between(horaInicio, horaFin);
        return duracionPrevista;
    }
    /*
     * Al llamar a este metódo se marca la hora de inicio de la reunion
     * con una función de time.Instant
     * */
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
    public String datosOrganizador(){
        return Organizador.Datos();
    }

    public Reunion(Empleado org, tipoReunion tipo){
        attendace = new Atraso();
        listaInvitados = new Invitacion();
        notas = new ArrayList<Nota>();
        Organizador = org;
        fecha = new Date();
        horadeCreacion = LocalTime.now();
        typereunionreunion = tipo;
    }
}