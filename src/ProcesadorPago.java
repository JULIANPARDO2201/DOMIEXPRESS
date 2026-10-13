/**
 * Factory Method: esta clase escribe el flujo de cobro una sola vez.
 * Cada subclase decide que pasarela crear. Aqui no hay if por tipo de pago.
 */
public abstract class ProcesadorPago {

    protected abstract PasarelaPago crearPasarela();

    public void procesar(Pedido pedido) {
        PasarelaPago pasarela = crearPasarela();
        double total = pedido.calcularTotal();

        System.out.println("Procesando " + pedido.getId() + " con " + pasarela.nombre()
                + " por $" + FormatoDinero.pesos(total));

        if (pasarela.cobrar(total)) {
            System.out.println("  -> Pago APROBADO");
        } else {
            System.out.println("  -> Pago RECHAZADO");
        }
    }
}
