package Ventanas;

import Logica.SaveConfiguraciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Configuraciones extends JFrame{
    Menu menu = new Menu();
    
    public JLabel fondo = new JLabel();
    JPanel elementos = new JPanel();
    
    public String datoTema = "1";
    
    public SaveConfiguraciones guardado = new SaveConfiguraciones();
    
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

        fondo = new JLabel();
        fondo.setIcon(new ImageIcon((new ImageIcon(menu.imagenFondo)).getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(null);
        this.add(fondo);
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBounds(905, 15, 50, 50);
        botonVolver.setFocusPainted(false);

        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setBackground(Color.decode(menu.colorBotonOscuro));
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        fondo.add(botonVolver);
        // Metodo para volver al menu
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
        
        Elementos();
    }
    private void Elementos(){
        elementos.setBackground(Color.decode(menu.colorPanelClaro));
        elementos.setLayout(new BoxLayout(elementos, BoxLayout.Y_AXIS));
        
        //Establecemos imagen de fondo.
        JScrollPane scrollFondo = new JScrollPane(elementos);
        scrollFondo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollFondo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFondo.setBounds(30,75,930,460);
        scrollFondo.setOpaque(true);
        scrollFondo.setBorder(null);
        fondo.add(scrollFondo);
        
        JLabel tituloUsuario = new JLabel("CONFIGURACIÓN USUARIO");
        tituloUsuario.setForeground(Color.white);
        tituloUsuario.setFont(new Font("Arial",1,40));
        tituloUsuario.setBounds(10, 15, 600, 50);
        fondo.add(tituloUsuario);
        
        ConfigUsuario();
        ConfigAdmin();
    }
    
    private void ConfigUsuario(){
        JPanel fondoConfigUsuario = new JPanel();
        fondoConfigUsuario.setOpaque(false);
        fondoConfigUsuario.setLayout(null);
        fondoConfigUsuario.setPreferredSize(new Dimension(1825, 450));
        fondoConfigUsuario.setMaximumSize(new Dimension(1825, 450));
        elementos.add(fondoConfigUsuario);
        
        JPanel configUsuario = new JPanel();
        configUsuario.setBackground(Color.decode(menu.colorPanelOscuro));
        configUsuario.setLayout(null);
        configUsuario.setBounds(10, 10, 890, 440);
        fondoConfigUsuario.add(configUsuario);
        
        JPanel temasApp = new JPanel();
        temasApp.setLayout(null);
        temasApp.setBackground(Color.decode(menu.colorPanelMedio));
        temasApp.setBounds(10, 10, 430, 420);
        configUsuario.add(temasApp);
        
        CambioTema(temasApp);
        
        JLabel tituloTemas = new JLabel("TEMAS");
        tituloTemas.setForeground(Color.white);
        tituloTemas.setFont(new Font("Arial",1,20));
        tituloTemas.setHorizontalAlignment(SwingConstants.CENTER);
        tituloTemas.setBounds(10, 10, 410, 25);
        temasApp.add(tituloTemas);
        
        JPanel modificarPerfil = new JPanel();
        modificarPerfil.setLayout(null);
        modificarPerfil.setBackground(Color.decode(menu.colorPanelMedio));
        modificarPerfil.setBounds(450, 10, 430, 420);
        configUsuario.add(modificarPerfil);
        
        ElementosModificarPerfil(modificarPerfil);
        
        JLabel tituloModificar = new JLabel("MODIFICAR PERFIL");
        tituloModificar.setForeground(Color.white);
        tituloModificar.setFont(new Font("Arial",1,20));
        tituloModificar.setHorizontalAlignment(SwingConstants.CENTER);
        tituloModificar.setBounds(10, 10, 410, 25);
        modificarPerfil.add(tituloModificar);
    }
    
    private void ConfigAdmin(){
        JPanel fondoConfigAdmin = new JPanel();
        fondoConfigAdmin.setOpaque(false);
        fondoConfigAdmin.setLayout(null);
        fondoConfigAdmin.setPreferredSize(new Dimension(1825, 450));
        fondoConfigAdmin.setMaximumSize(new Dimension(1825, 450));
        elementos.add(fondoConfigAdmin);
        
        JPanel configAdmin = new JPanel();
        configAdmin.setBackground(Color.decode(menu.colorPanelOscuro));
        configAdmin.setLayout(null);
        configAdmin.setBounds(10, 10, 890, 440);
        fondoConfigAdmin.add(configAdmin);
        
        JPanel Propiedades = new JPanel();
        Propiedades.setLayout(null);
        Propiedades.setBackground(Color.decode(menu.colorPanelMedio));
        Propiedades.setBounds(10, 10, 430, 420);
        configAdmin.add(Propiedades);
        
        ElementosPropiedades(Propiedades);
        
        JLabel tituloPropiedades = new JLabel("PROPIEDADES");
        tituloPropiedades.setForeground(Color.white);
        tituloPropiedades.setFont(new Font("Arial",1,20));
        tituloPropiedades.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPropiedades.setBounds(10, 10, 410, 25);
        Propiedades.add(tituloPropiedades);
        
        JPanel editarUsuarios = new JPanel();
        editarUsuarios.setLayout(null);
        editarUsuarios.setBackground(Color.decode(menu.colorPanelMedio));
        editarUsuarios.setBounds(450, 10, 430, 420);
        configAdmin.add(editarUsuarios);
        
        ElementosUsuarios(editarUsuarios);
        
        JLabel tituloEditar = new JLabel("EDITAR USUARIOS");
        tituloEditar.setForeground(Color.white);
        tituloEditar.setFont(new Font("Arial",1,20));
        tituloEditar.setHorizontalAlignment(SwingConstants.CENTER);
        tituloEditar.setBounds(10, 10, 410, 25);
        editarUsuarios.add(tituloEditar);
    }
    
    private void CambioTema(JPanel temasApp){
        JButton tema1 = new JButton();
        tema1.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/temaAzul.png")).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        tema1.setBounds(35, 45, 170, 170);
        tema1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datoTema = "1";
                List<String> datos = guardado.CargarDatos();
                
                if (!datos.get(0).equals(datoTema)){
                    guardado.GuardarConfiguraciones(datoTema);
                    menu.RecargarColores();

                    dispose();
                    menu.ReiniciarConfig();
                }
            }
        });
        temasApp.add(tema1);
        
        
        JButton tema2 = new JButton();
        tema2.setBounds(225, 45, 170, 170);
        tema2.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/temaRojo.png")).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        tema2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datoTema = "2";
                List<String> datos = guardado.CargarDatos();
                
                if (!datos.get(0).equals(datoTema)){
                    guardado.GuardarConfiguraciones(datoTema);
                    menu.RecargarColores();

                    dispose();
                    menu.ReiniciarConfig();
                }
            }
        });
        temasApp.add(tema2);
        
        JButton tema3 = new JButton();
        tema3.setBounds(35, 235, 170, 170);
        tema3.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/temaVerde.png")).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        tema3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datoTema = "3";
                List<String> datos = guardado.CargarDatos();
                
                if (!datos.get(0).equals(datoTema)){
                    guardado.GuardarConfiguraciones(datoTema);
                    menu.RecargarColores();

                    dispose();
                    menu.ReiniciarConfig();
                }
            }
        });
        temasApp.add(tema3);
        
        JButton tema4 = new JButton();
        tema4.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/temaAmarillo.png")).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH)));
        tema4.setBounds(225, 235, 170, 170);
        tema4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datoTema = "4";
                List<String> datos = guardado.CargarDatos();
                
                if (!datos.get(0).equals(datoTema)){
                    guardado.GuardarConfiguraciones(datoTema);
                    menu.RecargarColores();

                    dispose();
                    menu.ReiniciarConfig();
                }  
            }
        });
        temasApp.add(tema4);
    }
    
    private void ElementosModificarPerfil(JPanel modificarPerfil){
        JLabel imagenUsuario = new JLabel();
        imagenUsuario.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png")).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
        imagenUsuario.setBounds(20, 50, 180, 180);
        modificarPerfil.add(imagenUsuario);
        
        JLabel tituloNombre = new JLabel("USUARIO:");
        tituloNombre.setForeground(Color.white);
        tituloNombre.setBounds(210, 50, 70, 20);
        modificarPerfil.add(tituloNombre);
        
        JTextField textoNombre = new JTextField();
        textoNombre.setBounds(210, 80, 200, 20);
        modificarPerfil.add(textoNombre);
        
        JLabel tituloContrasena = new JLabel("CONTRASEÑA:");
        tituloContrasena.setForeground(Color.white);
        tituloContrasena.setBounds(210, 120, 100, 20);
        modificarPerfil.add(tituloContrasena);
        
        JTextField textoContrasena = new JTextField();
        textoContrasena.setBounds(210, 150, 200, 20);
        modificarPerfil.add(textoContrasena);
        
        JButton cambioImagen = new JButton("CAMBIAR IMAGEN");
        cambioImagen.setBackground(Color.decode(menu.colorBotonClaro));
        cambioImagen.setFocusPainted(false);
        cambioImagen.setBounds(210, 200, 200, 30);
        modificarPerfil.add(cambioImagen);
        
        JPanel mensaje = new JPanel();
        mensaje.setBorder(new EmptyBorder(10,10,10,10));
        mensaje.setBackground(Color.decode(menu.colorPanelClaro));
        mensaje.setBounds(20, 250, 390, 150);
        modificarPerfil.add(mensaje);
        
        JLabel textoMensaje = new JLabel();
        textoMensaje.setText("<html><p>Comúnicate con un administrador para que este realice<br>"
                                    + "la modificación de otros datos a los cuales no tienes<br>"
                                    + "acceso.      :)</p></html>");
        textoMensaje.setForeground(Color.white);
        mensaje.add(textoMensaje);
    }
    
    private void ElementosPropiedades(JPanel propiedades){
        // SENSIBILIDAD DE CAMARA
        JLabel tituloSensibilidad = new JLabel("SENSIBILIDAD:");
        tituloSensibilidad.setForeground(Color.white);
        tituloSensibilidad.setBounds(20, 50, 100, 20);
        propiedades.add(tituloSensibilidad);
        
        // Primer valor es lo minimo, segundo valor es lo maximo, tercer valor es con el que inicia.
        JSlider sensibilidadSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 30);
        sensibilidadSlider.setMinorTickSpacing(1);
        sensibilidadSlider.setPaintTicks(true);
        sensibilidadSlider.setPaintLabels(false);
        sensibilidadSlider.setSnapToTicks(true);
        sensibilidadSlider.setOpaque(false);
        sensibilidadSlider.setBounds(20, 70, 360, 40);
        propiedades.add(sensibilidadSlider);
        
        JLabel valorSensibilidad = new JLabel("30");
        valorSensibilidad.setForeground(Color.white);
        valorSensibilidad.setBounds(390, 80, 70, 20);
        propiedades.add(valorSensibilidad);
        
        // Agrega un ChangeListener al slider para cambiar el texto del label según cambie.
        sensibilidadSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Cuando cambia el slider, actualiza el texto de valorSensibilidad.
                int valor = sensibilidadSlider.getValue();
                valorSensibilidad.setText(String.valueOf(valor));
            }
        });
        
        JPanel mensaje1 = new JPanel();
        mensaje1.setBorder(new EmptyBorder(10,10,10,10));
        mensaje1.setBackground(Color.decode(menu.colorPanelClaro));
        mensaje1.setBounds(20, 120, 390, 80);
        propiedades.add(mensaje1);
        
        JLabel textoMensaje1 = new JLabel();
        textoMensaje1.setText("<html><p>Desde este slider puedes establecer la sensibiliad de<br>"
                                    + "la cámara, 10 es el valor recomendado pero puedes<br>"
                                    + "modificarla a tu gusto.</p></html>");
        textoMensaje1.setForeground(Color.white);
        mensaje1.add(textoMensaje1);
        
        // DURACION DE VIDEO
        JLabel tituloDuracion = new JLabel("DURACIÓN DE GRABACIÓN:");
        tituloDuracion.setForeground(Color.white);
        tituloDuracion.setBounds(20, 220, 200, 20);
        propiedades.add(tituloDuracion);
        
        // Primer valor es lo minimo, segundo valor es lo maximo, tercer valor es con el que inicia.
        JSlider duracionSlider = new JSlider(JSlider.HORIZONTAL, 5000, 60000, 10000);
        duracionSlider.setMinorTickSpacing(1000);
        duracionSlider.setPaintTicks(true);
        duracionSlider.setPaintLabels(false);
        duracionSlider.setSnapToTicks(true);
        duracionSlider.setOpaque(false);
        duracionSlider.setBounds(20, 240, 360, 40);
        propiedades.add(duracionSlider);
        
        JLabel valorDuracion = new JLabel("10");
        valorDuracion.setForeground(Color.white);
        valorDuracion.setBounds(390, 250, 70, 20);
        propiedades.add(valorDuracion);
        
        // Agrega un ChangeListener al slider para cambiar el texto del label según cambie.
        duracionSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Cuando cambia el slider, actualiza el texto de valorDuración en segundos.
                int valor = duracionSlider.getValue();
                int Segundos = valor / 1000;
                valorDuracion.setText(String.valueOf(Segundos));
            }
        });
        
        JPanel mensaje2 = new JPanel();
        mensaje2.setBorder(new EmptyBorder(10,10,10,10));
        mensaje2.setBackground(Color.decode(menu.colorPanelClaro));
        mensaje2.setBounds(20, 290, 390, 80);
        propiedades.add(mensaje2);
        
        JLabel textoMensaje2 = new JLabel();
        textoMensaje2.setText("<html><p>Desde este slider puedes modificar la duración de las<br>"
                                    + "grabaciones, donde el valor por defecto son 10 segundos,<br>"
                                    + "mínimo son 5 segundos y máximo son 60 segundos (1 minuto).</p></html>");
        textoMensaje2.setForeground(Color.white);
        mensaje2.add(textoMensaje2);
        
        
        JButton botonGuardar = new JButton("GUARDAR");
        botonGuardar.setBackground(Color.decode(menu.colorBotonClaro));
        botonGuardar.setFocusPainted(false);
        botonGuardar.setBounds(320,390,90,20);
        propiedades.add(botonGuardar);
        }
    
    private void ElementosUsuarios(JPanel editarUsuarios){
        
    }
}