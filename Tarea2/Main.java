package Tarea2;
public class Main {
    public static void main(String[] args) throws Exception{
        Empleado trabajador1 = new Empleado("" +10, "Empleado1" ,  "perez", "Empleado1@gmail.com");
        Empleado trabajador2 = new Empleado("" +20, "Empleado2" ,  "perez", "Empleado2@gmail.com");
        Empleado trabajador3 = new Empleado("" +30, "Empleado3" ,  "perez", "Empleado3@gmail.com");
        Empleado trabajador4 = new Empleado("" +40, "Empleado4" ,  "perez", "Empleado4@gmail.com");
        Empleado trabajador5 = new Empleado("" +50, "Empleado5" ,  "perez", "Empleado5@gmail.com");
        System.out.println(trabajador1.Datos());

        tipoReunion tipo = tipoReunion.MARKETING ;
        String salareu = "sala202";
        ReunionPresencial reu = new ReunionPresencial(trabajador1, tipo, salareu);

        try{
            trabajador1.invitar(reu, trabajador1);
            trabajador1.invitar(reu, trabajador2);
            trabajador1.invitar(reu, trabajador3);
            trabajador1.invitar(reu, trabajador4);
            reu.llegadaEmpleados(trabajador2);
            reu.llegadaEmpleados(trabajador1);
            reu.iniciar();
            reu.llegadaEmpleados(trabajador3);
            reu.generarNota("El trabajador 3 llego tarde xd");
            reu.finalizar();
            reu.generarNota("El trabajador 4 no llego a la reunión xd");


            System.out.println(reu.obtenerFechayHoraReunion()[0]);
            Informe informe = new Informe(reu);
            System.out.println(reu.obtenerFechayHoraReunion()[1]);
        }
        catch (ReunionNoValidaException | EmpleadoNoInvitadoException | SinContenidoException | FaltanDatosEmpleado  e){
            System.out.println(e.getMessage());
        }


    }
}