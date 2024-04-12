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
import java.util.ArrayList;
import java.util.List;
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

    public void escribirArchivo(String usuario, String nombre, String apellido,
            String cedula, String email, String password1, String password2,
            String genero, String telefono, String rutaFoto, String primeraPreg,
            String primeraResp, String segundaPreg, String segundaResp, String terceraPreg, String terceraResp) {

        try {
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(usuario + "|"
                    + nombre + "|"
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

    public String[] leerArchivo(String nombre) {

        String rutaCarpeta = "Data/Usuarios/usuarios.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCarpeta))) {
            String cadena;
            while ((cadena = br.readLine()) != null) {
                String[] datosUsuario = cadena.split("\\|");
                
                if (datosUsuario.length > 0 && datosUsuario[0].trim().equals(nombre)){
                    return datosUsuario;
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("No se puede leer el archivo");
        } catch (IOException ex) {
            System.out.println("No se puede leer la cadena de texto");
        }
        return null;

    }//Fin metodo leerArchivo
    
    public String[] RecuperarContra(String cedula){
        
        String rutaCarpeta = "Data/Usuarios/usuarios.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCarpeta))) {
            String cadena;
            while ((cadena = br.readLine()) != null) {
                String[] datosUsuario = cadena.split("\\|");
                
                if(datosUsuario.length > 0 && datosUsuario[3].trim().equals(cedula)){
                    return datosUsuario;
                }
            }
            } catch (FileNotFoundException ex) {
            System.err.println("No se puede recuperar la contraseña");
        } catch (IOException ex) {
            System.err.println("No se puede leer la cadena de texto");
        }
        return null;
    }//fin metodo recuperarContra 
    
}//fin classsssssss
