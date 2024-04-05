package Ventanas;

import Logica.SaveConfiguraciones;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame{
    public static boolean deteccionManager = false;
    
    public JLabel fondo = new JLabel();
    
    JButton opcion3 = new JButton();
    
    SaveConfiguraciones configVents = new SaveConfiguraciones();
    
    public Menu(){
        RecargarColores();
        PanelFondo();
        Deteccion();
    }
    
    // TEMAS
    public static String imagenFondo = "Imagenes/fondoAzul.png";
    public static String imagenMonitoreo = "Imagenes/Iconos/botonMonitoreoAzul.png";
    public static String imagenInfo = "Imagenes/Iconos/botonManualAzul.png";
    public static String imagenNotifi = "Imagenes/Iconos/botonNotificacionesAzul.png";
    public static String gifNotifi = "Imagenes/Iconos/botonNotificacionesAnimAzul.gif";
    public static String imagenConfig = "Imagenes/Iconos/botonConfigAzul.png";
    
    public static String colorPanelClaro = "#011b5a";
    public static String colorPanelMedio = "#000a45";
    public static String colorPanelOscuro = "#121a2d";
    public static String colorBotonClaro = "#85add5";
    public static String colorBotonClaroSeleccion = "#415F7E";
    public static String colorBotonOscuro = "#000e3c";
    
    private void PanelFondo(){
        
        //Creamos la ventana.
        setTitle("Menu");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon((new ImageIcon(imagenFondo)).getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(null);
        fondo.setBorder(new EmptyBorder(10,10,10,10)); //Establecemos margenes en el fondo.
        this.add(fondo);
        
        ParteSuperior();
        ParteInferior();
    }
        
    private void ParteSuperior(){
        //Creamos un panel para el titulo y el boton volver.
        JPanel elementosSuperiores = new JPanel();
        elementosSuperiores.setLayout(null);
        elementosSuperiores.setOpaque(false);
        elementosSuperiores.setBounds(320,0,660,100);
        fondo.add(elementosSuperiores);
        
        //Perfil con descripción.
        JPanel fondoPerfil = new JPanel();
        fondoPerfil.setBackground(Color.decode(colorPanelMedio));
        fondoPerfil.setBounds(0,0,320,600);
        fondoPerfil.setLayout(null);
        fondo.add(fondoPerfil);
        
        JLabel imagenPerfil = new JLabel();
        //Le establecemos una imagen de perfil con un tamaño preestablecido.
        imagenPerfil.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        imagenPerfil.setBounds(55,10,200,200);
        fondoPerfil.add(imagenPerfil);
        
        
        //Creamos un titulo de bienvenida.
        JLabel texto1 = new JLabel("<html><body><center><p>Bienvenida Hormiguita</p></html>"); //se puede utilizar html.
        texto1.setFont(new Font("Constantia Bold",1,35));
        texto1.setHorizontalAlignment(SwingConstants.CENTER);
        texto1.setForeground(Color.white); //Le establecemos un color con formato hexadecimal. //Centrar el texto
        texto1.setBounds(80,30, 440, 40);
        elementosSuperiores.add(texto1);

        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBackground(Color.decode(colorBotonOscuro));
        
        botonVolver.setBounds(915, 30, 50, 50);
        botonVolver.setFocusPainted(false);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        fondo.add(botonVolver);
        
        
        //Funciones botones.
        ActionListener irIngreso = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecargarColores();
                Ingreso ventanaIngreso = new Ingreso();
                ventanaIngreso.ActualizarIngreso();
                ventanaIngreso.setVisible(true);
                setVisible(false);
            }
        };
        botonVolver.addActionListener(irIngreso);
        
        JButton botonRegistrar = new JButton();
        botonRegistrar.setBackground(Color.decode(colorBotonOscuro));
        botonRegistrar.setBounds(915, 90, 50, 50);
        botonRegistrar.setFocusPainted(false);
        
        ImageIcon logoRegistrar = new ImageIcon("Imagenes/Iconos/registroUsuario.png");
        botonRegistrar.setIcon(new ImageIcon(logoRegistrar.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        fondo.add(botonRegistrar);
        
        
        //Funciones botones.
        ActionListener irRegistro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecargarColores();
                Registrar ventanaRegistrar = new Registrar();
                ventanaRegistrar.setVisible(true);
                setVisible(false);
            }
        };
        botonRegistrar.addActionListener(irRegistro);
    }
    
    private void ParteInferior(){
        //Separamos un poco los botones del titulo.
        fondo.add(Box.createRigidArea(new Dimension(0,30)));
        
        //Creamos un panel para colocar las opciones de ventanas.
        JPanel botonesPanel = new JPanel();
        botonesPanel.setOpaque(false);
        botonesPanel.setLayout(null);
        botonesPanel.setBounds(150,100,970,500);
        fondo.add(botonesPanel);
        
        //Creamos los botones para ir a las diferentes ventanas.
        //MONITOREO
        JButton opcion1 = new JButton();
        opcion1.setBounds(265, 10, 170, 176);
        ImageIcon logoArea = new ImageIcon(imagenMonitoreo);
        opcion1.setIcon(new ImageIcon(logoArea.getImage().getScaledInstance(opcion1.getWidth(), opcion1.getHeight(), Image.SCALE_DEFAULT)));
        
        botonesPanel.add(opcion1);
        
        //INFORMACIÓN
        JButton opcion2 = new JButton();
        opcion2.setBounds(505, 10, 170, 176);
        ImageIcon logoManual = new ImageIcon(imagenInfo);
        opcion2.setIcon(new ImageIcon(logoManual.getImage().getScaledInstance(opcion2.getWidth(), opcion2.getHeight(), Image.SCALE_DEFAULT)));
        
        botonesPanel.add(opcion2);
        
        //NOTIFICACIONES
        opcion3.setBounds(265, 230, 170, 176);
        notifiDetect();
        botonesPanel.add(opcion3);
        
        //CONFIGURACIONES
        JButton opcion4 = new JButton();
        opcion4.setBounds(505, 230, 170, 176);
        ImageIcon logoConfig = new ImageIcon(imagenConfig);
        opcion4.setIcon(new ImageIcon(logoConfig.getImage().getScaledInstance(opcion4.getWidth(), opcion4.getHeight(), Image.SCALE_DEFAULT)));
        
        botonesPanel.add(opcion4);
        
        //Funciones Botones.
        ActionListener irMonitoreo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecargarColores();
                Monitoreo ventanaMapa = new Monitoreo();
                ventanaMapa.setVisible(true);
                setVisible(false);
            }
        };
        opcion1.addActionListener(irMonitoreo);
        
        ActionListener irInfo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecargarColores();
                Info ventanaInfo = new Info();
                ventanaInfo.setVisible(true);
                setVisible(false);
            }
        };
        opcion2.addActionListener(irInfo);
        
        ActionListener irNotificaciones = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecargarColores();
                Notificaciones ventanaNotificaciones = new Notificaciones();
                ventanaNotificaciones.setVisible(true);
                setVisible(false);
            }
        };
        opcion3.addActionListener(irNotificaciones);
        
        ActionListener irConfig = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecargarColores();
                Configuraciones ventanaConfig = new Configuraciones();
                ventanaConfig.setVisible(true);
                setVisible(false);
            }
        };
        opcion4.addActionListener(irConfig);
    }
    
    public static void Deteccion(){
        if (deteccionManager == true){
            System.out.println("SISTEMA ENCENDIDO");
        }else{
            System.out.println("SISTEMA APAGADO");
        }
    }
    
    public void notifiDetect(){
        String carpeta = "Data/Notificaciones";
        
        // Crear la carpeta si no existe
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        
        File[] listaDeArchivos = directorio.listFiles();
        
        if (listaDeArchivos != null && listaDeArchivos.length > 0) {
            opcion3.setIcon(new ImageIcon((new ImageIcon(gifNotifi)).getImage().getScaledInstance(opcion3.getWidth(), opcion3.getHeight(), Image.SCALE_DEFAULT)));
        } else {
            opcion3.setIcon(new ImageIcon((new ImageIcon(imagenNotifi)).getImage().getScaledInstance(opcion3.getWidth(), opcion3.getHeight(), Image.SCALE_DEFAULT)));
            
        }
    }
    
    public void RecargarColores(){
        List<String> datos = configVents.CargarDatos();
        
        if (datos.get(0).equals("1")){
            imagenFondo = "Imagenes/fondoAzul.png";
            imagenMonitoreo = "Imagenes/Iconos/botonMonitoreoAzul.png";
            imagenInfo = "Imagenes/Iconos/botonManualAzul.png";
            imagenNotifi = "Imagenes/Iconos/botonNotificacionesAzul.png";
            gifNotifi = "Imagenes/Iconos/botonNotificacionesAnimAzul.gif";
            imagenConfig = "Imagenes/Iconos/botonConfigAzul.png";

            colorPanelClaro = "#011b5a";
            colorPanelMedio = "#000a45";
            colorPanelOscuro = "#121a2d";
            colorBotonClaro = "#85add5";
            colorBotonClaroSeleccion = "#415F7E";
            colorBotonOscuro = "#000e3c";
        }else if(datos.get(0).equals("2")){
            imagenFondo = "Imagenes/fondoRojo.png";
            imagenMonitoreo = "Imagenes/Iconos/botonMonitoreoRojo.png";
            imagenInfo = "Imagenes/Iconos/botonManualRojo.png";
            imagenNotifi = "Imagenes/Iconos/botonNotificacionesRojo.png";
            gifNotifi = "Imagenes/Iconos/botonNotificacionesAnimRojo.gif";
            imagenConfig = "Imagenes/Iconos/botonConfigRojo.png";
            
            colorPanelClaro = "#810505";
            colorPanelMedio = "#590303";
            colorPanelOscuro = "#440202";
            colorBotonClaro = "#E13737";
            colorBotonClaroSeleccion = "#892121";
            colorBotonOscuro = "#680C0C";
            
        }else if(datos.get(0).equals("3")){
            imagenFondo = "Imagenes/fondoVerde.png";
            imagenMonitoreo = "Imagenes/Iconos/botonMonitoreoVerde.png";
            imagenInfo = "Imagenes/Iconos/botonManualVerde.png";
            imagenNotifi = "Imagenes/Iconos/botonNotificacionesVerde.png";
            gifNotifi = "Imagenes/Iconos/botonNotificacionesAnimVerde.gif";
            imagenConfig = "Imagenes/Iconos/botonConfigVerde.png";
            
            colorPanelClaro = "#0E8105";
            colorPanelMedio = "#074500";
            colorPanelOscuro = "#024402";
            colorBotonClaro = "#2FB629";
            colorBotonClaroSeleccion = "#1C7421";
            colorBotonOscuro = "#12680C";
            
        }else if(datos.get(0).equals("4")){
            imagenFondo = "Imagenes/fondoAmarillo.png";
            imagenMonitoreo = "Imagenes/Iconos/botonMonitoreoAmarillo.png";
            imagenInfo = "Imagenes/Iconos/botonManualAmarillo.png";
            imagenNotifi = "Imagenes/Iconos/botonNotificacionesAmarillo.png";
            gifNotifi = "Imagenes/Iconos/botonNotificacionesAnimAmarillo.gif";
            imagenConfig = "Imagenes/Iconos/botonConfigAmarillo.png";
            
            colorPanelClaro = "#818105";
            colorPanelMedio = "#444500";
            colorPanelOscuro = "#434402";
            colorBotonClaro = "#B6B629";
            colorBotonClaroSeleccion = "#898921";
            colorBotonOscuro = "#64680C";
            
        }
    }
    
    public void ReiniciarConfig(){
        Configuraciones config = new Configuraciones();
        config.setVisible(true);
    }
}