package Guardado;

import Logica.SistemMonitoreo;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SaveAreas{
    public String nombreArea = "";
    public String nombreMapa = "";
    
    // Metodo para cargar los datos de un area
    public List<String> CargarDatos(String nombre) {
        // Adjuntar la extensión .txt al nombre del archivo si no la tiene
        if (!nombre.endsWith(".txt")) {
            nombre += ".txt";
        }

        // Carpeta donde se encuentran los archivos
        String carpeta = "Data/Areas";

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
                            // Miramos si la imagen existe.
                            File imagen = new File(partes[3]);
                            if (!imagen.exists()) {
                                partes[3] = "Imagenes/Iconos/areaEstandar.png";
                            }
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
    
    // Metodo para darle un identificador unico a las areas
    private String generarIdUnico(JButton boton) {
        return "ID_" + boton.hashCode();
    }
    
    // Metodo para añadir el nombre de las areas ya creadas al combobox de areas
    public List<String> obtenerNombresArchivos() {
        // Carpeta donde se encuentran los archivos
        String carpeta = "Data/Areas";

        // Crear una lista para almacenar los nombres de los archivos
        List<String> nombresArchivos = new ArrayList<>();

        // Obtener la lista de archivos en la carpeta
        File[] archivos = new File(carpeta).listFiles();
        if (archivos != null) {
            // Iterar sobre los archivos y agregar sus nombres a la lista
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    // Obtener solo el nombre del archivo sin la extensión
                    String nombreArchivo = archivo.getName().replaceFirst("[.][^.]+$", "");
                    nombresArchivos.add(nombreArchivo);
                }
            }
        } else {
            System.err.println("No se encontraron archivos.");
        }

        return nombresArchivos;
    }
    
    // Metodo para guardar un area
    public void guardarArea(String nombre, String descripcion, String color, String rutaImagen) {
        // Carpeta donde se guardarán los archivos
        String carpeta = "Data/Areas";

        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Determinar el nombre del nuevo archivo
        String nombreArchivoBase = nombre + ".txt";
        String nombreArchivo = carpeta + "/" + nombreArchivoBase;
        nombreArea = nombre;
        int contador = 1;

        // Verificar si el archivo con ese nombre ya existe
        while (new File(nombreArchivo).exists()) {
            nombreArchivo = carpeta + "/" + nombre + "_" + contador + ".txt";
            nombreArea = nombre + "_" + contador;
            contador++;
        }

        String[] lista = {nombreArea, descripcion, color, rutaImagen, "desActive"};

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
    public void editarArea(String nombreArchivo, List<String> nuevosDatos) {
        String carpeta = "Data/Areas";
        
        // Crear la ruta completa del archivo original
        String rutaArchivo = carpeta + "/" + nombreArchivo + ".txt";
    
        // Verificar si el archivo existe antes de continuar
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.err.println("El archivo " + nombreArchivo + " no existe.");
            return;
        }

        try {
            // Obtener el nuevo nombre del archivo a partir del elemento 0 de la lista de nuevos datos
            String nuevoNombreArchivo = nuevosDatos.get(0);

            // Verificar si el nuevo nombre de archivo es diferente al nombre actual del archivo
            if (!nombreArchivo.equals(nuevoNombreArchivo)) {
                // Verificar si el nuevo nombre de archivo ya existe
                String rutaNuevoArchivo = carpeta + "/" + nuevoNombreArchivo + ".txt";
                File nuevoArchivo = new File(rutaNuevoArchivo);
                nombreArea = nuevoNombreArchivo;
                int contador = 1;
                while (nuevoArchivo.exists()) {
                    // Generar un nuevo nombre único para el archivo
                    nuevoNombreArchivo = nuevosDatos.get(0) + "_" + contador;
                    rutaNuevoArchivo = carpeta + "/" + nuevoNombreArchivo + ".txt";
                    nuevoArchivo = new File(rutaNuevoArchivo);
                    nombreArea = nuevoNombreArchivo;
                    contador++;
                }
                
                nuevosDatos.set(0, nuevoNombreArchivo);

                // Renombrar el archivo con el nuevo nombre si no existe otro archivo con el mismo nombre
                if (!archivo.renameTo(new File(rutaNuevoArchivo))) {
                    System.err.println("Error al renombrar el archivo.");
                    return;
                }
                
                // Actualizar la ruta del archivo con el nuevo nombre
                rutaArchivo = rutaNuevoArchivo;
            }
            
            // Editar los botones en los archivos de la carpeta "Data/Mapas"
            editarBotonEnArchivos(nombreArchivo, nuevoNombreArchivo);

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
            System.out.println("Los datos se han reescrito correctamente en el archivo " + nuevoNombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al reescribir el archivo: " + e.getMessage());
        }
    }

    // Metodo para eliminar un area
    public void eliminarArea(String nombre) {
        String carpeta = "Data/Areas";
        String carpeta2 = "Data/Registros/" + nombre;

        // Llamar al método para eliminar la carpeta recursivamente
        eliminarDirectorio(new File(carpeta2));

        // Eliminar el archivo principal
        String nombreArchivo = carpeta + "/" + nombre + ".txt";
        File archivo = new File(nombreArchivo);
        
        System.out.println(nombreArchivo);

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

    // Método para eliminar un directorio y su contenido recursivamente
    private void eliminarDirectorio(File directorio) {
        if (!directorio.exists()) {
            return;
        }

        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    eliminarDirectorio(archivo);
                } else {
                    if (archivo.delete()) {
                        System.out.println("Se eliminó el archivo: " + archivo.getName());
                    } else {
                        System.out.println("No se pudo eliminar el archivo: " + archivo.getName());
                    }
                }
            }
        }

        // Eliminar el directorio una vez que se han eliminado todos los archivos y subdirectorios
        if (directorio.delete()) {
            System.out.println("Se eliminó la carpeta: " + directorio.getAbsolutePath());
        } else {
            System.out.println("No se pudo eliminar la carpeta: " + directorio.getAbsolutePath());
        }
    }
    
    // Metodo para eliminar el area dentro de los mapas
    public void editarBotonEnArchivos(String nombreArchivo, String nuevoNombreArchivo) {
        String carpeta = "Data/Mapas";

        // Obtener la lista de archivos en la carpeta "carpeta2"
        File directorio = new File(carpeta);
        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                try {
                    // Crear una lista temporal para almacenar las líneas del archivo
                    List<String> lineas = new ArrayList<>();

                    // Leer los datos del archivo y guardarlos en la lista temporal
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
                    String linea;
                    while ((linea = bufferedReader.readLine()) != null) {
                        // Dividir la línea en partes usando el separador "|"
                        String[] partes = linea.split("\\|");

                        // Verificar si hay al menos dos partes y si la segunda parte es igual al nombre del archivo
                        if (partes.length >= 2 && partes[1].equals(nombreArchivo)) {
                            // Reemplazar el nombre del botón con el nuevo nombre
                            partes[1] = nuevoNombreArchivo;
                            // Reconstruir la línea con las partes actualizadas
                            linea = String.join("|", partes);
                        }
                        // Agregar la línea actualizada a la lista
                        lineas.add(linea);
                    }
                    bufferedReader.close();

                    // Escribir los datos actualizados en el archivo
                    FileWriter fileWriter = new FileWriter(archivo);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    for (String line : lineas) {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.close();
                } catch (IOException e) {
                    System.err.println("Error al editar el botón en el archivo " + archivo.getName() + ": " + e.getMessage());
                }
            }
        }
    }
    
    // Metodo para cargar los mapas
    public void cargarMapa(String nombreArchivo, JPanel panelAreas,JLabel titulo, JTextArea descripcion, JLabel imagen, int[] offset, List<JButton> Areas) {
        String carpeta = "Data/Mapas";
        String rutaArchivo = carpeta + "/" + nombreArchivo + ".txt";
        Areas.clear();
        
        try {
            // Leer los datos del archivo
            BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                // Parsear los datos del botón desde la línea
                String nuevaLinea = sobrescribirDato(linea);
                
                String[] datosBoton = nuevaLinea.split("\\|");
                if (datosBoton.length == 7) { // Asegurar que hay suficientes datos
                    // Crear un nuevo botón con los datos
                    JButton botonArea = new JButton(datosBoton[1]); // Nombre del botón
                    botonArea.setFocusPainted(false);
                    
                    int x = Integer.parseInt(datosBoton[2]); // Posición x
                    int y = Integer.parseInt(datosBoton[3]); // Posición y
                    int ancho = Integer.parseInt(datosBoton[4]); // Ancho
                    int alto = Integer.parseInt(datosBoton[5]); // Alto
                    String color = datosBoton[6];
                    
                    // Establecer la posición y el tamaño del botón
                    botonArea.setBounds(x, y, ancho, alto);
                    botonArea.setBackground(Color.decode(color));
                    SistemMonitoreo.obtenerContrasteTexto(botonArea, Color.decode(color));
                    
                    botonArea.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            SistemMonitoreo.botonSeleccionado = botonArea;
                            JButton botonSelect2 = botonArea;
                            List<String> datosArchivo = CargarDatos(botonSelect2.getText());

                            titulo.setText(datosArchivo.get(0));
                            descripcion.setText(datosArchivo.get(1).replace("\\n", "\n"));
                            imagen.setIcon(new ImageIcon((new ImageIcon(datosArchivo.get(3))).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                        }
                        public void mousePressed(MouseEvent e) {
                            // Asignar valores al offset cuando se presiona el botón
                            offset[0] = e.getX();
                            offset[1] = e.getY();
                        }

                        public void mouseReleased(MouseEvent e) {
                            // implementación
                        }
                    });

                    botonArea.addMouseMotionListener(new MouseMotionAdapter() {
                        public void mouseDragged(MouseEvent e) {
                            // Acceder al offset y realizar otras operaciones de arrastre
                            int nuevoX = e.getX() + botonArea.getX() - offset[0];
                            int nuevoY = e.getY() + botonArea.getY() - offset[1];

                            nuevoX = Math.min(Math.max(nuevoX, 0), panelAreas.getWidth() - botonArea.getWidth());
                            nuevoY = Math.min(Math.max(nuevoY, 0), panelAreas.getHeight() - botonArea.getHeight());

                            Rectangle newXBounds = new Rectangle(nuevoX, botonArea.getY(), ancho, alto);
                            boolean collideX = false;
                            for (JButton otherButton : Areas) {
                                if (otherButton != botonArea && otherButton.getBounds().intersects(newXBounds)) {
                                    collideX = true;
                                    break;
                                }
                            }

                            Rectangle newYBounds = new Rectangle(botonArea.getX(), nuevoY, ancho, alto);
                            boolean collideY = false;
                            for (JButton otherButton : Areas) {
                                if (otherButton != botonArea && otherButton.getBounds().intersects(newYBounds)) {
                                    collideY = true;
                                    break;
                                }
                            }

                            if (!collideX) {
                                botonArea.setLocation(nuevoX, botonArea.getY());
                            }
                            if (!collideY) {
                                botonArea.setLocation(botonArea.getX(), nuevoY);
                            }
                        }
                    });
                    Areas.add(botonArea);
                    panelAreas.add(botonArea);
                }
            }
            bufferedReader.close();
            // Actualizar la interfaz gráfica después de agregar los botones
            panelAreas.revalidate();
            panelAreas.repaint();
            System.out.println("Botones cargados correctamente desde el archivo: " + nombreArchivo);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar los botones desde el archivo: " + e.getMessage());
        }
    }
    
    private String sobrescribirDato(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length >= 7) { // Asegurarse de que haya suficientes partes en la línea
            // Reconstruir la línea con los datos modificados
            return String.join("|", partes);
        } else {
            // No hay suficientes partes en la línea, devolver la línea sin modificaciones
            return linea;
        }
        }
    
    // Metodo para obtener los nombres de los mapas que están guardados
    public List<String> obtenerNombresMapas() {
        // Carpeta donde se encuentran los archivos
        String carpeta = "Data/Mapas";

        // Crear una lista para almacenar los nombres de los archivos
        List<String> nombresArchivos = new ArrayList<>();

        // Obtener la lista de archivos en la carpeta
        File[] archivos = new File(carpeta).listFiles();
        if (archivos != null) {
            // Iterar sobre los archivos y agregar sus nombres a la lista
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    // Obtener solo el nombre del archivo sin la extensión
                    String nombreArchivo = archivo.getName().replaceFirst("[.][^.]+$", "");
                    nombresArchivos.add(nombreArchivo);
                }
            }
        } else {
            System.err.println("No se encontraron archivos.");
        }

        return nombresArchivos;
    }
    
    // Metodo para crear un nuevo mapa
    public void crearMapa(List<JButton> Areas, String nombre, DefaultComboBoxModel<String> modeloMapas, JComboBox<String> mapa){
        // Carpeta donde se guardarán los archivos
        String carpeta = "Data/Mapas";
        
        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        
        // Determinar el nombre del nuevo archivo
        String nombreArchivoBase = nombre + ".txt";
        String nombreArchivo = carpeta + "/" + nombreArchivoBase;
        nombreMapa = nombre;
        int contador = 1;
        
        // Verificar si el archivo con ese nombre ya existe
        while (new File(nombreArchivo).exists()) {
            nombreArchivo = carpeta + "/" + nombre + "_" + contador + ".txt";
            nombreMapa = nombre + "_" + contador;
            contador++;
        }
        
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Iterar sobre la lista de botones
            for (JButton boton : Areas) {
                String idUnico = generarIdUnico(boton);
                // Obtener los datos del botón
                String nombreBoton = boton.getText();
                int x = boton.getX();
                int y = boton.getY();
                int ancho = boton.getWidth();
                int alto = boton.getHeight();
                Color colorRgb = boton.getBackground();
                String color = SistemMonitoreo.colorAHexadecimal(colorRgb);

                // Escribir los datos del botón en el archivo
                bufferedWriter.write(idUnico + "|" + nombreBoton + "|" + x + "|" + y + "|" + ancho + "|" + alto + "|" + color);
                bufferedWriter.newLine(); // Nueva línea para el próximo botón
            }

            // Agregar el nombre del mapa al modelo de mapas
            modeloMapas.addElement(nombreMapa);

            System.out.println("El array se ha guardado correctamente en el archivo " + nombreArchivo);
            
            mapa.setSelectedItem(nombreMapa);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
    
    // Metodo para guardar el mapa creado
    public void guardarMapa(List<JButton> areas, String nombre, DefaultComboBoxModel<String> modeloMapas, JComboBox<String> mapa){
        String carpeta = "Data/Mapas";
        String nombreArchivo = nombre + ".txt";
        String rutaArchivo = carpeta + "/" + nombreArchivo;

        try {
            // Crear el archivo si no existe
            File archivo = new File(rutaArchivo);

            // Escribir los datos de los botones en el archivo
            FileWriter fileWriter = new FileWriter(archivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir la información de los botones
            for (JButton boton : areas) {
                // Generar un identificador único para el botón
                String idUnico = generarIdUnico(boton);
                // Obtener datos del botón
                String nombreBoton = boton.getText();
                int x = boton.getX();
                int y = boton.getY();
                int ancho = boton.getWidth();
                int alto = boton.getHeight();
                Color colorRgb = boton.getBackground();
                String color = SistemMonitoreo.colorAHexadecimal(colorRgb);
                
                // Escribir los datos del botón en el archivo
                String datosBoton = idUnico + "|" + nombreBoton + "|" + x + "|" + y + "|" + ancho + "|" + alto + "|" + color;
                bufferedWriter.write(datosBoton);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("El archivo se ha actualizado correctamente: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }
    
    // Metodo para eliminar el archivo del mapa
    public void eliminarArchivo(String nombre) {
        String carpeta = "Data/Mapas";
        String nombreArchivo = nombre + ".txt";
        String rutaArchivo = carpeta + "/" + nombreArchivo;
        
        File archivo = new File(rutaArchivo);

        // Verificar si el archivo existe antes de intentar eliminarlo
        if (archivo.exists()) {
            // Intentar eliminar el archivo
            if (archivo.delete()) {
                System.out.println("El archivo se ha eliminado correctamente: " + rutaArchivo);
            } else {
                System.err.println("No se pudo eliminar el archivo: " + rutaArchivo);
            }
        } else {
            System.out.println("El archivo no existe: " + rutaArchivo);
        }
    }
    
    // Metodo para eliminar los botones del mapa
    public void EliminarBotonMapa(String nombre) {
        String carpeta = "Data/Mapas";

        // Obtener la lista de archivos en la carpeta
        File directorio = new File(carpeta);
        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                try {
                    // Crear una lista temporal para almacenar las líneas del archivo
                    List<String> lineas = new ArrayList<>();

                    // Leer los datos del archivo y guardarlos en la lista temporal
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
                    String linea;
                    while ((linea = bufferedReader.readLine()) != null) {
                        lineas.add(linea);
                    }
                    bufferedReader.close();

                    // Eliminar el archivo existente para escribir los datos actualizados
                    archivo.delete();

                    // Escribir los datos actualizados en el archivo
                    FileWriter fileWriter = new FileWriter(archivo);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // Recorrer las líneas y escribir las que no correspondan al nombre del botón a eliminar
                    for (String lineaExistente : lineas) {
                        String[] datosBoton = lineaExistente.split("\\|");
                        if (datosBoton.length > 1 && !datosBoton[1].equals(nombre)) {
                            bufferedWriter.write(lineaExistente);
                            bufferedWriter.newLine();
                        }
                    }

                    bufferedWriter.close();
                    System.out.println("Se han eliminado los botones con el nombre '" + nombre + "' del archivo: " + archivo.getName());
                } catch (IOException e) {
                    System.err.println("Error al eliminar los botones del archivo " + archivo.getName() + ": " + e.getMessage());
                }
            }
        }
    }
}