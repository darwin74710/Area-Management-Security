package Logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.JPanel;

public class SistemNotificaciones {
    
    public static void guardarNotifi(int Index){
        String carpeta = "Data/Notificaciones";
        
        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        // Datos notificacion
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd_MM_yyyy - HH;mm;ss ");
        String fecha = fechaHoraActual.format(formateador);
        
        String info = fecha + "Movimiento en la cámara " + Index;
        
        // Construye el nombre del nuevo archivo
        String nombreArchivo = info + ".txt";
        File nuevoArchivo = new File(carpeta, nombreArchivo);

        String[] lista = {info, String.valueOf(Index)};
        
        
        //Buscamos las areas que tengan la camara
        File carpeta2 = new File("Data/Areas");
        File[] listaDeArchivos = carpeta2.listFiles();
        
        if (listaDeArchivos != null) {
            for (File archivo : listaDeArchivos) {
                if (archivo.isFile()) {
                    List<String> datosArchivo = obtenerDatosDeArchivo(archivo);
                    if (datosArchivo.get(4).equals(String.valueOf(Index))){
                       // Si se cumple la condición, añadimos un dato al arreglo lista
                        String[] nuevaLista = new String[lista.length + 1];
                        System.arraycopy(lista, 0, nuevaLista, 0, lista.length); // Copiamos los elementos actuales
                        nuevaLista[lista.length] = datosArchivo.get(0); // Añadimos el nuevo dato
                        lista = nuevaLista; // Actualizamos la referencia del arreglo lista 
                    }
                }
            }
        } else {
            System.out.println("La carpeta no contiene archivos.");
        }

        try {
            FileWriter fileWriter = new FileWriter(nuevoArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir cada componente del array en el archivo, separados por |
            for (int i = 0; i < lista.length; i++) {
                // Si es la descripción, escribir línea por línea
                if (i == 1) {
                    String descripcionSinSaltos = lista[i].replace("\n", "\\n");
                    bufferedWriter.write(descripcionSinSaltos);
                } else {
                    bufferedWriter.write(lista[i]);
                }

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

    private static List<String> obtenerDatosDeArchivo(File archivo) {
        List<String> datosArchivo = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split("\\|");
                for (String dato : datos) {
                    datosArchivo.add(dato.trim()); // Eliminar espacios en blanco alrededor de los datos
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datosArchivo;
    }
}
