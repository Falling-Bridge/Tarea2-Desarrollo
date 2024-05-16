package Tarea2;
import Tarea2.Reunion.*;
import Tarea2.excepciones.*;
import java.time.*;
import java.util.ArrayList;
import java.io.*;

public class Informe {
    public Informe(Reunion reu) throws Exception{
        String nombreArchivo = "informe.txt";
        try {
            FileWriter escritor = new FileWriter(nombreArchivo);
            if(reu != null) {

                escritor.write("Organizador de la reunión: \n" + reu.datosOrganizador() + "\n\n");
                Object[] FechayHora = reu.obtenerFechayHoraReunion();
                escritor.write("La reunion se creo el día " + FechayHora[0] + " a las " + FechayHora[1]);
                Instant[] horas = reu.obtenerHoraInicioyFin();
                escritor.write(", empezo a las " + horas[0] + "\n y termino a las  " + horas[1]);
                escritor.write(" y duro un tiempo de " + Duration.between(horas[0], horas[1]) + "\n\n");
                escritor.write("La reunion es de tipo " + reu.obtenerTipoReunion() + "\n\n");

                if (reu instanceof ReunionVirtual) {
                    escritor.write("La reunión fue virtual y se uso el enlace: " + ((ReunionVirtual) reu).getEnlace() + "\n\n");
                }
                else if (reu instanceof ReunionPresencial) {
                    escritor.write("La reunión fue presencial y tomo lugar en la sala: " + ((ReunionPresencial) reu).getSala() + "\n\n");
                }

                ArrayList<Nota> notasfinales = reu.getNotas();
                if (!notasfinales.isEmpty()) {
                    escritor.write("Las notas generadas en la reunion fueron las siguente: \n\n");
                    for (int i = 0; i < notasfinales.size(); i++) {
                        escritor.write(notasfinales.get(i).getAnotacion() + "\n\n");
                    }
                }
                else {
                    escritor.write("No se creo ninguna nota durante la reunión");
                    throw new SinContenidoException("No se creo ninguna nota\n");
                }

                if (!reu.obtenerAsistencias().isEmpty()) {
                    escritor.write("El/Los empleado/s que asistieron a la reunion es/fueron: \n\n");
                    for (Empleado emp : reu.obtenerAsistencias()) {
                        escritor.write(emp.Datos() + "\n"); // Escribir los datos de los empleados q asistieron
                    }
                    escritor.write("\nY el porcentaje de asistentes fue de un " + reu.obtenerPorcentajeAsistencia() + "%\n");
                }
                else {
                    escritor.write("Ninguna persona asiastio a la reunión");
                    throw new SinContenidoException("Ninguno de los empleado que estaba en la lista de invitados de la reunion asistio a la reunión");
                }

                if (!reu.obtenerAtraso().isEmpty()) {
                    escritor.write("\nEl/Los empleados que llegaro/n tarde es/fueron" + reu.obtenerAtraso().size() +  " es/son: \n\n");
                    for (Empleado emp : reu.obtenerAtraso()) {
                        escritor.write(emp.Datos() + "\n"); // Escribir los datos de los empleados que llegaron tarde
                    }
                }
                else {
                    escritor.write("\nNinguna persona llego atrasada a la reunión\n\n");
                    throw new SinContenidoException("Ninguna persona llegoa atrasada\n");
                }

                if(!reu.obtenerAusencias().isEmpty()) {
                    escritor.write("\nEl/Las persona/s que no llegaron a la reunión es/fueron: \n\n");
                    for (Empleado ausente : reu.obtenerAusencias()) {
                        escritor.write(ausente.Datos() + "\n");
                    }
                }
                else {
                    escritor.write("\nNinguna persona falto a la reunión\n\n");
                    throw new SinContenidoException("Ningun empleado falto a la reunion\n");
                }
            }

            else{
                throw new ReunionNoValidaException("La reunión no se a creado correctamente, intentar nuevamente o con otra reunión");
            }


            escritor.close();
            System.out.println("Se escribieron los datos en el archivo exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
}