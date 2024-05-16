package Tarea2.Reunion;

public enum tipoReunion {

    TECNICA(100),
    MARKETING(200),
    OTRO(300);
    private int loquesenecesite;

    private tipoReunion (final int whatever){
        loquesenecesite = whatever;
    }
}