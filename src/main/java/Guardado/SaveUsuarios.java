package Guardado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveUsuarios {

    public List<String> CargarDatos(String nombre) {
        if (!nombre.endsWith(".txt")) {
            nombre += ".txt";
        }

        // Carpeta donde se encuentran los archivos
        String carpeta = "Data/Usuarios";

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
                            File imagen = new File(partes[9]);
                            if (!imagen.exists()) {
                                partes[9] = "Imagenes/Iconos/perfilEstandar.png";
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

    public String validaciones(String usuario, String cedula, String email) {
        String carpeta = "Data/Usuarios";

        // Iterar sobre los archivos en la carpeta
        File[] archivos = new File(carpeta).listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                List<String> datosArchivo = new ArrayList<>();

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

                if (datosArchivo.get(0).trim().equals(usuario)) {
                    return "Usuario";
                }
                if (datosArchivo.get(3).trim().equals(cedula)) {
                    return "Cc";
                }
                if (datosArchivo.get(4).trim().equals(email)) {
                    return "Correo";
                }
            }
        } else {
            System.err.println("No se encontraron archivos en la carpeta especificada.");
            return null;
        }
        return "|no";
    }

    public String[] RecuperarContra(String cedula) {
        String carpeta = "Data/Usuarios";

        // Iterar sobre los archivos en la carpeta
        File[] archivos = new File(carpeta).listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String cadena;
                    while ((cadena = br.readLine()) != null) {
                        String[] datosUsuario = cadena.split("\\|");

                        if (datosUsuario.length > 0 && datosUsuario[3].trim().equals(cedula)) {
                            return datosUsuario;
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.err.println("No se puede recuperar la contraseña");
                } catch (IOException ex) {
                    System.err.println("No se puede leer la cadena de texto");
                }
            }
        }
        return null;
    }//fin metodo recuperarContra

    // Metodo para guardar un area
    public void CrearUsu(String[] Usuario) {
        // Carpeta donde se guardarán los archivos
        String carpeta = "Data/Usuarios";

        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Determinar el nombre del nuevo archivo
        String nombreArchivoBase = Usuario[0] + ".txt";
        String nombreArchivo = carpeta + "/" + nombreArchivoBase;

        String[] lista = Usuario;

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

    public void editarUsuario(String usuarioAnt, String usuario, String contrasena, String rutaImagen) {
        String carpeta = "Data/Usuarios";
        String rutaArchivo = carpeta + "/" + usuarioAnt + ".txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
            StringBuilder contenido = new StringBuilder();
            String linea = reader.readLine();
            boolean usuarioModificado = false;

            while (linea != null) {
                String[] elementos = linea.split("\\|");

                if (elementos.length > 0 && elementos[0].equals(usuarioAnt)) {
                    elementos[0] = usuario;
                    elementos[5] = contrasena;
                    elementos[9] = rutaImagen;
                    usuarioModificado = true;
                }

                String nuevaLinea = String.join("|", elementos);
                contenido.append(nuevaLinea).append("\n");
                linea = reader.readLine();
            }

            reader.close();

            // Si el usuario ha sido modificado, actualizamos el archivo
            if (usuarioModificado) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo));

                writer.write(contenido.toString());
                writer.close();

                // Renombrar el archivo si el nombre del usuario ha cambiado
                if (!usuarioAnt.equals(usuario)) {
                    File archivoAntiguo = new File(rutaArchivo);
                    String rutaArchivoNuevo = carpeta + "/" + usuario + ".txt";
                    File archivoNuevo = new File(rutaArchivoNuevo);
                    if (!archivoAntiguo.renameTo(archivoNuevo)) {
                        System.err.println("No se pudo cambiar el nombre del archivo.");
                    }
                }

                System.out.println("Usuario editado con éxito.");
            } else {
                System.out.println("El usuario no ha sido modificado.");
            }
        } catch (IOException e) {
            System.err.println("Error al editar el usuario: " + e.getMessage());
        }
    }
    
    public void editarAdmin(String usuarioAnt, String usuario, String nombre, String apellido, String cedula, String email,
            String contrasena, String genero, String telefono, String rutaImg, String resp1, String resp2,
            String resp3, String tipUsu){
        
        String carpeta = "Data/Usuarios";
        String rutaArchivo = carpeta + "/" + usuarioAnt + ".txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
            StringBuilder contenido = new StringBuilder();
            String linea = reader.readLine();
            boolean usuarioModificado = false;

            while (linea != null) {
                String[] elementos = linea.split("\\|");

                if (elementos.length > 0 && elementos[0].equals(usuarioAnt)) {
                    elementos[0] = usuario;
                    elementos[1] = nombre;
                    elementos[2] = apellido;
                    elementos[3] = cedula;
                    elementos[4] = email;
                    elementos[5] = contrasena;
                    elementos[6] = contrasena;
                    elementos[7] = genero;
                    elementos[8] = telefono;
                    if(!rutaImg.equals("Imagenes/Iconos/perfilEstandar.png")){
                        elementos[9] = rutaImg;
                    }
                    elementos[11] = resp1;
                    elementos[13] = resp2;
                    elementos[15] = resp3;
                    elementos[16] = tipUsu;
                    
                    usuarioModificado = true;
                }

                String nuevaLinea = String.join("|", elementos);
                contenido.append(nuevaLinea).append("\n");
                linea = reader.readLine();
            }

            reader.close();

            // Si el usuario ha sido modificado, actualizamos el archivo
            if (usuarioModificado) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo));

                writer.write(contenido.toString());
                writer.close();

                // Renombrar el archivo si el nombre del usuario ha cambiado
                if (!usuarioAnt.equals(usuario)) {
                    File archivoAntiguo = new File(rutaArchivo);
                    String rutaArchivoNuevo = carpeta + "/" + usuario + ".txt";
                    File archivoNuevo = new File(rutaArchivoNuevo);
                    if (!archivoAntiguo.renameTo(archivoNuevo)) {
                        System.err.println("No se pudo cambiar el nombre del archivo.");
                    }
                }

                System.out.println("Usuario editado con éxito.");
            } else {
                System.out.println("El usuario no ha sido modificado.");
            }
        } catch (IOException e) {
            System.err.println("Error al editar el usuario: " + e.getMessage());
        }
    }
    
    public void EliminarUsu(String usuario){
        String carpeta = "Data/Usuarios/" + usuario + ".txt";
        
        File archivo = new File(carpeta);
        
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
