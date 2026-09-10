public class Carrito {
    private NodoCarrito cabeza;

    public Carrito() {
        cabeza = null;
    }
    public boolean estaVacio() {
        return cabeza == null;
    }
    public ItemCarrito buscarPorId(int id) {
        NodoCarrito actual = cabeza;

        while (actual != null) {
            if (actual.getItem().getProducto().getId() == id) {
                return actual.getItem();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no pude ser null.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        ItemCarrito existente = buscarPorId(producto.getId());
        if (existente != null) {
            if (cantidad > Integer.MAX_VALUE - existente.getCantidad()) {
                throw new IllegalArgumentException("La cantidad acumulada demasiado grande.");
            }
            existente.setCantidad(existente.getCantidad() + cantidad);
            return;
        }
        ItemCarrito item = new ItemCarrito(producto, cantidad);
        NodoCarrito nuevo = new NodoCarrito(item);

        if (estaVacio()) {
            cabeza = nuevo;
        } else {
            NodoCarrito actual = cabeza;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }
    public double calcularTotal() {
        double total = 0;
        NodoCarrito actual = cabeza;

        while (actual != null) {
            total += actual.getItem().getSubtotal();
            actual = actual.getSiguiente();
        }
        return total;
    }
    public long contarArticulos() {
        long totalArticulos = 0;
        NodoCarrito actual = cabeza;
        
        while (actual != null) {
            totalArticulos += actual.getItem().getCantidad();
            actual = actual.getSiguiente();
        }
        return totalArticulos;
    }
    public void mostrarCarrito() {
        if (estaVacio()) {
            System.out.println("El carrito está vacio.");
            return;
        }
        System.out.println(" Carrito de Compras ");
        NodoCarrito actual = cabeza;

        while (actual != null) {
            System.out.println(actual.getItem());
            actual = actual.getSiguiente();
        }
        System.out.println("Catidad total de artículos: " + contarArticulos());
        System.out.printf("Total a pagar: $%.2f%n", calcularTotal());
    }
    public ItemCarrito buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        String nombreBuscado = nombre.trim();
        NodoCarrito actual = cabeza;

        while (actual != null) {
            if (actual.getItem().getProducto().getNombre().equalsIgnoreCase(nombreBuscado)) {
                return actual.getItem();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    public boolean actualizarCantidad(int id, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        ItemCarrito item = buscarPorId(id);

        if (item == null) {
            return false;
        }
        item.setCantidad(nuevaCantidad);
        return true;
    }
    public boolean eliminarPorId(int id) {
        NodoCarrito actual = cabeza;
        NodoCarrito anterior = null;

        while(actual != null) {
            if (actual.getItem().getProducto().getId() == id) {
                if (anterior == null) {
                    cabeza = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }
    public boolean eliminarPorNombre(String nombre) {
        ItemCarrito item = buscarPorNombre(nombre);

        if (item == null) {
            return false;
        }
        return eliminarPorId(item.getProducto().getId());
    }
    public void vaciar() {
        cabeza = null;
    }
}
