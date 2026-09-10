public class Producto {
    private final int id;
    private final String nombre;
    private final double precio;

    public Producto(int id, String nombre, double precio) {
        if (id <=0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!Double.isFinite(precio) || precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser un número positivo.");
        }
        this.id = id;
        this.nombre = nombre.trim();
        this.precio = precio;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    @Override 
    public String toString() {
        return String.format(
            "%d | %s | $%.2f",
            id, nombre, precio
        );
    }
}