package model;
/**
 * Builder para construir objetos de tipo Jugador (y subclases).
 */
public class JugadorBuilder {
    private final String tipo;
    private final String nombre;
    private final int edad;
    private final int camiseta;
    private final String nacionalidad;
    private int estadistica = 0;

    public JugadorBuilder(String tipo, String nombre, int edad, int camiseta, String nacionalidad) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.edad = edad;
        this.camiseta = camiseta;
        this.nacionalidad = nacionalidad;
    }

    public JugadorBuilder estadistica(int estadistica) {
        this.estadistica = estadistica;
        return this;
    }
    /**
     * Construye el jugador según su tipo y los datos proporcionados.
     * Hace las validaciones correspondientes.
     */
    public Jugador build() {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Nombre vacío.");
        }
        if (edad < 15 || edad > 45) {
            System.out.println("Edad fuera de rango, se usará 18 por defecto.");
        }
        if (camiseta < 1 || camiseta > 99) {
            System.out.println("Número de camiseta inválido, se usará 99.");
        }
        if (nacionalidad == null || nacionalidad.isEmpty()) {
            System.out.println("Nacionalidad vacía, se usará 'Desconocido'.");
        }
        if (estadistica < 0) {
            System.out.println("Estadística negativa, se usará 0.");
        }

        String nombreVal = (nombre == null || nombre.isEmpty()) ? "Sin nombre" : nombre;
        int edadVal = (edad < 15 || edad > 45) ? 18 : edad;
        int camisetaVal = (camiseta < 1 || camiseta > 99) ? 99 : camiseta;
        String nacVal = (nacionalidad == null || nacionalidad.isEmpty()) ? "Desconocido" : nacionalidad;
        int statVal = (estadistica < 0) ? 0 : estadistica;

        return switch (tipo.toLowerCase()) {
            case "portero"       -> new Portero(nombreVal, edadVal, camisetaVal, nacVal, statVal);
            case "defensor"      -> new Defensor(nombreVal, edadVal, camisetaVal, nacVal, statVal);
            case "mediocampista" -> new Mediocampista(nombreVal, edadVal, camisetaVal, nacVal, statVal);
            case "delantero"     -> new Delantero(nombreVal, edadVal, camisetaVal, nacVal, statVal);
            default -> throw new IllegalArgumentException("Tipo de jugador desconocido: " + tipo);
        };
    }
}
