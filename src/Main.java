import services.Sistema;
import services.SistemaImpl;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new SistemaImpl();
        sistema.iniciar();
    }
}