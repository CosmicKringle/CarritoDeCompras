public class ListaProductos {
    private NodoProducto cabeza;

    public ListaProductos() {
        cabeza = null;
    }
    public boolean estaVacia() {
        return cabeza == null;
    }
    public boolean agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null.");
        }
        if (buscarPorId(producto.getId()) != null) {
            return false;
        }
        NodoProducto nuevo = new NodoProducto(producto);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            NodoProducto actual = cabeza;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        return true;
    }
    public Producto buscarPorId(int id) {
        NodoProducto actual = cabeza;

        while (actual != null) {
            if (actual.getProducto().getId() == id) {
                return actual.getProducto();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    public Producto buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        NodoProducto actual = cabeza;
        String nombreBuscado = nombre.trim();

        while (actual != null) {
            if (actual.getProducto().getNombre()
                .equalsIgnoreCase(nombreBuscado)) {
                return actual.getProducto();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    public void mostrarCatalogo() {
        if (estaVacia()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        System.out.println("\n Catálogo de Productos");
        System.out.println("ID | Nombre | Precio");

        NodoProducto actual = cabeza;

        while (actual != null) {
            System.out.println(actual.getProducto());
            actual = actual.getSiguiente();
        }
    }
}