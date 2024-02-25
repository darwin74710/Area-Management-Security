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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Ingreso extends JFrame{
    
    public JLabel fondo = new JLabel();
    
    public boolean activContra = false;
    
    public Ingreso(){
        PanelFondo();
    }
    
    private void PanelFondo(){
        
        //Creamos la ventana.
        setTitle("Ingreso de usuario");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel(new ImageIcon("Imagenes/fondo.png"));
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.X_AXIS)); //Le añadimos un layout a la imagen de fondo.
        this.add(fondo);
        
        //Agregamos un espacio en la distribución de izquierda a derecha.
        fondo.add(Box.createRigidArea(new Dimension(10,0)));
        
        // Añadimos la imagen izquierda de la ventana.
        ImageIcon imagen = new ImageIcon("Imagenes/LogoInicio.png"); //Creamos la ruta de una imagen.
        JLabel izquierda = new JLabel();//Creamos un layout de imagen.
        izquierda.setSize(400,400);
        izquierda.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(izquierda.getWidth(), izquierda.getHeight(), Image.SCALE_SMOOTH))); //Ajustamos el tamaño de la imagen.
        fondo.add(izquierda);
        
        ladoDerecho();
    }
    
    private void ladoDerecho(){
        //Creamos un panel para el lado derecho.
        JPanel derecha = new JPanel();
        derecha.setSize(600,600);
        derecha.setOpaque(false); //Hacemos que el color del panel no se vea.
        derecha.setLayout(new BoxLayout(derecha, BoxLayout.Y_AXIS)); //Le establecemos un layout al panel de forma vertical.
        
        //Agregamos un espacio.
        derecha.add(Box.createRigidArea(new Dimension(0, 35)));
        
        //Creamos un titulo de bienvenida.
        JLabel texto1 = new JLabel("BIENVENIDO/A");
        texto1.setFont(new Font("Constantia Bold",1,70));
        texto1.setForeground(Color.decode("#5c5f77")); //Le establecemos un color con formato hexadecimal.
        texto1.setAlignmentX(Component.CENTER_ALIGNMENT); //Centrar el texto
        derecha.add(texto1);
        
        //Creamos descripción de ventana.
        JLabel texto2 = new JLabel("Ingresa con tu usuario y contraseña para continuar.");
        texto2.setFont(new Font("Constantia Bold",1,22));
        texto2.setForeground(Color.decode("#4f526a"));
        texto2.setAlignmentX(Component.CENTER_ALIGNMENT); //Centrar el texto
        derecha.add(texto2);
        
        //Creamos un espacio.
        derecha.add(Box.createRigidArea(new Dimension(0, 20)));
        
        //Creamos el titulo de usuario.
        JLabel texto3 = new JLabel("Usuario");
        texto3.setBounds(50, 0, 300, 40);
        texto3.setFont(new Font("Constantia Bold",1,50));
        texto3.setForeground(Color.decode("#5c5f77"));
        texto3.setAlignmentX(Component.CENTER_ALIGNMENT);
        derecha.add(texto3);
        
        //Se crea un panel para poder redimensionar el campo de texto.
        JPanel campoTextoUsuario = new JPanel();
        campoTextoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoTextoUsuario.setOpaque(false);
        campoTextoUsuario.setLayout(null);
        campoTextoUsuario.setPreferredSize(new Dimension(600, 70));
        //Se crea el campo de texto.
        JTextField textoUsuario = new JTextField();
        textoUsuario.setBackground(Color.decode("#373d61"));
        textoUsuario.setForeground(Color.white);
        textoUsuario.setFont(new Font("Arial",1,40));
        textoUsuario.setBounds(80, 0, 400, 55);
        
        campoTextoUsuario.add(textoUsuario);
        derecha.add(campoTextoUsuario);
        
        //Se agrega un espacio.
        derecha.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JLabel texto4 = new JLabel("Contraseña");
        texto4.setBounds(50, 0, 300, 40);
        texto4.setFont(new Font("Constantia Bold",1,50));
        texto4.setForeground(Color.decode("#5c5f77"));
        texto4.setAlignmentX(Component.CENTER_ALIGNMENT);
        derecha.add(texto4);
        
        //Se crea un panel para poder redimensionar el campo de texto.
        JPanel campoTextoContraseña = new JPanel();
        campoTextoContraseña.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoTextoContraseña.setOpaque(false);
        campoTextoContraseña.setLayout(null);
        campoTextoContraseña.setPreferredSize(new Dimension(600, 70));
        //Se crea el campo de texto.
        JPasswordField textoContraseña = new JPasswordField(); //Campo de texto tipo contraseña.
        textoContraseña.setEchoChar((char) 0); //Desactivar la vista de contraseña.
        textoContraseña.setEchoChar('*'); //Activar la vista de contraseña.
        textoContraseña.setBackground(Color.decode("#373d61"));
        textoContraseña.setForeground(Color.white);
        textoContraseña.setFont(new Font("Arial",1,40));
        textoContraseña.setBounds(80, 0, 400, 55);
        
        campoTextoContraseña.add(textoContraseña);
        
        //Creamos boton para ver y no ver contraseña.
        JButton botonVerContra = new JButton();
        botonVerContra.setBounds(490, 8, 40, 40);
        botonVerContra.setOpaque(false);
        botonVerContra.setFocusPainted(false);
        botonVerContra.setBorderPainted(false);
        
        ImageIcon logoVer = new ImageIcon("Imagenes/Iconos/ver.png");
        ImageIcon logoNoVer = new ImageIcon("Imagenes/Iconos/nover.png");
        botonVerContra.setBackground(Color.decode("#000e3c"));
        botonVerContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        campoTextoContraseña.add(botonVerContra);
        
        ActionListener irIngreso = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activContra == false){
                    textoContraseña.setEchoChar((char) 0);
                    botonVerContra.setIcon(new ImageIcon(logoVer.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                    activContra = true;
                }else{
                    textoContraseña.setEchoChar('*');
                    botonVerContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                    activContra = false;
                }
                
            }
        };
        botonVerContra.addActionListener(irIngreso);
        
        derecha.add(campoTextoContraseña);
        
        //Creamos un panel para añadir botones de izquierda a derecha.
        JPanel botonesDerecha = new JPanel();
        botonesDerecha.setOpaque(false);
        botonesDerecha.setLayout(new BoxLayout(botonesDerecha, BoxLayout.X_AXIS));
        
        //Creamos los botones.
        JButton ingreso = new JButton("INGRESAR");
        ingreso.setBackground(Color.decode("#000e3c"));
        ingreso.setFocusPainted(false); //Quitamos las lineas de focus.
        ingreso.setBorderPainted(false); //Quitamos los bordes del botón.
        
        ingreso.setFont(new Font("Constantia Bold",1,28));
        ingreso.setForeground(Color.decode("#5c5e6b"));
        botonesDerecha.add(ingreso);
        
        //Creamos un espacio entre los botones.
        botonesDerecha.add(Box.createRigidArea(new Dimension(10,0)));
        
        JButton registrar = new JButton("REGISTRARSE");
        registrar.setBackground(Color.decode("#000e3c"));
        registrar.setFocusPainted(false);
        registrar.setBorderPainted(false);
        
        registrar.setFont(new Font("Constantia Bold",1,28));
        registrar.setForeground(Color.decode("#5c5e6b"));
        botonesDerecha.add(registrar);
        
        //Separamos los botones de los bordes.
        derecha.add(Box.createRigidArea(new Dimension(0, 30)));
        derecha.add(botonesDerecha);
        derecha.add(Box.createRigidArea(new Dimension(0, 30)));
        
        fondo.add(derecha);
        //Agregamos un espacio al final.
        fondo.add(Box.createRigidArea(new Dimension(10,0)));
        
        //Creamos las funciones de los botones.
        ActionListener irMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventanaMenu = new Menu();
                ventanaMenu.setVisible(true);
                setVisible(false);
            }
        };
        ingreso.addActionListener(irMenu);
        
        ActionListener irRegistrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar ventanaRegistrar = new Registrar();
                ventanaRegistrar.setVisible(true);
                dispose();
            }
        };
        registrar.addActionListener(irRegistrar);
    }
}
