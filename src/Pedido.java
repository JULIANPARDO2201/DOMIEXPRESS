import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * El pedido solo se crea con el Builder.
 * clonar() es el Prototype: copia el pedido y pide un numero nuevo.
 */
public class Pedido {

    private final String id;
    private final String cliente;
    private final TipoEntrega tipoEntrega;
    private final String direccion;
    private final List<ItemPedido> items;
    private final String notas;
    private final int cupon;
    private final double propina;

    private Pedido(Builder builder) {
        this.id = GeneradorConsecutivo.obtenerInstancia().siguiente();
        this.cliente = builder.cliente;
        this.tipoEntrega = builder.tipoEntrega;
        this.direccion = builder.direccion;
        this.items = new ArrayList<>(builder.items);
        this.notas = builder.notas;
        this.cupon = builder.cupon;
        this.propina = builder.propina;
    }

    // Copia usada por clonar(). El cupon no se copia: es de un solo uso.
    private Pedido(Pedido original) {
        this.id = GeneradorConsecutivo.obtenerInstancia().siguiente();
        this.cliente = original.cliente;
        this.tipoEntrega = original.tipoEntrega;
        this.direccion = original.direccion;
        this.items = new ArrayList<>(original.items);
        this.notas = original.notas;
        this.cupon = 0;
        this.propina = original.propina;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getNotas() {
        return notas;
    }

    public int getCupon() {
        return cupon;
    }

    public double getPropina() {
        return propina;
    }

    public double calcularTotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal = subtotal + item.getSubtotal();
        }
        return subtotal * (100 - cupon) / 100.0 + propina;
    }

    public void agregarItem(ItemPedido item) {
        items.add(item);
    }

    public Pedido clonar() {
        return new Pedido(this);
    }

    public void mostrarResumen() {
        new ResumenPedido().mostrar(this);
    }

    public static class Builder {

        private String cliente;
        private TipoEntrega tipoEntrega = TipoEntrega.RECOGER;
        private String direccion = "";
        private final List<ItemPedido> items = new ArrayList<>();
        private String notas = "";
        private int cupon = 0;
        private double propina = 0;

        public Builder conCliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder conTipoEntrega(TipoEntrega tipoEntrega) {
            this.tipoEntrega = tipoEntrega;
            return this;
        }

        public Builder conDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder agregarItem(ItemPedido item) {
            this.items.add(item);
            return this;
        }

        public Builder conNotas(String notas) {
            this.notas = notas;
            return this;
        }

        public Builder conCupon(int cupon) {
            this.cupon = cupon;
            return this;
        }

        public Builder conPropina(double propina) {
            this.propina = propina;
            return this;
        }

        public Pedido construir() {
            if (cliente == null || cliente.trim().isEmpty()) {
                throw new IllegalStateException("El cliente es obligatorio");
            }
            if (items.isEmpty()) {
                throw new IllegalStateException("El pedido debe tener al menos un item");
            }
            if (tipoEntrega == TipoEntrega.DOMICILIO
                    && (direccion == null || direccion.trim().isEmpty())) {
                throw new IllegalStateException("El domicilio requiere direccion");
            }
            if (cupon < 0 || cupon > 100) {
                throw new IllegalArgumentException("El cupon debe estar entre 0 y 100");
            }
            return new Pedido(this);
        }
    }
}
