package Logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaveRegistros {
    public static String nombreArea = "";
    public static String nombreRegistro = "";
    
    // Metodo para cargar los datos de un area
    public List<String> CargarDatosRegistros(String nombre) {
        // Adjuntar la extensión .txt al nombre del archivo si no la tiene
        if (!nombre.endsWith(".txt")) {
            nombre += ".txt";
        }

        // Carpeta donde se encuentran los archivos
        String carpeta = "Data/Registros/" + nombreArea;

        // Crear un array para guardar los datos del archivo
        List<String> datosArchivo = new ArrayList<>();

        // Iterar sobre los archivos en la carpeta
        File[] archivos = new File(carpeta).listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().equals(nombre)) {
                    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            // Dividir la línea en partes utilizando el punto y coma como separador
                            String[] partes = linea.split("\\|");
                            // Agregar cada parte al array de datos del archivo
                            for (String parte : partes) {
                                datosArchivo.add(parte);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error al leer el archivo: " + e.getMessage());
                    }
                    break; // Si se encuentra el archivo, se detiene la búsqueda
                }
            }
        } else {
            System.err.println("No se encontraron archivos en la carpeta especificada.");
            return null;
        }

        // Convertir la lista de datos a un array de strings
        String[] arrayDatos = new String[datosArchivo.size()];
        arrayDatos = datosArchivo.toArray(arrayDatos);

        // Retornar los datos.
        for (String dato : arrayDatos) {
            return datosArchivo;
        }
        return null;
    }
    
    public void guardarRegistro(String Registro, String descripcion, String registro1, String registro2, String registro3){
        String carpeta = "Data/Registros/" + nombreArea;
        
        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Determinar el nombre del nuevo archivo
        String nombreArchivoBase = Registro + ".txt";
        String nombreArchivo = carpeta + "/" + nombreArchivoBase;
        nombreRegistro = Registro;
        int contador = 1;
        
        // Verificar si el archivo con ese nombre ya existe
        while (new File(nombreArchivo).exists()) {
            nombreArchivo = carpeta + "/" + Registro + "_" + contador + ".txt";
            nombreRegistro = Registro + "_" + contador;
            contador++;
        }
        
        // Extraer la fecha y la hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm  -  dd/MM/yyyy");
        String fecha = fechaHoraActual.format(formateador);
        
        String[] lista = {nombreRegistro, descripcion, registro1, registro2, registro3, fecha};
        
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo);
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
    // Metodo para editar un area
    public void editarImagenRegistro(String nombreArchivo, List<String> nuevosDatos,int numRuta, String Ruta) {
        String carpeta = "Data/Registros/" + nombreArea;
        
        // Crear la ruta completa del archivo original
        String rutaArchivo = carpeta + "/" + nombreArchivo + ".txt";
    
        // Verificar si el archivo existe antes de continuar
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.err.println("El archivo " + nombreArchivo + " no existe.");
            return;
        }

        try { 
            nuevosDatos.set(numRuta, Ruta);
            // Crear un BufferedWriter para escribir en el archivo
            FileWriter fileWriter = new FileWriter(rutaArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir los nuevos datos en el archivo
            for (int i = 0; i < nuevosDatos.size(); i++) {
                bufferedWriter.write(nuevosDatos.get(i));

                // Si no es el último componente, escribir el | como separador
                if (i < nuevosDatos.size() - 1) {
                    bufferedWriter.write("|");
                }
            }
            // Cerrar el BufferedWriter después de escribir los datos
            bufferedWriter.close();
            System.out.println("Los datos se han reescrito correctamente en el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al reescribir el archivo: " + e.getMessage());
        }
    }
    
    public void editarDescriptRegistro(String nombreArchivo, List<String> nuevosDatos, String descripcion) {
        String carpeta = "Data/Registros/" + nombreArea;
        
        // Crear la ruta completa del archivo original
        String rutaArchivo = carpeta + "/" + nombreArchivo + ".txt";
    
        // Verificar si el archivo existe antes de continuar
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.err.println("El archivo " + nombreArchivo + " no existe.");
            return;
        }

        try { 
            nuevosDatos.set(1, descripcion);
            // Crear un BufferedWriter para escribir en el archivo
            FileWriter fileWriter = new FileWriter(rutaArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir los nuevos datos en el archivo
            for (int i = 0; i < nuevosDatos.size(); i++) {
                // Si es la descripción, escribir línea por línea
                if (i == 1) {
                    String descripcionSinSaltos = nuevosDatos.get(i).replace("\n", "\\n");
                    bufferedWriter.write(descripcionSinSaltos);
                } else {
                    bufferedWriter.write(nuevosDatos.get(i));
                }

                // Si no es el último componente, escribir el | como separador
                if (i < nuevosDatos.size() - 1) {
                    bufferedWriter.write("|");
                }
            }
            // Cerrar el BufferedWriter después de escribir los datos
            bufferedWriter.close();
            System.out.println("Los datos se han reescrito correctamente en el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al reescribir el archivo: " + e.getMessage());
        }
    }
    
    public void eliminarRegistro(String Registro){
        String carpeta = "Data/Registros/" + nombreArea;
        String nombreArchivo = carpeta + "/" + Registro + ".txt";
        
        File archivo = new File(nombreArchivo);
        
        // Verificar si el archivo existe antes de intentar eliminarlo
        if (archivo.exists()) {
            // Intentar eliminar el archivo
            if (archivo.delete()) {
                System.out.println("El archivo se eliminó correctamente.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}