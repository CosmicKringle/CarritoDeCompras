public class NodoProducto {
    private final Producto producto;
    private NodoProducto siguiente;

    public NodoProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null.");
        }
        this.producto = producto;
        this.siguiente = null;
    }
    public Producto getProducto() {
        return producto;
    }
    public NodoProducto getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }
}