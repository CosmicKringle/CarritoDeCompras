public class NodoCarrito {
    private final ItemCarrito item;
    private NodoCarrito siguiente;

    public NodoCarrito(ItemCarrito item) {
        if (item == null) {
            throw new IllegalArgumentException("El item no puede ser null.");
        }
        this.item = item;
        this.siguiente =null;
    }
    public ItemCarrito getItem() {
        return item;
    }
    public NodoCarrito getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoCarrito siguiente) {
        this.siguiente = siguiente;
    }
}
