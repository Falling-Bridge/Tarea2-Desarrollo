package Tarea2;

public class Nota {
    String anotacion;

    /**
     * Es el constructor y deja escrito el mensaje q se guarda en la nota
     * @param s es el textro q queda guardado en la nota¿
     */ 
    public Nota(String s) {
        anotacion = s;
    }

    /**
     * Es un getter q deja ver el contenido de la nota
     * @return un string q es lo q se escribio en la nota en el costructor
     */
    public String getAnotacion(){
        return anotacion;
    }
}