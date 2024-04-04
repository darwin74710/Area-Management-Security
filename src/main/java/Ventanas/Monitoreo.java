package Ventanas;

import Logica.CameraManager;
import Logica.SistemMonitoreo;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Monitoreo extends JFrame{
    Menu menu;
    // Instanciamos la clase SistemAreas para utilizar sus metodos
    public SistemMonitoreo SistemAreas = new SistemMonitoreo();
    
    // Creamos paneles para guardar los elementos
    public JLabel fondo = new JLabel();
    public JPanel configArea = new JPanel();
    
    public Monitoreo(){
        // Guardamos esta ventana en SistemAreas al iniciarla
        SistemAreas.ventanaMapa = this;
        // Llamamos a la creación de los paneles
        SistemAreas.controlPanel(configArea);
        SistemAreas.AreasPanel();
        PanelFondo();
    }
    
    private void PanelFondo(){
        // Creamos la ventana
        setTitle("Monitoreo");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        // Establecemos imagen de fondo.
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon((new ImageIcon(menu.imagenFondo)).getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(null);
        fondo.setBorder(new EmptyBorder(10,0,10,10)); //Establecemos margenes en el fondo.
        this.add(fondo);
        
        PanelArea();
        PanelConfigArea();
    }
    
    private void PanelArea(){
        // Agregamos el panel de mapas
        fondo.add(SistemAreas.panelMapas);
        // Le agregamos sus componentes
        SistemAreas.MapsPanel();
        
        // Creamos un scroll para el mapa de areas
        JScrollPane scrollArea = new JScrollPane();
        scrollArea.setBorder(null);
        scrollArea.setBounds(10,40,600,510);
        fondo.add(scrollArea);
        
        // Le establecemos al scroll el panel de la clase sistemAreasMaps
        scrollArea.setViewportView(SistemAreas.panelAreas);
    }
    private void PanelConfigArea(){
        // Creamos el panel de configuración
        configArea.setLayout(null);
        configArea.setBackground(Color.decode(menu.colorPanelMedio));
        configArea.setBorder(new EmptyBorder(10,10,10,10));
        fondo.add(configArea);
        configArea.setBounds(623,10,350,540);
        
        //Creamos el boton para volver
        JButton botonVolver = new JButton();
        botonVolver.setBounds(290, 10, 50, 50);
        botonVolver.setFocusPainted(false);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setBackground(Color.decode(menu.colorBotonOscuro));
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        configArea.add(botonVolver);
        
        // Metodo para volver al menu
        ActionListener irMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventanaMenu = new Menu();
                ventanaMenu.RecargarColores();
                ventanaMenu.notifiDetect();
                ventanaMenu.setVisible(true);
                dispose();
            }
        };
        botonVolver.addActionListener(irMenu);
        
        //Creamos el botón para activar la detección de movimiento
        JButton botonDeteccion = new JButton();
        botonDeteccion.setBounds(290, 70, 50, 50);
        botonDeteccion.setFocusPainted(false);
        
        botonDeteccion.setBackground(Color.decode(menu.colorBotonOscuro));
        botonDeteccion.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/nover.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        botonDeteccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Menu.deteccionManager == false){
                    Menu.deteccionManager = true;
                    Menu.Deteccion();
                    CameraManager.cerrarCamaras();
                    CameraManager.cargarCamaras();
                    CameraManager.detectarMovimiento(true, "Videos");
                    botonDeteccion.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/ver.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                }else{
                    Menu.deteccionManager = false;
                    Menu.Deteccion();
                    CameraManager.cerrarCamaras();
                    CameraManager.detectarMovimiento(false, "Videos");
                    botonDeteccion.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/nover.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                }
            }
        });
        configArea.add(botonDeteccion);
        
        //Creamos el botón para ver las grabaciones de las camaras
        JButton botonGrabaciones = new JButton();
        botonGrabaciones.setBounds(290, 130, 50, 50);
        botonGrabaciones.setFocusPainted(false);
        
        botonGrabaciones.setBackground(Color.decode(menu.colorBotonOscuro));
        botonGrabaciones.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/grabaciones.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        botonGrabaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Grabaciones ventanaGrab = new Grabaciones();
                ventanaGrab.ventanaAnterior = Monitoreo.this;
                ventanaGrab.setVisible(true);
                setVisible(false);
            }
        });
        configArea.add(botonGrabaciones);
        
        
        
        // Imagen del area
        JLabel imagen = new JLabel();
        imagen.setOpaque(true);
        imagen.setBounds(10, 10, 200, 200);
        configArea.add(imagen);
        
        // Decoración
        JLabel textoNombre = new JLabel("Nombre:");
        textoNombre.setForeground(Color.white);
        textoNombre.setBounds(10, 220, 340, 20);
        configArea.add(textoNombre);
        
        // Decoración
        JLabel marco1 = new JLabel();
        marco1.setBackground(Color.decode(menu.colorPanelClaro));
        marco1.setLayout(new BoxLayout(marco1, BoxLayout.X_AXIS));
        marco1.setBorder(new EmptyBorder(0, 10, 0, 10));
        marco1.setOpaque(true);
        marco1.setBounds(10, 245, 330, 40);
        configArea.add(marco1);
        
        // Nombre del area
        JLabel titulo = new JLabel("SELECCIONE UN AREA");
        titulo.setForeground(Color.white);
        marco1.add(titulo);
        
        // Decoración
        JLabel textoDescripcion = new JLabel("Descripción:");
        textoDescripcion.setForeground(Color.white);
        textoDescripcion.setBounds(10, 295, 340, 20);
        configArea.add(textoDescripcion);
        
        // Decoración
        JLabel marco2 = new JLabel();
        marco2.setBackground(Color.decode(menu.colorPanelClaro));
        marco2.setLayout(new BoxLayout(marco2, BoxLayout.X_AXIS));
        marco2.setOpaque(true);
        marco2.setBounds(10, 320, 330, 170);
        configArea.add(marco2);
        
        // Descripción del area
        JTextArea descripcion = new JTextArea();
        descripcion.setMargin(new Insets(10, 10, 10, 10));
        descripcion.setForeground(Color.white);
        descripcion.setBackground(Color.decode(menu.colorPanelClaro));
        descripcion.setEditable(false);
        JScrollPane scrollDescript = new JScrollPane();
        scrollDescript.setBorder(null);
        scrollDescript.setViewportView(descripcion);
        marco2.add(scrollDescript);
        
        // Ejecutamos el metodo para enviar los campos de texto y el label de la imagen
        SistemAreas.extraerElementos(titulo, descripcion, imagen);
    }
}