package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class ZonasMapa extends JFrame{
    public JLabel fondo = new JLabel();
    
    public ZonasMapa(){
        PanelFondo();
    }
    
    private void PanelFondo(){
        
        //Creamos la ventana.
        setTitle("Mapas");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel(new ImageIcon("Imagenes/fondo.png"));
        fondo.setLayout(null);
        fondo.setBorder(new EmptyBorder(10,10,10,10)); //Establecemos margenes en el fondo.
        this.add(fondo);
        
        PanelArea();
        PanelConfigArea();
    }
    
    private void PanelArea(){
        JScrollPane scrollArea = new JScrollPane();
        scrollArea.setOpaque(false);
        scrollArea.setBounds(10,10,600,540);
        fondo.add(scrollArea);
        
        JPanel panelArea = new JPanel();
        panelArea.setBackground(Color.decode("#121a2d"));
        panelArea.setLayout(null);
        panelArea.setPreferredSize(new Dimension(1000,1000));
        scrollArea.setViewportView(panelArea);
        
        
    }
    private void PanelConfigArea(){
        JPanel configArea = new JPanel();
        configArea.setBackground(Color.decode("#000a45"));
        configArea.setBorder(new EmptyBorder(10,10,10,10));
        configArea.setLayout(null);
        fondo.add(configArea);
        configArea.setBounds(623,10,350,540);

        ConfigSuperior(configArea);
        ConfigPosicion(configArea);
        ConfigEscalado(configArea);
        CRUD(configArea);
    }
    
    private void ConfigSuperior(JPanel configArea){
        JLabel textArea = new JLabel("AREA");
        textArea.setBounds(10,0,400,100);
        textArea.setForeground(Color.decode("#85add5"));
        textArea.setFont(new Font("Constantia Bold",1,60));
        configArea.add(textArea);
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBounds(290,10,50,50);
        botonVolver.setOpaque(false);
        botonVolver.setFocusPainted(false);
        botonVolver.setBorderPainted(false);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setBackground(Color.decode("#000e3c"));
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        configArea.add(botonVolver);
        
        ActionListener irIngreso = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventanaMenu = new Menu();
                ventanaMenu.setVisible(true);
                setVisible(false);
            }
        };
        botonVolver.addActionListener(irIngreso);
        
        
    }
    
    private void ConfigPosicion(JPanel configArea){
        JLabel textPosicion = new JLabel("POSICIÓN");
        textPosicion.setBounds(10,90,160,50);
        textPosicion.setFont(new Font("Constantia Bold",1,30));
        textPosicion.setForeground(Color.decode("#5c5f77"));
        configArea.add(textPosicion);    
        
        JLabel letraX = new JLabel("X");
        letraX.setFont(new Font("Arial",1,20));
        letraX.setForeground(Color.white);
        letraX.setBounds(10,140,100,20);
        configArea.add(letraX);
        
        JTextField posicionX = new JTextField();
        posicionX.setBounds(30,140,100,20);
        configArea.add(posicionX);
        
        JLabel letraY = new JLabel("Y");
        letraY.setFont(new Font("Arial",1,20));
        letraY.setForeground(Color.white);
        letraY.setBounds(150,140,100,20);
        configArea.add(letraY);
        
        JTextField posicionY = new JTextField();
        posicionY.setBounds(170,140,100,20);
        configArea.add(posicionY);
    }
    
    private void ConfigEscalado(JPanel configArea){
        JLabel textEscalado = new JLabel("ESCALADO");
        textEscalado.setBounds(10,180,160,50);
        textEscalado.setFont(new Font("Constantia Bold",1,30));
        textEscalado.setForeground(Color.decode("#5c5f77"));
        configArea.add(textEscalado);
        
        JLabel letraX = new JLabel("X");
        letraX.setFont(new Font("Arial",1,20));
        letraX.setForeground(Color.white);
        letraX.setBounds(10,230,100,20);
        configArea.add(letraX);
        
        JTextField escaladoX = new JTextField();
        escaladoX.setBounds(30,230,100,20);
        configArea.add(escaladoX);
        
        JLabel letraY = new JLabel("Y");
        letraY.setFont(new Font("Arial",1,20));
        letraY.setForeground(Color.white);
        letraY.setBounds(150,230,100,20);
        configArea.add(letraY);
        
        JTextField escaladoY = new JTextField();
        escaladoY.setBounds(170,230,100,20);
        configArea.add(escaladoY);
    }
    
    private void CRUD(JPanel configArea){
        //Boton Agregar.
        JButton botonAgregar = new JButton("AGREGAR");
        botonAgregar.setBackground(Color.decode("#85add5"));
        botonAgregar.setFocusPainted(false); //Quitamos las lineas de focus.
        botonAgregar.setBorderPainted(false); //Quitamos los bordes del botón.
        botonAgregar.setBounds(15,510,100,20);
        
        botonAgregar.setFont(new Font("Constantia Bold",1,12));
        botonAgregar.setForeground(Color.decode("#5c5e6b"));
        configArea.add(botonAgregar);
        
        //Boton Editar.
        JButton botonEditar = new JButton("EDITAR");
        botonEditar.setBackground(Color.decode("#85add5"));
        botonEditar.setFocusPainted(false); //Quitamos las lineas de focus.
        botonEditar.setBorderPainted(false); //Quitamos los bordes del botón.
        botonEditar.setBounds(125,510,100,20);
        
        botonEditar.setFont(new Font("Constantia Bold",1,12));
        botonEditar.setForeground(Color.decode("#5c5e6b"));
        configArea.add(botonEditar);
        
        //Boton Eliminar.
        JButton botonEliminar = new JButton("ELIMINAR");
        botonEliminar.setBackground(Color.decode("#85add5"));
        botonEliminar.setFocusPainted(false); //Quitamos las lineas de focus.
        botonEliminar.setBorderPainted(false); //Quitamos los bordes del botón.
        botonEliminar.setBounds(235,510,100,20);
        
        botonEliminar.setFont(new Font("Constantia Bold",1,12));
        botonEliminar.setForeground(Color.decode("#5c5e6b"));
        configArea.add(botonEliminar);
    }

}
