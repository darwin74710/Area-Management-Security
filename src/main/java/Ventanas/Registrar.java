package Ventanas;

import Guardado.SaveUsuarios;
import Logica.AnimMenu;
import Extra.TextPrompt;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Registrar extends JFrame {

    Menu menu;
    public AnimMenu anim = new AnimMenu();
    SaveUsuarios guardado = new SaveUsuarios();
    
    TextPrompt textoCaja;

    public static String ruta;

    //variables para logica del boton ver contraseña
    ActionListener activar;
    boolean activContra = false;

    //Label para el fondo de la ventana
    public JPanel fondo = new JPanel();

    //varible para el oyente de accion 
    ActionListener obtenerInfo;

    //contructor de la ventana registrar
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

        fondo = new JPanel();
        fondo.setBackground(Color.decode(menu.colorPanelClaro));
        fondo.setLayout(new BoxLayout(fondo, BoxLayout.Y_AXIS));
        fondo.setBorder(new EmptyBorder(10, 0, 10, 10));
        this.add(fondo);

        //agregamos los constructores
        Titulo();
        Formulario();
        fondo.add(Box.createRigidArea(new Dimension(0, 15)));
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

        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBackground(Color.decode(menu.colorPanelMedio));
        botonVolver.setFocusPainted(false);
        botonVolver.setMaximumSize(new Dimension(50, 50));
        botonVolver.setPreferredSize(new Dimension(50, 50));

        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        titPanel.add(botonVolver);
        //Funciones botones.
        ActionListener irMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventanaMenu = new Menu();
                ventanaMenu.RecargarColores();
                ventanaMenu.notifiDetect();
                ventanaMenu.setVisible(true);
                anim.detenerMensajes();
                anim.standar();
                dispose();
            }
        };
        botonVolver.addActionListener(irMenu);
    }//FIN METODO

    private void Formulario() {
        //se crea un panel para intrudicir el formulario
        JScrollPane ScrollForm = new JScrollPane();
        ScrollForm.setOpaque(false);
        ScrollForm.setMaximumSize(new Dimension(800, 400));
        ScrollForm.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollForm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollForm.setBorder(null);
        fondo.add(ScrollForm);

        JPanel panForm = new JPanel();
        panForm.setBackground(Color.decode(menu.colorPanelMedio));
        panForm.setLayout(null);
        panForm.setPreferredSize(new Dimension(700, 1235));
        ScrollForm.setViewportView(panForm);

        //se llama el metodo 
        ElementosForm(panForm);

    }//FIN METODO

    private void ElementosForm(JPanel panForm) {

        Border bordeTexto = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white);

        String[] tipUsuario = {"Administrador", "Usuario"};

        //se crea el JComboBox
        //Para ingresar tipo de usuario
        JComboBox tipoUsuario = new JComboBox(tipUsuario);
        tipoUsuario.setBounds(20, 10, 160, 30);
        panForm.add(tipoUsuario);

        // Se crea caja de texto para introducir usuario
        JTextField usuarioT = new JTextField();
        usuarioT.setBounds(20, 100, 220, 40);
        usuarioT.setBorder(bordeTexto);
        usuarioT.setOpaque(true);
        usuarioT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Usuario(*)", usuarioT);
        textoCaja.setForeground(Color.white);
        usuarioT.setForeground(Color.white);
        panForm.add(usuarioT);

        // 2.1 -- Se crea caja de texto para introducir el campo solicitado
        JTextField nombreT = new JTextField();
        nombreT.setBounds(20, 200, 220, 40);
        nombreT.setBorder(bordeTexto);
        nombreT.setOpaque(true);
        nombreT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Nombre completo(*)", nombreT);
        textoCaja.setForeground(Color.white);
        nombreT.setForeground(Color.white);
        panForm.add(nombreT);

        //2.3
        JTextField apellidoT = new JTextField();
        apellidoT.setBounds(20, 300, 220, 40);
        apellidoT.setBorder(bordeTexto);
        apellidoT.setOpaque(true);
        apellidoT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Apellidos(*)", apellidoT);
        textoCaja.setForeground(Color.white);
        apellidoT.setForeground(Color.white);
        panForm.add(apellidoT);

        //2.5
        JTextField cedulaT = new JTextField();
        cedulaT.setBounds(20, 400, 220, 40);
        cedulaT.setBorder(bordeTexto);
        cedulaT.setOpaque(true);
        cedulaT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Cédula", cedulaT);
        textoCaja.setForeground(Color.white);
        cedulaT.setForeground(Color.white);
        panForm.add(cedulaT);

        //2.6
        JTextField emailT = new JTextField();
        emailT.setBounds(20, 500, 220, 40);
        emailT.setBorder(bordeTexto);
        emailT.setOpaque(true);
        emailT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Email(*)", emailT);
        textoCaja.setForeground(Color.white);
        emailT.setForeground(Color.white);
        panForm.add(emailT);

        //2.7
        JPasswordField password1T = new JPasswordField();
        password1T.setBounds(20, 600, 220, 40);
        password1T.setBorder(bordeTexto);
        password1T.setOpaque(true);
        password1T.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Contraseña(*)", password1T);
        textoCaja.setForeground(Color.white);
        password1T.setForeground(Color.white);
        password1T.setEchoChar((char) 0);
        password1T.setEchoChar('*');
        panForm.add(password1T);

        //boton para activar la contraseña visible
        JButton verContra = new JButton();
        verContra.setBounds(260, 600, 40, 40);
        verContra.setOpaque(false);
        verContra.setFocusPainted(false);
        verContra.setBorderPainted(false);

        ImageIcon logoVer = new ImageIcon("Imagenes/Iconos/ver.png");
        ImageIcon logoNoVer = new ImageIcon("Imagenes/Iconos/nover.png");
        verContra.setBackground(Color.decode(menu.colorBotonOscuro));
        verContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        panForm.add(verContra);
        
        //2.8
        JPasswordField password2T = new JPasswordField();
        password2T.setBounds(20, 700, 220, 40);
        password2T.setBorder(bordeTexto);
        password2T.setOpaque(true);
        password2T.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Confirmar contraseña(*)", password2T);
        textoCaja.setForeground(Color.white);
        password2T.setForeground(Color.white);
        password2T.setEchoChar((char) 0);
        password2T.setEchoChar('*');
        panForm.add(password2T);

        activar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (activContra == false) {
                    password1T.setEchoChar((char) 0);
                    password2T.setEchoChar((char) 0);
                    verContra.setIcon(new ImageIcon(logoVer.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                    activContra = true;
                } else {
                    password1T.setEchoChar('*');
                    password2T.setEchoChar('*');
                    verContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                    activContra = false;
                }
            }
        };
        verContra.addActionListener(activar);

        //2.9
        //arreglo de objetos tipo String para asisgnarle al JComboBox
        String[] genero = {"Masculino", "Femenino", "No especificado"};

        //se crea el JComboBox
        JComboBox generoT = new JComboBox(genero);
        generoT.setBounds(20, 800, 220, 40);
        panForm.add(generoT);

        //2.10
        JTextField telefonoT = new JTextField();
        telefonoT.setBounds(20, 900, 220, 40);
        telefonoT.setBorder(bordeTexto);
        telefonoT.setOpaque(true);
        telefonoT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Télefono(*)", telefonoT);
        textoCaja.setForeground(Color.white);
        telefonoT.setForeground(Color.white);
        panForm.add(telefonoT);

        //2.11
        JButton fotoPerfilT = new JButton("Cargar Imagen");
        fotoPerfilT.setBackground(Color.decode(menu.colorBotonClaro));
        fotoPerfilT.setBounds(20, 1000, 140, 50);
        panForm.add(fotoPerfilT);
        
        //Label para cargar la imagen 
        JLabel fotoPerfil = new JLabel();
        fotoPerfil.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png"))
                .getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH)));
        fotoPerfil.setBounds(20, 1070, 140, 140);
        panForm.add(fotoPerfil);
        
        //funcionalidad del boton cargar imagen
        fotoPerfilT.addActionListener((ActionEvent e) -> {
            CargarImagen(fotoPerfil);
        });

        //linea que divide el panel
        JPanel panDiv = new JPanel();
        panDiv.setBounds(375, 10, 1, 1215);
        panForm.add(panDiv);

        //División del panel para recuperación de la contraseña
        //-- Titulo --
        JPanel titPanel2 = new JPanel();
        titPanel2.setBounds(410, 10, 358, 40);
        titPanel2.setOpaque(false);
        panForm.add(titPanel2);

        JLabel textRePs = new JLabel("<html><body><center><p>Recuperar"
                + " Contraseña</p></html>");
        textRePs.setFont(new Font("Arial", Font.BOLD, 20));
        textRePs.setForeground(Color.white);
        titPanel2.add(textRePs);

        String[] preg1 = {"¿Como se llama tu mascota?"};
        JComboBox jcbPreg1 = new JComboBox(preg1);
        jcbPreg1.setBounds(410, 70, 220, 40);
        panForm.add(jcbPreg1);

        // 4.1 campo texto para respuesta
        JTextField primeraRespT = new JTextField();
        primeraRespT.setBounds(410, 130, 300, 40);
        primeraRespT.setBorder(bordeTexto);
        primeraRespT.setOpaque(true);
        primeraRespT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Respuesta(*)", primeraRespT);
        textoCaja.setForeground(Color.white);
        primeraRespT.setForeground(Color.white);
        panForm.add(primeraRespT);

        String[] preg2 = {"¿Dondé Naciste?"};
        JComboBox jcbPreg2 = new JComboBox(preg2);
        jcbPreg2.setBounds(410, 230, 220, 40);
        panForm.add(jcbPreg2);

        //2.2
        JTextField segundaRespT = new JTextField();
        segundaRespT.setBounds(410, 290, 300, 40);
        segundaRespT.setBorder(bordeTexto);
        segundaRespT.setOpaque(true);
        segundaRespT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Respuesta(*)", segundaRespT);
        textoCaja.setForeground(Color.white);
        segundaRespT.setForeground(Color.white);
        panForm.add(segundaRespT);

        String[] preg3 = {"¿Cúal sería tu trabajo ideal?"};
        JComboBox jcbPreg3 = new JComboBox(preg3);
        jcbPreg3.setBounds(410, 390, 220, 40);
        panForm.add(jcbPreg3);

        //4.3
        JTextField terceraRespT = new JTextField();
        terceraRespT.setBounds(410, 450, 300, 40);
        terceraRespT.setBorder(bordeTexto);
        terceraRespT.setOpaque(true);
        terceraRespT.setBackground(Color.decode(menu.colorPanelClaro));
        textoCaja = new TextPrompt("Respuesta(*)", terceraRespT);
        textoCaja.setForeground(Color.white);
        terceraRespT.setForeground(Color.white);
        panForm.add(terceraRespT);
        
        
        //PANEL DE BOTONES
        JPanel buttPanel = new JPanel();
        buttPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.X_AXIS));
        buttPanel.setOpaque(false);

        fondo.add(buttPanel);

        //-- Se crea Botón para agregar los usuarios -- 
        JButton botonAgregar = new JButton("Crear");
        botonAgregar.setBackground(Color.decode(menu.colorBotonClaro));
        botonAgregar.setFocusPainted(false);
        botonAgregar.setBorderPainted(false);
        buttPanel.add(botonAgregar);

        //funcionalidad del boton registrar
        botonAgregar.addActionListener((ActionEvent e) -> {
            String[] usuario = {usuarioT.getText().trim(), nombreT.getText().trim(), apellidoT.getText().trim(),
            cedulaT.getText().trim(), emailT.getText().trim(), String.valueOf(password1T.getPassword()).trim(),
            String.valueOf(password2T.getPassword()).trim(), (String)generoT.getSelectedItem(), telefonoT.getText().trim(),
            fotoPerfilT.getText().trim(), (String)jcbPreg1.getSelectedItem(), primeraRespT.getText(),
            (String)jcbPreg2.getSelectedItem(), segundaRespT.getText(), (String)jcbPreg3.getSelectedItem(),
            terceraRespT.getText(), (String)tipoUsuario.getSelectedItem()};
            
            CrearUsuario(usuario);
        });

        buttPanel.add(Box.createRigidArea(new Dimension(10, 0)));//generamos espacio entre los botones         

        //-- Se crea Botón para limpiar los campos de texto --
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBackground(Color.decode(menu.colorBotonClaro));
        botonLimpiar.setFocusPainted(false);
        botonLimpiar.setBorderPainted(false);
        buttPanel.add(botonLimpiar);

        obtenerInfo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //volvemos a colocar las cajas de texto vacias
                usuarioT.setText("");
                nombreT.setText("");
                apellidoT.setText("");
                cedulaT.setText("");
                emailT.setText("");
                password1T.setText("");
                password2T.setText("");
                telefonoT.setText("");
                primeraRespT.setText("");
                segundaRespT.setText("");
                terceraRespT.setText("");
                fotoPerfil.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png"))
                        .getImage().getScaledInstance(140, 80, Image.SCALE_SMOOTH)));
            }
        };
        botonLimpiar.addActionListener(obtenerInfo);
    }//FIN METODO

    public void CargarImagen(JLabel fotoPerfil) {

        JFileChooser cargarImagen = new JFileChooser();
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG", "PNG", "GIF", "jpg", "png", "gif");
        cargarImagen.setFileFilter(filtrado);

        int seleccion = cargarImagen.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            ruta = cargarImagen.getSelectedFile().getPath();

            Image foto = new ImageIcon(ruta).getImage();
            ImageIcon mIcon = new ImageIcon(foto.getScaledInstance(fotoPerfil.getWidth(), fotoPerfil.getHeight(), foto.SCALE_SMOOTH));
            fotoPerfil.setIcon(mIcon);

        }
    }// Fin btnCargarImagen

    public void CrearUsuario(String[] Usuario) {
        if (Usuario[0].isEmpty() || Usuario[0] == null
                || Usuario[1].isEmpty() || Usuario[1] == null
                || Usuario[2].isEmpty() || Usuario[2] == null
                || Usuario[3].isEmpty() || Usuario[3] == null
                || Usuario[4].isEmpty() || Usuario[4] == null
                || Usuario[5].isEmpty() || Usuario[5] == null
                || Usuario[6].isEmpty() || Usuario[6] == null
                || Usuario[7].isEmpty() || Usuario[7] == null
                || Usuario[8].isEmpty() || Usuario[8] == null
                || Usuario[10].isEmpty() || Usuario[10] == null
                || Usuario[11].isEmpty() || Usuario[11] == null
                || Usuario[12].isEmpty() || Usuario[12] == null
                || Usuario[13].isEmpty() || Usuario[13] == null
                || Usuario[14].isEmpty() || Usuario[14] == null
                || Usuario[15].isEmpty() || Usuario[15] == null
                || Usuario[16].isEmpty() || Usuario[16] == null) {

            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos de texto solicitados");
            return;
        }
        
        if (Usuario[0].trim().contains("|")
            || Usuario[1].trim().contains("|")
            || Usuario[2].trim().contains("|")
            || Usuario[3].trim().contains("|")
            || Usuario[4].trim().contains("|")
            || Usuario[5].trim().contains("|")
            || Usuario[6].trim().contains("|")
            || Usuario[7].trim().contains("|")
            || Usuario[8].trim().contains("|")
            || Usuario[10].trim().contains("|")
            || Usuario[11].trim().contains("|")
            || Usuario[12].trim().contains("|")
            || Usuario[13].trim().contains("|")
            || Usuario[14].trim().contains("|")
            || Usuario[15].trim().contains("|")
            || Usuario[16].trim().contains("|")) {

            JOptionPane.showMessageDialog(this, "No puedes ingresar el caracter \" | \"", "Caracter Invalido.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String dato = guardado.validaciones(Usuario[0], Usuario[3], Usuario[4]);

        if (dato.equals("Usuario")) {
            JOptionPane.showMessageDialog(this, "El usuario " + Usuario[0] + " ya existe.");
            return;
        }
        if (dato.equals("Cc")) {
            JOptionPane.showMessageDialog(this, "La cédula " + Usuario[3] + " ya existe.");
            return;
        }
        if (dato.equals("Correo")) {
            JOptionPane.showMessageDialog(this, Usuario[4] + " ya se encuentra registrado.");
            return;
        }
        if (!Usuario[5].equals(Usuario[6])) {
            JOptionPane.showMessageDialog(this, "Las contraseñas deben ser iguales.");
            return;
        } else {
            guardado.CrearUsu(Usuario);
            JOptionPane.showMessageDialog(null, "El usuario se creo exitosamente");
//            usuarioT.setText("");
//            nombreT.setText("");
//            apellidoT.setText("");
//            cedulaT.setText("");
//            emailT.setText("");
//            password1T.setText("");
//            password2T.setText("");
//            telefonoT.setText("");
//            primeraRespT.setText("");
//            segundaRespT.setText("");
//            terceraRespT.setText("");
//            fotoPerfilT.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png"))
//                    .getImage().getScaledInstance(140, 80, Image.SCALE_SMOOTH)));
        }

    }//Fin btnRegistrarUsuario

}//FIN CLASS
