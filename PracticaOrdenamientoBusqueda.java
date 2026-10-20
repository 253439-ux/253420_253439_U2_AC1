import java.util.Scanner;

public class PracticaOrdenamientoBusqueda {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int listaOriginal[] = new int[20];
        boolean generado = false;
        boolean ordenado = false;
        int opcion;

        do {
            System.out.println("\n=== MENU DE ORDENAMIENTO Y BUSQUEDA ===");
            System.out.println("1. Generar arreglo aleatorio");
            System.out.println("2. Ordenar arreglo");
            System.out.println("3. Buscar un numero");
            System.out.println("4. Mostrar arreglo actual");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    generarNumeros(listaOriginal);
                    generado = true;
                    ordenado = false;
                    break;

                case 2:
                    if (!generado) {
                        System.out.println("Primero debe generar los numeros (Opcion 1).");
                    } else {
                        System.out.println("\n--- Proceso de ordenamiento QuickSort ---");
                        ordenarQuicksort(listaOriginal, 0, listaOriginal.length - 1);
                        ordenado = true;
                        System.out.println("\nArreglo ordenado correctamente:");
                        imprimirArreglo(listaOriginal);
                    }
                    break;

                case 3:
                    if (!generado) {
                        System.out.println("Primero debe generar los numeros (Opcion 1).");
                    } else {
                        // La busqueda binaria requiere que el arreglo este previamente ordenado
                        if (!ordenado) {
                            System.out.println("Ordenando el arreglo antes de realizar la busqueda...");
                            ordenarQuicksort(listaOriginal, 0, listaOriginal.length - 1);
                            ordenado = true;
                            imprimirArreglo(listaOriginal);
                        }
                        System.out.print("\nIngrese el numero que desea buscar: ");
                        int valorBuscado = scanner.nextInt();
                        int posicion = buscar(listaOriginal, valorBuscado, 0, listaOriginal.length - 1);

                        if (posicion != -1) {
                            // Determina si la posicion encontrada esta en la mitad izquierda o derecha
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
                        imprimirArreglo(listaOriginal);
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

    public static void generarNumeros(int listaOriginal[]) {
        int number;
        System.out.println("\nLista original:");
        for (int i = 0; i < listaOriginal.length; i++) {
            // Genera numeros enteros aleatorios entre 1 y 50
            number = (int) (Math.random() * 50 + 1);
            listaOriginal[i] = number;
            System.out.print(listaOriginal[i] + " ");
        }
        System.out.println();
    }

    public static void ordenarQuicksort(int listaOriginal[], int inferior, int superior) {
        int i, j, central;
        double pivote;
        central = (inferior + superior) / 2;
        pivote = listaOriginal[central];
        i = inferior;
        j = superior;

        System.out.println("Limites que se analizan. Inferior: " + i + " Superior: " + j + " Pivote: " + pivote);

        // Particion del arreglo en base al pivote
        do {
            while (listaOriginal[i] < pivote) i++;
            while (listaOriginal[j] > pivote) j--;
            if (i <= j) {
                intercambiar(listaOriginal, i, j);
                i++;
                j--;
            }
        } while (i <= j);

        // Llamadas recursivas para ordenar los subconjuntos izquierdo y derecho
        if (inferior < j) {
            ordenarQuicksort(listaOriginal, inferior, j);
        }
        if (i < superior) {
            ordenarQuicksort(listaOriginal, i, superior);
        }
    }

    public static void intercambiar(int listaOriginal[], int i, int j) {
        int aux = listaOriginal[i];
        listaOriginal[i] = listaOriginal[j];
        listaOriginal[j] = aux;
    }

    public static int buscar(int listaOriginal[], int valorBuscado, int inferior, int superior) {
        int central;
        // Caso base: el elemento no existe en el arreglo
        if (inferior > superior) {
            return -1;
        } else {
            central = (inferior + superior) / 2;
            if (listaOriginal[central] == valorBuscado) {
                return central;
            } else if (listaOriginal[central] < valorBuscado) {
                // Busca recursivamente en la mitad derecha
                return buscar(listaOriginal, valorBuscado, central + 1, superior);
            } else {
                // Busca recursivamente en la mitad izquierda
                return buscar(listaOriginal, valorBuscado, inferior, central - 1);
            }
        }
    }

    public static void imprimirArreglo(int listaOriginal[]) {
        System.out.print("Indices: ");
        for (int i = 0; i < listaOriginal.length; i++) {
            System.out.printf("%3d ", i);
        }
        System.out.print("\nValores: ");
        for (int i = 0; i < listaOriginal.length; i++) {
            System.out.printf("%3d ", listaOriginal[i]);
        }
        System.out.println();
    }
}