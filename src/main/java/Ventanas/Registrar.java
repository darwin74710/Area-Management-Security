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
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Registrar extends JFrame {

    public JLabel fondo = new JLabel();

    public Registrar() {
        PanelFondo();
    }//FIN CONSTRUCTOR

    private void PanelFondo() {

        //Creamos la ventana.
        setTitle("Registrar Usuario");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        //Establecemos imagen de fondo.
        fondo = new JLabel(new ImageIcon("Imagenes/fondo.png"));
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.Y_AXIS));
        fondo.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(fondo);

        //agregamos los constructores
        Titulo();
        Formulario();
        fondo.add(Box.createRigidArea(new Dimension(0, 15)));
        Botones();
    }//FIN METODO

    private void Titulo() {
        //se crea el titulo
        JPanel titPanel = new JPanel();
        titPanel.setLayout(new BoxLayout(titPanel, BoxLayout.X_AXIS));
        titPanel.setOpaque(false);

        fondo.add(titPanel);

        JLabel titulo = new JLabel("Registrar Usuarios");
        titulo.setFont(new Font("Constantia Bold", 1, 30));
        titulo.setForeground(Color.white);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titPanel.add(titulo);

        titPanel.add(Box.createRigidArea(new Dimension(450, 70)));

        JButton botonVolver = new JButton();
        botonVolver.setOpaque(false);
        botonVolver.setFocusPainted(false);
        botonVolver.setBorderPainted(false);

        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setBackground(Color.decode("#000e3c"));
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        titPanel.add(botonVolver);

        ActionListener irIngreso = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ingreso ventanaIngreso = new Ingreso();
                ventanaIngreso.setVisible(true);
                setVisible(false);
            }
        };
        botonVolver.addActionListener(irIngreso);
    }//FIN METODO

    private void Formulario() {
        //se crea un panel para intrudicir el formulario
        JScrollPane ScrollForm = new JScrollPane();
        ScrollForm.setOpaque(false);
        ScrollForm.setMaximumSize(new Dimension(800, 400));
        fondo.add(ScrollForm);

        JPanel panForm = new JPanel();
        panForm.setBackground(Color.decode("#121a2d"));
        panForm.setLayout(null);
        panForm.setPreferredSize(new Dimension(700, 900));
        ScrollForm.setViewportView(panForm);

        //se llama el metodo 
        ElementosForm(panForm);

    }//FIN METODO

    private void ElementosForm(JPanel panForm) {

        // 1.1 -- Se crea el texto para solcitud de llenado de campo de texto. 
        JLabel textN1 = new JLabel("Primer Nombre(*)");
        textN1.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN1.setForeground(Color.white);
        textN1.setBounds(20, 10, 140, 30);
        panForm.add(textN1);

        // 2.1 -- Se crea caja de texto para introducir el campo solicitado
        JTextField cajText1 = new JTextField();
        cajText1.setBounds(20, 40, 140, 20);
        panForm.add(cajText1);

        //1.2
        JLabel textN2 = new JLabel("Segundo Nombre");
        textN2.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN2.setForeground(Color.white);
        textN2.setBounds(200, 10, 140, 30);
        panForm.add(textN2);

        //2.2
        JTextField cajText2 = new JTextField();
        cajText2.setBounds(200, 40, 140, 20);
        panForm.add(cajText2);

        //1.3
        JLabel textN3 = new JLabel("Primer Apellido(*)");
        textN3.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN3.setForeground(Color.white);
        textN3.setBounds(20, 100, 140, 30);
        panForm.add(textN3);

        //2.3
        JTextField cajText3 = new JTextField();
        cajText3.setBounds(20, 130, 140, 20);
        panForm.add(cajText3);

        //1.4
        JLabel textN4 = new JLabel("Segundo Apellido");
        textN4.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN4.setForeground(Color.white);
        textN4.setBounds(200, 100, 140, 30);
        panForm.add(textN4);

        //2.4
        JTextField cajText4 = new JTextField();
        cajText4.setBounds(200, 130, 140, 20);
        panForm.add(cajText4);

        //1.5
        JLabel textN5 = new JLabel("Cédula(*)");
        textN5.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN5.setForeground(Color.white);
        textN5.setBounds(20, 190, 140, 30);
        panForm.add(textN5);

        //2.5
        JTextField cajText5 = new JTextField();
        cajText5.setBounds(20, 220, 140, 20);
        panForm.add(cajText5);

        //1.6
        JLabel textN6 = new JLabel("Email(*)");
        textN6.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN6.setForeground(Color.white);
        textN6.setBounds(20, 280, 140, 30);
        panForm.add(textN6);

        //2.6
        JTextField cajText6 = new JTextField();
        cajText6.setBounds(20, 310, 140, 20);
        panForm.add(cajText6);

        //1.7
        JLabel textN7 = new JLabel("Password(*)");
        textN7.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN7.setForeground(Color.white);
        textN7.setBounds(20, 370, 140, 30);
        panForm.add(textN7);

        //2.7
        JTextField cajText7 = new JTextField();
        cajText7.setBounds(20, 400, 140, 20);
        panForm.add(cajText7);

        //1.8
        JLabel textN8 = new JLabel("Confirmar Password(*)");
        textN8.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN8.setForeground(Color.white);
        textN8.setBounds(20, 460, 170, 30);
        panForm.add(textN8);

        //2.8
        JTextField cajText8 = new JTextField();
        cajText8.setBounds(20, 490, 140, 20);
        panForm.add(cajText8);

        //1.9
        JLabel textN9 = new JLabel("Género(*)");
        textN9.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN9.setForeground(Color.white);
        textN9.setBounds(20, 550, 140, 30);
        panForm.add(textN9);

        //2.9
        //arreglo de objetos tipo String para asisgnarle al JComboBox
        String[] genero = {"Masculino", "Femenino", "No especificado"};

        //se crea el JComboBox
        JComboBox listaGenero = new JComboBox(genero);
        listaGenero.setBounds(20, 580, 140, 20);
        panForm.add(listaGenero);

        //1.10
        JLabel textN10 = new JLabel("Teléfono(*)");
        textN10.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN10.setForeground(Color.white);
        textN10.setBounds(20, 640, 140, 30);
        panForm.add(textN10);

        //2.10
        JTextField cajText10 = new JTextField();
        cajText10.setBounds(20, 670, 140, 20);
        panForm.add(cajText10);

        //1.11
        JLabel textN11 = new JLabel("<html><body><center><p>Cargar imagen"
                + " de perfil</p></html>");
        textN11.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        textN11.setForeground(Color.white);
        textN11.setBounds(20, 730, 140, 40);
        panForm.add(textN11);

        //2.11
        JButton botonCargar = new JButton("Cargar");
        botonCargar.setBackground(Color.decode("#85add5"));
        botonCargar.setBounds(20, 785, 140, 50);
        panForm.add(botonCargar);

        //linea que divide el panel
        JPanel panDiv = new JPanel();
        panDiv.setBounds(375, 10, 1, 880);
        panForm.add(panDiv);

        //División del panel para recuperación de la contraseña
        //-- Titulo --
        JPanel titPanel2 = new JPanel();
        titPanel2.setBounds(410, 10, 358, 40);
        titPanel2.setOpaque(false);
        panForm.add(titPanel2);

        JLabel textRePs = new JLabel("<html><body><center><p>Recuperar"
                + " Password</p></html>");
        textRePs.setFont(new Font("Constantia Bold", Font.BOLD, 20));
        textRePs.setForeground(Color.white);
        titPanel2.add(textRePs);

        // 1.1 Preguntas de recuperación
        JLabel preg1 = new JLabel("Primera pregunta(*)");
        preg1.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        preg1.setForeground(Color.white);
        preg1.setBounds(410, 70, 358, 30);
        panForm.add(preg1);

        // 2.1 caja de texto para agregar la pregunta
        JTextField cajTextP1 = new JTextField();
        cajTextP1.setBounds(410, 100, 358, 20);
        panForm.add(cajTextP1);

        // 3.1 Respuesta de recuperacion
        JLabel res1 = new JLabel("Primera Respuesta(*)");
        res1.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        res1.setForeground(Color.white);
        res1.setBounds(410, 160, 358, 30);
        panForm.add(res1);

        // 4.1 campo texto para respuesta
        JTextField cajTextR1 = new JTextField();
        cajTextR1.setBounds(410, 190, 358, 20);
        panForm.add(cajTextR1);

        //1.2
        JLabel preg2 = new JLabel("Segunda pregunta(*)");
        preg2.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        preg2.setForeground(Color.white);
        preg2.setBounds(410, 250, 358, 30);
        panForm.add(preg2);

        //2.2
        JTextField cajTextP2 = new JTextField();
        cajTextP2.setBounds(410, 280, 358, 20);
        panForm.add(cajTextP2);

        //3.2
        JLabel res2 = new JLabel("Segunda Respuesta(*)");
        res2.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        res2.setForeground(Color.white);
        res2.setBounds(410, 340, 358, 30);
        panForm.add(res2);

        //4.2
        JTextField cajTextR2 = new JTextField();
        cajTextR2.setBounds(410, 370, 358, 20);
        panForm.add(cajTextR2);
        
        //1.3
        JLabel preg3 = new JLabel("Tercera Pregunta(*)");
        preg3.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        preg3.setForeground(Color.white);
        preg3.setBounds(410, 430, 358, 30);
        panForm.add(preg3);
        
        //2.3
        JTextField cajTextP3 = new JTextField();
        cajTextP3.setBounds(410, 460, 358, 20);
        panForm.add(cajTextP3);
        
        //3.3
        JLabel res3 = new JLabel("Tercera Respuesta(*)");
        res3.setFont(new Font("Constantia Bold", Font.BOLD, 15));
        res3.setForeground(Color.white);
        res3.setBounds(410, 520, 358, 30);
        panForm.add(res3);
        
        //4.3
        JTextField cajTextR3 = new JTextField();
        cajTextR3.setBounds(410, 550, 358, 20);
        panForm.add(cajTextR3);
        
        
    }//FIN METODO

    private void Botones() {

        JPanel buttPanel = new JPanel();
        buttPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.X_AXIS));
        buttPanel.setOpaque(false);
        fondo.add(buttPanel);

        //-- Se crea Botón para agregar los usuarios -- 
        JButton botonAgregar = new JButton("Crear");
        botonAgregar.setBackground(Color.decode("#85add5"));
        botonAgregar.setFocusPainted(false);
        botonAgregar.setBorderPainted(false);
        buttPanel.add(botonAgregar);

        buttPanel.add(Box.createRigidArea(new Dimension(10, 0)));//generamos espacio entre los botones         

        //-- Se crea Boton para recuperar la contraseña --
        JButton botonRecuperar = new JButton("Recuperar");
        botonRecuperar.setBackground(Color.decode("#85add5"));
        botonRecuperar.setFocusPainted(false);
        botonRecuperar.setBorderPainted(false);
        buttPanel.add(botonRecuperar);

        buttPanel.add(Box.createRigidArea(new Dimension(10, 0)));//se genera espacio entre los botones

        //-- Se crea Botón para limpiar los campos de texto --
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBackground(Color.decode("#85add5"));
        botonLimpiar.setFocusPainted(false);
        botonLimpiar.setBorderPainted(false);
        buttPanel.add(botonLimpiar);

    }//FIN METODO
}//FIN CLASS
