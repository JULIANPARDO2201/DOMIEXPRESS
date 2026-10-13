public class FormatoDinero {

    private FormatoDinero() {
    }

    public static String pesos(double valor) {
        return String.format("%.0f", valor);
    }
}
