package Ventanas;

import Logica.AnimMenu;
import Logica.CameraManager;
import Logica.SaveConfiguraciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Configuraciones extends JFrame{
    Menu menu = new Menu();
    public AnimMenu anim = new AnimMenu();
    
    public JPanel fondo = new JPanel();
    JPanel elementos = new JPanel();
    
    public String datoTema = "1";
    boolean activContra = false;
    
    public SaveConfiguraciones guardado = new SaveConfiguraciones();
    CameraManager camaras = new CameraManager();
    
    String Ruta;
    
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

        fondo = new JPanel();
        fondo.setBackground(Color.decode(menu.colorPanelClaro));
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
                anim.detenerMensajes();
                anim.standar();
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
        
        String tipoUsuario = "";
        if (menu.usuario[16].equals("Administrador")){
            tipoUsuario = "ADMINISTRADOR";
        }else if(menu.usuario[16].equals("Usuario")){
            tipoUsuario = "USUARIO";
        }
        
        JLabel tituloUsuario = new JLabel("CONFIGURACIÓN " + tipoUsuario);
        tituloUsuario.setForeground(Color.white);
        tituloUsuario.setFont(new Font("Arial",1,40));
        tituloUsuario.setBounds(10, 15, 800, 50);
        fondo.add(tituloUsuario);
        
        ConfigUsuario();
        if (menu.usuario[16].equals("Administrador")){
            ConfigAdmin();
        }
        
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
                    guardado.GuardarConfiguraciones(datoTema, datos.get(1), datos.get(2));
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
                    guardado.GuardarConfiguraciones(datoTema, datos.get(1), datos.get(2));
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
                    guardado.GuardarConfiguraciones(datoTema, datos.get(1), datos.get(2));
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
                    guardado.GuardarConfiguraciones(datoTema, datos.get(1), datos.get(2));
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
        imagenUsuario.setIcon(new ImageIcon((new ImageIcon(menu.usuario[9])).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
        imagenUsuario.setBounds(20, 50, 180, 180);
        modificarPerfil.add(imagenUsuario);
        
        JLabel tituloNombre = new JLabel("USUARIO:");
        tituloNombre.setForeground(Color.white);
        tituloNombre.setBounds(210, 50, 70, 20);
        modificarPerfil.add(tituloNombre);
        
        JTextField textoNombre = new JTextField();
        textoNombre.setBounds(210, 80, 200, 20);
        textoNombre.setText(menu.usuario[0]);
        modificarPerfil.add(textoNombre);
        
        JLabel tituloContrasena = new JLabel("CONTRASEÑA:");
        tituloContrasena.setForeground(Color.white);
        tituloContrasena.setBounds(210, 120, 100, 20);
        modificarPerfil.add(tituloContrasena);
        
        JPasswordField textoContrasena = new JPasswordField();
        textoContrasena.setBounds(210, 150, 170, 20);
        modificarPerfil.add(textoContrasena);
        
        ImageIcon logoVer = new ImageIcon("Imagenes/Iconos/ver.png");
        ImageIcon logoNoVer = new ImageIcon("Imagenes/Iconos/nover.png");
        
        JLabel botonVerContra = new JLabel();
        botonVerContra.setBounds(390, 150, 20, 20);
        botonVerContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        modificarPerfil.add(botonVerContra);

        botonVerContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (activContra == false){
                    textoContrasena.setEchoChar((char) 0);
                    botonVerContra.setIcon(new ImageIcon(logoVer.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                    activContra = true;
                }else{
                    textoContrasena.setEchoChar('*');
                    botonVerContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                    activContra = false;
                }
            }
            
        });
        
        JButton cambioImagen = new JButton("CAMBIAR IMAGEN");
        cambioImagen.setBackground(Color.decode(menu.colorBotonClaro));
        cambioImagen.setFocusPainted(false);
        cambioImagen.setBounds(210, 200, 200, 30);
        modificarPerfil.add(cambioImagen);
        
        Ruta = menu.usuario[9];
        
        cambioImagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                jFileChooser.setFileFilter(filtrado);
                
                int respuesta = jFileChooser.showOpenDialog(Configuraciones.this);
                
                if (respuesta == JFileChooser.APPROVE_OPTION){
                    Ruta = jFileChooser.getSelectedFile().getPath();
                    imagenUsuario.setIcon(new ImageIcon((new ImageIcon(Ruta)).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
                    imagenUsuario.repaint();
                }else{
                    Ruta = menu.usuario[9];
                }
            }
        });
        
        JButton botonGuardar = new JButton("GUARDAR");
        botonGuardar.setBackground(Color.decode(menu.colorBotonClaro));
        botonGuardar.setFocusPainted(false);
        botonGuardar.setBounds(310,370,90,20);
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // VALIDACIONES
                if (textoNombre.getText().trim().equals("") || String.valueOf(textoContrasena.getPassword()).trim().equals("")){
                    JOptionPane.showMessageDialog(Configuraciones.this, "Por favor ingrese todos los datos.", "Ingresar Datos", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (textoNombre.getText().trim().length() > 20){
                    JOptionPane.showMessageDialog(Configuraciones.this, "Ingrese menos de 20 caracteres en el usuario.", "Mucho texto.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (textoNombre.getText().trim().contains("|") || String.valueOf(textoContrasena.getPassword()).trim().contains("|")){
                    JOptionPane.showMessageDialog(Configuraciones.this, "No puede ingresar el caracter \" | \"", "Caracter Invalido.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                System.out.println("SE EDITÓ EL USUARIO CORRECATMENTE PERO FALTA EL METODO PARA PODER HACERLO");
            }
        });
        modificarPerfil.add(botonGuardar);
        
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
        List<String> datos = guardado.CargarDatos();
        
        JLabel tituloSensibilidad = new JLabel("SENSIBILIDAD:");
        tituloSensibilidad.setForeground(Color.white);
        tituloSensibilidad.setBounds(20, 50, 100, 20);
        propiedades.add(tituloSensibilidad);
        
        // Crear el JSlider con el rango visual de 10 a 200
        JSlider sensibilidadSlider = new JSlider(JSlider.HORIZONTAL, 10, 200, Integer.parseInt(datos.get(1)));
        sensibilidadSlider.setMinorTickSpacing(10);
        sensibilidadSlider.setPaintTicks(false);
        sensibilidadSlider.setPaintLabels(false);
        sensibilidadSlider.setSnapToTicks(true);
        sensibilidadSlider.setOpaque(false);
        sensibilidadSlider.setBounds(20, 70, 360, 40);
        propiedades.add(sensibilidadSlider);

        // Crear el JLabel con el valor inicial (10 a 200)
        JLabel valorSensibilidad = new JLabel(datos.get(1));
        valorSensibilidad.setForeground(Color.white);
        valorSensibilidad.setBounds(390, 80, 70, 20);
        propiedades.add(valorSensibilidad);

        // Agregar un ChangeListener al slider para cambiar el texto del label según cambie
        sensibilidadSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Cuando cambia el slider, actualiza el texto de valorSensibilidad.
                // Invierte el valor para que vaya de 200 a 10 internamente
                int valor = 210 - sensibilidadSlider.getValue(); // Rango de 200 a 10 (200 - 10 + 1)
                // Invierte el valor para que se muestre correctamente en el JLabel
                int valorMostrado = 210 - valor; // Rango de 10 a 200 (200 - 10 + 1)
                valorSensibilidad.setText(String.valueOf(valorMostrado));
            }
        });
        
        JPanel mensaje1 = new JPanel();
        mensaje1.setBackground(Color.decode(menu.colorPanelClaro));
        mensaje1.setBounds(20, 120, 390, 80);
        propiedades.add(mensaje1);
        
        JLabel textoMensaje1 = new JLabel();
        textoMensaje1.setText("<html><p>Desde este slider puedes establecer la sensibiliad de<br>"
                                    + "la cámara, se necesita reiniciar el sistema de detección<br>"
                                    + "de movimiento para efectuar los cambios guardados.<br>"
                                    + "10 es muy sensible y 200 es poco sensible.</p></html>");
        textoMensaje1.setForeground(Color.white);
        mensaje1.add(textoMensaje1);
        
        // DURACION DE VIDEO
        JLabel tituloDuracion = new JLabel("DURACIÓN DE GRABACIÓN:");
        tituloDuracion.setForeground(Color.white);
        tituloDuracion.setBounds(20, 220, 200, 20);
        propiedades.add(tituloDuracion);
        
        // Primer valor es lo minimo, segundo valor es lo maximo, tercer valor es con el que inicia.
        JSlider duracionSlider = new JSlider(JSlider.HORIZONTAL, 5000, 60000, Integer.parseInt(datos.get(2)));
        duracionSlider.setMinorTickSpacing(1000);
        duracionSlider.setPaintTicks(true);
        duracionSlider.setPaintLabels(false);
        duracionSlider.setSnapToTicks(true);
        duracionSlider.setOpaque(false);
        duracionSlider.setBounds(20, 240, 360, 40);
        propiedades.add(duracionSlider);
        
        JLabel valorDuracion = new JLabel(String.valueOf(Integer.parseInt(datos.get(2)) / 1000));
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
                                    + "grabaciones, es importante saber que la duración no es precisa,<br>"
                                    + "mínimo son 5 segundos y máximo son 60 segundos (1 minuto).</p></html>");
        textoMensaje2.setForeground(Color.white);
        mensaje2.add(textoMensaje2);
        
        
        JButton botonGuardar = new JButton("GUARDAR");
        botonGuardar.setBackground(Color.decode(menu.colorBotonClaro));
        botonGuardar.setFocusPainted(false);
        botonGuardar.setBounds(320,390,90,20);
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                camaras.sensibilidadCamara = sensibilidadSlider.getValue();
                camaras.duracionVideo = Integer.parseInt(valorDuracion.getText()) * 1000;
                guardado.GuardarConfiguraciones(datos.get(0), valorSensibilidad.getText(), String.valueOf(Integer.parseInt(valorDuracion.getText()) * 1000));
            }
        });
        propiedades.add(botonGuardar);
        }
    
    private void ElementosUsuarios(JPanel editarUsuarios){
        JPanel panelUsuarios = new JPanel();
        panelUsuarios.setBackground(Color.decode(menu.colorPanelClaro));
        
        JScrollPane scrollUsuarios = new JScrollPane(panelUsuarios);
        scrollUsuarios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollUsuarios.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollUsuarios.setOpaque(true);
        scrollUsuarios.setBorder(null);
        scrollUsuarios.setBounds(10, 50, 320, 360);
        editarUsuarios.add(scrollUsuarios);
        
        JButton botonEditar = new JButton();
        botonEditar.setBackground(Color.decode(menu.colorBotonClaro));
        botonEditar.setFocusPainted(false);
        botonEditar.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/editar.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        botonEditar.setBounds(350, 50, 70, 70);
        editarUsuarios.add(botonEditar);
        
        JButton botonEliminar = new JButton();
        botonEliminar.setBackground(Color.decode(menu.colorBotonClaro));
        botonEliminar.setFocusPainted(false);
        botonEliminar.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/basura.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        botonEliminar.setBounds(350, 140, 70, 70);
        editarUsuarios.add(botonEliminar);
    }
}