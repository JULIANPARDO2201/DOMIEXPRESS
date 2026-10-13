//Singleton, constructor privado para que solo exista un punto de entrada
public class GeneradorConsecutivo {

    private static GeneradorConsecutivo instancia;
    private int siguienteNumero;

    private GeneradorConsecutivo() {
        this.siguienteNumero = 1;
        System.out.println("[Consecutivo] Instancia creada.");
    }

    public static GeneradorConsecutivo obtenerInstancia() {
        if (instancia == null) {
            instancia = new GeneradorConsecutivo();
        }
        return instancia;
    }

    public String siguiente() {
        String id = String.format("PED-%04d", siguienteNumero);
        siguienteNumero++;
        return id;
    }
}
