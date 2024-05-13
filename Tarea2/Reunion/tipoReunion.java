package Tarea2.Reunion;

public enum tipoReunion {

    TECNICA(100),
    MARKETING(200),
    OTRO(300);

    private Reunion typereunionenum;
    private int loquesenecesite;

    public int loquesenecesite(){

        return loquesenecesite;
    }
    
    private tipoReunion (final int whatever){

        loquesenecesite = whatever;
    }
}
