package Ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Configuraciones extends JFrame{
    public JLabel fondo = new JLabel();
    public JFrame ventanaAnterior = new JFrame();
    
    public Configuraciones(){
        PanelFondo();
    }
    
    private void PanelFondo(){
        //Creamos la ventana.
        setTitle("Configuraciones");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        fondo = new JLabel(new ImageIcon("Imagenes/fondo.png"));
        fondo.setLayout(null);
        
        //Establecemos imagen de fondo.
        JScrollPane scrollFondo = new JScrollPane(fondo);
        scrollFondo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollFondo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFondo.setOpaque(true);
        scrollFondo.setBorder(null);
        this.add(scrollFondo);
        
        
        Elementos();
    }
    private void Elementos(){
        JPanel elementos = new JPanel();
        elementos.setLayout(null);
        elementos.setBounds(10,10,965,540);
        elementos.setOpaque(false);
        fondo.add(elementos);
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBounds(890, 20, 50, 50);
        botonVolver.setFocusPainted(false);

        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setBackground(Color.decode("#000e3c"));
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        elementos.add(botonVolver);
        
        JLabel tituloRegistro = new JLabel("Configuraciones Usuario");
        tituloRegistro.setForeground(Color.white);
        tituloRegistro.setFont(new Font("Arial",1,40));
        tituloRegistro.setBounds(10, 15, 500, 50);
        elementos.add(tituloRegistro);
        
        // Metodo para volver al menu
        ActionListener irCamaras = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
                dispose();
            }
        };
        botonVolver.addActionListener(irCamaras);
        
        JPanel configUsuarui = new JPanel();
        configUsuarui.setBackground(Color.decode("#000a45"));
        configUsuarui.setLayout(null);
        configUsuarui.setBounds(10, 80, 925, 470);
        elementos.add(configUsuarui);
    }
}