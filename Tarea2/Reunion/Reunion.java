package Tarea2.Reunion;
import Tarea2.*;
import Tarea2.excepciones.*;
import java.util.*;
import java.time.*;

public abstract class Reunion {
    private Date fecha;
    private LocalTime horadeCreacion;
    private Empleado Organizador;
    private Instant horaInicio;
    private Instant horaFin;
    private Asistencia attendace;
    private Atraso atraso;
    private Invitacion listaInvitados;
    private ArrayList<Nota> notas;
    private tipoReunion typereunionreunion;

    public Object[] obtenerFechayHoraReunion(){
        return new Object[]{fecha, horadeCreacion};
    }

    public Instant[] obtenerHoraInicioyFin(){
        return new Instant[]{horaInicio, horaFin};
    }

    public void agregarInvitados(Empleado emp){
        listaInvitados.invitado.add(emp);
    }

    public ArrayList<Empleado> obtenerAsistencias(){
        return attendace.getAsistentes();
    }

    public ArrayList<Empleado> obtenerAtraso(){
        return atraso.getAtrasados();
    }

    public ArrayList<Empleado> obtenerAusencias() {
        ArrayList<Empleado> ausentes = new ArrayList<>(listaInvitados.getInvitados());
        ausentes.removeAll(attendace.getAsistentes());
        return ausentes;
    }

    public tipoReunion obtenerTipoReunion(){
        return typereunionreunion;
    }

    public void llegadaEmpleados(Empleado em) throws Exception {
        if(listaInvitados.getInvitados().contains(em)){
            Instant aux = Instant.now();
            if (horaInicio == null) {
                attendace.addAsistentes(em);
            }else {
                if (horaFin == null) {
                    attendace.addAsistentes(em);
                    atraso.addAtrasados(em);
                }
            }
        }else {
            throw new EmpleadoNoInvitadoException("El empleado no ha sido invitado");
        }
    }

    public float obtenerPorcentajeAsistencia(){ //float
        return (float) (attendace.getAsistentes().size()) / listaInvitados.invitado.size();
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

    /* Al llamar a este metódo se marca la hora de inicio de la reunion
     * con una función de time.Instant
     * */
    public Instant iniciar(){
        return horaInicio = Instant.now();
    }
    /*
     *Al llamar este metódo se marca la hora en que se finaliza la reunión
     * */
    public Instant finalizar() {
        return horaFin = Instant.now();
    }
    /*
     * Metodo que se usa en Nota para entregar mas informción acerca del organizador de la reunion
     * */
    public String datosOrganizador(){
        return Organizador.Datos();
    }

    public void InvitarDepartamento(Departamento departamento){
        for (Empleado emp : departamento.getEmpleados()){
            agregarInvitados(emp);
        }
    }

    public Reunion(Empleado org, tipoReunion tipo){
        attendace = new Asistencia();
        atraso = new Atraso();
        listaInvitados = new Invitacion();
        notas = new ArrayList<Nota>();
        Organizador = org;
        fecha = new Date();
        horadeCreacion = LocalTime.now();
        typereunionreunion = tipo;
    }
}