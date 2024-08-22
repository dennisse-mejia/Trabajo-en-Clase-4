
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class MiFile {

    Scanner lea = new Scanner(System.in);

    private File mf = null;

    public void setFile(String dir) {
        mf = new File(dir);
    }

    public void Info() {
        if (mf.exists()) {
            System.out.println("Sí existe:");
            System.out.println("Nombre: " + mf.getName()
                    + "\nPath: " + mf.getPath()
                    + "\nAbsoluta: " + mf.getAbsolutePath()
                    + "\nPadre: " + mf.getAbsoluteFile().getParentFile().getName()
                    + "\nBytes: " + mf.length());

            if (mf.isFile()) {
                System.out.println("Es un archivo");
            } else if (mf.isDirectory()) {
                System.out.println("Es un folder");
            }

            System.out.println("Ultima modificación: " + new Date(mf.lastModified()));

        } else {
            System.out.println("El archivo no existe");
        }
    }

    public void crearFile() throws IOException {
        if (mf.createNewFile()) {
            System.out.println("Creado exitosamente");
        } else {
            System.out.println("No se pudo crear");
        }
    }

    public void crearFolder() {
        if (mf.mkdir()) {
            System.out.println("Creado exitosamente");
        } else {
            System.out.println("No se pudo crear");
        }
    }

    private boolean antidoto(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                antidoto(child);
            }

        }
        return file.delete();

    }

    public void borrar() {
        if (antidoto(mf)) {
            System.out.println("Se Borro");
        } else {
            System.out.println("No se borro");
        }
    }

    public void dir() {
        if (mf.isDirectory()) {
            System.out.println("Directorio de: " + mf.getAbsolutePath());
            System.out.println("");
            int cfiles = 0, cdirs = 0, tbytes = 0;

            for (File child : mf.listFiles()) {
                if (!child.isHidden()) {
                    //Fecha ultima modificacion
                    Date ultima = new Date(child.lastModified());
                    System.out.print(ultima + "\t");
                    //Si es File or Folder
                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.print("<DIR>\t\t");

                    } else {
                        //Si es un file
                        cfiles++;
                        tbytes += child.length();
                        System.out.println("      \t" + child.length() + "\t");

                    }
                    System.out.println(child.getName());

                }

            }
            System.out.println(cfiles + " archivos\t" + tbytes + " bytes" + "\n" + cdirs + " dirs");
        }

    }

    public void tree() {
        tree(mf, "-");

    }

    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles()) {

                if (!child.isHidden()) {

                    tree(child, tab + "--");

                }

            }

        }

    }

    public void reemplazarContenido() throws IOException {
        if (mf.exists() && mf.isFile()) {
            lea.nextLine();
            System.out.println("Introduce el nuevo contenido que reemplazará el anterior: ");
            String nuevoContenido = lea.nextLine();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(mf))) {
                writer.write(nuevoContenido);
                System.out.println("Contenido reemplazado exitosamente.");
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo válido para reemplazar contenido.");
        }
    }

    public void agregarContenido() throws IOException {
        if (mf.exists() && mf.isFile()) {
            lea.nextLine();
            System.out.println("Introduce el contenido que quieres agregar al archivo: ");
            String nuevoContenido = lea.nextLine();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(mf, true))) {
                writer.write(nuevoContenido);
                writer.newLine();
                System.out.println("Contenido agregado exitosamente.");
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo válido para agregar contenido.");
        }
    }

    public void leerArchivo() throws IOException {
        if (mf.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(mf))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }
        } else {
            System.out.println("No es un archivo válido para leer.");
        }
    }

}
