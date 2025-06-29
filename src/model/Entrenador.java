package model;
/**
 * Representa al entrenador principal del equipo. Implementado como Singleton.
 */
public class Entrenador {
    private static Entrenador instancia;
    private String nombre;
    private String nacionalidad;

    public Entrenador(String nacionalidad, String nombre) {
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
    }
    public static Entrenador getInstancia(String nombre, String nacionalidad) {
        if (instancia == null)
            instancia = new Entrenador(nombre, nacionalidad);
        return instancia;
    }

    public static Entrenador getInstancia() {
        return instancia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public String getNombre() {
        return nombre;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }

    public String toLinea() {
        return String.format("ENTRENADOR;%s;%s", nombre, nacionalidad);
    }

    public String toString() {
        return "Entrenador: " + nombre + " (" + nacionalidad + ")";
    }

}



