import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaProductos catalogo = crearCatalogo();
        Carrito carrito = new Carrito();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean salir = false;

            while(!salir) {
                mostrarMenu();

                
                try {
                    int opcion = leerEntero(scanner, "Slecciona una opción: ");

                    switch (opcion) {
                        case 1:
                        catalogo.mostrarCatalogo();
                        break;

                        case 2: {
                            int id = leerPositivo(scanner, "ID del producto: ");
                            mostrarProducto(catalogo.buscarPorId(id));
                            break;
                        }

                        case 3: {
                            String nombre = leerTexto(
                                scanner, "Nombre del producto: "
                            );
                            mostrarProducto(catalogo.buscarPorNombre(nombre));
                            break;
                        }

                        case 4: {
                            catalogo.mostrarCatalogo();

                            int id = leerPositivo(scanner, "ID del producto que deseas agregar: ");
                            Producto producto = catalogo.buscarPorId(id);
                            if (producto == null) {
                                System.out.println("Producto no encontrado.");
                                break;
                            }
                            int cantidad = leerPositivo(scanner, "Cantidad que deseas agregar: ");
                            carrito.agregarProducto(producto, cantidad);
                            System.out.println("Producto agregado al carrito.");
                            break;
                        }

                        case 5: 
                        carrito.mostrarCarrito();
                        break;

                        case 6: {
                            if (carrito.estaVacio()) {
                                System.out.println("el carrito está vacío.");
                                break;
                            }
                            carrito.mostrarCarrito();
                            int id = leerPositivo(scanner, "ID del producto que deseas modificar: ");

                            if (carrito.buscarPorId(id) == null) {
                                System.out.println("El producto no está en el carrito.");
                                break;
                            }
                            int cantidad = leerPositivo(scanner, "Nueva cantidad: ");
                            carrito.actualizarCantidad(id, cantidad);
                            System.out.println("cantidad actualizada.");
                            carrito.mostrarCarrito();
                            break;
                        }

                        case 7: {
                            int id = leerPositivo(scanner, "ID del producto que deseas eliminar: ");
                            mostrarResultadoEliminacion(carrito.eliminarPorId(id));
                            break;
                        }

                        case 8: {
                            String nombre = leerTexto(scanner, "Nombre del producto que deseas eliminar: ");
                            mostrarResultadoEliminacion(carrito.eliminarPorNombre(nombre));
                            break;
                        }
                        case 9:
                        carrito.vaciar();
                        System.out.println("Carrito vacío. Puedes iniciar una nueva compra.");
                        break;
                        
                        case 10:
                        finalizarCompra(carrito);
                        break;

                        case 0:
                        salir = true;
                        break;

                        default:
                        System.out.println("OPción inválida. Selecciona un número del 0 al 10.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (java.util.NoSuchElementException e) {
                    salir = true;
                }
            }
        }
        System.out.println("Program finalizado.");
    }
    private static ListaProductos crearCatalogo() {
        ListaProductos catalogo = new ListaProductos();

        catalogo.agregarProducto(new Producto(1, "Leche", 28.50));
        catalogo.agregarProducto(new Producto(2, "Pan", 42.00));
        catalogo.agregarProducto(new Producto(3, "Huevos", 56.00));
        catalogo.agregarProducto(new Producto(4, "Arroz", 24.50));
        catalogo.agregarProducto(new Producto(5, "Frijoles", 32.00));

        return catalogo;
    }

    private static void mostrarMenu() {
        System.out.println("Tienda");
        System.out.println("1. Mostrar catálogo");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Buscar por nombre");
        System.out.println("4. Agregar producto al carrito");
        System.out.println("5. Mostrar carrito");
        System.out.println("6. Cambiar cantidad de un prodcuto");
        System.out.println("7. Eliminar producto por ID");
        System.out.println("8. Eliminar producto por nombre");
        System.out.println("9. Vaciar carrito");
        System.out.println("10. Finalizar compra");
        System.out.println("0. Salir");
    }
    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) { 
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido dentro del rando de int.");
            }
        }
    }
    private static int leerPositivo(Scanner scanner, String mensaje) {
        while (true) { 
            int numero = leerEntero(scanner, mensaje);

            if (numero > 0) {
                return numero;
            }
            System.out.println("El número debe ser mayor que cero.");
        }
    }
    private static String leerTexto(Scanner scanner, String mensaje) {
        while (true) { 
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("El texto no puede estar vacío.");
        }
    }
    private static void mostrarProducto(Producto producto) {
        if(producto == null) {
            System.out.println("Producto no enconrado.");
        } else {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        }
    }
    private static void mostrarResultadoEliminacion(boolean eliminado) {
        if (eliminado) {
            System.out.println("Producto eliminado del carrito.");
        } else {
            System.out.println("El prodcuto no está en el carrito.");
        }
    }
    private static void finalizarCompra(Carrito carrito) {
        if (carrito.estaVacio()) {
            System.out.println("El carrito está vacio. Agrega productos anteds de finalizar.");
            return;
        }
        System.out.println("\n Resumen de Compra ");
        carrito.mostrarCarrito();
        System.out.println("¡Gracias por tu compra!");

        carrito.vaciar();
        System.out.println("Puedes iniciar una nueva compra.");
    }
}