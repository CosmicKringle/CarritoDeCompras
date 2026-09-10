public class ItemCarrito {
    private final Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null.");
        }
        this.producto = producto;
        setCantidad(cantidad);
    }
    public Producto getProducto() {
        return producto;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Laa cantidad debe ser mayor que cero.");
        }
        this.cantidad = cantidad;
    }
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override 
    public String toString() {
        return String.format(
            "%d | %s | Cantidad: %d | Precio: $%.2f | Subtotal: $%.2f",
            producto.getId(),
            producto.getNombre(),
            cantidad,
            producto.getPrecio(),
            getSubtotal()
            );
    }
}