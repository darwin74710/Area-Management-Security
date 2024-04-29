package Guardado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveConfiguraciones {
    
    public List<String> CargarDatos() {
        String carpeta = "Data/configuraciones.txt";
        
        List<String> datosArchivo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(carpeta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en elementos usando el separador "|"
                String[] elementos = linea.split("\\|");
                // Agregar los elementos a la lista
                for (String elemento : elementos) {
                    datosArchivo.add(elemento.trim()); // Trim elimina espacios en blanco alrededor de los elementos
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datosArchivo;
    }
    
    public void GuardarConfiguraciones(String tema, String sensibilidad, String duracion){
        String carpeta = "Data";
        
        File directorio = new File (carpeta);
        if (!directorio.exists()){
            directorio.mkdirs();
        }
        
        String nombreArchivo = carpeta + "/configuraciones.txt";
        
        String[] lista = {tema, sensibilidad, duracion};
        
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir cada componente del array en el archivo, separados por |
            for (int i = 0; i < lista.length; i++) {
                bufferedWriter.write(lista[i]);

                // Si no es el último componente, escribir el |
                if (i < lista.length - 1) {
                    bufferedWriter.write("|");
                }
            }

            // Cerrar el BufferedWriter
            bufferedWriter.close();
            System.out.println("El array se ha guardado correctamente en el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
