package Guardado;

import Logica.SistemCamaras;
import static Logica.SistemMonitoreo.botonSeleccionado;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SaveCamara extends JFrame{
    SaveAreas guardado = new SaveAreas();
    
    // Metodo para guardar la camara
    public void GuardarCamara(JDialog ventanaAgregar, JPanel panelPrincipal, int NumIndex){
        if (SistemCamaras.listaCamaras.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "SELECCIONE UNA CÁMARA.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            panelPrincipal.removeAll();
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
            
            List<String> datosArchivo = guardado.CargarDatos(botonSeleccionado.getText());
            editarArea(botonSeleccionado.getText(), datosArchivo, NumIndex);
            
            ventanaAgregar.dispose();
        }
    }
    
    // Metodo para editar un area
    public void editarArea(String nombreArchivo, List<String> nuevosDatos, int NumIndex) {
        String carpeta = "Data/Areas";
        
        // Crear la ruta completa del archivo original
        String rutaArchivo = carpeta + "/" + nombreArchivo + ".txt";
    
        // Verificar si el archivo existe antes de continuar
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.err.println("El archivo " + nombreArchivo + " no existe.");
            return;
        }
        
        // Cambiamos el número
        nuevosDatos.set(4, String.valueOf(NumIndex));

        try {
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
}