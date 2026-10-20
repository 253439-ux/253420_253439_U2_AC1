import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Declaracion y creacion del arreglo de 20 numeros en el metodo main
        int listaOriginal[] = new int[20];
        boolean generado = false;
        boolean ordenado = false;
        int opcion;

        do {
            System.out.println("\n=== MENU DE ORDENAMIENTO Y BUSQUEDA ===");
            System.out.println("1. Generar arreglo aleatorio (20 numeros)");
            System.out.println("2. Ordenar arreglo (QuickSort)");
            System.out.println("3. Buscar un numero (Busqueda Binaria)");
            System.out.println("4. Mostrar arreglo actual");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    BusquedaDatos.generarNumeros(listaOriginal);
                    generado = true;
                    ordenado = false;
                    break;

                case 2:
                    if (!generado) {
                        System.out.println("Primero debe generar los numeros (Opcion 1).");
                    } else {
                        System.out.println("\n--- Proceso de ordenamiento QuickSort ---");
                        Ordenamiento.ordenarQuicksort(listaOriginal, 0, listaOriginal.length - 1);
                        ordenado = true;
                        System.out.println("\nArreglo ordenado correctamente:");
                        BusquedaDatos.imprimirArreglo(listaOriginal);
                    }
                    break;

                case 3:
                    if (!generado) {
                        System.out.println("Primero debe generar los numeros (Opcion 1).");
                    } else {
                        // La busqueda binaria requiere que el arreglo este ordenado
                        if (!ordenado) {
                            System.out.println("Ordenando el arreglo antes de realizar la busqueda...");
                            Ordenamiento.ordenarQuicksort(listaOriginal, 0, listaOriginal.length - 1);
                            ordenado = true;
                            BusquedaDatos.imprimirArreglo(listaOriginal);
                        }
                        System.out.print("\nIngrese el numero que desea buscar: ");
                        int valorBuscado = scanner.nextInt();
                        int posicion = BusquedaDatos.buscar(listaOriginal, valorBuscado, 0, listaOriginal.length - 1);

                        if (posicion != -1) {
                            // Determina si se encontro en el lado izquierdo o derecho del arreglo
                            int mitad = (listaOriginal.length - 1) / 2;
                            String lado = (posicion <= mitad) ? "LADO IZQUIERDO" : "LADO DERECHO";
                            System.out.println("El numero " + valorBuscado + " fue encontrado en el indice [" + posicion + "] (" + lado + ").");
                        } else {
                            System.out.println("El numero " + valorBuscado + " NO se encuentra en el arreglo.");
                        }
                    }
                    break;

                case 4:
                    if (!generado) {
                        System.out.println("Aun no se han generado los numeros.");
                    } else {
                        BusquedaDatos.imprimirArreglo(listaOriginal);
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}