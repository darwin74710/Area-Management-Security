package Ventanas;

import Logica.AnimMenu;
import static Logica.CameraManager.cargarCamaras;
import Logica.botones;
import static Logica.botones.activContra;
import archivos.ArchivoUsuarios;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Ingreso extends JFrame {

    Menu menu = new Menu();
    public AnimMenu anim = new AnimMenu();
    
    public JTextField textoUsuario;
    public JPasswordField textoContraseña;

    public JPanel fondo = new JPanel();

    JLabel botonVerContra = new JLabel();
    JButton ingreso = new JButton("INGRESAR");
    JLabel recuperarContra = new JLabel("¿Se te olvidó tu contraseña?");

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

        fondo = new JPanel();
        fondo.setLayout(null);
        this.add(fondo);
    }

    private void Distribucion() {
        // Añadimos la imagen izquierda de la ventana.
        ImageIcon imagen = new ImageIcon("Imagenes/LogoInicio.png"); //Creamos la ruta de una imagen.
        JLabel izquierda = new JLabel();//Creamos un layout de imagen.
        izquierda.setBounds(80, 80, 300, 300);
        izquierda.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(izquierda.getWidth(), izquierda.getHeight(), Image.SCALE_SMOOTH))); //Ajustamos el tamaño de la imagen.
        fondo.add(izquierda);
        
        JLabel texto0 = new JLabel("AMS");
        texto0.setFont(new Font("Arial", 1, 100));
        texto0.setForeground(Color.white);
        texto0.setBounds(120, 290, 300, 300);
        fondo.add(texto0);

        ladoDerecho();
    }

    private void ladoDerecho() {
        // Para el botón de ver contraseña
        ImageIcon logoVer = new ImageIcon("Imagenes/Iconos/ver.png");
        ImageIcon logoNoVer = new ImageIcon("Imagenes/Iconos/nover.png");
        
        JLabel texto1 = new JLabel("LOGIN");
        texto1.setFont(new Font("Arial", 1, 60));
        texto1.setForeground(Color.white);
        texto1.setAlignmentX(Component.CENTER_ALIGNMENT); //Centrar el texto
        texto1.setBounds(620, 30, 500, 80);
        fondo.add(texto1);
        
        //Creamos descripción de ventana.
        JLabel texto2 = new JLabel("<html><body><center><p>INGRESE CON USUARIO Y CONTRASEÑA PARA CONTINUAR</p></html>");
        texto2.setFont(new Font("Arial", 1, 20));
        texto2.setForeground(Color.white);
        texto2.setAlignmentX(Component.CENTER_ALIGNMENT); //Centrar el texto
        texto2.setBounds(470, 100, 500, 80);
        fondo.add(texto2);
        
        Border bordeTexto = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white);

        //Se crea el campo de texto.
        textoUsuario = new JTextField("Usuario");
        textoUsuario.setOpaque(false);
        textoUsuario.setBorder(bordeTexto);
        textoUsuario.setForeground(Color.gray);
        textoUsuario.setFont(new Font("Arial", 1, 27));
        textoUsuario.setBounds(550, 220, 330, 50);
        
        // Agregar un FocusListener al JTextField
        textoUsuario.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Si el campo de texto obtiene el foco y el texto es igual al texto indicativo, eliminarlo
                if (textoUsuario.getText().equals("Usuario")) {
                    textoUsuario.setText("");
                    textoUsuario.setForeground(Color.white); // Restablecer el color del texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Si el campo de texto pierde el foco y está vacío, establecer el texto indicativo
                if (textoUsuario.getText().isEmpty()) {
                    textoUsuario.setText("Usuario");
                    textoUsuario.setForeground(Color.gray);
                }
            }
        });

        fondo.add(textoUsuario);

        //Se crea el campo de texto.
        textoContraseña = new JPasswordField("Contraseña"); //Campo de texto tipo contraseña.
        textoContraseña.setEchoChar((char) 0); //Desactivar la vista de contraseña.
        textoContraseña.setOpaque(false);
        textoContraseña.setBorder(bordeTexto);
        textoContraseña.setForeground(Color.gray);
        textoContraseña.setFont(new Font("Arial", 1, 27));
        textoContraseña.setBounds(550, 320, 330, 50);
        fondo.add(textoContraseña);
        
        // Agregar un FocusListener al JTextField
        textoContraseña.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Si el campo de texto obtiene el foco y el texto es igual al texto indicativo, eliminarlo
                if (String.valueOf(textoContraseña.getPassword()).equals("Contraseña")) {
                    textoContraseña.setText("");
                    if (activContra == false){
                        textoContraseña.setEchoChar('*');
                    }else{
                        textoContraseña.setEchoChar((char) 0);
                    }
                    textoContraseña.setForeground(Color.white); // Restablecer el color del texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Si el campo de texto pierde el foco y está vacío, establecer el texto indicativo
                if (String.valueOf(textoContraseña.getPassword()).isEmpty()) {
                    textoContraseña.setEchoChar((char) 0);
                    textoContraseña.setText("Contraseña");
                    textoContraseña.setForeground(Color.gray);
                }
            }
        });

        //Creamos boton para ver y no ver contraseña.
        botonVerContra.setBounds(890, 330, 30, 30);
        
        botonVerContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        fondo.add(botonVerContra);

        //Agregamos la logica al botón.
        botones.verContra(textoContraseña, botonVerContra, logoVer, logoNoVer);

        //Creamos los botones.
        ingreso.setBackground(Color.decode(menu.colorBotonOscuro));
        ingreso.setFocusPainted(false); //Quitamos las lineas de focus.
        ingreso.setBounds(550, 400, 330, 50);
        ingreso.setFont(new Font("Arial", 1, 20));
        ingreso.setForeground(Color.white);
        fondo.add(ingreso);

        //funcionalidad del boton ingresar
        ingreso.addActionListener((ActionEvent e) -> {
            btnIngresarActionPerformed(e);
        });

        recuperarContra.setBackground(Color.decode(menu.colorBotonOscuro));
        
        recuperarContra.setFont(new Font("Arial", 1, 15));
        recuperarContra.setForeground(Color.white);
        recuperarContra.setBounds(610, 460, 330, 30);
        fondo.add(recuperarContra);

        // ----- Funcionalidad btnRecuperar -----
        recuperarContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnRecuperarContra();
            }
        });
        
        //Decoración de ventana
        JPanel panelFondo = new JPanel();
        panelFondo.setOpaque(true);
        panelFondo.setBackground(Color.decode(menu.colorPanelMedio));
        panelFondo.setBounds(460, 0, 530, 560);
        fondo.add(panelFondo);
    }

    public void btnIngresarActionPerformed(ActionEvent e) {

        String usuario = null, contraseña = null;

        usuario = textoUsuario.getText();
        contraseña = String.valueOf(textoContraseña.getPassword());

        if (usuario.isEmpty() || usuario == null || usuario.equals("Usuario")) {
            JOptionPane.showMessageDialog(this, "Ingrese un usuario valido");
            return;
        }
        if (contraseña.isEmpty() || contraseña == null || contraseña.equals("Contraseña")) {
            JOptionPane.showMessageDialog(this, "Ingrese una contraseña valida");
            return;
        }

        ArchivoUsuarios archivoU = new ArchivoUsuarios();
        String[] dato = archivoU.leerArchivo(usuario);
        if (dato != null) {
            if (dato[5].equals(contraseña)) {
                menu.usuario = dato;
                Menu ventanaMenu = new Menu();
                ventanaMenu.setVisible(true);
                setVisible(false);
                anim.standar();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario y contraseña no validos");
                textoUsuario.setText("Usuario");
                textoUsuario.setForeground(Color.gray);
                textoContraseña.setText("Contraseña");
                textoContraseña.setForeground(Color.gray);
                textoContraseña.setEchoChar((char) 0);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Usuario y contraseña no validos");
            textoUsuario.setText("Usuario");
            textoUsuario.setForeground(Color.gray);
            textoContraseña.setText("Contraseña");
            textoContraseña.setForeground(Color.gray);
            textoContraseña.setEchoChar((char) 0);
        }

//       
    }//Fin metodo btnIngresar

    public void btnRecuperarContra() {

        JDialog ventRecuperar = new JDialog(this, "Recuperar contraseña", true);
        ventRecuperar.setSize(380, 150);
        ventRecuperar.setLocationRelativeTo(null);
        ventRecuperar.setResizable(false);

        // Crear un panel para el contenido
        JPanel pnlRecuperar = new JPanel();
        pnlRecuperar.setLayout(null);
        pnlRecuperar.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel

        JLabel texto = new JLabel("Ingrese su Cédula para recuperar la contraseña");
        texto.setBounds(50, 10, 300, 20);
        texto.setForeground(Color.white);
        pnlRecuperar.add(texto);

        JTextField txtRCedula = new JTextField();
        txtRCedula.setBounds(110, 40, 150, 20);
        pnlRecuperar.add(txtRCedula);

        JButton botonAceptar = new JButton("ACEPTAR");
        botonAceptar.setBounds(130, 80, 100, 20);
        botonAceptar.setBackground(Color.decode(menu.colorBotonClaro));
        botonAceptar.setFocusPainted(false); //Quitamos las lineas de focus.
        pnlRecuperar.add(botonAceptar);

        //Funcionalidad btnAceptar
        botonAceptar.addActionListener(e -> btnAceptarValidacion(ventRecuperar, txtRCedula));

        JButton botonCancelar = new JButton("CANCELAR");
        botonCancelar.setBounds(250, 80, 100, 20);
        botonCancelar.setBackground(Color.decode(menu.colorBotonClaro));
        botonCancelar.setFocusPainted(false); //Quitamos las lineas de focus.
        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventRecuperar.dispose();
            }
        };
        botonCancelar.addActionListener(cancelar);
        pnlRecuperar.add(botonCancelar);

        ventRecuperar.add(pnlRecuperar, BorderLayout.CENTER);
        ventRecuperar.setLocationRelativeTo(this);
        ventRecuperar.setVisible(true);

    }

    public void ActualizarIngreso() {
        fondo.setBackground(Color.decode(menu.colorPanelClaro));
        botonVerContra.setBackground(Color.decode(menu.colorBotonOscuro));
        ingreso.setBackground(Color.decode(menu.colorBotonOscuro));

        fondo.revalidate();
        fondo.repaint();
    }

    public void btnAceptarValidacion(JDialog ventRecuperar, JTextField txtRCedula) {
        String cedula = null;

        cedula = txtRCedula.getText();

        if (cedula.isEmpty() || cedula == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número de cédula");
            txtRCedula.requestFocusInWindow();
            return;
        }

        ArchivoUsuarios archivoU = new ArchivoUsuarios();
        String[] dato = archivoU.RecuperarContra(cedula);
        if (dato != null) {
        if (dato[3].equals(cedula)) {
                ventRecuperar.dispose();

                JDialog ventPreg = new JDialog(this, "Preguntas de validación", true);
                ventPreg.setSize(400, 400);
                ventPreg.setLocationRelativeTo(null);
                ventPreg.setResizable(false);

                JPanel pnlPreg = new JPanel();
                pnlPreg.setLayout(null);
                pnlPreg.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel

                JLabel texto = new JLabel("Ingrese las respuestas de las preguntas");
                texto.setFont(new Font("Arial", 1, 17));
                texto.setHorizontalAlignment(SwingConstants.CENTER);
                texto.setBounds(20, 10, 345, 20);
                texto.setForeground(Color.white);
                pnlPreg.add(texto);

                JPanel pnlpreg2 = new JPanel();
                pnlpreg2.setLayout(null);
                pnlpreg2.setBackground(Color.decode(menu.colorPanelClaro));
                pnlpreg2.setBounds(20, 40, 345, 280);
                pnlPreg.add(pnlpreg2);

                //agregamos al panel2 las preguntas y respuestas
                JLabel preg1 = new JLabel(dato[10]);
                preg1.setBounds(20, 10, 305, 20);
                preg1.setHorizontalAlignment(SwingConstants.CENTER);
                preg1.setForeground(Color.white);
                preg1.setOpaque(true);
                preg1.setBackground(Color.decode(menu.colorPanelMedio));
                pnlpreg2.add(preg1);

                JTextField txtR1 = new JTextField();
                txtR1.setBounds(45, 40, 250, 20);
                pnlpreg2.add(txtR1);

                JLabel preg2 = new JLabel(dato[12]);
                preg2.setBounds(20, 90, 305, 20);
                preg2.setHorizontalAlignment(SwingConstants.CENTER);
                preg2.setForeground(Color.white);
                preg2.setOpaque(true);
                preg2.setBackground(Color.decode(menu.colorPanelMedio));
                pnlpreg2.add(preg2);

                JTextField txtR2 = new JTextField();
                txtR2.setBounds(45, 120, 250, 20);
                pnlpreg2.add(txtR2);

                JLabel preg3 = new JLabel(dato[14]);
                preg3.setBounds(20, 170, 305, 20);
                preg3.setHorizontalAlignment(SwingConstants.CENTER);
                preg3.setForeground(Color.white);
                preg3.setOpaque(true);
                preg3.setBackground(Color.decode(menu.colorPanelMedio));
                pnlpreg2.add(preg3);

                JTextField txtR3 = new JTextField();
                txtR3.setBounds(45, 200, 250, 20);
                pnlpreg2.add(txtR3);

                JButton BtnRecuperar = new JButton("RECUPERAR");
                BtnRecuperar.setBounds(135, 330, 120, 20);
                BtnRecuperar.setBackground(Color.decode(menu.colorBotonClaro));
                BtnRecuperar.setFocusPainted(false);
                pnlPreg.add(BtnRecuperar);

                //funcionalidad del btnRecuperar
                ActionListener recuperar = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(dato[11].equals(txtR1.getText()) && dato[13].equals(txtR2.getText()) && dato[15].equals(txtR3.getText())){
                            JOptionPane.showMessageDialog(null, "Su usuario es " + dato[0] + " y la contraseña es " + dato[3]);
                            ventPreg.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null, "Respuestas incorrectas");
                        }
                    }
                };
                BtnRecuperar.addActionListener(recuperar);

                ventPreg.add(pnlPreg, BorderLayout.CENTER);
                ventPreg.setLocationRelativeTo(this);
                ventPreg.setVisible(true);
            }
        } else {
            System.out.println("algo");
            JOptionPane.showMessageDialog(null, "La Cédula no esta en la base de datos");
        }


    }
}
