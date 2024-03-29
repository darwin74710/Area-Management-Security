package archivos;

import Ventanas.Ingreso;
import Ventanas.Menu;
import Ventanas.Registrar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ArchivoUsuarios {

    Registrar registro = new Registrar();
    File archivo;

    Ingreso ingreso = new Ingreso();

    public void crearArchivo() {

        try {
            archivo = new File("Data/Usuarios");

            if (archivo.mkdir()) {
                System.out.println("El direcorio se ha creado exitosamente");
            }

            archivo = new File("Data/Usuarios/usuarios.txt");
            if (archivo.createNewFile()) {
                System.out.println("El archivo se ha creado exitosamente");
            }
        } catch (IOException ex) {
            System.err.println("No se pudo crear el archivo" + ex);
        }
    }

    public void escribirArchivo(String nombre, String apellido,
            String cedula, String email, String password1, String password2,
            String genero, String telefono, String rutaFoto, String primeraPreg,
            String primeraResp, String segundaPreg, String segundaResp, String terceraPreg, String terceraResp) {

        try {
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(nombre + "|"
                    + apellido + "|"
                    + cedula + "|"
                    + email + "|"
                    + password1 + "|"
                    + password2 + "|"
                    + genero + "|"
                    + telefono + "|"
                    + rutaFoto + "|"
                    + primeraPreg + "|"
                    + primeraResp + "|"
                    + segundaPreg + "|"
                    + segundaResp + "|"
                    + terceraPreg + "|"
                    + terceraResp + "|"
                    + "\r\n");
            escribir.close();
        } catch (IOException ex) {
            System.err.println("No se puede escribir en el archivo" + ex);
        }

    }//Fin metodo escribirArchivo

    public void leerArchivo() {

        String cadena;

        try {
            FileReader leerArchivo = new FileReader(archivo);
            BufferedReader leer = new BufferedReader(leerArchivo);
            cadena = leer.readLine();

            while (cadena != null) {
                cadena = leer.readLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("No se puede leer el archivo");
        } catch (IOException ex) {
            System.out.println("No se puede leer la cadena de texto");
        }

    }//Fin metodo leerArchivo

}//fin classsssssss
