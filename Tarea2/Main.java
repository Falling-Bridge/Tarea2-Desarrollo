//package Tarea2;
//import Tarea2.Reunion.*;
//import Tarea2.excepciones.*;
//
//public class Main {
//    public static void main(String[] args) throws Exception{
//        Empleado trabajador1 = new Empleado("" +10, "Empleado1" ,  "Empleado1@gmail.com", "perez");
//        Empleado trabajador2 = new Empleado("" +20, "Empleado2" ,  "Empleado2@gmail.com", "perez");
//        Empleado trabajador3 = new Empleado("" +30, "Empleado3" ,  "Empleado3@gmail.com", "perez");
//        Empleado trabajador4 = new Empleado("" +40, "Empleado4" ,  "Empleado4@gmail.com", "perez");
//        System.out.println(trabajador1.getDatos());

//        tipoReunion tipo = tipoReunion.MARKETING ;
//        String salareu = "sala202";
//        ReunionPresencial reu = new ReunionPresencial(trabajador1, tipo, salareu);
//        reu.llegadaEmpleados(trabajador2);
//        ReunionVirtual reuv = null;
//        try{
//            Informe informe = new Informe(reuv);
//        }
//        catch (ReunionNoValidaException e){
//            System.out.println(e.getMessage());
//        }
//    }
//}