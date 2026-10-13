public class ResumenPedido {

    public void mostrar(Pedido pedido) {
        String entrega = String.valueOf(pedido.getTipoEntrega());
        if (pedido.getTipoEntrega() == TipoEntrega.DOMICILIO) {
            entrega = entrega + " (" + pedido.getDireccion() + ")";
        }
        System.out.println("Pedido " + pedido.getId()
                + " | Cliente: " + pedido.getCliente()
                + " | Entrega: " + entrega);

        for (ItemPedido item : pedido.getItems()) {
            System.out.println("  - " + item.getCantidad() + " x " + item.getNombre()
                    + " ($" + FormatoDinero.pesos(item.getPrecioUnitario()) + ")");
        }

        if (pedido.getNotas() != null && !pedido.getNotas().isEmpty()) {
            System.out.println("  Notas: " + pedido.getNotas());
        }

        System.out.println("  Cupon: " + pedido.getCupon() + "% | Propina: $"
                + FormatoDinero.pesos(pedido.getPropina())
                + " | Total: $" + FormatoDinero.pesos(pedido.calcularTotal()));
    }
}
