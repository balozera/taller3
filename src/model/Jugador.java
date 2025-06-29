package model;


/**
 * Representa un jugador genérico del equipo.
 * Contiene los atributos comunes a todas las posiciones.
 */
public abstract class Jugador {
    protected String nombre;
    protected int edad;
    protected int camiseta;
    protected String nacionalidad;

    public Jugador(String nombre, int edad, int camiseta, String nacionalidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.camiseta = camiseta;
        this.nacionalidad = nacionalidad;
    }

    public abstract String getTipo();
    public abstract int getEstadistica();
    public abstract String toLinea();

    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    public int getNumero() {
        return camiseta;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (#%d), %d años, %s", getTipo(), nombre, camiseta, edad, nacionalidad);
    }
}