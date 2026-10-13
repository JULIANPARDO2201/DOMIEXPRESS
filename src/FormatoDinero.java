/**
 * Una sola responsabilidad: pasar un monto a texto.
 * Ni el pedido ni el cobro deciden el formato.
 */
public class FormatoDinero {

    private FormatoDinero() {
    }

    public static String pesos(double valor) {
        return String.format("%.0f", valor);
    }
}
