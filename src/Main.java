import service.Sistema;
import service.SistemaImpl;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new SistemaImpl();
        sistema.iniciar();
    }
}