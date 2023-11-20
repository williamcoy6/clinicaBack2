package co.edu.uniquindio.clinica.modelo.Enum;

public enum TipoSangre {

    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    O_POSITIVO("O+"),
    O_NEGATIVO( "O-");

    private String nombre;

    private TipoSangre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
