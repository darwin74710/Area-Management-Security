package Ventanas;

import Logica.AnimMenu;
import Logica.SaveConfiguraciones;
import Logica.botones;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

    public static boolean deteccionManager = false;

    public static String[] usuario;

    public JPanel fondo = new JPanel();
    public static JPanel animFondo = new JPanel();

    SaveConfiguraciones configVents = new SaveConfiguraciones();
    botones VerContra = new botones();
    public AnimMenu anim = new AnimMenu();

    JPanel elementosSuperiores = new JPanel();
    JPanel botonesPanel = new JPanel();
    JPanel fondoPerfil = new JPanel();
    JPanel fondoNotifi = new JPanel();
    JPanel pnlOpcionesPerfil = new JPanel();
    JPanel indicador = new JPanel();
    
    ImageIcon imagenNormal;
    ImageIcon imagenOscura;
    
    public JLabel btnNotifi = new JLabel();

    boolean opcionesPerfil = false;
    boolean verNotifi = false;

    // TEMAS
    public static String colorPanelClaro = "#011b5a";
    public static String colorPanelMedio = "#000a45";
    public static String colorPanelOscuro = "#121a2d";
    public static String colorBotonClaro = "#85add5";
    public static String colorBotonClaroSeleccion = "#415F7E";
    public static String colorBotonOscuro = "#000e3c";

    public Menu() {
        RecargarColores();
        PanelFondo();
        Deteccion();
    }

    private void PanelFondo() {

        //Creamos la ventana.
        setTitle("Menu");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        //Establecemos imagen de fondo.
        fondo = new JPanel();
        fondo.setBackground(Color.decode(colorPanelClaro));
        fondo.setLayout(null);
        this.add(fondo);

        elementos();
    }

    private void elementos() {
        JPanel barraSuperior = new JPanel();
        barraSuperior.setBackground(Color.decode(colorPanelMedio));
        barraSuperior.setOpaque(true);
        barraSuperior.setLayout(null);
        barraSuperior.setBounds(0, 0, 990, 70);
        fondo.add(barraSuperior);
        
        // OPCIONES PRINCIPALES
        JLabel btnInicio = new JLabel();
        btnInicio.setIcon(new ImageIcon((new ImageIcon("Imagenes/LogoInicio.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        btnInicio.setBounds(10, 0, 70, 70);
        barraSuperior.add(btnInicio);
        
        JLabel btnMonitoreo = new JLabel();
        btnMonitoreo.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/grabaciones.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        btnMonitoreo.setBounds(100, 0, 70, 70);
        barraSuperior.add(btnMonitoreo);
        
        btnMonitoreo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RecargarColores();
                Monitoreo ventanaMapa = new Monitoreo();
                ventanaMapa.setVisible(true);
                setVisible(false);
                anim.detenerMensajes();
            }
            public void mouseEntered(MouseEvent e) {
                indicador.setBounds(95, 68, 65, 2);
                barraSuperior.add(indicador);
                barraSuperior.revalidate();
                barraSuperior.repaint();
            }
            public void mouseExited(MouseEvent e) {
                barraSuperior.remove(indicador);
                barraSuperior.revalidate();
                barraSuperior.repaint();
            }
        });
        
        JPanel separador1 = new JPanel();
        separador1.setBounds(170, 10, 2, 50);
        barraSuperior.add(separador1);
        
        JLabel btnInfo = new JLabel();
        btnInfo.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/libro.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        btnInfo.setBounds(190, 0, 70, 70);
        barraSuperior.add(btnInfo);
        
        btnInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RecargarColores();
                Info ventanaInfo = new Info();
                ventanaInfo.setVisible(true);
                setVisible(false);
                anim.detenerMensajes();
            }
            public void mouseEntered(MouseEvent e) {
                indicador.setBounds(180, 68, 70, 2);
                barraSuperior.add(indicador);
                barraSuperior.revalidate();
                barraSuperior.repaint();
            }
            public void mouseExited(MouseEvent e) {
                barraSuperior.remove(indicador);
                barraSuperior.revalidate();
                barraSuperior.repaint();
            }
        });
        
        if (usuario != null){
            if (usuario[16].equals("Administrador")){
                JPanel separador2 = new JPanel();
                separador2.setBounds(260, 10, 2, 50);
                barraSuperior.add(separador2);
                
                JLabel btnRegistrar = new JLabel();
                btnRegistrar.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/registroUsuario.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                btnRegistrar.setBounds(280, 0, 70, 70);
                barraSuperior.add(btnRegistrar);
                
                btnRegistrar.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        RecargarColores();
                        Registrar ventanaRegistrar = new Registrar();
                        ventanaRegistrar.setVisible(true);
                        setVisible(false);
                        anim.detenerMensajes();
                    }
                    public void mouseEntered(MouseEvent e) {
                        indicador.setBounds(270, 68, 65, 2);
                        barraSuperior.add(indicador);
                        barraSuperior.revalidate();
                        barraSuperior.repaint();
                    }
                    public void mouseExited(MouseEvent e) {
                        barraSuperior.remove(indicador);
                        barraSuperior.revalidate();
                        barraSuperior.repaint();
                    }
                });
            }
        }
        
        btnNotifi.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/campana.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        btnNotifi.setBounds(850, 0, 70, 70);
        barraSuperior.add(btnNotifi);
        
        btnNotifi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (verNotifi == true){
                    fondo.remove(pnlOpcionesPerfil);
                    fondo.remove(fondoPerfil);
                    fondo.remove(fondoNotifi);
                    fondo.repaint();
                    fondo.revalidate();
                    verNotifi = false;
                    opcionesPerfil = false;
                }else{
                    fondo.add(fondoNotifi);
                    fondo.remove(pnlOpcionesPerfil);
                    fondo.remove(fondoPerfil);
                    fondo.repaint();
                    fondo.revalidate();

                    opcionesPerfil = false;
                    verNotifi = true;
                }
            }
            public void mouseEntered(MouseEvent e) {
                indicador.setBounds(840, 68, 60, 2);
                barraSuperior.add(indicador);
                barraSuperior.revalidate();
                barraSuperior.repaint();
            }
            public void mouseExited(MouseEvent e) {
                barraSuperior.remove(indicador);
                barraSuperior.revalidate();
                barraSuperior.repaint();
            }
        });
        
        // PERFIL Y CONFIGURACIONES
        // Cargar la imagen original
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File("Imagenes/Iconos/marcoPerfil.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Color colorNuevo = Color.decode(colorPanelMedio);
        
        // Procesar la imagen y cambiar el color
        BufferedImage imagenProcesada = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagenProcesada.createGraphics();
        g.drawImage(originalImage, 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(colorNuevo);
        g.fillRect(0, 0, originalImage.getWidth(), originalImage.getHeight());
        g.dispose();
        
        // Escalar la imagen procesada
        Image imagenEscalaSuave = imagenProcesada.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        
        JLabel marcoPerfil = new JLabel(new ImageIcon(imagenEscalaSuave));
        marcoPerfil.setBounds(920, 10, 50, 50);
        barraSuperior.add(marcoPerfil);

        
        JLabel btnPerfil = new JLabel();
        if (usuario != null) {
            imagenNormal = new ImageIcon((new ImageIcon(usuario[9])).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            imagenOscura = oscurecerImagen(imagenNormal);
        } else {
            imagenNormal = new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            imagenOscura = oscurecerImagen(imagenNormal);
        }
        btnPerfil.setIcon(imagenNormal);
        btnPerfil.setBounds(920, 10, 50, 50);
        barraSuperior.add(btnPerfil);
        
        // Función al darle click a la imagen
        btnPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (opcionesPerfil == true){
                    fondo.remove(fondoNotifi);
                    fondo.remove(pnlOpcionesPerfil);
                    fondo.remove(fondoPerfil);
                    fondo.repaint();
                    fondo.revalidate();
                    
                    opcionesPerfil = false;
                    verNotifi = false;
                }else{
                    fondo.remove(fondoNotifi);
                    fondo.add(pnlOpcionesPerfil);
                    fondo.repaint();
                    fondo.revalidate();
                    
                    opcionesPerfil = true;
                    verNotifi = false;
                }
            }
            public void mouseEntered(MouseEvent e) {
                // Cuando el mouse entra, cambia la imagen a la más oscura
                btnPerfil.setIcon(imagenOscura);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Cuando el mouse sale, vuelve a la imagen normal
                btnPerfil.setIcon(imagenNormal);
            }
            
        });
        
        // PANEL PARA OPCIONES DE DEL MENÚ
        pnlOpcionesPerfil.setBackground(Color.decode(colorPanelMedio));
        pnlOpcionesPerfil.setOpaque(true);
        pnlOpcionesPerfil.setLayout(null);
        pnlOpcionesPerfil.setBounds(825, 70, 150, 90);
        
        JLabel botonPerfil = new JLabel();
        botonPerfil.setOpaque(true);
        botonPerfil.setBackground(null);
        botonPerfil.setBounds(0, 0, 150, 30);
        pnlOpcionesPerfil.add(botonPerfil);
        
        JLabel imgPerfil = new JLabel();
        imgPerfil.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/usuario.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        imgPerfil.setBounds(10, 0, 30, 30);
        botonPerfil.add(imgPerfil);
        
        JLabel textoPerfil = new JLabel("PERFIL");
        textoPerfil.setForeground(Color.white);
        textoPerfil.setBounds(40, 0, 110, 30);
        botonPerfil.add(textoPerfil);
        
        botonPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fondo.add(fondoPerfil);
                fondo.remove(pnlOpcionesPerfil);
                fondo.remove(fondoNotifi);
                fondo.repaint();
                fondo.revalidate();
                
                verNotifi = false;
            }
            public void mouseEntered(MouseEvent e) {
                botonPerfil.setBackground(Color.decode(colorBotonOscuro));
            }
            public void mouseExited(MouseEvent e) {
                botonPerfil.setBackground(null);
            }
        });
        
        JLabel botonConfig = new JLabel();
        botonConfig.setOpaque(true);
        botonConfig.setBackground(null);
        botonConfig.setBounds(0, 30, 150, 30);
        pnlOpcionesPerfil.add(botonConfig);
        
        JLabel imgConfig = new JLabel();
        imgConfig.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/engranaje.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        imgConfig.setBounds(10, 0, 30, 30);
        botonConfig.add(imgConfig);
        
        JLabel textoConfig = new JLabel("CONFIGURACIÓN");
        textoConfig.setForeground(Color.white);
        textoConfig.setBounds(40, 0, 110, 30);
        botonConfig.add(textoConfig);
        
        // Función al darle click a la opción
        botonConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RecargarColores();
                Configuraciones ventanaConfig = new Configuraciones();
                ventanaConfig.setVisible(true);
                setVisible(false);
            }
            public void mouseEntered(MouseEvent e) {
                botonConfig.setBackground(Color.decode(colorBotonOscuro));
            }
            public void mouseExited(MouseEvent e) {
                botonConfig.setBackground(null);
            }
        });
        
        JLabel btnVolver = new JLabel();
        btnVolver.setOpaque(true);
        btnVolver.setBackground(null);
        btnVolver.setBounds(0, 60, 150, 30);
        pnlOpcionesPerfil.add(btnVolver);
        
        JLabel imgVolver = new JLabel();
        imgVolver.setOpaque(true);
        imgVolver.setBackground(null);
        imgVolver.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/casa.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        imgVolver.setBounds(10, 0, 30, 30);
        btnVolver.add(imgVolver);
        
        JLabel txtVolver = new JLabel("SALIR");
        txtVolver.setForeground(Color.white);
        txtVolver.setBounds(40, 0, 110, 30);
        btnVolver.add(txtVolver);

        // Función al darle click a la opción
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RecargarColores();
                VerContra.activContra = false;
                Ingreso ventanaIngreso = new Ingreso();
                ventanaIngreso.ActualizarIngreso();
                ventanaIngreso.setVisible(true);
                setVisible(false);
                anim.detenerMensajes();
            }
            public void mouseEntered(MouseEvent e) {
                btnVolver.setBackground(Color.decode(colorBotonOscuro));
            }
            public void mouseExited(MouseEvent e) {
                btnVolver.setBackground(null);
            }
        });
        
        InfoPerfil();
        InfoNotifi();
        
        animFondo.setOpaque(false);
        animFondo.setLayout(null);
        animFondo.setBounds(0, 70, 985, 495);
        fondo.add(animFondo);
    }
    
    private void InfoPerfil(){
        // ----- INFORMACIÓN DEL USUARIO ----- 
        fondoPerfil.setBackground(Color.decode(colorPanelMedio));
        fondoPerfil.setBounds(680, 70, 300, 370);
        fondoPerfil.setLayout(null);

        JLabel imagenPerfil = new JLabel();
        //Le establecemos una imagen de perfil con un tamaño preestablecido.d
        if (usuario != null) {
            imagenPerfil.setIcon(new ImageIcon((new ImageIcon(usuario[9])).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        } else {
            imagenPerfil.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }

        imagenPerfil.setBounds(10, 10, 100, 100);
        fondoPerfil.add(imagenPerfil);
        
        JLabel titUsuario = new JLabel("USUARIO");
        titUsuario.setForeground(Color.white);
        titUsuario.setBounds(120, 10, 170, 10);
        fondoPerfil.add(titUsuario);
        
        JLabel txtUsuario = new JLabel();
        txtUsuario.setBackground(Color.decode(colorPanelClaro));
        txtUsuario.setBorder(new EmptyBorder(0,5,0,0));
        txtUsuario.setOpaque(true);
        txtUsuario.setForeground(Color.white);
        txtUsuario.setBounds(120, 25, 170, 30);
        fondoPerfil.add(txtUsuario);
        
        JLabel titCedula = new JLabel("DOCUMENTO");
        titCedula.setForeground(Color.white);
        titCedula.setBounds(120, 65, 170, 10);
        fondoPerfil.add(titCedula);
        
        JLabel txtCedula = new JLabel();
        txtCedula.setBackground(Color.decode(colorPanelClaro));
        txtCedula.setBorder(new EmptyBorder(0,5,0,0));
        txtCedula.setOpaque(true);
        txtCedula.setForeground(Color.white);
        txtCedula.setBounds(120, 80, 170, 30);
        fondoPerfil.add(txtCedula);
        
        JLabel titNombre = new JLabel("NOMBRE COMPLETO");
        titNombre.setForeground(Color.white);
        titNombre.setBounds(10, 130, 280, 10);
        fondoPerfil.add(titNombre);
        
        JLabel txtNombre = new JLabel();
        txtNombre.setBackground(Color.decode(colorPanelClaro));
        txtNombre.setBorder(new EmptyBorder(0,5,0,0));
        txtNombre.setOpaque(true);
        txtNombre.setForeground(Color.white);
        txtNombre.setBounds(10, 145, 280, 30);
        fondoPerfil.add(txtNombre);
        
        JLabel titEmail = new JLabel("CORREO");
        titEmail.setForeground(Color.white);
        titEmail.setBounds(10, 190, 280, 10);
        fondoPerfil.add(titEmail);
        
        JLabel txtEmail = new JLabel();
        txtEmail.setBackground(Color.decode(colorPanelClaro));
        txtEmail.setBorder(new EmptyBorder(0,5,0,0));
        txtEmail.setOpaque(true);
        txtEmail.setForeground(Color.white);
        txtEmail.setBounds(10, 205, 280, 30);
        fondoPerfil.add(txtEmail);
        
        JLabel titTelefono = new JLabel("CELULAR");
        titTelefono.setForeground(Color.white);
        titTelefono.setBounds(10, 250, 280, 10);
        fondoPerfil.add(titTelefono);
        
        JLabel txtTelefono = new JLabel();
        txtTelefono.setBackground(Color.decode(colorPanelClaro));
        txtTelefono.setBorder(new EmptyBorder(0,5,0,0));
        txtTelefono.setOpaque(true);
        txtTelefono.setForeground(Color.white);
        txtTelefono.setBounds(10, 265, 280, 30);
        fondoPerfil.add(txtTelefono);
        
        JLabel titGenero = new JLabel("GÉNERO");
        titGenero.setForeground(Color.white);
        titGenero.setBounds(10, 310, 280, 10);
        fondoPerfil.add(titGenero);
        
        JLabel txtGenero = new JLabel();
        txtGenero.setBackground(Color.decode(colorPanelClaro));
        txtGenero.setBorder(new EmptyBorder(0,5,0,0));
        txtGenero.setOpaque(true);
        txtGenero.setForeground(Color.white);
        txtGenero.setBounds(10, 325, 280, 30);
        fondoPerfil.add(txtGenero);
        
        if (usuario != null) {
            txtUsuario.setText(usuario[0]);
            txtNombre.setText(usuario[1] + " " + usuario[2]);
            txtCedula.setText(usuario[3]);
            txtEmail.setText(usuario[4]);
            txtTelefono.setText(usuario[8]);
            txtGenero.setText(usuario[7]);
        }
    }
    
    private void InfoNotifi(){
        // ----- INFORMACIÓN DEL USUARIO ----- 
        fondoNotifi.setBackground(Color.decode(colorPanelMedio));
        fondoNotifi.setBounds(740, 70, 240, 370);
        fondoNotifi.setLayout(null);
    }

    public static void Deteccion() {
        if (deteccionManager == true) {
            System.out.println("SISTEMA ENCENDIDO");
        } else {
            System.out.println("SISTEMA APAGADO");
        }
    }

    public void notifiDetect() {
        String carpeta = "Data/Notificaciones";

        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File[] listaDeArchivos = directorio.listFiles();

        if (listaDeArchivos != null && listaDeArchivos.length > 0) {
            //opcion3.setIcon(new ImageIcon((new ImageIcon(gifNotifi)).getImage().getScaledInstance(opcion3.getWidth(), opcion3.getHeight(), Image.SCALE_DEFAULT)));
        } else {
            btnNotifi.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/campana.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

        }
    }

    public void RecargarColores() {
        List<String> datos = configVents.CargarDatos();

        if (datos.get(0).equals("1")) {
            colorPanelClaro = "#011b5a";
            colorPanelMedio = "#000a45";
            colorPanelOscuro = "#121a2d";
            colorBotonClaro = "#85add5";
            colorBotonClaroSeleccion = "#415F7E";
            colorBotonOscuro = "#000e3c";
        } else if (datos.get(0).equals("2")) {
            colorPanelClaro = "#810505";
            colorPanelMedio = "#590303";
            colorPanelOscuro = "#440202";
            colorBotonClaro = "#E13737";
            colorBotonClaroSeleccion = "#892121";
            colorBotonOscuro = "#680C0C";

        } else if (datos.get(0).equals("3")) {
            colorPanelClaro = "#0E8105";
            colorPanelMedio = "#074500";
            colorPanelOscuro = "#043703";
            colorBotonClaro = "#2FB629";
            colorBotonClaroSeleccion = "#1C7421";
            colorBotonOscuro = "#12680C";

        } else if (datos.get(0).equals("4")) {
            colorPanelClaro = "#818105";
            colorPanelMedio = "#444500";
            colorPanelOscuro = "#323703";
            colorBotonClaro = "#B6B629";
            colorBotonClaroSeleccion = "#898921";
            colorBotonOscuro = "#64680C";

        }
    }
    
    // Función para crear una versión más oscura de un ImageIcon
    private ImageIcon oscurecerImagen(ImageIcon icon) {
        Image image = icon.getImage();

        // Creamos un BufferedImage para manipular la imagen
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        // Ajustamos el brillo para hacerla más oscura
        RescaleOp op = new RescaleOp(0.7f, 0, null);
        bufferedImage = op.filter(bufferedImage, null);

        // Convertimos el BufferedImage de nuevo a ImageIcon
        return new ImageIcon(bufferedImage);
    }

    public void ReiniciarConfig() {
        Configuraciones config = new Configuraciones();
        config.setVisible(true);
    }
}