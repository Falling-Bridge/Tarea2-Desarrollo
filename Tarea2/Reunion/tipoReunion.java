package Tarea2.Reunion;

public enum tipoReunion {

    TECNICA(100),
    MARKETING(200),
    OTRO(300);
    private int loquesenecesite;
    /**
     * Constructor de la enum, donde se le ingresa un entero, y se guarda el tipo de reunion dependiendo del int q se ponga
     * @param whatever es el int q llega desde q se llama, y dependiendo de si es 100, 200 o 300, la enum será de tipo TECNICA, MARKETING, o  OTRO
     */
    private tipoReunion (final int whatever){
        loquesenecesite = whatever;
    }
}