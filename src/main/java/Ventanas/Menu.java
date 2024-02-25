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
        
        //Creamos un titulo de bienvenida.
        JLabel texto1 = new JLabel("AREA MANAGEMENT SECURITY");
        texto1.setFont(new Font("Constantia Bold",1,50));
        texto1.setForeground(Color.decode("#5c5f77")); //Le establecemos un color con formato hexadecimal. //Centrar el texto
        elementosSuperiores.add(texto1);
        
        elementosSuperiores.add(Box.createRigidArea(new Dimension(120, 0)));
        
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
        //Creamos un panel para colocar las opciones de ventanas.
        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(new GridLayout(2,2,10,10));
        fondo.add(botonesPanel);
        
        //Creamos los botones para ir a las diferentes ventanas.
        //SCROLL
        JButton opcion1 = new JButton("ZONAS TIPO SCROLL");
        
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
        JButton opcion2 = new JButton("ZONAS TIPO MAPA");
        
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
        JButton opcion3 = new JButton("MANUAL");
        
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
        JButton opcion4 = new JButton("CONFIGURACIONES");
        
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
