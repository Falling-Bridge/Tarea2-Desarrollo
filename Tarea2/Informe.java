package Tarea2;
import java.util.ArrayList;
import Tarea2.excepciones.*;


import java.io.FileWriter;
import java.io.IOException;

public class Informe {//esta cumple las necesidades de la clase informe, no de nota, nota es simplemente un string, cambiar y crear la nueva
    public Informe(Reunion reu) throws Exception{
        String nombreArchivo = "informe.txt";
        try {
            FileWriter escritor = new FileWriter(nombreArchivo);
            if(reu != null) {
                escritor.write("Organizador de la reunión: \n" + reu.datosOrganizador() + "\n\n");
                ArrayList<Empleado> asistentes = reu.obtenerAsistencias();
                if (asistentes.size() != 0) {
                    for (int i = 0; i < asistentes.size(); i++) {
                        Empleado emp1 = asistentes.get(i);
                        escritor.write(emp1.Datos() + "\n"); // Escribir los datos de los empleados q asistieron
                    }
                } else {
                    escritor.write("Ninguna persona asiastio a la reunión");
                    throw new SinAsistentesException("Ninguno de los empleado que estaba en la lista de invitados de la reunion asistio a la reunión");
                }

                ArrayList<Empleado> atrasados = reu.obtenerAsistencias();
                if (atrasados.size() != 0) {
                    for (int i = 0; i < atrasados.size(); i++) {
                        Empleado emp1 = atrasados.get(i);
                        escritor.write(emp1.Datos() + "\n"); // Escribir los datos de los empleados q asistieron
                    }
                } else {
                    escritor.write("Ninguna persona llego atrasada a la reunión");
                    throw new SinAsistentesException("Ninguna persona llegoa atrasada");
                }

                if (reu instanceof ReunionVirtual) {
                    escritor.write("La reunión fue virtual y se uso el enlace: " + ((ReunionVirtual) reu).getEnlace() + "\n\n");
                } else if (reu instanceof ReunionPresencial) {
                    escritor.write("La reunión fue precencial y fue en la sala: " + ((ReunionPresencial) reu).getSala() + "\n\n");
                }

                escritor.write("Las notas generadas en la reunion fueron las siguente: \n\n");
                ArrayList<Nota> notasfinales = reu.getNoptas();
                if (notasfinales.size() != 0) {
                    for (int i = 0; i < notasfinales.size(); i++) {
                        escritor.write(notasfinales.get(i).getAnotacion() + "\n");
                    }
                } else {
                    escritor.write("No se creo ninguna nota durante la reunión");
                    throw new NotasNullException();
                }
            }
            else{
                throw new ReunionNoValidaException();
            }


            escritor.close();
            System.out.println("Se escribieron los datos en el archivo exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
}
//####################
//preguntar si la puedo convertir en una interface y q reu herede su metodo
//#####