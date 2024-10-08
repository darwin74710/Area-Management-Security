package Logica;

import Guardado.SaveAreas;
import Ventanas.Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class SistemNotificaciones extends JFrame{
    static JPanel panelNotifi;
    
    public static JButton notifiSeleccionada;
    public static JButton areaDataSeleccionada;
    
    SaveAreas guardadoAreas = new SaveAreas();
    
    public static void SeleccionarPanel(JPanel panel){
        panelNotifi = panel;
    }
    
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
        
        String info = fecha + "Cam " + Index;
        
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
    
    public void notifiDetect(){
        String carpeta = "Data/Notificaciones";
        
        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        
        File[] listaDeArchivos = directorio.listFiles();
        
        if (listaDeArchivos != null) {
            for (File archivo : listaDeArchivos) {
                if (archivo.isFile()) {
                    List<String> datosArchivo = obtenerDatosDeArchivo(archivo);
                    
                    JButton boton = new JButton(datosArchivo.get(0));
                    boton.setMaximumSize(new Dimension(203, 40));
                    boton.setPreferredSize(new Dimension(203, 40));
                    boton.setBackground(Color.decode(Menu.colorBotonClaro));
                    boton.setFocusPainted(false);
                    boton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        // Metodo para darle un color de selección
                        if (notifiSeleccionada != null){
                            notifiSeleccionada.setBackground(Color.decode(Menu.colorBotonClaro));
                        }
                        notifiSeleccionada = boton;
                        notifiSeleccionada.setBackground(Color.decode(Menu.colorBotonClaroSeleccion));
                        
                        ventanaDatos(datosArchivo);
                    }
                    });
                    panelNotifi.add(boton);
                }
            }
        }else{
            panelNotifi.removeAll();
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
    
    private void ventanaDatos(List<String> datosArchivo){
        // Crear un diálogo personalizado
        JDialog ventanaDatosNotifi = new JDialog(this, "Movimiento Cámara: " + datosArchivo.get(1), true);
        ventanaDatosNotifi.setSize(255, 310);
        ventanaDatosNotifi.setLocationRelativeTo(null);
        ventanaDatosNotifi.setResizable(false);
        
        // Crear un panel para el contenido
        JPanel panelDatosNotifi = new JPanel();
        panelDatosNotifi.setLayout(null);
        panelDatosNotifi.setBackground(Color.decode(Menu.colorBotonOscuro));
        
        JLabel titulo = new JLabel("<html><body><center><p>Se detectó un movimiento en la Cámara: " + datosArchivo.get(1) + "</p></html>");
        titulo.setForeground(Color.white);
        titulo.setBounds(10, 10, 217, 40);
        panelDatosNotifi.add(titulo);
        
        JLabel titulo2 = new JLabel("AREAS AFECTADAS:");
        titulo2.setHorizontalAlignment(SwingConstants.CENTER);
        titulo2.setForeground(Color.white);
        titulo2.setBounds(10, 60, 217, 20);
        panelDatosNotifi.add(titulo2);
        
        JPanel panelAreas = new JPanel();
        panelAreas.setLayout(new BoxLayout(panelAreas, BoxLayout.Y_AXIS));
        
        panelAreas.setBackground(Color.decode(Menu.colorPanelClaro));

        JScrollPane scrollAreas = new JScrollPane(panelAreas);
        scrollAreas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollAreas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollAreas.setBounds(10, 80, 217, 150);
        scrollAreas.setOpaque(true);
        scrollAreas.setBorder(null);
        panelDatosNotifi.add(scrollAreas);
        
        if (datosArchivo.size() > 2){
            for (int i = 0; i < (datosArchivo.size() - 2); i++){
                int Index = i;
                
                JButton area = new JButton(datosArchivo.get(i + 2));
                area.setBackground(Color.decode(Menu.colorBotonClaro));
                area.setFocusPainted(false);
                area.setMaximumSize(new Dimension(200, 40));
                area.setPreferredSize(new Dimension(200, 40));
                area.setHorizontalAlignment(SwingConstants.CENTER);
                area.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        // Metodo para darle un color de selección
                        if (areaDataSeleccionada != null){
                            areaDataSeleccionada.setBackground(Color.decode(Menu.colorBotonClaro));
                        }
                        areaDataSeleccionada = area;
                        areaDataSeleccionada.setBackground(Color.decode(Menu.colorBotonClaroSeleccion));
                        
                        ventanaInfoArea(datosArchivo, Index, ventanaDatosNotifi);
                    }
                    });
                panelAreas.add(area);
            }
        }else{
            panelAreas.removeAll();
        }
        
        JButton elimianSeleccion = new JButton("ELIMINAR");
        elimianSeleccion.setBounds(130, 240, 97, 20);
        elimianSeleccion.setBackground(Color.decode(Menu.colorBotonClaro));
        elimianSeleccion.setFocusPainted(false);
        elimianSeleccion.addActionListener(e-> EliminarSeleccion(ventanaDatosNotifi));
        panelDatosNotifi.add(elimianSeleccion);
        
        ventanaDatosNotifi.add(panelDatosNotifi, BorderLayout.CENTER);
        ventanaDatosNotifi.setLocationRelativeTo(this);
        
        ventanaDatosNotifi.setVisible(true);
    }
    
    private void ventanaInfoArea(List<String> datosArchivo, int Index, JDialog ventanaDatosNotifi){
        List<String> datosArea = guardadoAreas.CargarDatos(datosArchivo.get(Index + 2));
        
        JDialog ventanaDatosAreas = new JDialog(ventanaDatosNotifi, "Información del Area: " + datosArchivo.get(Index + 2), true);
        ventanaDatosAreas.setSize(500, 400);
        ventanaDatosAreas.setLocationRelativeTo(null);
        ventanaDatosAreas.setResizable(false);
        
        // Crear un panel para el contenido
        JPanel panelDatosAreas = new JPanel();
        panelDatosAreas.setLayout(null);
        panelDatosAreas.setBackground(Color.decode(Menu.colorPanelMedio));
        
        JLabel imagen = new JLabel();
        imagen.setIcon(new ImageIcon((new ImageIcon(datosArea.get(3))).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        imagen.setBounds(10, 10, 200, 200);
        panelDatosAreas.add(imagen);
        
        JLabel tituloMap = new JLabel("MAPAS:");
        tituloMap.setHorizontalAlignment(SwingConstants.CENTER);
        tituloMap.setBounds(10, 220, 200, 20);
        tituloMap.setForeground(Color.white);
        panelDatosAreas.add(tituloMap);
        
        
        JPanel panelMapas = new JPanel();
        panelMapas.setForeground(Color.white);
        panelMapas.setBackground(Color.decode(Menu.colorPanelOscuro));
        panelMapas.setLayout(new BoxLayout(panelMapas, BoxLayout.Y_AXIS));
        
        JScrollPane scrollMapas = new JScrollPane(panelMapas);
        scrollMapas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMapas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMapas.setBounds(10,240,200,110);
        scrollMapas.setBorder(null);
        panelDatosAreas.add(scrollMapas);
        
        // Buscamos los mapas
        String carpeta = "Data/Mapas";
        
        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        
        File[] listaDeArchivos = directorio.listFiles();
        
        // Crear un conjunto para almacenar los nombres de archivo ya agregados
        Set<String> nombresAgregados = new HashSet<>();

        if (listaDeArchivos != null) {
            for (File archivo : listaDeArchivos) {
                if (archivo.isFile()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = reader.readLine()) != null) {
                            List<String> datosMapa = Arrays.asList(linea.split("\\|"));
                            if (datosArchivo.get(Index + 2).equals(datosMapa.get(1))) {
                                String nombreArchivo = archivo.getName();
                                // Eliminar la extensión .txt del nombre del archivo
                                if (nombreArchivo.endsWith(".txt")) {
                                    nombreArchivo = nombreArchivo.substring(0, nombreArchivo.length() - 4);
                                }
                                // Verificar si el nombre de archivo ya está agregado
                                if (!nombresAgregados.contains(nombreArchivo)) {
                                    JLabel mapa = new JLabel(nombreArchivo);
                                    mapa.setHorizontalAlignment(SwingConstants.CENTER);
                                    mapa.setForeground(Color.decode(Menu.colorBotonClaro));
                                    mapa.setPreferredSize(new Dimension(200, 20));
                                    mapa.setMaximumSize(new Dimension(200, 20));
                                    panelMapas.add(mapa);
                                    // Agregar el nombre de archivo al conjunto
                                    nombresAgregados.add(nombreArchivo);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error al leer el archivo: " + e.getMessage());
                    }
                }
            }
        }
            
        JTextArea descript = new JTextArea(datosArea.get(1).replace("\\n", "\n"));
        descript.setMargin(new Insets(10, 10, 10, 10));
        descript.setForeground(Color.white);
        descript.setBackground(Color.decode(Menu.colorPanelClaro));
        descript.setEditable(false);
        
        JScrollPane scrollDescript = new JScrollPane(descript);
        scrollDescript.setBounds(220,10,255,340);
        scrollDescript.setBorder(null);
        panelDatosAreas.add(scrollDescript);
        
        ventanaDatosAreas.add(panelDatosAreas, BorderLayout.CENTER);
        ventanaDatosAreas.setLocationRelativeTo(ventanaDatosNotifi);
        
        ventanaDatosAreas.setVisible(true);
    }
    
    private void EliminarSeleccion(JDialog ventanaDatosNotifi){
        if (notifiSeleccionada != null){
            // Ruta del archivo que deseas eliminar
            String rutaArchivo = "Data/Notificaciones/" + notifiSeleccionada.getText() + ".txt";

            // Crear un objeto File con la ruta del archivo
            File archivo = new File(rutaArchivo);

            // Verificar si el archivo existe antes de intentar eliminarlo
            if (archivo.exists()) {
                // Intentar eliminar el archivo
                if (archivo.delete()) {
                    System.out.println("El archivo se eliminó correctamente.");
                } else {
                    System.out.println("No se pudo eliminar el archivo.");
                }
            } else {
                System.out.println("El archivo no existe en la ruta especificada.");
            }

            panelNotifi.remove(notifiSeleccionada);
            panelNotifi.revalidate();
            panelNotifi.repaint();
            
            notifiSeleccionada = null;

            ventanaDatosNotifi.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Por favor seleccione un video.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void EliminarNotificaciones(){
        // Directorio donde se encuentran los archivos
        String directorio = "Data/Notificaciones";

        // Crear un objeto File con el directorio
        File carpeta = new File(directorio);

        // Verificar si el directorio existe y es una carpeta
        if (carpeta.exists() && carpeta.isDirectory()) {
            // Obtener la lista de archivos en el directorio
            File[] archivos = carpeta.listFiles();

            // Iterar sobre cada archivo en el directorio
            for (File archivo : archivos) {
                // Verificar si el archivo es un archivo de texto (.txt)
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".txt")) {
                    // Intentar eliminar el archivo
                    if (archivo.delete()) {
                        System.out.println("Se eliminó el archivo: " + archivo.getName());
                    } else {
                        System.out.println("No se pudo eliminar el archivo: " + archivo.getName());
                    }
                }
            }
            
            panelNotifi.removeAll();
            panelNotifi.revalidate();
            panelNotifi.repaint();
        } else {
            System.out.println("El directorio especificado no existe o no es una carpeta.");
        }
    }
}
