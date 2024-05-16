package Tarea2;
import Tarea2.Reunion.*;
import org.junit.jupiter.api.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test   @DisplayName("test01")  //prueba la creación de empleados (no se agrega al departamento)
                                    //y que devuelva los mismos datos usados al crear el empleado
    public void testCrearEmpleado() {
        Empleado empleado1 = new Empleado("001", "Alvaro","Flar", "alvaro@example.com");
        String datosEsperados = "id: 001; nombre: Alvaro; Apellidos: Flar; correo: alvaro@example.com";
        assertEquals(datosEsperados, empleado1.Datos(), "Falla en test01:");
    }

    @Test   @DisplayName("test02")//agregación de los empleados a la lista de empleados en Departamento
    public void AgregarEmpleadoALista() {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado empleado1 = new Empleado("001", "Juan", "Pérez","juan@example.com" );
        Empleado empleado2 = new Empleado("002", "María", "López", "maria@example.com");
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);

        assertEquals(2, departamento.ObtenerCantidadEmpleados(), "El número de empleados en el departamento debe ser 3");
    }

    @Test   @DisplayName("test03") //prueba que no hayan empleados repetidos
    public void noHayEmpleadosDuplicados() {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado empleado1 = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado2 = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado3 = new Empleado("002", "María", "López", "maria@example.com");

        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado3);
        departamento.AgregarEmpleado(empleado2);

        assertEquals(2, departamento.ObtenerCantidadEmpleados(), "El número de empleados debe ser 3");
    }

    @Test   @DisplayName("test 04") //creación de reunión, se necesita un organizador para ello, por lo tanto se crea un
                                    //organizador para la misma. el tipo de reunion podría ser 'MARKETING,TÉCNICA U OTRO'
                                    //no influye en el como se crea
    public void CrearReunionespresenciales() {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        departamento.AgregarEmpleado(organizador);
        //para crear una reunión (virtual o presencial) pide: alguien que la organice, el tipo de reunion y el lugar donde tomara lugar
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202");

        assertNotNull(reunionpres, "La reunión presencial no se creó correctamente");
        assertEquals(organizador, departamento.Organizadorreunion(), "El organizador de la reunión no coincide");
        assertEquals(tipoReunion.MARKETING, reunionpres.obtenerTipoReunion(), "El tipo de reunión no coincide");
        assertEquals("Sala 202", reunionpres.getSala(), "El lugar de la reunión no coincide");
    }

    @Test   @DisplayName("test 05") //creación de reunión virtual, requiere lo mismo que una reunion presencial
                                    //lo unico que difiere es: que  en lugar de una sala requiere un enlace y se crea un
                                    //objeto de tipo ReunionVirtual

    public void CrearReunionesvirtuales() {
        Departamento departamento = new Departamento(0,"departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        departamento.AgregarEmpleado(organizador);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.MARKETING, "https://www.youtube.com/watch?v=xvFZjo5PgG0");

        assertNotNull(reunionvir, "La reunión virtual no se creó correctamente");
        assertEquals(organizador, departamento.Organizadorreunion(), "El organizador de la reunión no coincide");
        assertEquals(tipoReunion.MARKETING, reunionvir.obtenerTipoReunion(), "El tipo de reunión no coincide");
        assertEquals("https://www.youtube.com/watch?v=xvFZjo5PgG0", reunionvir.getEnlace(), "El lugar de la reunión no coincide");
    }

    @Test   @DisplayName("test 06") //creación de reunion presencial con personas que llegan antes de que comience
                                    //se hace lo que se ha probado antes, pero ahora también se invitan a la reunion
                                    //a todos los empleados del departamento
    public void CrearReunionespresencialesconempleados() throws Exception { //reunion presencial con tiempos y personas
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202");

        reunionpres.InvitarDepartamento(departamento);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(empleado2);
        reunionpres.obtenerFechayHoraReunion();
        //al crear un sentido de tiempo, con los siguientes 2 metodos se requiere saber si al usar llegadaEmpleados()
        //llegan antes, durante o después del tiempo que dure la reunión
        reunionpres.iniciar();
        reunionpres.finalizar();

        assertTrue(reunionpres.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado2), "El empleado 2 no está registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(3, reunionpres.obtenerAsistencias().size(), "El número total de asistentes no es correcto");
    }

    @Test   @DisplayName("test 07") //creación de reunion virtual con personas, que llegan antes del comienzo de la reunion
    public void CrearReunionesvirtualesconempleados() throws Exception { //reunion presencial con tiempos y personas
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.MARKETING, "\"https://www.youtube.com/watch?v=xvFZjo5PgG0\"");

        reunionvir.InvitarDepartamento(departamento);
        reunionvir.llegadaEmpleados(empleado1);
        reunionvir.llegadaEmpleados(empleado2);
        reunionvir.llegadaEmpleados(organizador);
        reunionvir.obtenerHoraInicioyFin();
        //al crear un sentido de tiempo, con los siguientes 2 metodos se requiere saber si al usar llegadaEmpleados()
        //llegan antes, durante o después del tiempo que dure la reunión
        reunionvir.iniciar();
        reunionvir.finalizar();

        assertTrue(reunionvir.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(empleado2), "El empleado 2 no está registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(3, reunionvir.obtenerAsistencias().size(), "El número total de asistentes no es correcto");
    }

    @Test   @DisplayName("test 08") //creación de reuniones presenciales con personas que llegan antes y después de la reunion
    public void Crearreunionespresencialesconinasistencias() throws Exception {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202");

        reunionpres.InvitarDepartamento(departamento);
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.obtenerFechayHoraReunion();
        reunionpres.iniciar();
        reunionpres.finalizar();
        reunionpres.llegadaEmpleados(empleado2);

        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionpres.obtenerAusencias().contains(empleado2), "El empleado 2 no está registrado con ausencia");
        assertEquals(2, reunionpres.obtenerAsistencias().size(), "El número total de asistentes no es correcto");
        assertEquals(1, reunionpres.obtenerAusencias().size(), "El número total de ausentes no es correcto");
    }

    @Test   @DisplayName("test 09") //creación de reuniones virtuales con personas que llegan antes y después de la reunion
    public void Crearreunionesvirtualesesconinasistencias() throws Exception {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.MARKETING, "\"https://www.youtube.com/watch?v=xvFZjo5PgG0\"");

        reunionvir.InvitarDepartamento(departamento);
        reunionvir.llegadaEmpleados(empleado1);
        reunionvir.llegadaEmpleados(organizador);
        reunionvir.iniciar();
        reunionvir.finalizar();
        reunionvir.llegadaEmpleados(empleado2);

        assertTrue(reunionvir.obtenerAsistencias().contains(empleado1), "El empleado 1 no está registrado como asistente");
        assertTrue(reunionvir.obtenerAusencias().contains(empleado2), "El empleado 2 no está registrado como inasistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertEquals(2, reunionvir.obtenerAsistencias().size(), "El número total de asistentes no es correcto");
        assertEquals(1, reunionvir.obtenerAusencias().size(), "El número total de ausentes no es correcto");
    }

    @Test   @DisplayName("test10")//creacion de reuniones presenciales con asistencias y retrasos
    public void Reunionespresencialescontiempoasistenciayretraso() throws Exception {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202");

        reunionpres.InvitarDepartamento(departamento);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.iniciar();
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(empleado2);
        reunionpres.finalizar();
        //si llegan después del comienzo pero antes del fin, se considerará en la lista de asistencia y la de atrasos

        assertTrue(reunionpres.obtenerAsistencias().contains(organizador),"El organizador no está registrado como asistente");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado1),"El empleado1 no está registrado con atraso");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado2),"El empleado2 no está registrado con atraso");
        assertEquals(2,reunionpres.obtenerAtraso().size(), "El número de retrasos no es correcto");
        assertEquals(3,reunionpres.obtenerAsistencias().size(),"El número de asistencias no es correcto");
    }

    @Test   @DisplayName("test 11") //creación de reuniones presenciales con asistencias, retrasos y ausencias
    public void Reunionespresencialesconasistenciasinasistenciasretrasos() throws Exception {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.TECNICA, "Sala 202");

        reunionpres.InvitarDepartamento(departamento);
        reunionpres.llegadaEmpleados(organizador);
        reunionpres.iniciar();
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.finalizar();
        reunionpres.llegadaEmpleados(empleado2);

        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado1), "El empleado1 no está registrado con atraso");
        assertTrue(reunionpres.obtenerAusencias().contains(empleado2), "El empleado2 no está registrado como ausente");
        assertEquals(2,reunionpres.obtenerAsistencias().size(),"El número de asistentes no es correcto");
        assertEquals(1,reunionpres.obtenerAtraso().size(),"El número de retrasos no es correcto");
        assertEquals(1,reunionpres.obtenerAusencias().size(),"El número de ausentes no es correcto");
    }

    @Test   @DisplayName("test 12") //creación de reuniones virtuales con asistencias, retrasos y ausencias
    public void Reunionesvirtualesconasistenciasinasistenciasretrasos() throws Exception {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juan", "Pérez", "juan@example.com");
        Empleado empleado1 = new Empleado("002", "María", "López", "maria@example.com");
        Empleado empleado2 = new Empleado("003", "Pablo", "Ética", "pablo@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.TECNICA, "\"https://www.youtube.com/watch?v=xvFZjo5PgG0\"");

        reunionvir.InvitarDepartamento(departamento);
        reunionvir.llegadaEmpleados(organizador);
        reunionvir.iniciar();
        reunionvir.llegadaEmpleados(empleado1);
        reunionvir.finalizar();
        reunionvir.llegadaEmpleados(empleado2);

        assertTrue(reunionvir.obtenerAsistencias().contains(organizador), "El organizador no está registrado");
        assertTrue(reunionvir.obtenerAtraso().contains(empleado1), "El empleado1 no está registrado con atraso");
        assertTrue(reunionvir.obtenerAusencias().contains(empleado2), "El empleado2 no está registrado como ausente");
        assertEquals(2,reunionvir.obtenerAsistencias().size(),"El número de asistentes no es correcto");
        assertEquals(1,reunionvir.obtenerAtraso().size(),"El número de retrasos no es correcto");
        assertEquals(1,reunionvir.obtenerAusencias().size(),"El número de ausentes no es correcto");
    }

    @Test   @DisplayName("test 13") //creacion de informe de reunion presencial, con:
                                    //fecha - hora de la hora (inicio, fin) - duracion de la reunion
                                    //tema de la reunion (tipo reunion) - nota de lo que se hablò
                                    //tipo de reunion (presencial o virtual)
                                    //lista invitados, asistentes, atrasados, ausentes
    public void CreaciondeInformereunionpresencial() throws Exception {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juanito","Alcachofa", "juanito@example.com");
        Empleado empleado1 = new Empleado("002", "Juan", "Pérez", "juan@example.com");
        Empleado empleado2 = new Empleado("003", "María", "López", "maria@example.com");
        Empleado empleado3 = new Empleado("004", "Pedro", "González", "pedro@example.com");
        Empleado empleado4 = new Empleado("005", "Ana", "Martínez", "ana@example.com");
        Empleado empleado5 = new Empleado("006", "Carlos", "Rodríguez", "carlos@example.com");
        Empleado empleado6 = new Empleado("007", "Sofía", "Hernández", "sofia@example.com");
        Empleado empleado7 = new Empleado("008", "Luis", "Díaz", "luis@example.com");
        Empleado empleado8 = new Empleado("009", "Laura", "García", "laura@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        departamento.AgregarEmpleado(empleado3);
        departamento.AgregarEmpleado(empleado4);
        departamento.AgregarEmpleado(empleado5);
        departamento.AgregarEmpleado(empleado6);
        departamento.AgregarEmpleado(empleado7);
        departamento.AgregarEmpleado(empleado8);
        ReunionPresencial reunionpres = new ReunionPresencial(organizador, tipoReunion.MARKETING, "Sala 202");

        reunionpres.InvitarDepartamento(departamento);

        reunionpres.llegadaEmpleados(organizador);
        reunionpres.llegadaEmpleados(empleado1);
        reunionpres.llegadaEmpleados(empleado2);
        reunionpres.iniciar();
        //se crea la sensación de que el tiempo ha 'pasado'
        Thread.sleep(500);
        reunionpres.llegadaEmpleados(empleado3);
        reunionpres.llegadaEmpleados(empleado4);
        reunionpres.llegadaEmpleados(empleado5);
        reunionpres.finalizar();
        reunionpres.llegadaEmpleados(empleado6);
        reunionpres.llegadaEmpleados(empleado7);
        reunionpres.llegadaEmpleados(empleado8);

        //se verifica que las listas concuerden con el como 'sucedio' la reunion; en este caso hubieron 6 asistentes, 3 retrasos
        // y 3 ausencias
        assertTrue(reunionpres.obtenerAsistencias().contains(organizador), "El organizador no està registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado1), "El empleado1 no està registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado2),"El empleado2 no està registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado3),"El empleado3 no està registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado4),"El empleado4 no està registrado como asistente");
        assertTrue(reunionpres.obtenerAsistencias().contains(empleado5),"El empleado5 no està registrado como asistente");

        assertTrue(reunionpres.obtenerAtraso().contains(empleado3),"El empleado3 no està registrado con atraso");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado4),"El empleado4 no està registrado con atraso");
        assertTrue(reunionpres.obtenerAtraso().contains(empleado5),"El empleado5 no està registrado con atraso");

        assertTrue(reunionpres.obtenerAusencias().contains(empleado6),"El empleado6 no està registrado con atraso");
        assertTrue(reunionpres.obtenerAusencias().contains(empleado7),"El empleado7 no està registrado con atraso");
        assertTrue(reunionpres.obtenerAusencias().contains(empleado8),"El empleado8 no està registrado con atraso");

        assertEquals(6,reunionpres.obtenerAsistencias().size(), "El nùmero de asistentes es erròneo");
        assertEquals(3,reunionpres.obtenerAtraso().size(), "El nùmero de atrasos es erròneo");
        assertEquals(3,reunionpres.obtenerAusencias().size(), "El nùmero de asistentes es erròneo");

        //la generación de notas se logra al llamar al metodo 'generarNota' con el objeto de tipo ReunionPresencial
        //para crear una nota solo se requiere ingresar un string
        reunionpres.generarNota("Se hablò de como mejorar los anuncios para marketing");
        //luego de haber finalizado la reunión y haber tomado notas de la misma, se procede a crear la reunión
        //metodo el cual tomara toda la información que se muestra antes y escribirá un archivo llamado "informe.txt"
        Informe informe = new Informe(reunionpres);
    }

    @Test   @DisplayName("test 14") //creacion de informe de reunion virtual, con:
                                    //fecha - hora de la hora (inicio, fin) - duracion de la reunion
                                    //tema de la reunion (tipo reunion) - nota de lo que se hablò
                                    //tipo de reunion (presencial o virtual)
                                    //lista invitados, asistentes, atrasados, ausentes

    public void CreaciondeInformereunionvirtual() throws Exception {
        Departamento departamento = new Departamento(0, "departamento");
        Empleado organizador = new Empleado("001", "Juanito", "Alcachofa", "juanito@example.com");
        Empleado empleado1 = new Empleado("002", "Juan", "Pérez", "juan@example.com");
        Empleado empleado2 = new Empleado("003", "María", "López", "maria@example.com");
        Empleado empleado3 = new Empleado("004", "Pedro", "González", "pedro@example.com");
        Empleado empleado4 = new Empleado("005", "Ana", "Martínez", "ana@example.com");
        Empleado empleado5 = new Empleado("006", "Carlos", "Rodríguez", "carlos@example.com");
        Empleado empleado6 = new Empleado("007", "Sofía", "Hernández", "sofia@example.com");
        Empleado empleado7 = new Empleado("008", "Luis", "Díaz", "luis@example.com");
        Empleado empleado8 = new Empleado("009", "Laura", "García", "laura@example.com");
        departamento.AgregarEmpleado(organizador);
        departamento.AgregarEmpleado(empleado1);
        departamento.AgregarEmpleado(empleado2);
        departamento.AgregarEmpleado(empleado3);
        departamento.AgregarEmpleado(empleado4);
        departamento.AgregarEmpleado(empleado5);
        departamento.AgregarEmpleado(empleado6);
        departamento.AgregarEmpleado(empleado7);
        departamento.AgregarEmpleado(empleado8);
        ReunionVirtual reunionvir = new ReunionVirtual(organizador, tipoReunion.TECNICA, "\"https://www.youtube.com/watch?v=xvFZjo5PgG0\"");

        reunionvir.InvitarDepartamento(departamento);

        reunionvir.llegadaEmpleados(organizador);
        reunionvir.llegadaEmpleados(empleado1);
        reunionvir.llegadaEmpleados(empleado2);
        reunionvir.iniciar();
        //se crea la sencación de que el tiempo ha 'pasado'
        Thread.sleep(500);
        reunionvir.llegadaEmpleados(empleado3);
        reunionvir.llegadaEmpleados(empleado4);
        reunionvir.llegadaEmpleados(empleado5);
        reunionvir.finalizar();
        reunionvir.llegadaEmpleados(empleado6);
        reunionvir.llegadaEmpleados(empleado7);
        reunionvir.llegadaEmpleados(empleado8);

        //se verifica que las listas concuerden con el como 'sucedio' la reunion; en este caso hubieron 6 asistentes, 3 retrasos
        // y 3 ausencias
        assertTrue(reunionvir.obtenerAsistencias().contains(organizador), "El organizador no està registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(empleado1), "El empleado1 no està registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(empleado2), "El empleado2 no està registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(empleado3), "El empleado3 no està registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(empleado4), "El empleado4 no està registrado como asistente");
        assertTrue(reunionvir.obtenerAsistencias().contains(empleado5), "El empleado5 no està registrado como asistente");

        assertTrue(reunionvir.obtenerAtraso().contains(empleado3), "El empleado3 no està registrado con atraso");
        assertTrue(reunionvir.obtenerAtraso().contains(empleado4), "El empleado4 no està registrado con atraso");
        assertTrue(reunionvir.obtenerAtraso().contains(empleado5), "El empleado5 no està registrado con atraso");

        assertTrue(reunionvir.obtenerAusencias().contains(empleado6), "El empleado6 no està registrado con atraso");
        assertTrue(reunionvir.obtenerAusencias().contains(empleado7), "El empleado7 no està registrado con atraso");
        assertTrue(reunionvir.obtenerAusencias().contains(empleado8), "El empleado8 no està registrado con atraso");

        assertEquals(6, reunionvir.obtenerAsistencias().size(), "El nùmero de asistentes es erròneo");
        assertEquals(3, reunionvir.obtenerAtraso().size(), "El nùmero de atrasos es erròneo");
        assertEquals(3, reunionvir.obtenerAusencias().size(), "El nùmero de asistentes es erròneo");

        //luego de haber finalizado la reunión y haber tomado notas de la misma, se procede a crear la reunión
        //metodo el cual tomara toda la información que se muestra antes y escribirá un archivo llamado "informe.txt"
        reunionvir.generarNota("Se hablò de como mejorar los tècnicas al momento de hablar con el cliente");
        //luego de haber finalizado la reunión y haber tomado notas de la misma, se procede a crear la reunión
        //metodo el cual tomara toda la información que se muestra antes y escribirá un archivo llamado "informe.txt"
        Informe informe = new Informe(reunionvir);
    }
}