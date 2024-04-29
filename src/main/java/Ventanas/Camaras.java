package Ventanas;

import Logica.CameraManager;
import static Logica.CameraManager.cargarCamaras;
import static Logica.CameraManager.cerrarCamaras;
import Guardado.SaveAreas;
import Guardado.SaveRegistros;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Logica.SistemCamaras;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Camaras extends JFrame{
    Menu menu;
    public JPanel fondo = new JPanel();
    public static String nombreArea = new String();
    public JFrame ventanaAnterior;
    public static JButton botonSeleccionado;
    public JPanel panelPrincipal = new JPanel();
    public JPanel fondoReg = new JPanel();
    public JPanel panelRegistros = new JPanel();
    
    SaveAreas guardado = new SaveAreas();
    SaveRegistros guardadoRegistros = new SaveRegistros();
    
    public static JButton registroSeleccionado;
    public static String nombreRegistro;
    
    JTextField registro = new JTextField();
    JTextArea descripcion = new JTextArea();
    
    String ruta1 = "Imagenes/Iconos/registros.png";
    String ruta2 = "Imagenes/Iconos/registros.png";
    String ruta3 = "Imagenes/Iconos/registros.png";
    
    public Camaras(){
        PanelFondo();
        Elementos();
        cargarBotones();
    }
    
    private void PanelFondo(){
        setTitle("Cámara del area: " + nombreArea);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        fondo = new JPanel();
        fondo.setBackground(Color.decode(menu.colorPanelClaro));
        fondo.setLayout(null);
        fondo.setBorder(new EmptyBorder(10,0,10,10)); //Establecemos margenes en el fondo.
        this.add(fondo);
    }
    
    private void Elementos(){
        JPanel elementos = new JPanel();
        elementos.setLayout(null);
        elementos.setBounds(10,10,965,540);
        elementos.setOpaque(false);
        fondo.add(elementos);
        
        JLabel titulo = new JLabel("REGISTROS");
        titulo.setForeground(Color.white);
        titulo.setFont(new Font("Arial",1,30));
        titulo.setBounds(680, 15, 200, 40);
        elementos.add(titulo);
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBackground(Color.decode(menu.colorBotonOscuro));
        botonVolver.setFocusPainted(false);
        botonVolver.setBounds(905, 10, 50, 50);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        elementos.add(botonVolver);
        //Funciones botones.
        ActionListener irMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
                registroSeleccionado = null;
                setVisible(false);
            }
        };
        botonVolver.addActionListener(irMenu);
        
        // PANEL DE LA CAMARA
        panelPrincipal.setBackground(Color.decode(menu.colorPanelOscuro));
        panelPrincipal.setLayout(null);
        panelPrincipal.setBounds(10,10,650,520);
        panelPrincipal.setOpaque(true);
        elementos.add(panelPrincipal);
        
        // Agregamos la camara
        Camaras();
        
        // FONDO DE LOS REGISTROS (DECORATIVO)
        fondoReg.setBackground(Color.decode(menu.colorPanelMedio));
        fondoReg.setLayout(null);
        fondoReg.setBounds(665,10,290,520);
        fondoReg.setOpaque(true);
        elementos.add(fondoReg);
        
        // PANEL PARA GUARDAR REGISTROS
        panelRegistros.setBackground(Color.decode(menu.colorPanelOscuro));
        panelRegistros.setLayout(new BoxLayout(panelRegistros, BoxLayout.Y_AXIS));
        
        // SCROLL PARA EL PANEL DE REGISTROS
        JScrollPane scrollRegistros = new JScrollPane();
        scrollRegistros.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollRegistros.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRegistros.setBounds(10,55,270,420);
        scrollRegistros.setOpaque(true);
        scrollRegistros.setBorder(null);
        scrollRegistros.setViewportView(panelRegistros);
        fondoReg.add(scrollRegistros);
        
        //BOTONES DE REGISTRO
        JButton abrirRegistro = new JButton("ABRIR");
        abrirRegistro.setBackground(Color.decode(menu.colorBotonClaro));
        abrirRegistro.setFocusPainted(false); //Quitamos las lineas de focus.
        abrirRegistro.addActionListener(e-> Abrir());
        fondoReg.add(abrirRegistro);
        
        JButton crearRegistro = new JButton("CREAR");
        crearRegistro.setBackground(Color.decode(menu.colorBotonClaro));
        crearRegistro.setFocusPainted(false); //Quitamos las lineas de focus.
        crearRegistro.addActionListener(e-> Crear());
        fondoReg.add(crearRegistro);
        
        JButton eliminarRegistro = new JButton("ELIMINAR");
        eliminarRegistro.setBackground(Color.decode(menu.colorBotonClaro));
        eliminarRegistro.setFocusPainted(false); //Quitamos las lineas de focus.
        eliminarRegistro.setBounds(190,485,90,20);
        eliminarRegistro.addActionListener(e-> Eliminar());
        
        
        if (menu.usuario.get(16).equals("Administrador")){
            abrirRegistro.setBounds(10,485,80,20);
            crearRegistro.setBounds(100,485,80,20);
            
            fondoReg.add(eliminarRegistro);
        }else if(menu.usuario.get(16).equals("Usuario")){
            abrirRegistro.setBounds(55,485,80,20);
            crearRegistro.setBounds(145,485,80,20);
        }
    }
    
    private void Camaras(){
        // Cargamos la lista de datos.
        List<String> datosArchivo = guardado.CargarDatos(nombreArea);
        
        ImageIcon add = new ImageIcon("Imagenes/Iconos/add.png");
        
        // PREGUNTAMOS SI LAS NO HAY CAMARAS
        if ("desActive".equals(datosArchivo.get(4))){
            // Boton para añadir la camara
            JButton botonCam = new JButton();
            botonCam.setForeground(Color.decode(menu.colorBotonOscuro));
            botonCam.setBackground(Color.decode(menu.colorBotonOscuro));
            botonCam.setIcon(new ImageIcon(add.getImage().getScaledInstance(650, 520, Image.SCALE_SMOOTH)));
            botonCam.setBounds(0, 0, 650, 520);
            botonCam.setFocusPainted(false);
            botonCam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SistemCamaras logica = new SistemCamaras();
                    logica.AgregarCamara(panelPrincipal);
                }
            });
            panelPrincipal.add(botonCam);
        }else{
            int index = Integer.parseInt(datosArchivo.get(4));
            
            cerrarCamaras();
            cargarCamaras();
            CameraManager.mostrarCamaraEnPanel(index, panelPrincipal);
            
            // Boton para editar la camara
            JButton editarCam = new JButton("EDITAR");
            editarCam.setBackground(Color.decode(menu.colorBotonClaro));
            editarCam.setFocusPainted(false);
            editarCam.setBounds(560, 0, 80, 20);
            editarCam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SistemCamaras logica = new SistemCamaras();
                    logica.AgregarCamara(panelPrincipal);
                }
            });
            panelPrincipal.add(editarCam);
        }
    }
    
    private void Abrir(){
        if (registroSeleccionado != null){
            Registros ventanaRegistros = new Registros();
            ventanaRegistros.ventanaAnterior = this;
            ventanaRegistros.setVisible(true);
            setVisible(false);
        }else{
            JOptionPane.showMessageDialog(this, "Por favor seleccione un registro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void Crear(){
        registro.setText("");
        descripcion.setText("");
        ruta1 = "Imagenes/Iconos/registros.png";
        ruta2 = "Imagenes/Iconos/registros.png";
        ruta3 = "Imagenes/Iconos/registros.png";
        
        JDialog ventanaCrear = new JDialog(this, "Crear Registro", true);
        ventanaCrear.setSize(430, 410);
        ventanaCrear.setLocationRelativeTo(null);
        ventanaCrear.setResizable(false);

        // Crear un panel para el contenido
        JPanel panelCrear = new JPanel();
        panelCrear.setLayout(null);
        panelCrear.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel

        // DECORACION
        JPanel decorCrear = new JPanel();
        decorCrear.setBounds(10,10,390,320);
        decorCrear.setLayout(null);
        decorCrear.setBackground(Color.decode(menu.colorPanelClaro));
        panelCrear.add(decorCrear);

        JLabel tituloNombre = new JLabel("Nombre:");
        tituloNombre.setForeground(Color.white);
        tituloNombre.setBounds(10,10,100,20);
        decorCrear.add(tituloNombre);

        registro.setBounds(10, 40, 200, 20);
        decorCrear.add(registro);

        JLabel tituloDescript = new JLabel("Descripción:");
        tituloDescript.setForeground(Color.white);
        tituloDescript.setBounds(10,70,100,20);
        decorCrear.add(tituloDescript);
        
        JScrollPane scrollDescript = new JScrollPane(descripcion);
        scrollDescript.setBounds(10, 100, 200, 200);
        decorCrear.add(scrollDescript);

        JButton imagen1 = new JButton();
        imagen1.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/registros.png")).getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH)));
        imagen1.setFocusPainted(false);
        imagen1.setBounds(230,10,150,90);
        imagen1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                jFileChooser.setFileFilter(filtrado);
                
                int respuesta = jFileChooser.showOpenDialog(Camaras.this);
                
                if (respuesta == JFileChooser.APPROVE_OPTION){
                    ruta1 = jFileChooser.getSelectedFile().getPath();
                    imagen1.setIcon(new ImageIcon((new ImageIcon(ruta1)).getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH)));
                }else{
                    ruta1 = "Imagenes/Iconos/registros.png";
                }
            }
        });
        decorCrear.add(imagen1);

        JButton imagen2 = new JButton();
        imagen2.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/registros.png")).getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH)));
        imagen2.setFocusPainted(false);
        imagen2.setBounds(230,110,150,90);
        imagen2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                jFileChooser.setFileFilter(filtrado);
                
                int respuesta = jFileChooser.showOpenDialog(Camaras.this);
                
                if (respuesta == JFileChooser.APPROVE_OPTION){
                    ruta2 = jFileChooser.getSelectedFile().getPath();
                    imagen2.setIcon(new ImageIcon((new ImageIcon(ruta2)).getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH)));
                }else{
                    ruta2 = "Imagenes/Iconos/registros.png";
                }
            }
        });
        decorCrear.add(imagen2);

        JButton imagen3 = new JButton();
        imagen3.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/registros.png")).getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH)));
        imagen3.setFocusPainted(false);
        imagen3.setBounds(230,210,150,90);
        imagen3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                jFileChooser.setFileFilter(filtrado);
                
                int respuesta = jFileChooser.showOpenDialog(Camaras.this);
                
                if (respuesta == JFileChooser.APPROVE_OPTION){
                    ruta3 = jFileChooser.getSelectedFile().getPath();
                    imagen3.setIcon(new ImageIcon((new ImageIcon(ruta3)).getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH)));
                }else{
                    ruta3 = "Imagenes/Iconos/registros.png";
                }
            }
        });
        decorCrear.add(imagen3);

        // BOTONES PARA REALIZAR OPERACIONES
        JButton botonCrear = new JButton("CREAR");
        botonCrear.setBounds(190,340,100,20);
        botonCrear.setBackground(Color.decode(menu.colorBotonClaro));
        botonCrear.setFocusPainted(false); //Quitamos las lineas de focus.
        botonCrear.addActionListener(e -> {
            try {
                // Eliminar espacios al inicio y al final
                String nombre = registro.getText().trim(); 
                // Verificar nombre.
                if (nombre.length() > 30){
                    JOptionPane.showMessageDialog(this, "Ingrese menos de 30 caracteres en el nombre.", "Mucho texto.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (nombre.length() <= 0){
                    JOptionPane.showMessageDialog(this, "Ingresele un nombre al Registro.", "Poco texto.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (nombre.contains("|") || descripcion.getText().contains("|")){
                    JOptionPane.showMessageDialog(this, "No puede ingresar el caracter \" | \"", "Caracter Invalido.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                guardadoRegistros.guardarRegistro(nombre, descripcion.getText(), ruta1, ruta2, ruta3);

                JButton boton = new JButton(guardadoRegistros.nombreRegistro);
                boton.setBackground(Color.decode(menu.colorBotonClaro));
                boton.setFocusPainted(false); //Quitamos las lineas de focus.
                boton.setPreferredSize(new Dimension(253, 50));
                boton.setMaximumSize(new Dimension(253, 50));
                boton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        // Metodo para darle un color de selección
                        if (registroSeleccionado != null){
                            registroSeleccionado.setBackground(Color.decode(menu.colorBotonClaro));
                        }
                        registroSeleccionado = boton;
                        registroSeleccionado.setBackground(Color.decode(menu.colorBotonClaroSeleccion));

                        nombreRegistro = registroSeleccionado.getText();
                }
                });
                panelRegistros.add(boton);
                
                panelRegistros.revalidate();
                panelRegistros.repaint();
                
                ventanaCrear.dispose(); // Cerrar el diálogo después de agregar el botón
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error al crear.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelCrear.add(botonCrear);

        JButton botonCancelar = new JButton("CANCELAR");
        botonCancelar.setBounds(300,340,100,20);
        botonCancelar.setBackground(Color.decode(menu.colorBotonClaro));
        botonCancelar.setFocusPainted(false); //Quitamos las lineas de focus.
        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaCrear.dispose();
            }
        };
        botonCancelar.addActionListener(cancelar);
        panelCrear.add(botonCancelar);

        ventanaCrear.add(panelCrear, BorderLayout.CENTER);
        ventanaCrear.setLocationRelativeTo(this);
        ventanaCrear.setVisible(true);
    }
    
    private void Eliminar() {
        if (registroSeleccionado != null) {
            JDialog ventanaEliminar = new JDialog(this, "Eliminar Registro", true);
            ventanaEliminar.setSize(300, 120);
            ventanaEliminar.setLocationRelativeTo(null);
            ventanaEliminar.setResizable(false);

            // Crear un panel para el contenido
            JPanel panelEliminar = new JPanel();
            panelEliminar.setLayout(null);
            panelEliminar.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel

            JLabel texto = new JLabel("Desea eliminar el Registro " + registroSeleccionado.getText() + "?");
            texto.setBounds(10,10,300,20);
            texto.setForeground(Color.white);
            panelEliminar.add(texto);

            JButton botonEliminar = new JButton("ELIMINAR");
            botonEliminar.setBounds(10,50,100,20);
            botonEliminar.setBackground(Color.decode(menu.colorBotonClaro));
            botonEliminar.setFocusPainted(false); //Quitamos las lineas de focus.
            botonEliminar.addActionListener(e -> {
                try {
                    guardadoRegistros.eliminarRegistro(registroSeleccionado.getText());
                    
                    panelRegistros.remove(registroSeleccionado);
                    panelRegistros.revalidate();
                    panelRegistros.repaint();
                    registroSeleccionado = null;
                    ventanaEliminar.dispose(); // Cerrar el diálogo después de agregar el botón
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panelEliminar.add(botonEliminar);
            
            JButton botonCancelar = new JButton("CANCELAR");
            botonCancelar.setBounds(170,50,100,20);
            botonCancelar.setBackground(Color.decode(menu.colorBotonClaro));
            botonCancelar.setFocusPainted(false); //Quitamos las lineas de focus.
            ActionListener cancelar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ventanaEliminar.dispose();
                }
            };
            botonCancelar.addActionListener(cancelar);
            panelEliminar.add(botonCancelar);

            ventanaEliminar.add(panelEliminar, BorderLayout.CENTER);
            ventanaEliminar.setLocationRelativeTo(this);
            ventanaEliminar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un Registro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarBotones() {
        File directorio = new File("Data/Registros/" + nombreArea);
        File[] archivos = directorio.listFiles((dir, nombre) -> nombre.toLowerCase().endsWith(".txt"));

        // Limpiar el panel antes de agregar nuevos botones
        panelRegistros.removeAll();
        
        if (archivos != null) {
            for (File archivo : archivos) {
                String nombreArchivo = archivo.getName();
                JButton boton = new JButton(nombreArchivo.substring(0, nombreArchivo.lastIndexOf(".")));
                boton.setBackground(Color.decode(menu.colorBotonClaro));
                boton.setFocusPainted(false);
                boton.setPreferredSize(new Dimension(253, 50));
                boton.setMaximumSize(new Dimension(253, 50));
                boton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        // Metodo para darle un color de selección
                        if (registroSeleccionado != null){
                            registroSeleccionado.setBackground(Color.decode(menu.colorBotonClaro));
                        }
                        registroSeleccionado = boton;
                        registroSeleccionado.setBackground(Color.decode(menu.colorBotonClaroSeleccion));

                        nombreRegistro = registroSeleccionado.getText();
                }
                });
                panelRegistros.add(boton);
            }
        } else {
            // Si no hay archivos .txt en la carpeta, mostrar un mensaje o realizar alguna acción
        }
        
        // Volver a validar y repintar el panel para que los cambios sean visibles
        panelRegistros.revalidate();
        panelRegistros.repaint();
    }
}