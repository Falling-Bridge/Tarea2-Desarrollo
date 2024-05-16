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

    /**
     * Es un getter para obtener la fecha en la q se crea la reunion, y la hora de lo mismo 
     * @return un arreglo de tipo Objet, ya q retorna fecha q es tipo Date y horadecreación q es tipo Instant 
     */
    public Object[] obtenerFechayHoraReunion(){
        return new Object[]{fecha, horadeCreacion};
    }
    /**
     * Es un getter para obtener la hora de inicio y la de fin de una, entregando un arreglo de la clase instant
     * @return retorna un arreglo de instantes
     */

    public Instant[] obtenerHoraInicioyFin(){
        return new Instant[]{horaInicio, horaFin};
    }
    /**
     * Es el método q se usa para agregar los empleados q se quieren ver en la reunion
     * @param emp es el empleado q se quiere invitar, el cual ingresa al arreglo de invitados
     */
    public void agregarInvitados(Empleado emp){
        listaInvitados.invitado.add(emp);
    }
    /**
     * Es un getter para obtener todos los invitados q llegaron a la reunion, se q llegaron tarde o a la hora
     * @return el arreglo de asistencias q esta en la clase asistencia
     */
    public ArrayList<Empleado> obtenerAsistencias(){
        return attendace.getAsistentes();
    }
    
    /**
     * es un getter q develve un arreglo con los atrasado, q se obtiene de la clase Atraso
     * @return el arreglo de empleados q no llegaron antes de q la reunion empezara, pero q si llegaron
     */
    public ArrayList<Empleado> obtenerAtraso(){
        return atraso.getAtrasados();
    }

    /**
     * Es un getter para obtener los empleados q fueron invitados pero q no llegaron a la reunión, o llegaron despues de q finalizara
     * @return un arreglo con todas las personas q no estan en el arreglo de asistentes pero si en el de invitados
     */
    public ArrayList<Empleado> obtenerAusencias() {
        ArrayList<Empleado> ausentes = new ArrayList<>(listaInvitados.getInvitados());
        ausentes.removeAll(attendace.getAsistentes());
        return ausentes;
    }

    /**
     * Metodo para obtener el tipo de la reunión q se le al momento de crearla
     * @return retorna la variable typereunionreunion, la cual se define en el costructor
     */
    public tipoReunion obtenerTipoReunion(){
        return typereunionreunion;
    }

    /**
     * Este es un método q registra la llegada de los empleados y los agrega a los arreglos de asistencia y/o atraso dedpendiendo de su hora de llegada
     * @param em es el empleado que intenta ingresar a la reunión, si llega antes del inicio se añade a asistentes,
     * pero si llega despues de la hora de inicio y antes dce que termine la reunión se añade a la de atrasados 
     * @throws Exception tira un excepción en caso de que el empleado que intenta entrar no este invitado a la reunión 
     */
    public void llegadaEmpleados(Empleado em) throws Exception {
            if(listaInvitados.getInvitados().contains(em)){
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

    /**
     * Método que se usa en informe para obtener el porcentaje de asistentes como su nombre lo indica xd
     * @return retorna el flotante de la divición entre la cantidad de asistentes y la de invitados
     */
    public float obtenerPorcentajeAsistencia(){ //float
        return (float) (attendace.getAsistentes().size()) / listaInvitados.invitado.size();
    }

    /**
     * Este método genera y crea una nota, la cual se guarda en un Arraylist de Notas, para que luego se puedan revisar en el informe
     * @param textonota es el texto que se tiene q ingresar como string, con el contenido de la nota, al momento de instanciarla
     */
    public void generarNota(String textonota){
        Nota nota = new Nota(textonota);
        notas.add(nota);
    }

    /**
     * Este getter se crea para poder ingresar la infomación de las notas al informe, y se usa en la clase informe
     * @return retorna un arreglo de Notas, con todas las q se crearon a lo largo de la runion
     */
    public ArrayList<Nota> getNotas(){
        return notas;
    }

    /*
     * Al llamar a este metódo se marca la hora de inicio de la reunion con una función de time.Instant
     * */
    public void iniciar(){
        horaInicio = Instant.now();
    }

    /*
     * Al llamar este metódo se instancia la hora en que se finaliza la reunión con la misma función q en iniciar
     * */
    public void finalizar() {
        horaFin = Instant.now();
    }

    /**
     * Metodo que se usa en Informe para entregar informción acerca del organizador de la reunion
     * @return retorna lo q entrega el método Datos de Empleado, q entrega todas las variables privadas de este
     */
    public String datosOrganizador(){
        return Organizador.Datos();
    }

    /**
     * Con este metodo se puede invitar a un departamento entero, invitando a cada uno de sus participantes
     * @param departamento es el departamento q se quiere invitar
     */
     public void InvitarDepartamento(Departamento departamento){
        for (Empleado emp : departamento.getEmpleados()){
            agregarInvitados(emp);
        }
    }

    /**
     * Es el constructor de la clase, en donde se inicializan los arreglos
     * @param org es el organizador de la reunión, que se guarda en la variable organizador
     * @param tipo es el tipo de la reunion, q es uno de los valores de la clase enum
     */
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