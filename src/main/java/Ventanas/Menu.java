package Ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public JLabel fondo = new JLabel();
    
    public Menu(){
        PanelFondo();
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
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.Y_AXIS));
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
        fondo.add(elementosSuperiores);
        
        elementosSuperiores.add(Box.createRigidArea(new Dimension(80,0)));
        //Creamos un titulo de bienvenida.
        JLabel texto1 = new JLabel("<html><body><center><p>AREA MANAGEMENT SECURITY</p></html>"); //se puede utilizar html.
        texto1.setFont(new Font("Constantia Bold",1,70));
        texto1.setForeground(Color.decode("#5c5f77")); //Le establecemos un color con formato hexadecimal. //Centrar el texto
        elementosSuperiores.add(texto1);
        
        elementosSuperiores.add(Box.createRigidArea(new Dimension(50, 200)));
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setOpaque(false);
        botonVolver.setFocusPainted(false);
        botonVolver.setBorderPainted(false);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setBackground(Color.decode("#000e3c"));
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        elementosSuperiores.add(botonVolver);
        
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
        fondo.add(botonesPanel);
        
        //Creamos los botones para ir a las diferentes ventanas.
        //SCROLL
        JButton opcion1 = new JButton();
        opcion1.setBounds(5, 10, 233, 241);
        ImageIcon logoScroll = new ImageIcon("Imagenes/Iconos/botonScrolls.png");
        opcion1.setIcon(new ImageIcon(logoScroll.getImage().getScaledInstance(opcion1.getWidth(), opcion1.getHeight(), Image.SCALE_DEFAULT)));
        
        ActionListener irScroll = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZonasScroll ventanaScroll = new ZonasScroll();
                ventanaScroll.setVisible(true);
                setVisible(false);
            }
        };
        opcion1.addActionListener(irScroll);
        botonesPanel.add(opcion1);
        
        //MAPA
        JButton opcion2 = new JButton();
        opcion2.setBounds(245, 10, 233, 241);
        ImageIcon logoArea = new ImageIcon("Imagenes/Iconos/botonAreas.png");
        opcion2.setIcon(new ImageIcon(logoArea.getImage().getScaledInstance(opcion2.getWidth(), opcion2.getHeight(), Image.SCALE_DEFAULT)));
        
        ActionListener irMapa = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZonasMapa ventanaMapa = new ZonasMapa();
                ventanaMapa.setVisible(true);
                setVisible(false);
            }
        };
        opcion2.addActionListener(irMapa);
        botonesPanel.add(opcion2);
        
        //MANUAL
        JButton opcion3 = new JButton();
        opcion3.setBounds(485, 10, 233, 241);
        ImageIcon logoManual = new ImageIcon("Imagenes/Iconos/botonManual.png");
        opcion3.setIcon(new ImageIcon(logoManual.getImage().getScaledInstance(opcion3.getWidth(), opcion3.getHeight(), Image.SCALE_DEFAULT)));
        
        ActionListener irManual = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manual ventanaManual = new Manual();
                ventanaManual.setVisible(true);
                setVisible(false);
            }
        };
        opcion3.addActionListener(irManual);
        botonesPanel.add(opcion3);
        
        //CONFIGURACIONES
        JButton opcion4 = new JButton();
        opcion4.setBounds(725, 10, 233, 241);
        ImageIcon logoConfig = new ImageIcon("Imagenes/Iconos/botonConfig.png");
        opcion4.setIcon(new ImageIcon(logoConfig.getImage().getScaledInstance(opcion4.getWidth(), opcion4.getHeight(), Image.SCALE_DEFAULT)));
        
        ActionListener irConfig = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuraciones ventanaConfig = new Configuraciones();
                ventanaConfig.setVisible(true);
                setVisible(false);
            }
        };
        opcion4.addActionListener(irConfig);
        botonesPanel.add(opcion4);
    }

}
