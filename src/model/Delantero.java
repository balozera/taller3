package model;
/**
 * Representa un jugador en la posición de delantero.
 */
public class Delantero extends Jugador {
    private int goles;

    public Delantero(String nombre, int edad, int camiseta, String nacionalidad, int goles) {
        super(nombre, edad, camiseta, nacionalidad);
        this.goles = goles;
    }

    public String getTipo() {
        return "Delantero";
    }

    public int getEstadistica() {
        return goles;
    }

    public void setGoles(int goles1) {
        this.goles = goles1;
    }
    public String toLinea() {
        return String.format("JUGADOR;Delantero;%s;%d;%d;%s;%d", nombre, edad, camiseta, nacionalidad, goles);
    }

}
