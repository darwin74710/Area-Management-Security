package Ventanas;

import Logica.AnimMenu;
import Logica.CameraManager;
import Guardado.SaveConfiguraciones;
import Guardado.SaveUsuarios;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
    SaveUsuarios guardadoUsu = new SaveUsuarios();
    
    public JPanel fondo = new JPanel();
    JPanel panelUsuarios = new JPanel();
    JPanel elementos = new JPanel();
    
    JLabel imagenUsu = new JLabel();
    
    public JButton usuarioSeleccionado;
    
    public String datoTema = "1";
    public String nombreUsuario;
    
    JTextField textoNombreUsu = new JTextField();
    
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
        if (menu.usuario.get(16).equals("Administrador")){
            tipoUsuario = "ADMINISTRADOR";
        }else if(menu.usuario.get(16).equals("Usuario")){
            tipoUsuario = "USUARIO";
        }
        
        JLabel tituloUsuario = new JLabel("CONFIGURACIÓN " + tipoUsuario);
        tituloUsuario.setForeground(Color.white);
        tituloUsuario.setFont(new Font("Arial",1,40));
        tituloUsuario.setBounds(10, 15, 800, 50);
        fondo.add(tituloUsuario);
        
        ConfigUsuario();
        if (menu.usuario.get(16).equals("Administrador")){
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
        imagenUsu.setIcon(new ImageIcon((new ImageIcon(menu.usuario.get(9))).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
        imagenUsu.setBounds(20, 50, 180, 180);
        modificarPerfil.add(imagenUsu);
        
        JLabel tituloNombre = new JLabel("USUARIO:");
        tituloNombre.setForeground(Color.white);
        tituloNombre.setBounds(210, 50, 70, 20);
        modificarPerfil.add(tituloNombre);
        
        textoNombreUsu.setBounds(210, 80, 200, 20);
        textoNombreUsu.setText(menu.usuario.get(0));
        modificarPerfil.add(textoNombreUsu);
        
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
        
        Ruta = menu.usuario.get(9);
        
        cambioImagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                jFileChooser.setFileFilter(filtrado);
                
                int respuesta = jFileChooser.showOpenDialog(Configuraciones.this);
                
                if (respuesta == JFileChooser.APPROVE_OPTION){
                    Ruta = jFileChooser.getSelectedFile().getPath();
                    imagenUsu.setIcon(new ImageIcon((new ImageIcon(Ruta)).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
                    imagenUsu.repaint();
                }else{
                    Ruta = menu.usuario.get(9);
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
                if (textoNombreUsu.getText().trim().equals("") || String.valueOf(textoContrasena.getPassword()).trim().equals("")){
                    JOptionPane.showMessageDialog(Configuraciones.this, "Por favor ingrese todos los datos.", "Ingresar Datos", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (textoNombreUsu.getText().trim().length() > 20){
                    JOptionPane.showMessageDialog(Configuraciones.this, "Ingrese menos de 20 caracteres en el usuario.", "Mucho texto.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (textoNombreUsu.getText().trim().contains("|") || String.valueOf(textoContrasena.getPassword()).trim().contains("|")){
                    JOptionPane.showMessageDialog(Configuraciones.this, "No puede ingresar el caracter \" | \"", "Caracter Invalido.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String carpeta = "Data/Usuarios";
                File[] archivos = new File(carpeta).listFiles();
                
                if (archivos != null) {
                    for (File archivo : archivos) {
                        if (archivo.getName().equals(textoNombreUsu.getText() + ".txt") && !archivo.getName().equals(menu.usuario.get(0) + ".txt")){
                            JOptionPane.showMessageDialog(Configuraciones.this, "El usuario " + textoNombreUsu.getText() + " ya existe.", "Usuario existente.", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                }
                // Editar Usuario
                guardadoUsu.editarUsuario(menu.usuario.get(0), textoNombreUsu.getText().trim(), String.valueOf(textoContrasena.getPassword()).trim(), Ruta);
                
                // Modificar Usuario guardado del menú
                menu.usuario = guardadoUsu.CargarDatos(textoNombreUsu.getText().trim());
                cargarBotones();
                
                //usuarios.editarUsuarioStandar(menu.usuario.get(0), textoNombre.getText().trim(), String.valueOf(textoContrasena.getPassword()).trim(), Ruta);
                System.out.println("SE EDITÓ EL USUARIO CORRECATMENTE.");
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
        panelUsuarios.setLayout(new BoxLayout(panelUsuarios, BoxLayout.Y_AXIS));
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
        botonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarUsuarios();
            }
        });
        editarUsuarios.add(botonEditar);
        
        JButton botonEliminar = new JButton();
        botonEliminar.setBackground(Color.decode(menu.colorBotonClaro));
        botonEliminar.setFocusPainted(false);
        botonEliminar.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/basura.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        botonEliminar.setBounds(350, 140, 70, 70);
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarUsu();
            }
        });
        editarUsuarios.add(botonEliminar);
        
        cargarBotones();
    }
    
    private void cargarBotones() {
        String carpeta = "Data/Usuarios";
        File[] archivos = new File(carpeta).listFiles();

        // Limpiar el panel antes de agregar nuevos botones
        panelUsuarios.removeAll();
        
        if (archivos != null) {
            for (File archivo : archivos) {
                String nombreArchivo = archivo.getName();
                JButton boton = new JButton(nombreArchivo.substring(0, nombreArchivo.lastIndexOf(".")));
                boton.setBackground(Color.decode(menu.colorBotonClaro));
                boton.setFocusPainted(false);
                boton.setPreferredSize(new Dimension(303, 50));
                boton.setMaximumSize(new Dimension(303, 50));
                boton.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        // Metodo para darle un color de selección
                        if (usuarioSeleccionado != null){
                            usuarioSeleccionado.setBackground(Color.decode(menu.colorBotonClaro));
                        }
                        usuarioSeleccionado = boton;
                        usuarioSeleccionado.setBackground(Color.decode(menu.colorBotonClaroSeleccion));

                        nombreUsuario = usuarioSeleccionado.getText();
                }
                });
                panelUsuarios.add(boton);
            }
        } else {
            // Si no hay archivos .txt en la carpeta, mostrar un mensaje o realizar alguna acción
        }
        
        // Volver a validar y repintar el panel para que los cambios sean visibles
        panelUsuarios.revalidate();
        panelUsuarios.repaint();
    }
    
    private void EditarUsuarios(){
        if (nombreUsuario == null || nombreUsuario == ""){
            JOptionPane.showMessageDialog(Configuraciones.this, "Por favor seleccione un usuario.", "Seleccionar Usuario", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JDialog ventanaEditar = new JDialog(this, "Editar Usuario", true);
        ventanaEditar.setSize(435, 575);
        ventanaEditar.setLocationRelativeTo(this);
        
        JPanel panelEditar = new JPanel();
        panelEditar.setLayout(null);
        panelEditar.setBackground(Color.decode(menu.colorPanelMedio));
        
        
        List<String> datos = guardadoUsu.CargarDatos(nombreUsuario);
        
        // ELEMENTOS EDITAR
        // IZQUIERDA
        JLabel titulo1 = new JLabel("Usuario:");
        titulo1.setForeground(Color.white);
        titulo1.setBounds(10, 10, 100, 20);
        panelEditar.add(titulo1);
        
        JTextField usuarioT = new JTextField(datos.get(0));
        usuarioT.setBounds(10, 35, 190, 20);
        panelEditar.add(usuarioT);
        
        JLabel titulo2 = new JLabel("Nombre:");
        titulo2.setForeground(Color.white);
        titulo2.setBounds(10, 65, 100, 20);
        panelEditar.add(titulo2);
        
        JTextField nombreT = new JTextField(datos.get(1));
        nombreT.setBounds(10, 90, 190, 20);
        panelEditar.add(nombreT);
        
        JLabel titulo3 = new JLabel("Apellido:");
        titulo3.setForeground(Color.white);
        titulo3.setBounds(10, 120, 100, 20);
        panelEditar.add(titulo3);
        
        JTextField apellidoT = new JTextField(datos.get(2));
        apellidoT.setBounds(10, 145, 190, 20);
        panelEditar.add(apellidoT);
        
        JLabel titulo4 = new JLabel("Cedula:");
        titulo4.setForeground(Color.white);
        titulo4.setBounds(10, 175, 100, 20);
        panelEditar.add(titulo4);
        
        JTextField cedulaT = new JTextField(datos.get(3));
        cedulaT.setBounds(10, 200, 190, 20);
        panelEditar.add(cedulaT);
        
        JLabel titulo5 = new JLabel("Email:");
        titulo5.setForeground(Color.white);
        titulo5.setBounds(10, 230, 100, 20);
        panelEditar.add(titulo5);
        
        JTextField EmailT = new JTextField(datos.get(4));
        EmailT.setBounds(10, 255, 190, 20);
        panelEditar.add(EmailT);
        
        JLabel titulo6 = new JLabel("Telefono:");
        titulo6.setForeground(Color.white);
        titulo6.setBounds(10, 285, 150, 20);
        panelEditar.add(titulo6);
        
        JTextField telefonoT = new JTextField(datos.get(8));
        telefonoT.setBounds(10, 310, 190, 20);
        panelEditar.add(telefonoT);
        
        JLabel titulo7 = new JLabel("Contraseña:");
        titulo7.setForeground(Color.white);
        titulo7.setBounds(10, 340, 100, 20);
        panelEditar.add(titulo7);
        
        JPasswordField contraseña1T = new JPasswordField();
        contraseña1T.setBounds(10, 365, 190, 20);
        panelEditar.add(contraseña1T);
        
        JLabel titulo8 = new JLabel("Repita Contraseña:");
        titulo8.setForeground(Color.white);
        titulo8.setBounds(10, 395, 150, 20);
        panelEditar.add(titulo8);
        
        JPasswordField contraseña2T = new JPasswordField();
        contraseña2T.setBounds(10, 420, 190, 20);
        panelEditar.add(contraseña2T);
        
        // DERECHA
        JLabel titulo9 = new JLabel("Tipo Usuario:");
        titulo9.setForeground(Color.white);
        titulo9.setBounds(220, 10, 150, 20);
        panelEditar.add(titulo9);
        
        String[] tipoUArr = {"Administrador", "Usuario"};
        JComboBox tipoUsu = new JComboBox(tipoUArr);
        tipoUsu.setSelectedItem(datos.get(16));
        tipoUsu.setBounds(220, 35, 190, 20);
        panelEditar.add(tipoUsu);
        
        JLabel titulo10 = new JLabel("Genero:");
        titulo10.setForeground(Color.white);
        titulo10.setBounds(220, 65, 100, 20);
        panelEditar.add(titulo10);
        
        String[] generoArr = {"Masculino", "Femenino", "No especificado"};
        JComboBox generoT = new JComboBox(generoArr);
        generoT.setSelectedItem(datos.get(7));
        generoT.setBounds(220, 90, 190, 20);
        panelEditar.add(generoT);
        
        JLabel titulo11 = new JLabel(datos.get(10));
        titulo11.setForeground(Color.white);
        titulo11.setBounds(220, 120, 190, 20);
        panelEditar.add(titulo11);
        
        JTextField resp1T = new JTextField(datos.get(11));
        resp1T.setBounds(220, 145, 190, 20);
        panelEditar.add(resp1T);
        
        JLabel titulo12 = new JLabel(datos.get(12));
        titulo12.setForeground(Color.white);
        titulo12.setBounds(220, 175, 190, 20);
        panelEditar.add(titulo12);
        
        JTextField resp2T = new JTextField(datos.get(13));
        resp2T.setBounds(220, 200, 190, 20);
        panelEditar.add(resp2T);
        
        JLabel titulo13 = new JLabel(datos.get(14));
        titulo13.setForeground(Color.white);
        titulo13.setBounds(220, 230, 190, 20);
        panelEditar.add(titulo13);
        
        JTextField resp3T = new JTextField(datos.get(15));
        resp3T.setBounds(220, 255, 190, 20);
        panelEditar.add(resp3T);
        
        JButton imagenT = new JButton("IMAGEN");
        imagenT.setBounds(220, 310, 190, 20);
        imagenT.setBackground(Color.decode(menu.colorBotonClaro));
        imagenT.setFocusPainted(false);
        Ruta = datos.get(9);
        panelEditar.add(imagenT);
        
        JLabel lblimagen = new JLabel();
        lblimagen.setIcon(new ImageIcon((new ImageIcon(datos.get(9))).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        lblimagen.setBounds(240, 340, 150, 150);
        panelEditar.add(lblimagen);
        
        imagenT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                jFileChooser.setFileFilter(filtrado);

                int respuesta = jFileChooser.showOpenDialog(Configuraciones.this);

                if (respuesta == JFileChooser.APPROVE_OPTION){
                    Ruta = jFileChooser.getSelectedFile().getPath();
                    lblimagen.setIcon(new ImageIcon((new ImageIcon(Ruta)).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
                    
                    panelEditar.revalidate();
                    panelEditar.repaint();
                }else{
                    Ruta = datos.get(9);
                }
            }
        });

        JButton botonEditar = new JButton("EDITAR");
        botonEditar.setBounds(200,505,100,20);
        botonEditar.setBackground(Color.decode(menu.colorBotonClaro));
        botonEditar.setFocusPainted(false);
        panelEditar.add(botonEditar);
        botonEditar.addActionListener(e -> {
                if (usuarioT.getText().trim().equals("") 
                || nombreT.getText().trim().equals("") 
                || apellidoT.getText().trim().equals("")
                || cedulaT.getText().trim().equals("") 
                || EmailT.getText().trim().equals("") 
                || String.valueOf(contraseña1T.getPassword()).trim().equals("")
                || String.valueOf(contraseña2T.getPassword()).trim().equals("")
                || telefonoT.getText().trim().equals("")
                || resp1T.getText().trim().equals("")
                || resp2T.getText().trim().equals("")
                || resp3T.getText().trim().equals("")){
                    
                    JOptionPane.showMessageDialog(this, "Ingrese datos en los campos de texto.", "Poco texto", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(!contraseña1T.getText().trim().equals(contraseña2T.getText().trim())){
                    JOptionPane.showMessageDialog(this, "Ingrese la misma contraseña.", "Diferentes contraseñas.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (usuarioT.getText().trim().contains("|")
                || nombreT.getText().trim().contains("|")
                || apellidoT.getText().trim().contains("|")
                || cedulaT.getText().trim().contains("|")
                || EmailT.getText().trim().contains("|")
                || String.valueOf(contraseña1T.getPassword()).trim().contains("|")
                || String.valueOf(contraseña2T.getPassword()).trim().contains("|")
                || telefonoT.getText().trim().contains("|")
                || resp1T.getText().trim().contains("|")
                || resp2T.getText().trim().contains("|")
                || resp3T.getText().trim().contains("|")){

                    JOptionPane.showMessageDialog(this, "No puedes ingresar el caracter \" | \"", "Caracter Invalido.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                guardadoUsu.editarAdmin(nombreUsuario, usuarioT.getText().trim(), nombreT.getText().trim(), apellidoT.getText().trim(),
                                        cedulaT.getText().trim(), EmailT.getText().trim(), String.valueOf(contraseña1T.getPassword()).trim(),
                                        (String)generoT.getSelectedItem(), telefonoT.getText().trim(), Ruta, resp1T.getText().trim(),
                                        resp2T.getText().trim(), resp3T.getText().trim(), (String)tipoUsu.getSelectedItem());
                
                if (nombreUsuario.equals(menu.usuario.get(0))){
                    List<String> datosNuv = guardadoUsu.CargarDatos(usuarioT.getText().trim());
                    
                    textoNombreUsu.setText(datosNuv.get(0));
                    imagenUsu.setIcon(new ImageIcon((new ImageIcon(datosNuv.get(9))).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
                    imagenUsu.revalidate();
                    imagenUsu.repaint();
                    
                    menu.usuario = datosNuv;
                }
                
                nombreUsuario = "";
                cargarBotones();
                JOptionPane.showMessageDialog(this, "Usuario editado correctamente.");
                ventanaEditar.dispose();
            });

        JButton botonCerrar = new JButton("CANCELAR");
        botonCerrar.setBounds(310,505,100,20);
        botonCerrar.setBackground(Color.decode(menu.colorBotonClaro));
        botonCerrar.setFocusPainted(false);
        botonCerrar.addActionListener(e -> ventanaEditar.dispose());
        panelEditar.add(botonCerrar);

        ventanaEditar.add(panelEditar);
        ventanaEditar.setVisible(true);
    }
    
    private void EliminarUsu(){
        if (nombreUsuario == null || nombreUsuario == ""){
            JOptionPane.showMessageDialog(Configuraciones.this, "Por favor seleccione un usuario.", "Seleccionar Usuario", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (nombreUsuario.equals(menu.usuario.get(0))){
            JOptionPane.showMessageDialog(Configuraciones.this, "No puedes eliminar el usuario con el que ingresaste.", "Seleccionar otro Usuario", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JDialog ventanaEliminar = new JDialog(this, "Eliminar Usuario", true);
        ventanaEliminar.setSize(300, 120);
        ventanaEliminar.setLocationRelativeTo(null);
        ventanaEliminar.setResizable(false);
        
        JPanel panelEliminar = new JPanel();
        panelEliminar.setLayout(null);
        panelEliminar.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel

        JLabel texto = new JLabel("Desea eliminar el usuario " + nombreUsuario + "?");
        texto.setBounds(10,10,300,20);
        texto.setForeground(Color.white);
        panelEliminar.add(texto);
        
        JButton botonEliminar = new JButton("ELIMINAR");
        botonEliminar.setBounds(10,50,100,20);
        botonEliminar.setBackground(Color.decode(menu.colorBotonClaro));
        botonEliminar.setFocusPainted(false); //Quitamos las lineas de focus.
        botonEliminar.addActionListener(e -> {
            try {
                guardadoUsu.EliminarUsu(nombreUsuario);
                cargarBotones();
                
                nombreUsuario = null;
                ventanaEliminar.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelEliminar.add(botonEliminar);

        JButton botonCancelar = new JButton("CANCELAR");
        botonCancelar.setBounds(170,50,100,20);
        botonCancelar.setBackground(Color.decode(menu.colorBotonClaro));
        botonCancelar.setFocusPainted(false); //Quitamos las lineas de focus.
        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaEliminar.dispose();
            }
        };
        botonCancelar.addActionListener(cancelar);
        panelEliminar.add(botonCancelar);

        ventanaEliminar.add(panelEliminar, BorderLayout.CENTER);
        ventanaEliminar.setLocationRelativeTo(this);
        ventanaEliminar.setVisible(true);
    }
}