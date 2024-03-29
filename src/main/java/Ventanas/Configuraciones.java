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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Configuraciones extends JFrame{
    public JLabel fondo = new JLabel();
    
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
        
        //Establecemos imagen de fondo.
        fondo = new JLabel(new ImageIcon("Imagenes/fondo.png"));
        this.add(fondo);
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
        
        elementosSuperiores.add(Box.createRigidArea(new Dimension(80,200)));
        //Creamos un titulo de bienvenida.
        JLabel texto1 = new JLabel("CONFIGURACIONES"); //se puede utilizar html.
        texto1.setFont(new Font("Constantia Bold",1,70));
        texto1.setForeground(Color.decode("#5c5f77")); //Le establecemos un color con formato hexadecimal. //Centrar el texto
        elementosSuperiores.add(texto1);
        
        elementosSuperiores.add(Box.createRigidArea(new Dimension(50, 0)));
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setOpaque(false);
        botonVolver.setFocusPainted(false);
        botonVolver.setBorderPainted(false);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setBackground(Color.decode("#000e3c"));
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        elementosSuperiores.add(botonVolver);
        
        ActionListener irMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventanaMenu = new Menu();
                ventanaMenu.setVisible(true);
                setVisible(false);
            }
        };
        botonVolver.addActionListener(irMenu);
    }
        
        
    private void ParteInferior(){
       
        JPanel elementosInferiores = new JPanel();
        elementosInferiores.setBackground(Color.red);
        elementosInferiores.setOpaque(false);
        elementosInferiores.setLayout(new BoxLayout(elementosInferiores, BoxLayout.X_AXIS));
        
        fondo.add(elementosInferiores);

        
        
        JPanel izquierdo = new JPanel();
        izquierdo.setLayout(null);
        izquierdo.setOpaque(false);
        
        JLabel texto1 = new JLabel("Configuración de usuario");
        texto1.setBounds(0, 0, 190, 50);
        texto1.setFont(new Font("Constantia Bold",1,20));
        texto1.setForeground(Color.decode("#5c5f77")); //Le establecemos un color con formato hexadecimal.
        texto1.setAlignmentX(Component.CENTER_ALIGNMENT); //Centrar el texto
        izquierdo.add(texto1);
        
        String [] opciones = {"Cambiar nombre de perfil", "Cambiar contraseña", "Permisos del usuario"
                 ,"Cambiar correo enlazado", "Eliminar cuenta de usuario"};
                
        JComboBox ListaDesplegable = new JComboBox(opciones);
        ListaDesplegable.setBounds(0, 100, 160, 50);
        
 
        
        izquierdo.add(ListaDesplegable);
        
        elementosInferiores.add(izquierdo);
        
        
        elementosInferiores.add(Box.createRigidArea(new Dimension(10, 0)));
        
        
        JPanel derecho = new JPanel();
        derecho.setLayout(null);
        derecho.setOpaque(false);
        
        JLabel texto2 = new JLabel("Configuración de aplicación");
        
        texto2.setFont(new Font("Constantia Bold",1,20));
        texto2.setBounds(0, 0, 190, 50);
        texto2.setForeground(Color.decode("#5c5f77")); //Le establecemos un color con formato hexadecimal.
        texto2.setAlignmentX(Component.CENTER_ALIGNMENT); //Centrar el texto
        derecho.add(texto2);
        
        String [] opciones2 = {"Color de fondo", "Tipo de letra", "Eliminar datos guardados"};
                
        JComboBox ListaDesplegable1 = new JComboBox(opciones2);
        ListaDesplegable1.setBounds(0, 100, 160, 50);
                
                
        derecho.add(ListaDesplegable1);
        
        
        
        elementosInferiores.add(derecho);
        
  
        

    }
    

    
    
}