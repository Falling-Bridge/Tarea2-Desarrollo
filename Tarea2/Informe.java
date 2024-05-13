package Tarea2;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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
                escritor.write(", empezo a las " + horas[0] + " y termino a las  " + horas[1]);
                escritor.write(" y duro un tiempo de " + Duration.between(horas[0], horas[1]) + "\n\n");
                escritor.write("La reunion es de tipo " + reu.obtenerTipoReunion() + "\n\n");

                if (!reu.obtenerAsistencias().isEmpty()) {
                    for (Empleado emp : reu.obtenerAsistencias()) {
                        escritor.write(emp.Datos() + "\n"); // Escribir los datos de los empleados q asistieron
                        escritor.write("Y el porcentaje de asistentes fue de un " + reu.obtenerPorcentajeAsistencia() + "%");
                    }
                }
                else {
                    escritor.write("Ninguna persona asiastio a la reunión");
                    throw new SinContenidoException("Ninguno de los empleado que estaba en la lista de invitados de la reunion asistio a la reunión");
                }

                if (!reu.obtenerAtraso().isEmpty()) {
                    for (Empleado emp : reu.obtenerAtraso()) {
                        escritor.write(emp.Datos() + "\n"); // Escribir los datos de los empleados que llegaron tarde
                    }
                }
                else {
                    escritor.write("Ninguna persona llego atrasada a la reunión\n\n");
                    throw new SinContenidoException("Ninguna persona llegoa atrasada\n");
                }

                if(!reu.obtenerAusencias().isEmpty()) {
                    escritor.write("Las personas que llegaron ausentes fueron: \n\n");
                    for (Empleado ausente : reu.obtenerAusencias()) {
                        escritor.write(ausente.Datos() + "\n");
                    }
                }
                else {
                    escritor.write("Ninguna persona falto a la reunión\n\n");
                    throw new SinContenidoException("Ningun empleado falto a la reunion\n");
                }

                if (reu instanceof ReunionVirtual) {
                    escritor.write("La reunión fue virtual y se uso el enlace: " + ((ReunionVirtual) reu).getEnlace() + "\n\n");
                }
                else if (reu instanceof ReunionPresencial) {
                    escritor.write("La reunión fue precencial y fue en la sala: " + ((ReunionPresencial) reu).getSala() + "\n\n");
                }


                escritor.write("Las notas generadas en la reunion fueron las siguente: \n\n");
                ArrayList<Nota> notasfinales = reu.getNotas();
                if (!notasfinales.isEmpty()) {
                    for (int i = 0; i < notasfinales.size(); i++) {
                        escritor.write(notasfinales.get(i).getAnotacion() + "\n\n");
                    }
                }
                else {
                    escritor.write("No se creo ninguna nota durante la reunión");
                    throw new SinContenidoException("No se creo ninguna nota\n");
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