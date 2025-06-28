package models;
/**
 * Representa un jugador en la posición de mediocampista.
 */
public class Mediocampista extends Jugador {
    private int asistencias;

    public Mediocampista(String nombre, int edad, int camiseta, String nacionalidad, int asistencias) {
        super(nombre, edad, camiseta, nacionalidad);
        this.asistencias = asistencias;
    }

    public String getTipo() {
        return "Mediocampista";
    }

    public int getEstadistica() {
        return asistencias;
    }

    public void setAsistencias(int asistencias1) {
        this.asistencias = asistencias1;
    }

    public String toLinea() {
        return String.format("JUGADOR;Mediocampista;%s;%d;%d;%s;%d", nombre, edad, camiseta, nacionalidad, asistencias);
    }
}
