package Ventanas;

import static Logica.CameraManager.cargarCamaras;
import Logica.botones;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Ingreso extends JFrame{
    Menu menu = new Menu();
    public JTextField textoUsuario;
    public JPasswordField textoContraseña;

    public JLabel fondo = new JLabel();

    JButton botonVerContra = new JButton();
    JButton ingreso = new JButton("INGRESAR");
    JButton recuperarContra = new JButton("RECUPERAR");
    
    public Ingreso() {
        menu.RecargarColores();
        PanelFondo();
        ActualizarIngreso();
        Distribucion();
        cargarCamaras();
    }
    
    private void PanelFondo() {
        //Creamos la ventana.
        setTitle("Ingreso de usuario");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        //Establecemos imagen de fondo.
        fondo = new JLabel();
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.X_AXIS)); //Le añadimos un layout a la imagen de fondo.
        this.add(fondo);
    }
    
    private void Distribucion() {
        //Agregamos un espacio en la distribución de izquierda a derecha.
        fondo.add(Box.createRigidArea(new Dimension(10, 0)));

        // Añadimos la imagen izquierda de la ventana.
        ImageIcon imagen = new ImageIcon("Imagenes/LogoInicio.png"); //Creamos la ruta de una imagen.
        JLabel izquierda = new JLabel();//Creamos un layout de imagen.
        izquierda.setSize(400, 400);
        izquierda.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(izquierda.getWidth(), izquierda.getHeight(), Image.SCALE_SMOOTH))); //Ajustamos el tamaño de la imagen.
        fondo.add(izquierda);

        ladoDerecho();
    }
    
    private void ladoDerecho() {
        //Creamos un panel para el lado derecho.
        JPanel derecha = new JPanel();
        derecha.setSize(600, 600);
        derecha.setOpaque(false); //Hacemos que el color del panel no se vea.
        derecha.setLayout(new BoxLayout(derecha, BoxLayout.Y_AXIS)); //Le establecemos un layout al panel de forma vertical.

        //Agregamos un espacio.
        derecha.add(Box.createRigidArea(new Dimension(0, 20)));

        //Creamos descripción de ventana.
        JLabel texto2 = new JLabel("<html><body><center><h1 style=\"font-size: 30px; margin-bottom: 0px\">AREA MANAGEMENT SECURITY</h1>"
                + "<p>Ingresa con tu usuario y contraseña para continuar.</p></html>");
        texto2.setFont(new Font("Constantia Bold", 1, 30));
        texto2.setForeground(Color.white);
        texto2.setAlignmentX(Component.CENTER_ALIGNMENT); //Centrar el texto
        derecha.add(texto2);

        //Creamos un espacio.
        derecha.add(Box.createRigidArea(new Dimension(0, 30)));

        //Creamos el titulo de usuario.
        JLabel texto3 = new JLabel("Usuario");
        texto3.setBounds(50, 0, 300, 40);
        texto3.setFont(new Font("Constantia Bold", 1, 30));
        texto3.setForeground(Color.white);
        texto3.setAlignmentX(Component.CENTER_ALIGNMENT);
        derecha.add(texto3);

        //Se crea un panel para poder redimensionar el campo de texto.
        JPanel campoTextoUsuario = new JPanel();
        campoTextoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoTextoUsuario.setOpaque(false);
        campoTextoUsuario.setLayout(null);
        campoTextoUsuario.setPreferredSize(new Dimension(600, 70));
        //Se crea el campo de texto.
        textoUsuario = new JTextField();
        textoUsuario.setFont(new Font("Arial", 1, 40));
        textoUsuario.setBounds(80, 0, 400, 55);

        campoTextoUsuario.add(textoUsuario);
        derecha.add(campoTextoUsuario);

        //Se agrega un espacio.
        derecha.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel texto4 = new JLabel("Contraseña");
        texto4.setBounds(50, 0, 300, 40);
        texto4.setFont(new Font("Constantia Bold", 1, 30));
        texto4.setForeground(Color.white);
        texto4.setAlignmentX(Component.CENTER_ALIGNMENT);
        derecha.add(texto4);

        //Se crea un panel para poder redimensionar el campo de texto.
        JPanel campoTextoContraseña = new JPanel();
        campoTextoContraseña.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoTextoContraseña.setOpaque(false);
        campoTextoContraseña.setLayout(null);
        campoTextoContraseña.setPreferredSize(new Dimension(600, 70));
        derecha.add(campoTextoContraseña);

        //Se crea el campo de texto.
        textoContraseña = new JPasswordField(); //Campo de texto tipo contraseña.
        textoContraseña.setEchoChar((char) 0); //Desactivar la vista de contraseña.
        textoContraseña.setEchoChar('*'); //Activar la vista de contraseña.
        textoContraseña.setFont(new Font("Arial", 1, 40));
        textoContraseña.setBounds(80, 0, 400, 55);

        campoTextoContraseña.add(textoContraseña);

        //Creamos boton para ver y no ver contraseña.
        botonVerContra.setBounds(490, 8, 40, 40);
        botonVerContra.setOpaque(false);
        botonVerContra.setFocusPainted(false);

        ImageIcon logoVer = new ImageIcon("Imagenes/Iconos/ver.png");
        ImageIcon logoNoVer = new ImageIcon("Imagenes/Iconos/nover.png");
        
        botonVerContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        campoTextoContraseña.add(botonVerContra);

        //Agregamos la logica al botón.
        botones.verContra(textoContraseña, botonVerContra, logoVer, logoNoVer);

        //Creamos un panel para añadir botones de izquierda a derecha.
        JPanel botonesDerecha = new JPanel();
        botonesDerecha.setOpaque(false);
        botonesDerecha.setLayout(new BoxLayout(botonesDerecha, BoxLayout.X_AXIS));

        //Creamos los botones.
        ingreso.setBackground(Color.decode(menu.colorBotonOscuro));
        ingreso.setFocusPainted(false); //Quitamos las lineas de focus.

        ingreso.setFont(new Font("Arial", 1, 25));
        ingreso.setForeground(Color.white);
        botonesDerecha.add(ingreso);

        //funcionalidad del boton ingresar
        ingreso.addActionListener((ActionEvent e) -> {
            btnIngresarActionPerformed(e);
        });

        //Creamos un espacio entre los botones.
        botonesDerecha.add(Box.createRigidArea(new Dimension(10, 0)));

        recuperarContra.setBackground(Color.decode(menu.colorBotonOscuro));
        recuperarContra.setFocusPainted(false);

        recuperarContra.setFont(new Font("Arial", 1, 25));
        recuperarContra.setForeground(Color.white);
        botonesDerecha.add(recuperarContra);
        
        recuperarContra.addActionListener((ActionEvent e) -> {
            btnRecuperarContra();
        });

        //Separamos los botones de los bordes.
        derecha.add(Box.createRigidArea(new Dimension(0, 30)));
        derecha.add(botonesDerecha);
        derecha.add(Box.createRigidArea(new Dimension(0, 30)));

        fondo.add(derecha);
        //Agregamos un espacio al final.
        fondo.add(Box.createRigidArea(new Dimension(10, 0)));
    }

    public void btnIngresarActionPerformed(ActionEvent e) {

        String usuario = null, contraseña = null;

        usuario = textoUsuario.getText();
        contraseña = String.valueOf(textoContraseña.getPassword());

        /*if (usuario.isEmpty() || usuario == null) {
            JOptionPane.showMessageDialog(this, "Ingrese un usuario valido");
            textoUsuario.requestFocusInWindow();
            return;
        }
         if (contraseña.isEmpty() || contraseña == null) {
            JOptionPane.showMessageDialog(this, "Ingrese una contraseña valida");
            textoContraseña.requestFocusInWindow();
            return;
        }*/
       
        if (usuario.equals("") && contraseña.equals("")) {

            Menu ventanaMenu = new Menu();
            ventanaMenu.setVisible(true);
            setVisible(false);

        } else {
            JOptionPane.showMessageDialog(this, "Usuario y contraseña no validos");
            System.out.println(usuario + contraseña);
            textoUsuario.setText("");
            textoContraseña.setText("");
            textoUsuario.requestFocusInWindow();
        }
    }//Fin metodo btnIngresar
    
    public void btnRecuperarContra(){
        
    }
    
    public void ActualizarIngreso(){
        fondo.setIcon(new ImageIcon((new ImageIcon(menu.imagenFondo)).getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
        botonVerContra.setBackground(Color.decode(menu.colorBotonOscuro));
        ingreso.setBackground(Color.decode(menu.colorBotonOscuro));
        
        fondo.revalidate();
        fondo.repaint();
    }
}
