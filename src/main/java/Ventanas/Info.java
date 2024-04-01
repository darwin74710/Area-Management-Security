package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Info extends JFrame{
    Menu menu;
    
    public JLabel fondo = new JLabel();
    
    public Info(){
        PanelFondo();
        ElementosManual();
    }
    
    private void PanelFondo(){
        
        //Creamos la ventana.
        setTitle("Información");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon((new ImageIcon(menu.imagenFondo)).getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.Y_AXIS));
        fondo.setBorder(new EmptyBorder(10,10,10,10));
        this.add(fondo);
    }
    
    private void ElementosManual(){
        JPanel elementosSuperiores = new JPanel();
        elementosSuperiores.setLayout(new BoxLayout(elementosSuperiores, BoxLayout.X_AXIS));
        elementosSuperiores.setOpaque(false);
        fondo.add(elementosSuperiores);
        
        elementosSuperiores.add(Box.createRigidArea(new Dimension(900, 70)));
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBackground(Color.decode(menu.colorBotonOscuro));
        botonVolver.setMaximumSize(new Dimension(50,50));
        botonVolver.setPreferredSize(new Dimension(50, 50));
        botonVolver.setFocusPainted(false);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        elementosSuperiores.add(botonVolver);

        //Funciones botones.
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
        
        JScrollPane scrollArea = new JScrollPane();
        scrollArea.setOpaque(false);
        fondo.add(scrollArea);
        
        JPanel panelManuel = new JPanel();
        panelManuel.setPreferredSize(new Dimension(940,500));
        panelManuel.setBackground(Color.decode(menu.colorPanelOscuro));
        panelManuel.setLayout(null);
        
        scrollArea.setViewportView(panelManuel);
        
        JPanel botones = new JPanel();
        botones.setOpaque(false);
        fondo.add(botones);
        
        JButton atras = new JButton();
        atras.setPreferredSize(new Dimension(70, 40));
        atras.setMaximumSize(new Dimension(70, 40));
        atras.setBackground(Color.decode(menu.colorBotonOscuro));
        atras.setFocusPainted(false); //Quitamos las lineas de focus.
        
        ImageIcon logoAtras = new ImageIcon("Imagenes/Iconos/flechaIzquierda.png"); 
        atras.setIcon(new ImageIcon(logoAtras.getImage().getScaledInstance(60, 30, Image.SCALE_SMOOTH)));
        botones.add(atras);
        
        botones.add(Box.createRigidArea(new Dimension(750, 40)));
        
        JButton siguiente = new JButton();
        siguiente.setPreferredSize(new Dimension(70, 40));
        siguiente.setMaximumSize(new Dimension(70, 40));
        siguiente.setBackground(Color.decode(menu.colorBotonOscuro));
        siguiente.setFocusPainted(false); //Quitamos las lineas de focus.
        
        ImageIcon logoSiguiente = new ImageIcon("Imagenes/Iconos/flechaDerecha.png"); 
        siguiente.setIcon(new ImageIcon(logoSiguiente.getImage().getScaledInstance(60, 30, Image.SCALE_SMOOTH)));
        botones.add(siguiente);
    }
}