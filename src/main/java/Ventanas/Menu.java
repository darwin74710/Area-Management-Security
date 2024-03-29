package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame{
    public static boolean deteccionManager = false;
    
    public JLabel fondo = new JLabel();
    
    JButton opcion3 = new JButton();
    public ImageIcon logoNotificaciones = new ImageIcon("Imagenes/Iconos/botonNotificaciones.png");
    
    public Menu(){
        PanelFondo();
        Deteccion();
    }
    
    private void PanelFondo(){
        
        //Creamos la ventana.
        setTitle("Menu");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel(new ImageIcon("Imagenes/fondo.png"));
        fondo.setLayout(null);
        fondo.setBorder(new EmptyBorder(10,10,10,10)); //Establecemos margenes en el fondo.
        this.add(fondo);
        
        ParteSuperior();
        ParteInferior();
    }
        
    private void ParteSuperior(){
        //Creamos un panel para el titulo y el boton volver.
        JPanel elementosSuperiores = new JPanel();
        elementosSuperiores.setLayout(new BoxLayout(elementosSuperiores, BoxLayout.X_AXIS));
        elementosSuperiores.setOpaque(false);
        elementosSuperiores.setBounds(0,0,970,100);
        fondo.add(elementosSuperiores);
        
        //Perfil con descripción.
        JPanel fondoPerfil = new JPanel();
        fondoPerfil.setBackground(Color.decode("#000a45"));
        fondoPerfil.setBounds(0,0,320,600);
        fondoPerfil.setLayout(null);
        fondo.add(fondoPerfil);
        
        JLabel imagenPerfil = new JLabel();
        //Le establecemos una imagen de perfil con un tamaño preestablecido.
        imagenPerfil.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        imagenPerfil.setBounds(55,10,200,200);
        fondoPerfil.add(imagenPerfil);
        
        
        elementosSuperiores.add(Box.createRigidArea(new Dimension(410,0)));
        //Creamos un titulo de bienvenida.
        JLabel texto1 = new JLabel("<html><body><center><p>Bienvenida Hormiguita</p></html>"); //se puede utilizar html.
        texto1.setFont(new Font("Constantia Bold",1,35));
        texto1.setForeground(Color.white); //Le establecemos un color con formato hexadecimal. //Centrar el texto
        elementosSuperiores.add(texto1);
        
        elementosSuperiores.add(Box.createRigidArea(new Dimension(50, 70)));
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBackground(Color.decode("#000e3c"));
        botonVolver.setMaximumSize(new Dimension(50,50));
        botonVolver.setPreferredSize(new Dimension(50, 50));
        botonVolver.setFocusPainted(false);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        elementosSuperiores.add(botonVolver);
        
        
        //Funciones botones.
        ActionListener irIngreso = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ingreso ventanaIngreso = new Ingreso();
                ventanaIngreso.setVisible(true);
                setVisible(false);
            }
        };
        botonVolver.addActionListener(irIngreso);
    }
    
    private void ParteInferior(){
        //Separamos un poco los botones del titulo.
        fondo.add(Box.createRigidArea(new Dimension(0,30)));
        
        //Creamos un panel para colocar las opciones de ventanas.
        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(null);
        botonesPanel.setBounds(150,100,970,500);
        fondo.add(botonesPanel);
        
        //Creamos los botones para ir a las diferentes ventanas.
        //MONITOREO
        JButton opcion1 = new JButton();
        opcion1.setBounds(265, 10, 170, 176);
        ImageIcon logoArea = new ImageIcon("Imagenes/Iconos/botonMonitoreo.png");
        opcion1.setIcon(new ImageIcon(logoArea.getImage().getScaledInstance(opcion1.getWidth(), opcion1.getHeight(), Image.SCALE_DEFAULT)));
        
        botonesPanel.add(opcion1);
        
        //INFORMACIÓN
        JButton opcion2 = new JButton();
        opcion2.setBounds(505, 10, 170, 176);
        ImageIcon logoManual = new ImageIcon("Imagenes/Iconos/botonManual.png");
        opcion2.setIcon(new ImageIcon(logoManual.getImage().getScaledInstance(opcion2.getWidth(), opcion2.getHeight(), Image.SCALE_DEFAULT)));
        
        botonesPanel.add(opcion2);
        
        //NOTIFICACIONES
        opcion3.setBounds(265, 230, 170, 176);
        notifiDetect();
        botonesPanel.add(opcion3);
        
        //CONFIGURACIONES
        JButton opcion4 = new JButton();
        opcion4.setBounds(505, 230, 170, 176);
        ImageIcon logoConfig = new ImageIcon("Imagenes/Iconos/botonConfig.png");
        opcion4.setIcon(new ImageIcon(logoConfig.getImage().getScaledInstance(opcion4.getWidth(), opcion4.getHeight(), Image.SCALE_DEFAULT)));
        
        botonesPanel.add(opcion4);
        
        //Funciones Botones.
        ActionListener irMonitoreo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Monitoreo ventanaMapa = new Monitoreo();
                ventanaMapa.setVisible(true);
                setVisible(false);
            }
        };
        opcion1.addActionListener(irMonitoreo);
        
        ActionListener irInfo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Info ventanaInfo = new Info();
                ventanaInfo.setVisible(true);
                setVisible(false);
            }
        };
        opcion2.addActionListener(irInfo);
        
        ActionListener irNotificaciones = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notificaciones ventanaNotificaciones = new Notificaciones();
                ventanaNotificaciones.setVisible(true);
                setVisible(false);
            }
        };
        opcion3.addActionListener(irNotificaciones);
        
        ActionListener irConfig = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuraciones ventanaConfig = new Configuraciones();
                ventanaConfig.setVisible(true);
                setVisible(false);
            }
        };
        opcion4.addActionListener(irConfig);
    }
    
    public static void Deteccion(){
        if (deteccionManager == true){
            System.out.println("SISTEMA ENCENDIDO");
        }else{
            System.out.println("SISTEMA APAGADO");
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
        
        if (listaDeArchivos != null && listaDeArchivos.length > 0) {
            opcion3.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/botonNotificacionesAnim.gif")).getImage().getScaledInstance(opcion3.getWidth(), opcion3.getHeight(), Image.SCALE_DEFAULT)));
        } else {
            opcion3.setIcon(new ImageIcon(logoNotificaciones.getImage().getScaledInstance(opcion3.getWidth(), opcion3.getHeight(), Image.SCALE_DEFAULT)));
            
        }
    }
}