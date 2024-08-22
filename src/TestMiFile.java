
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class TestMiFile {

    static MiFile mf = new MiFile();
    static Scanner lea = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;
        do {
            System.out.println("""
                           MENU
                           1) Set el archivo/folder
                           2) Ver información
                           3) Crear un archivo
                           4) Crear un folder
                           5) Borrar
                           6) CMD - DIR
                           7) Tree
                           8) Reemplazar contenido del archivo
                           9) Agregar contenido al archivo
                           10) Leer archivo
                           11) Salir
                           Escoger una opción: """);
            try {
                opcion = lea.nextInt();
                switch (opcion) {
                    case 1:
                        set();
                        break;
                    case 2:
                        mf.Info();
                        break;
                    case 3:
                        mf.crearFile();
                        break;
                    case 4:
                        mf.crearFolder();
                        break;
                    case 5:
                        mf.borrar();
                        break;
                    case 6:
                        mf.dir();
                        break;

                    case 7:
                        mf.tree();
                        break;
                    case 8:
                        mf.reemplazarContenido();
                        break;
                    case 9:
                        mf.agregarContenido();
                        break;
                    case 10:
                        mf.leerArchivo();
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, escoge una opción válida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido.");
                lea.nextLine();
            } catch (NullPointerException e) {
                System.out.println("Debes seleccionar la opción 1 por lo menos una vez");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 11);

    }

    private static void set() {
        System.out.println("Dirección: ");
        mf.setFile(lea.next());
    }

}
