package model;
/**
 * Representa un jugador en la posición de portero.
 */
public class Portero extends Jugador {
    private int atajadas;

    public Portero(String nombre, int edad, int numero, String nacionalidad, int atajadas) {
        super(nombre, edad, numero, nacionalidad);
        this.atajadas = atajadas;
    }

    public String getTipo() {
        return "Portero";
    }
    public int getEstadistica() {
        return atajadas;
    }
    public void setAtajadas(int atajadas1) {
        this.atajadas = atajadas1;
    }
    public String toLinea() {
        return String.format("JUGADOR;Portero;%s;%d;%d;%s;%d", nombre, edad, camiseta, nacionalidad, atajadas);
    }
}