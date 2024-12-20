package Biblioteca;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DAOUsuario daoUsuario = new DAOUsuario();
        DAOPrestamo daoPrestamo = new DAOPrestamo();
        DAOEjemplar daoEjemplar = new DAOEjemplar();


        System.out.print("Introduce el ID del usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = daoUsuario.getbyId(idUsuario);

        if (usuario.getTipo().equalsIgnoreCase("administrador")) {
            menuGestor(daoPrestamo, daoUsuario, daoEjemplar);
        } else if (usuario.getTipo().equalsIgnoreCase("normal")) {
            menuNormal(usuario);
        }
    }

    private static void menuGestor(DAOPrestamo daoPrestamo, DAOUsuario daoUsuario, DAOEjemplar daoEjemplar) {
        int opcion;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Menú Administrador");
            System.out.println("1. Ver todos los préstamos");
            System.out.println("2. Registrar devolución");
            System.out.println("3. Realizar un préstamo");
            System.out.println("4. Establecer penalización a un usuario");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    List<Prestamo> prestamos = daoPrestamo.getAll();
                    System.out.println("Todos los préstamos");
                    prestamos.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Introduce el ID del préstamo a devolver: ");
                    int idPrestamo = scanner.nextInt();
                    scanner.nextLine();
                    Prestamo prestamo = daoPrestamo.getbyId(idPrestamo);
                    if (prestamo != null) {
                        prestamo.setFechaDevolucion(daoPrestamo.registrarDevolucion(prestamo));
                        daoPrestamo.update(prestamo);
                        System.out.println("Devolución registrada correctamente.");
                    } else {
                        System.out.println("El préstamo no existe.");
                    }
                    break;
                case 3:
                    System.out.print("Introduce el ID del usuario: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuario = daoUsuario.getbyId(idUsuario);

                    if (usuario == null) {
                        System.out.println("El usuario no existe.");
                        break;
                    }

                    System.out.print("Introduce el ID del ejemplar: ");
                    int idEjemplar = scanner.nextInt();
                    scanner.nextLine();
                    Ejemplar ejemplar = daoEjemplar.getbyId(idEjemplar);

                    if (ejemplar == null) {
                        System.out.println("El ejemplar no existe.");
                        break;
                    }

                    Prestamo nuevoPrestamo= daoPrestamo.realizarPrestamo(ejemplar, usuario, LocalDate.now());
                    daoPrestamo.add(nuevoPrestamo);
                    daoEjemplar.updateEjemplar(ejemplar);
                    System.out.println("Préstamo realizado correctamente.");
                    break;
                case 4:
                    System.out.print("Introduce el ID del usuario");
                    int idUsuarioPenalizado = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuarioPenalizado = daoUsuario.getbyId(idUsuarioPenalizado);

                    if (usuarioPenalizado == null) {
                        System.out.println("El usuario no existe.");
                        break;
                    }

                    daoPrestamo.establecerPenalizacion(usuarioPenalizado);
                    daoUsuario.update(usuarioPenalizado);
                    System.out.println("Penalización establecida correctamente.");
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void menuNormal(Usuario usuario) {
        int opcion;
        Scanner scanner= new Scanner(System.in);
        do {
            System.out.println("Menú Usuario Normal");
            System.out.println("1. Ver mis préstamos");
            System.out.println("2. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Tus préstamos");
                    usuario.getPrestamos().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 2);
    }
}
