package model;
/**
 * Representa un jugador en la posición de defensor.
 */
public class Defensor extends Jugador {
    private int despejes;

    public Defensor(String nombre, int edad, int camiseta, String nacionalidad, int despejes) {
        super(nombre, edad, camiseta, nacionalidad);
        this.despejes = despejes;
    }
    public String getTipo() {
        return "Defensor";
    }

    public int getEstadistica() {
        return despejes;
    }

    public void setDespejes(int despejes1) {
        this.despejes = despejes1;
    }

    public String toLinea() {
        return String.format("JUGADOR;Defensor;%s;%d;%d;%s;%d", nombre, edad, camiseta, nacionalidad, despejes);
    }

}
