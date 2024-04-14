package Ventanas;

import archivos.ArchivoUsuarios;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Registrar extends JFrame {

    Menu menu;

    /* -- variables para los campos de texto de la informacion
    que se esta solicitando -- */
    JComboBox tipoUsuario;
    JTextField usuarioT;
    JTextField nombreT;
    JTextField apellidoT;
    JTextField cedulaT;
    JTextField emailT;
    JPasswordField password1T;
    JPasswordField password2T;
    JComboBox generoT;
    JTextField telefonoT;
    JButton fotoPerfilT;
    JLabel fotoPerfil;
    JButton verContra;
    JTextField primeraPregT;
    JTextField primeraRespT;
    JTextField segundaPregT;
    JTextField segundaRespT;
    JTextField terceraPregT;
    JTextField terceraRespT;

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
        fondo.add(ScrollForm);

        JPanel panForm = new JPanel();
        panForm.setBackground(Color.decode(menu.colorPanelOscuro));
        panForm.setLayout(null);
        panForm.setPreferredSize(new Dimension(700, 1210));
        ScrollForm.setViewportView(panForm);

        //se llama el metodo 
        ElementosForm(panForm);

    }//FIN METODO

    private void ElementosForm(JPanel panForm) {
        
        String[] tipUsuario = {"Administrador", "Usuario"};

        //se crea el JComboBox
        //Para ingresar tipo de usuario
        tipoUsuario = new JComboBox(tipUsuario);
        tipoUsuario.setBounds(20, 10, 140, 20);
        panForm.add(tipoUsuario);
        
        // 1.1 -- Se crea el texto para solcitud de llenado de campo de texto. 
        JLabel textN0 = new JLabel("Usuario(*)");
        textN0.setFont(new Font("Arial", Font.BOLD, 15));
        textN0.setForeground(Color.white);
        textN0.setBounds(20, 70, 140, 30);
        panForm.add(textN0);

        // 2.1 -- Se crea caja de texto para introducir el campo solicitado
        usuarioT = new JTextField();
        usuarioT.setBounds(20, 100, 140, 20);
        panForm.add(usuarioT);

        // 1.1 -- Se crea el texto para solcitud de llenado de campo de texto. 
        JLabel textN1 = new JLabel("Nombre(*)");
        textN1.setFont(new Font("Arial", Font.BOLD, 15));
        textN1.setForeground(Color.white);
        textN1.setBounds(20, 160, 140, 30);
        panForm.add(textN1);

        // 2.1 -- Se crea caja de texto para introducir el campo solicitado
        nombreT = new JTextField();
        nombreT.setBounds(20, 200, 140, 20);
        panForm.add(nombreT);

        //1.3
        JLabel textN3 = new JLabel("Apellido(*)");
        textN3.setFont(new Font("Arial", Font.BOLD, 15));
        textN3.setForeground(Color.white);
        textN3.setBounds(20, 260, 140, 30);
        panForm.add(textN3);

        //2.3
        apellidoT = new JTextField();
        apellidoT.setBounds(20, 300, 140, 20);
        panForm.add(apellidoT);

        //1.5
        JLabel textN5 = new JLabel("Cédula(*)");
        textN5.setFont(new Font("Arial", Font.BOLD, 15));
        textN5.setForeground(Color.white);
        textN5.setBounds(20, 360, 140, 30);
        panForm.add(textN5);

        //2.5
        cedulaT = new JTextField();
        cedulaT.setBounds(20, 400, 140, 20);
        panForm.add(cedulaT);

        //1.6
        JLabel textN6 = new JLabel("Email(*)");
        textN6.setFont(new Font("Arial", Font.BOLD, 15));
        textN6.setForeground(Color.white);
        textN6.setBounds(20, 460, 140, 30);
        panForm.add(textN6);

        //2.6
        emailT = new JTextField();
        emailT.setBounds(20, 500, 140, 20);
        panForm.add(emailT);

        //1.7
        JLabel textN7 = new JLabel("Password(*)");
        textN7.setFont(new Font("Arial", Font.BOLD, 15));
        textN7.setForeground(Color.white);
        textN7.setBounds(20, 560, 140, 30);
        panForm.add(textN7);

        //2.7
        password1T = new JPasswordField();
        password1T.setBounds(20, 600, 140, 20);
        password1T.setEchoChar((char) 0);
        password1T.setEchoChar('*');
        panForm.add(password1T);

        //boton para activar la contraseña visible
        verContra = new JButton();
        verContra.setBounds(180, 600, 20, 20);
        verContra.setOpaque(false);
        verContra.setFocusPainted(false);
        verContra.setBorderPainted(false);

        ImageIcon logoVer = new ImageIcon("Imagenes/Iconos/ver.png");
        ImageIcon logoNoVer = new ImageIcon("Imagenes/Iconos/nover.png");
        verContra.setBackground(Color.decode(menu.colorBotonOscuro));
        verContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        panForm.add(verContra);

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

        //1.8
        JLabel textN8 = new JLabel("Confirmar Password(*)");
        textN8.setFont(new Font("Arial", Font.BOLD, 15));
        textN8.setForeground(Color.white);
        textN8.setBounds(20, 660, 170, 30);
        panForm.add(textN8);

        //2.8
        password2T = new JPasswordField();
        password2T.setBounds(20, 690, 140, 20);
        password2T.setEchoChar((char) 0);
        password2T.setEchoChar('*');
        panForm.add(password2T);

        //1.9
        JLabel textN9 = new JLabel("Género(*)");
        textN9.setFont(new Font("Arial", Font.BOLD, 15));
        textN9.setForeground(Color.white);
        textN9.setBounds(20, 750, 140, 30);
        panForm.add(textN9);

        //2.9
        //arreglo de objetos tipo String para asisgnarle al JComboBox
        String[] genero = {"Masculino", "Femenino", "No especificado"};

        //se crea el JComboBox
        generoT = new JComboBox(genero);
        generoT.setBounds(20, 780, 140, 20);
        panForm.add(generoT);

        //1.10
        JLabel textN10 = new JLabel("Teléfono(*)");
        textN10.setFont(new Font("Arial", Font.BOLD, 15));
        textN10.setForeground(Color.white);
        textN10.setBounds(20, 840, 140, 30);
        panForm.add(textN10);

        //2.10
        telefonoT = new JTextField();
        telefonoT.setBounds(20, 870, 140, 20);
        panForm.add(telefonoT);

        //1.11
        JLabel textN11 = new JLabel("<html><body><center><p>Cargar imagen"
                + " de perfil</p></html>");
        textN11.setFont(new Font("Arial", Font.BOLD, 15));
        textN11.setForeground(Color.white);
        textN11.setBounds(20, 930, 140, 40);
        panForm.add(textN11);

        //2.11
        fotoPerfilT = new JButton("Cargar");
        fotoPerfilT.setBackground(Color.decode(menu.colorBotonClaro));
        fotoPerfilT.setBounds(20, 985, 140, 50);
        panForm.add(fotoPerfilT);

        //funcionalidad del boton cargar imagen
        fotoPerfilT.addActionListener((ActionEvent e) -> {
            btnCargarImagenActionPerformed(e);
        });

        //Label para cargar la imagen 
        fotoPerfil = new JLabel();
        fotoPerfil.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png"))
                .getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH)));
        fotoPerfil.setBounds(20, 1045, 140, 140);
        panForm.add(fotoPerfil);

        //linea que divide el panel
        JPanel panDiv = new JPanel();
        panDiv.setBounds(375, 10, 1, 1190);
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

        // 1.1 Preguntas de recuperación
        JLabel preg1 = new JLabel("Primera pregunta(*)");
        preg1.setFont(new Font("Arial", Font.BOLD, 15));
        preg1.setForeground(Color.white);
        preg1.setBounds(410, 70, 358, 30);
        panForm.add(preg1);

        // 2.1 caja de texto para agregar la pregunta
        primeraPregT = new JTextField();
        primeraPregT.setBounds(410, 100, 358, 20);
        panForm.add(primeraPregT);

        // 3.1 Respuesta de recuperacion
        JLabel res1 = new JLabel("Primera Respuesta(*)");
        res1.setFont(new Font("Arial", Font.BOLD, 15));
        res1.setForeground(Color.white);
        res1.setBounds(410, 160, 358, 30);
        panForm.add(res1);

        // 4.1 campo texto para respuesta
        primeraRespT = new JTextField();
        primeraRespT.setBounds(410, 190, 358, 20);
        panForm.add(primeraRespT);

        //1.2
        JLabel preg2 = new JLabel("Segunda pregunta(*)");
        preg2.setFont(new Font("Arial", Font.BOLD, 15));
        preg2.setForeground(Color.white);
        preg2.setBounds(410, 250, 358, 30);
        panForm.add(preg2);

        //2.2
        segundaPregT = new JTextField();
        segundaPregT.setBounds(410, 280, 358, 20);
        panForm.add(segundaPregT);

        //3.2
        JLabel res2 = new JLabel("Segunda Respuesta(*)");
        res2.setFont(new Font("Arial", Font.BOLD, 15));
        res2.setForeground(Color.white);
        res2.setBounds(410, 340, 358, 30);
        panForm.add(res2);

        //4.2
        segundaRespT = new JTextField();
        segundaRespT.setBounds(410, 370, 358, 20);
        panForm.add(segundaRespT);

        //1.3
        JLabel preg3 = new JLabel("Tercera Pregunta(*)");
        preg3.setFont(new Font("Arial", Font.BOLD, 15));
        preg3.setForeground(Color.white);
        preg3.setBounds(410, 430, 358, 30);
        panForm.add(preg3);

        //2.3
        terceraPregT = new JTextField();
        terceraPregT.setBounds(410, 460, 358, 20);
        panForm.add(terceraPregT);

        //3.3
        JLabel res3 = new JLabel("Tercera Respuesta(*)");
        res3.setFont(new Font("Arial", Font.BOLD, 15));
        res3.setForeground(Color.white);
        res3.setBounds(410, 520, 358, 30);
        panForm.add(res3);

        //4.3
        terceraRespT = new JTextField();
        terceraRespT.setBounds(410, 550, 358, 20);
        panForm.add(terceraRespT);

    }//FIN METODO

    private void Botones() {

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
            btnRegistrarUsuarioActionPerformed(e);
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
                nombreT.setText("");
                apellidoT.setText("");
                cedulaT.setText("");
                emailT.setText("");
                password1T.setText("");
                password2T.setText("");
                telefonoT.setText("");
                primeraPregT.setText("");
                primeraRespT.setText("");
                segundaPregT.setText("");
                segundaRespT.setText("");
                terceraPregT.setText("");
                terceraRespT.setText("");
                fotoPerfil.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png"))
                        .getImage().getScaledInstance(140, 80, Image.SCALE_SMOOTH)));
            }
        };
        botonLimpiar.addActionListener(obtenerInfo);

    }//FIN METODO

    public void btnCargarImagenActionPerformed(ActionEvent e) {

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

    public void btnRegistrarUsuarioActionPerformed(ActionEvent e) {
        String usuario = usuarioT.getText().trim();
        String nombre = nombreT.getText().trim();
        String apellido = apellidoT.getText().trim();
        String cedula = cedulaT.getText().trim();
        String email = emailT.getText().trim();
        String password1 = String.valueOf(password1T.getPassword()).trim();
        String password2 = String.valueOf(password2T.getPassword()).trim();
        String genero = (String) generoT.getSelectedItem();
        String telefono = telefonoT.getText().trim();
        String fotoPerfil = fotoPerfilT.getText().trim();
        String primeraPreg = primeraPregT.getText();
        String primeraResp = primeraRespT.getText();
        String segundaPreg = segundaPregT.getText();
        String segundaResp = segundaRespT.getText();
        String terceraPreg = terceraPregT.getText();
        String terceraResp = terceraRespT.getText();
        String tUsuario = (String) tipoUsuario.getSelectedItem();

        if (usuario.isEmpty() || usuario == null
                && nombre.isEmpty() || nombre == null
                && apellido.isEmpty() || apellido == null
                && cedula.isEmpty() || cedula == null
                && email.isEmpty() || email == null
                && password1.isEmpty() || password1 == null
                && password2.isEmpty() || password2 == null
                && genero.isEmpty() || genero == null
                && telefono.isEmpty() || telefono == null
                && primeraPreg.isEmpty() || primeraPreg == null
                && primeraResp.isEmpty() || primeraResp == null
                && segundaPreg.isEmpty() || segundaPreg == null
                && segundaResp.isEmpty() || segundaResp == null
                && terceraPreg.isEmpty() || terceraPreg == null
                && terceraResp.isEmpty() || terceraResp == null
                && tUsuario.isEmpty() || tUsuario == null) {

            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos de texto solicitados");

        }//fin if 
        else if (password1.equals(password2)) {
            ArchivoUsuarios archivoU = new ArchivoUsuarios();
            archivoU.crearArchivo();

            archivoU.escribirArchivo(usuario, nombre, apellido,
                    cedula, email, password1, password2,
                    genero, telefono, ruta, primeraPreg,
                    primeraResp, segundaPreg, segundaResp, terceraPreg, terceraResp, tUsuario);
        } else {
            JOptionPane.showMessageDialog(this, "Las contraseñas deben ser iguales");

        }//fin if 

    }//Fin btnRegistrarUsuario

}//FIN CLASS
