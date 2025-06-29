package service;
import model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaImpl implements Sistema {
    private List<Jugador> plantilla = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private static final String ARCHIVO = "plantilla.txt";

    @Override
    public void iniciar() {
        leerPlantilla();
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1) Registrar jugador");
            System.out.println("2) Visualizar plantilla");
            System.out.println("3) Editar jugador");
            System.out.println("4) Eliminar jugador");
            System.out.println("5) Cambiar entrenador");
            System.out.println("6) Guardar y salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarJugador();
                case 2 -> visualizarPlantilla();
                case 3 -> editarJugador();
                case 4 -> eliminarJugador();
                case 5 -> cambiarEntrenador();
                case 6 -> guardarYSalir();
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    /**
     * Solicita datos al usuario para registrar un nuevo jugador y lo agrega a la plantilla.
     * Utiliza el patrón Builder.
     */
    @Override
    public void registrarJugador() {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            System.out.print("Número de camiseta: ");
            int camiseta = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nacionalidad: ");
            String nacionalidad = scanner.nextLine();
            System.out.print("Tipo (Portero/Defensor/Mediocampista/Delantero): ");
            String tipo = scanner.nextLine();
            System.out.print("Estadística: ");
            int estadistica = scanner.nextInt();
            scanner.nextLine();

            if (plantilla.stream().anyMatch(j -> j.getNumero() == camiseta)) {
                System.out.println("Número de camiseta repetido.");
                return;
            }

            Jugador jugador = new JugadorBuilder(tipo, nombre, edad, camiseta, nacionalidad)
                    .estadistica(estadistica)
                    .build();

            System.out.printf("Registrado: %s, Edad: %d, Camiseta: #%d, Nacionalidad: %s, Estadística: %d\n",
                    jugador.getNombre(), jugador.getEdad(), jugador.getNumero(), jugador.getNacionalidad(), jugador.getEstadistica());

            plantilla.add(jugador);
            System.out.println("Jugador registrado.");

        } catch (Exception e) {
            System.out.println("Error en el registro: " + e.getMessage());
            scanner.nextLine();
        }
    }
    /**
     * Visualizador de plantilla con todos los jugadores.
     * Permite visualizar una o más paginas.
     */
    @Override
    public void visualizarPlantilla() {
        System.out.printf("\nENTRENADOR: %s (%s)\n", Entrenador.getInstancia().getNombre(), Entrenador.getInstancia().getNacionalidad());
        System.out.println("_______________________________________________");
        final int POR_PAGINA = 4;
        int pagina = 0;
        while (true) {
            int totalPaginas = (int) Math.ceil((double) plantilla.size() / POR_PAGINA);
            int desde = pagina * POR_PAGINA;
            int hasta = Math.min(desde + POR_PAGINA, plantilla.size());
            if (desde >= hasta) {
                System.out.println("No hay jugadores para mostrar.");
                break;
            }
            System.out.println("\nPágina " + (pagina + 1) + " de " + totalPaginas);
            for (int i = desde; i < hasta; i++) {
                Jugador j = plantilla.get(i);
                String estadistica = switch (j.getTipo()) {
                    case "Portero"       -> "Atajadas: " + j.getEstadistica();
                    case "Defensor"      -> "Despejes: " + j.getEstadistica();
                    case "Mediocampista" -> "Asistencias: " + j.getEstadistica();
                    case "Delantero"     -> "Goles: " + j.getEstadistica();
                    default -> "";
                };
                System.out.printf("%d) [%s] %s - Edad: %d - N°: %d - %s - %s\n",
                        (i + 1), j.getTipo(), j.getNombre(), j.getEdad(), j.getNumero(), j.getNacionalidad(), estadistica);
            }
            System.out.println("\n[1] Siguiente\n[2] Anterior\n[3] Salir");
            System.out.print("Opción: ");
            String cmd = scanner.nextLine().trim();
            if (cmd.equals("1") && hasta < plantilla.size()) pagina++;
            else if (cmd.equals("2") && pagina > 0) pagina--;
            else if (cmd.equals("3")) break;
        }
    }

    /**
     * Edita los datos de un jugador existente, identificado por su número de camiseta.
     */
    @Override
    public void editarJugador() {
        System.out.print("Número de camiseta del jugador a editar: ");
        int camiseta = scanner.nextInt();
        scanner.nextLine();
        for (Jugador j : plantilla) {
            if (j.getNumero() == camiseta) {
                System.out.println("Jugador actual: " + j);
                System.out.printf("Edad actual: %d | Nacionalidad: %s | Estadística: %d\n",
                        j.getEdad(), j.getNacionalidad(), j.getEstadistica());

                System.out.print("Nueva edad: ");
                j.setEdad(scanner.nextInt());
                scanner.nextLine();
                System.out.print("Nueva nacionalidad: ");
                j.setNacionalidad(scanner.nextLine());
                System.out.print("Nueva estadística: ");
                int est = scanner.nextInt();
                scanner.nextLine();
                switch (j.getTipo()) {
                    case "Portero"       -> ((Portero) j).setAtajadas(est);
                    case "Defensor"      -> ((Defensor) j).setDespejes(est);
                    case "Mediocampista" -> ((Mediocampista) j).setAsistencias(est);
                    case "Delantero"     -> ((Delantero) j).setGoles(est);
                }
                System.out.println("Jugador actualizado.");
                return;
            }
        }
        System.out.println("Jugador no encontrado.");
    }

    /**
     * Elimina un jugador por nombre, si existe en la plantilla.
     */
    @Override
    public void eliminarJugador() {
        System.out.print("Nombre del jugador a eliminar: ");
        String nombre = scanner.nextLine();
        if (plantilla.removeIf(j -> j.getNombre().equalsIgnoreCase(nombre)))
            System.out.println("Jugador eliminado.");
        else
            System.out.println("Jugador no encontrado.");
    }


    /**
     * Modifica los datos del entrenador actual utilizando el patrón Singleton.
     */
    @Override
    public void cambiarEntrenador() {
        System.out.print("Nuevo nombre del entrenador: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva nacionalidad: ");
        String nac = scanner.nextLine();
        Entrenador.getInstancia().setNombre(nombre);
        Entrenador.getInstancia().setNacionalidad(nac);
        System.out.println("Entrenador actualizado.");
    }

    /**
     * Guarda en archivo todos los datos actuales del sistema.
     */

    @Override
    public void guardarYSalir() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            pw.println(Entrenador.getInstancia().toLinea());
            for (Jugador j : plantilla) pw.println(j.toLinea());
            System.out.println("Datos guardados.");
        } catch (IOException e) {
            System.out.println("Error al guardar archivo.");
        }
    }

    /**
     * Carga desde archivo la información previa de jugadores y entrenador.
     */
    private void leerPlantilla() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals("ENTRENADOR")) {
                    Entrenador.getInstancia(datos[1], datos[2]);
                } else if (datos[0].equals("JUGADOR")) {
                    Jugador j = new JugadorBuilder(datos[1], datos[2],
                            Integer.parseInt(datos[3]),
                            Integer.parseInt(datos[4]),
                            datos[5])
                            .estadistica(Integer.parseInt(datos[6]))
                            .build();
                    plantilla.add(j);
                }
            }
            if (Entrenador.getInstancia() == null)
                Entrenador.getInstancia("Entrenador Default", "Desconocido");
            System.out.println("Plantilla cargada desde archivo.");
        } catch (IOException e) {
            System.out.println("Archivo no encontrado.");
            Entrenador.getInstancia("Entrenador Default", "Desconocido");
        }
    }
}
