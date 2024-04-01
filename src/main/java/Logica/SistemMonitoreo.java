package Logica;

import static Logica.botones.sistemaMapas;
import Ventanas.Camaras;
import Ventanas.Monitoreo;
import Ventanas.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SistemMonitoreo extends JFrame {
    public Monitoreo ventanaMapa;
    private List<JButton> Areas;
    
    Menu menu;
    
    public JPanel panelAreas;
    public JPanel panelMapas = new JPanel();
    public static JButton botonSeleccionado;
    private Color color = Color.decode(menu.colorBotonClaro);
    String Ruta = "Imagenes/Iconos/areaEstandar.png";
    
    public JLabel imagen = new JLabel();
    public JLabel titulo = new JLabel();
    public JTextArea descripcion = new JTextArea();

    JTextField datoNombre = new JTextField();
    JTextArea datoDescript = new JTextArea();
    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    JComboBox<String> comboAreas = new JComboBox<>(comboBoxModel);
    JTextField datoAncho = new JTextField();
    JTextField datoAlto = new JTextField();
    
    JTextField datoNombreMap = new JTextField();
    
    SaveAreas guardado = new SaveAreas();
    
    

    private static final int tamañoMinimo = 70;
    private static final int tamañoMaximo = 500;
    private int[] offset = new int[2];

    public SistemMonitoreo() {
        Areas = new ArrayList<>();
    }
    
    public void extraerElementos(JLabel tituloVent, JTextArea descripcionVent, JLabel imagenVent){
        imagen = imagenVent;
        titulo = tituloVent;
        descripcion = descripcionVent;
        
        imagen.setIcon(new ImageIcon(Ruta));
    }
    
    public void controlPanel(JPanel configArea) {
        JButton botonAbrir = new JButton("ABRIR");
        botonAbrir.setBackground(Color.decode(menu.colorBotonClaro));
        botonAbrir.setFocusPainted(false);
        botonAbrir.setBounds(10,510,75,20);
        botonAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (botonSeleccionado != null){
                    Camaras.nombreArea = botonSeleccionado.getText();
                    SaveRegistros.nombreArea = botonSeleccionado.getText();
                    Camaras.botonSeleccionado = botonSeleccionado;
                    Camaras ventanaElementos = new Camaras();
                    ventanaElementos.ventanaAnterior = ventanaMapa;
                    
                    ventanaElementos.setVisible(true);
                    ventanaMapa.dispose();
                }else{
                    JOptionPane.showMessageDialog(SistemMonitoreo.this, "Por favor seleccione un area.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        configArea.add(botonAbrir); 
        
        JButton botonAgregar = new JButton("CREAR");
        botonAgregar.setBackground(Color.decode(menu.colorBotonClaro));
        botonAgregar.setFocusPainted(false);
        botonAgregar.setBounds(90,510,75,20);
        
        botonAgregar.addActionListener(e -> añadirNuevaArea());
        configArea.add(botonAgregar);
        
        
        JButton botonEditar = new JButton("EDITAR");
        botonEditar.setBackground(Color.decode(menu.colorBotonClaro));
        botonEditar.setFocusPainted(false);
        botonEditar.setBounds(170,510,75,20);
        botonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        configArea.add(botonEditar); 
        
        // Agregar botón para eliminar el botón seleccionado
        JButton botonBorrar = new JButton("ELIMINAR");
        botonBorrar.setBackground(Color.decode(menu.colorBotonClaro));
        botonBorrar.setFocusPainted(false);
        botonBorrar.setBounds(250,510,90,20);
        botonBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarArea();
            }
        });
        configArea.add(botonBorrar);
    }

    public void AreasPanel() {
        panelAreas = new JPanel();
        panelAreas.setPreferredSize(new Dimension(1000,1000));
        panelAreas.setBackground(Color.decode(menu.colorPanelOscuro));
        panelAreas.setLayout(null);
    }
    
    public void MapsPanel(){
        panelMapas.setOpaque(false);
        panelMapas.setLayout(null);
        panelMapas.setBounds(10,5,600,30);
        
        JLabel textoMapa = new JLabel("Mapa:");
        textoMapa.setForeground(Color.white);
        textoMapa.setBounds(10,5,40,20);
        panelMapas.add(textoMapa);
        
        List<String> nombresArchivos = guardado.obtenerNombresMapas();
        
        DefaultComboBoxModel<String> modeloMapas = new DefaultComboBoxModel<>();
        modeloMapas.removeAllElements();
        modeloMapas.addElement("Nuevo");
        if (nombresArchivos != null && !nombresArchivos.isEmpty()) {
            for (String nombreArchivo : nombresArchivos) {
                modeloMapas.addElement(nombreArchivo);
            }
        }
        
        JComboBox<String> mapa = new JComboBox<>(modeloMapas);
        mapa.setOpaque(true);
        mapa.setBackground(Color.decode(menu.colorBotonClaro));
        mapa.setBounds(50,5,150,20);
        panelMapas.add(mapa);
        
        JButton mapaGuardar = new JButton("GUARDAR");
        mapaGuardar.setBackground(Color.decode(menu.colorBotonClaro));
        mapaGuardar.setFocusPainted(false); //Quitamos las lineas de focus.
        mapaGuardar.setBounds(410,5,90,20);
        panelMapas.add(mapaGuardar);
        
        JButton mapaEliminar = new JButton("ELIMINAR");
        mapaEliminar.setBackground(Color.decode(menu.colorBotonClaro));
        mapaEliminar.setFocusPainted(false); //Quitamos las lineas de focus.
        mapaEliminar.setBounds(510,5,90,20);
        panelMapas.add(mapaEliminar);
        
        mapaGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(mapa.getSelectedIndex() == 0) {
                    // Crear un diálogo personalizado
                    JDialog ventanaCrearMapa = new JDialog(sistemaMapas, "Guardar Mapa", true);
                    ventanaCrearMapa.setSize(255, 150);
                    ventanaCrearMapa.setLocationRelativeTo(null);
                    ventanaCrearMapa.setResizable(false);

                    // Crear un panel para el contenido
                    JPanel panelCrearMapa = new JPanel();
                    panelCrearMapa.setLayout(null);
                    panelCrearMapa.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel

                    JLabel textoNombre = new JLabel("Nombre:");
                    textoNombre.setForeground(Color.white);
                    textoNombre.setBounds(10,10,100,20);
                    panelCrearMapa.add(textoNombre);

                    datoNombreMap.setBounds(10,35,220,20);
                    panelCrearMapa.add(datoNombreMap);

                    JButton botonAgregar = new JButton("CREAR");
                    botonAgregar.setBounds(10,80,100,20);
                    botonAgregar.setBackground(Color.decode(menu.colorBotonClaro));
                    botonAgregar.setFocusPainted(false); //Quitamos las lineas de focus.
                    panelCrearMapa.add(botonAgregar);
                    ActionListener agregar = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String nombreMapa = datoNombreMap.getText().trim();
                            try{
                                if (nombreMapa.length() > 20){
                                    JOptionPane.showMessageDialog(SistemMonitoreo.this, "Ingrese menos de 20 caracteres en el nombre.", "Mucho texto.", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                                if (nombreMapa.length() <= 0){
                                    JOptionPane.showMessageDialog(SistemMonitoreo.this, "Ingresele un nombre al Mapa.", "Poco texto.", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                                
                                if(mapa.getSelectedIndex() == 0) {
                                    guardado.crearMapa(Areas, nombreMapa, modeloMapas, mapa);
                                    guardado.cargarMapa((String) mapa.getSelectedItem(), panelAreas, titulo, 
                                            descripcion, imagen, offset, Areas);
                                }
                            } catch(NumberFormatException ex){
                                JOptionPane.showMessageDialog(SistemMonitoreo.this, "Error al crear.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            
                            ventanaCrearMapa.dispose();      
                            datoNombreMap.setText("");
                        }
                    };
                    botonAgregar.addActionListener(agregar);

                    JButton botonCancelar = new JButton("CANCELAR");
                    botonCancelar.setBounds(130,80,100,20);
                    botonCancelar.setBackground(Color.decode(menu.colorBotonClaro));
                    botonCancelar.setFocusPainted(false); //Quitamos las lineas de focus.
                    ActionListener cancelar = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ventanaCrearMapa.dispose();
                        }
                    };
                    botonCancelar.addActionListener(cancelar);
                    panelCrearMapa.add(botonCancelar);

                    ventanaCrearMapa.add(panelCrearMapa, BorderLayout.CENTER);
                    ventanaCrearMapa.setLocationRelativeTo(SistemMonitoreo.this);

                    ventanaCrearMapa.setVisible(true);
                }else{
                    //Cargar items
                    String nombreSeleccion = (String) mapa.getSelectedItem();
                    guardado.guardarMapa(Areas, nombreSeleccion, modeloMapas, mapa);
                }
                
                panelAreas.revalidate();
                panelAreas.repaint();
            }
        });
        mapaEliminar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
            if (mapa.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(SistemMonitoreo.this, "Debes crea un mapa para poder eliminarlo.", "No puedes eliminar", JOptionPane.WARNING_MESSAGE);
            }else{
                JDialog ventanaEliminar = new JDialog(SistemMonitoreo.this, "Eliminar Area", true);
                ventanaEliminar.setSize(255, 120);
                ventanaEliminar.setLocationRelativeTo(null);
                ventanaEliminar.setResizable(false);

                // Crear un panel para el contenido
                JPanel panelEliminar = new JPanel();
                panelEliminar.setLayout(null);
                panelEliminar.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel

                JLabel texto = new JLabel("Desea eliminar el mapa " + (String)mapa.getSelectedItem() + "?");
                texto.setBounds(10,10,220,20);
                texto.setForeground(Color.white);
                panelEliminar.add(texto);

                JButton botonEliminar = new JButton("ELIMINAR");
                botonEliminar.setBounds(10,50,100,20);
                botonEliminar.setBackground(Color.decode(menu.colorBotonClaro));
                botonEliminar.setFocusPainted(false); //Quitamos las lineas de focus.
                ActionListener eliminar = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                guardado.eliminarArchivo((String)mapa.getSelectedItem());
                                int indiceSeleccionado = mapa.getSelectedIndex();
                                // Verificar si hay un elemento seleccionado
                                if (indiceSeleccionado != -1) {
                                    // Eliminar el elemento del modelo del JComboBox
                                    mapa.removeItemAt(indiceSeleccionado);
                                }
                                mapa.setSelectedIndex(0);
                                ventanaEliminar.dispose();
                            }
                        };
                botonEliminar.addActionListener(eliminar);
                panelEliminar.add(botonEliminar);

                JButton botonCancelar = new JButton("CANCELAR");
                botonCancelar.setBounds(130,50,100,20);
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
                ventanaEliminar.setLocationRelativeTo(SistemMonitoreo.this);
                ventanaEliminar.setVisible(true);
            }
            
           }
       });
        
        
        // Agregamos un listener al combobox de los mapas.
        mapa.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Verificamos si va a crear una nueva area o no.
                    if (mapa.getSelectedIndex() == 0) {
                        // Obtener las areas.
                        Component[] componentes = panelAreas.getComponents();
                        // Eliminar cada area.
                        for (Component componente : componentes){
                            panelAreas.remove(componente);
                        }
                        // Eliminar areas de la lista.
                        Areas.clear();
                        panelAreas.revalidate();
                        panelAreas.repaint();
                    }else{
                        // Obtener las areas.
                        Component[] componentes = panelAreas.getComponents();
                        // Eliminar cada area.
                        for (Component componente : componentes){
                            panelAreas.remove(componente);
                        }
                        // Eliminar areas de la lista.
                        Areas.clear();
                        panelAreas.revalidate();
                        panelAreas.repaint();
                        
                        guardado.cargarMapa((String) mapa.getSelectedItem(), panelAreas, titulo, 
                                descripcion, imagen, offset, Areas);
                    }
                }
            }
        });
    }
    
    private void añadirArea(String nombre, int x, int y, int width, int height, Color color) {
        if(comboAreas.getSelectedIndex() == 0) {
            guardado.guardarArea(nombre, datoDescript.getText(), colorAHexadecimal(color), Ruta);
        }else{
            guardado.nombreArea = nombre;
        }
        
        JButton botonArea = new JButton(guardado.nombreArea);
        botonArea.setFocusPainted(false);
        
        botonArea.setBounds(x, y, width, height);
        botonArea.setBackground(color);
        obtenerContrasteTexto(botonArea, color);

        botonArea.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                botonSeleccionado = botonArea;
                List<String> datosArchivo = guardado.CargarDatos(botonSeleccionado.getText());
                
                titulo.setText(datosArchivo.get(0));
                descripcion.setText(datosArchivo.get(1).replace("\\n", "\n"));
                imagen.setIcon(new ImageIcon((new ImageIcon(datosArchivo.get(3))).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            }
            public void mousePressed(MouseEvent e) {
                // Asignar valores al offset cuando se presiona el botón
                offset[0] = e.getX();
                offset[1] = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                // implementación
            }
        });

        botonArea.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Acceder al offset y realizar otras operaciones de arrastre
                int nuevoX = e.getX() + botonArea.getX() - offset[0];
                int nuevoY = e.getY() + botonArea.getY() - offset[1];

                nuevoX = Math.min(Math.max(nuevoX, 0), panelAreas.getWidth() - botonArea.getWidth());
                nuevoY = Math.min(Math.max(nuevoY, 0), panelAreas.getHeight() - botonArea.getHeight());

                Rectangle newXBounds = new Rectangle(nuevoX, botonArea.getY(), width, height);
                boolean collideX = false;
                for (JButton otherButton : Areas) {
                    if (otherButton != botonArea && otherButton.getBounds().intersects(newXBounds)) {
                        collideX = true;
                        break;
                    }
                }

                Rectangle newYBounds = new Rectangle(botonArea.getX(), nuevoY, width, height);
                boolean collideY = false;
                for (JButton otherButton : Areas) {
                    if (otherButton != botonArea && otherButton.getBounds().intersects(newYBounds)) {
                        collideY = true;
                        break;
                    }
                }

                if (!collideX) {
                    botonArea.setLocation(nuevoX, botonArea.getY());
                }
                if (!collideY) {
                    botonArea.setLocation(botonArea.getX(), nuevoY);
                }
            }
        });
        Areas.add(botonArea);
        panelAreas.add(botonArea);
        panelAreas.revalidate();
        panelAreas.repaint();
    }

    private void añadirNuevaArea() {
        // Crear un diálogo personalizado
        JDialog ventanaCrear = new JDialog(this, "Agregar Area", true);
        ventanaCrear.setSize(255, 500);
        ventanaCrear.setLocationRelativeTo(null);
        ventanaCrear.setResizable(false);

        // Crear un panel para el contenido
        JPanel panelCrear = new JPanel();
        panelCrear.setLayout(null);
        panelCrear.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel
        
        JLabel textoNombre = new JLabel("Nombre:");
        textoNombre.setForeground(Color.white);
        textoNombre.setBounds(10,10,100,20);
        panelCrear.add(textoNombre);
        
        datoNombre.setBounds(10,35,220,20);
        panelCrear.add(datoNombre);
        
        JLabel textoDescript = new JLabel("Descripción:");
        textoDescript.setForeground(Color.white);
        textoDescript.setBounds(10,70,100,20);
        panelCrear.add(textoDescript);
        
        JScrollPane scrollDescript = new JScrollPane(datoDescript);
        scrollDescript.setBounds(10,95,220,90);
        panelCrear.add(scrollDescript);
        
        JLabel textoArea = new JLabel("Area:");
        textoArea.setForeground(Color.white);
        textoArea.setBounds(10,195,100,20);
        panelCrear.add(textoArea);
        
        //Modelo para añadir la lista de nombres al combobox.
        List<String> nombresArchivos = guardado.obtenerNombresArchivos();
        //Añadir una opción de nuevo.
        comboBoxModel.removeAllElements();
        comboBoxModel.addElement("Nuevo");
        if (nombresArchivos != null && !nombresArchivos.isEmpty()) {
            for (String nombreArchivo : nombresArchivos) {
                comboBoxModel.addElement(nombreArchivo);
            }
        }
        
        comboAreas.setBackground(Color.decode(menu.colorBotonClaro));
        comboAreas.setBounds(10,220,220,20);
        panelCrear.add(comboAreas);

        JLabel textoAncho = new JLabel("Ancho:");
        textoAncho.setForeground(Color.white);
        textoAncho.setBounds(10,250,100,20);
        panelCrear.add(textoAncho);
        
        datoAncho.setBounds(10,275,100,20);
        panelCrear.add(datoAncho);
        
        JLabel textoAlto = new JLabel("Alto:");
        textoAlto.setBounds(130,250,100,20);
        textoAlto.setForeground(Color.white);
        panelCrear.add(textoAlto);
        
        datoAlto.setBounds(130,275,100,20);
        panelCrear.add(datoAlto);
        
        JLabel textoColor = new JLabel("Color:");
        textoColor.setBounds(10,305,100,20);
        textoColor.setForeground(Color.white);
        panelCrear.add(textoColor);

        // Selector de color para elegir el color del botón
        JButton BotonColor = new JButton();
        BotonColor.setBackground(Color.decode(menu.colorBotonClaro));
        BotonColor.setBounds(10,330,220,20);
        BotonColor.setFocusPainted(false); //Quitamos las lineas de focus.
        BotonColor.addActionListener(e -> {
            color = JColorChooser.showDialog(SistemMonitoreo.this, "Selecciona un color", color);
            BotonColor.setBackground(color);
            if (color == null) { // Verifica si se seleccionó un color
                color = Color.decode(menu.colorBotonClaro);
                BotonColor.setBackground(color);
            }
        });
        panelCrear.add(BotonColor);
        
        JLabel textoImagen = new JLabel("Imagen:");
        textoImagen.setBounds(10,360,100,20);
        textoImagen.setForeground(Color.white);
        panelCrear.add(textoImagen);
        
        JButton botonImagen = new JButton("IMAGEN");
        botonImagen.setBounds(10,385,220,20);
        botonImagen.setBackground(Color.decode(menu.colorBotonClaro));
        botonImagen.setFocusPainted(false);
        botonImagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                jFileChooser.setFileFilter(filtrado);
                
                int respuesta = jFileChooser.showOpenDialog(SistemMonitoreo.this);
                
                if (respuesta == JFileChooser.APPROVE_OPTION){
                    Ruta = jFileChooser.getSelectedFile().getPath();
                }else{
                    Ruta = "Imagenes/Iconos/areaEstandar.png";
                }
            }
        });
        panelCrear.add(botonImagen);
        
        // Agregar un ItemListener al JComboBox
        comboAreas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Verificamos si va a crear una nueva area o no.
                    if (comboAreas.getSelectedIndex() == 0) {
                        datoNombre.setText("");
                        datoNombre.setEditable(true);
                        datoNombre.setBackground(Color.white);
                        datoDescript.setText("");
                        datoDescript.setEditable(true);
                        datoDescript.setBackground(Color.white);
                        BotonColor.setBackground(Color.decode(menu.colorBotonClaro));
                        color = Color.decode((menu.colorBotonClaro));
                    }else{
                        String nombreSeleccionado = (String) comboAreas.getSelectedItem();
                        // Cargar los datos del archivo
                        List<String> datos = guardado.CargarDatos(nombreSeleccionado);
                        
                        datoNombre.setText(datos.get(0));
                        datoNombre.setEditable(false);
                        datoNombre.setBackground(Color.gray);
                        datoDescript.setText(datos.get(1).replace("\\n", "\n"));
                        datoDescript.setEditable(false);
                        datoDescript.setBackground(Color.gray);
                        BotonColor.setBackground(Color.decode(datos.get(2)));
                        color = Color.decode(datos.get(2));
                    }
                }
            }
        });

        // Botón para añadir el nuevo botón al panel principal
        JButton botonAgregar = new JButton("CREAR");
        botonAgregar.setBounds(10,430,100,20);
        botonAgregar.setBackground(Color.decode(menu.colorBotonClaro));
        botonAgregar.setFocusPainted(false); //Quitamos las lineas de focus.
        botonAgregar.addActionListener(e -> {
            // Obtener el ancho y el alto del nuevo botón desde los campos de texto
            try {
                // Eliminar espacios al inicio y al final
                String nombre = datoNombre.getText().trim();
                int ancho = Integer.parseInt(datoAncho.getText());
                int alto = Integer.parseInt(datoAlto.getText());            
                
                // Verificar nombre.
                if (nombre.length() > 20){
                    JOptionPane.showMessageDialog(this, "Ingrese menos de 20 caracteres en el nombre.", "Mucho texto.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (nombre.length() <= 0){
                    JOptionPane.showMessageDialog(this, "Ingresele un nombre al area.", "Mucho texto.", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Verificar tamaño.
                if (ancho < tamañoMinimo || ancho > tamañoMaximo || alto < tamañoMinimo || alto > tamañoMaximo) {
                    JOptionPane.showMessageDialog(this, "El tamaño del area debe estar entre " + tamañoMinimo + "x" + tamañoMinimo + " y " + tamañoMaximo + "x" + tamañoMaximo + ".", "Tamaño no válido", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Encuentra una posición no ocupada para el nuevo botón
                int x = 0; // Coordenadas iniciales
                int y = 0;
                while (isButtonOverlap(x, y, ancho, alto)) {
                    x += 1;
                    if (x + ancho > panelAreas.getWidth()) {
                        x = 0;
                        y += 1;
                    }
                    if (y + alto > panelAreas.getHeight()) {
                        JOptionPane.showMessageDialog(this, "No hay espacio para añadir mas areas.", "Alerta", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                añadirArea(nombre, x, y, ancho, alto, color);
                ventanaCrear.dispose(); // Cerrar el diálogo después de agregar el botón
                //Reiniciamos los valores
                datoNombre.setText("");
                datoDescript.setText("");
                color = Color.decode(menu.colorBotonClaro);
                Ruta = "Imagenes/Iconos/areaEstandar.png";
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingresele un tamaño al area.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelCrear.add(botonAgregar);
        
        JButton botonCancelar = new JButton("CANCELAR");
        botonCancelar.setBounds(130,430,100,20);
        botonCancelar.setBackground(Color.decode(menu.colorBotonClaro));
        botonCancelar.setFocusPainted(false); //Quitamos las lineas de focus.
        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaCrear.dispose();
            }
        };
        botonCancelar.addActionListener(cancelar);
        panelCrear.add(botonCancelar);

        ventanaCrear.add(panelCrear, BorderLayout.CENTER);
        ventanaCrear.setLocationRelativeTo(this);
        
        //Reiniciamos los valores
        datoNombre.setText("");
        datoDescript.setText("");
        color = Color.decode(menu.colorBotonClaro);
        datoAncho.setText("");
        datoAlto.setText("");
        Ruta = "Imagenes/Iconos/areaEstandar.png";
                
        ventanaCrear.setVisible(true);
    }
    
    private void editar() {
        if (botonSeleccionado != null) {
            List<String> datosArchivo = guardado.CargarDatos(botonSeleccionado.getText());
            String nombreViejo = datosArchivo.get(0);
            
            // Crear un diálogo personalizado para editar el color
            JDialog ventanaEditar = new JDialog(this, "Editar Area", true);
            ventanaEditar.setSize(255, 375);
            ventanaEditar.setLocationRelativeTo(this);

            // Panel para el contenido del diálogo
            JPanel panelEditar = new JPanel();
            panelEditar.setLayout(null);
            panelEditar.setBackground(Color.decode(menu.colorPanelMedio));

            JLabel textoNombre = new JLabel("Nombre:");
            textoNombre.setForeground(Color.white);
            textoNombre.setBounds(10,10,100,20);
            panelEditar.add(textoNombre);
            
            datoNombre.setText(datosArchivo.get(0));
            datoNombre.setBounds(10,35,220,20);
            panelEditar.add(datoNombre);

            JLabel textoDescript = new JLabel("Descripción:");
            textoDescript.setForeground(Color.white);
            textoDescript.setBounds(10,70,100,20);
            panelEditar.add(textoDescript);
            
            datoDescript.setText(datosArchivo.get(1).replace("\\n", "\n"));
            JScrollPane scrollDescript = new JScrollPane(datoDescript);
            scrollDescript.setBounds(10,95,220,90);
            panelEditar.add(scrollDescript);
            
            JLabel colorLabel = new JLabel("Color:");
            colorLabel.setBounds(10,195,100,20);
            colorLabel.setForeground(Color.white);
            panelEditar.add(colorLabel);

            JButton botonColor = new JButton();
            botonColor.setBackground(botonSeleccionado.getBackground());
            botonColor.setBounds(10,220,220,20);
            botonColor.setFocusPainted(false);
            color = botonSeleccionado.getBackground();
            botonColor.addActionListener(e -> {
            color = botonSeleccionado.getBackground();
            color = JColorChooser.showDialog(SistemMonitoreo.this, "Selecciona un color", color);
            botonColor.setBackground(color);
            if (color == null) { // Verifica si se seleccionó un color
                color = botonSeleccionado.getBackground();
                botonColor.setBackground(color);
            }
            });
            panelEditar.add(botonColor);
            
            JLabel textoImagen = new JLabel("Imagen:");
            textoImagen.setBounds(10,250,100,20);
            textoImagen.setForeground(Color.white);
            panelEditar.add(textoImagen);

            JButton botonImagen = new JButton("IMAGEN");
            botonImagen.setBounds(10,275,220,20);
            botonImagen.setBackground(Color.decode(menu.colorBotonClaro));
            botonImagen.setFocusPainted(false);
            Ruta = datosArchivo.get(3);
            
            botonImagen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jFileChooser = new JFileChooser();
                    FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                    jFileChooser.setFileFilter(filtrado);

                    int respuesta = jFileChooser.showOpenDialog(SistemMonitoreo.this);

                    if (respuesta == JFileChooser.APPROVE_OPTION){
                        Ruta = jFileChooser.getSelectedFile().getPath();
                    }else{
                        Ruta = datosArchivo.get(3);
                    }
                }
            });
            panelEditar.add(botonImagen);
            
            
            JButton botonEditar = new JButton("EDITAR");
            botonEditar.setBounds(10,305,100,20);
            botonEditar.setBackground(Color.decode(menu.colorBotonClaro));
            botonEditar.setFocusPainted(false);
            botonEditar.addActionListener(e -> {
                try {
                    String nombre = datoNombre.getText().trim();
                    //Validaciones
                    if (nombre.length() > 20){
                        JOptionPane.showMessageDialog(this, "Ingrese menos de 20 caracteres en el nombre.", "Mucho texto.", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (nombre.length() <= 0){
                        JOptionPane.showMessageDialog(this, "Ingresele un nombre al area.", "Mucho texto.", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    datosArchivo.set(0, nombre);
                    datosArchivo.set(1, datoDescript.getText());
                    datosArchivo.set(2, colorAHexadecimal(color));
                    datosArchivo.set(3, Ruta);
                    
                    guardado.nombreArea = nombre;
                    
                    guardado.editarArea(botonSeleccionado.getText(), datosArchivo);;
                    // Actualizar el texto del botón seleccionado
                    botonSeleccionado.setText(guardado.nombreArea);
                    // Obtener todos los componentes del panelAreas
                    
                    Component[] componentes = panelAreas.getComponents();

                    // Recorrer todos los componentes para buscar los botones con el nombre viejo
                    for (Component componente : componentes) {
                        if (componente instanceof JButton) { // Verificar si es un JButton
                            JButton boton = (JButton) componente;
                            if (boton.getText().equals(nombreViejo)) { // Verificar si el texto del botón coincide con el nombre viejo
                                // Modificar el texto del botón con el nombre nuevo
                                boton.setText(datosArchivo.get(0));
                            }
                        }
                    }

                    // Cambiar el color de fondo del botón seleccionado
                    Color nuevoColor = Color.decode(datosArchivo.get(2));
                    botonSeleccionado.setBackground(nuevoColor);

                    // Actualizar el contraste del texto del botón según el nuevo color de fondo
                    obtenerContrasteTexto(botonSeleccionado, nuevoColor);

                    List<String> NuevosDatos = guardado.CargarDatos(botonSeleccionado.getText());
                
                    titulo.setText(NuevosDatos.get(0));
                    descripcion.setText(NuevosDatos.get(1).replace("\\n", "\n"));
                    imagen.setIcon(new ImageIcon((new ImageIcon(NuevosDatos.get(3))).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                    
                    panelAreas.revalidate();
                    panelAreas.repaint();
                    
                    ventanaEditar.dispose(); // Cerrar el diálogo después de agregar el botón
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panelEditar.add(botonEditar);
            
            JButton botonCerrar = new JButton("CANCELAR");
            botonCerrar.setBounds(130,305,100,20);
            botonCerrar.setBackground(Color.decode(menu.colorBotonClaro));
            botonCerrar.setFocusPainted(false);
            botonCerrar.addActionListener(e -> ventanaEditar.dispose());
            panelEditar.add(botonCerrar);

            ventanaEditar.add(panelEditar);
            ventanaEditar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un area.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarArea() {
        if (botonSeleccionado != null) {
            JDialog ventanaEliminar = new JDialog(this, "Eliminar Area", true);
            ventanaEliminar.setSize(380, 120);
            ventanaEliminar.setLocationRelativeTo(null);
            ventanaEliminar.setResizable(false);

            // Crear un panel para el contenido
            JPanel panelEliminar = new JPanel();
            panelEliminar.setLayout(null);
            panelEliminar.setBackground(Color.decode(menu.colorPanelMedio)); // Establecer el color de fondo del panel


            JLabel texto = new JLabel("Desea eliminar el archivo o el area de " + botonSeleccionado.getText() + "?");
            texto.setBounds(10,10,300,20);
            texto.setForeground(Color.white);
            panelEliminar.add(texto);

            JButton botonArchivo = new JButton("ARCHIVO");
            botonArchivo.setBounds(10,50,100,20);
            botonArchivo.setBackground(Color.decode(menu.colorBotonClaro));
            botonArchivo.setFocusPainted(false); //Quitamos las lineas de focus.
            botonArchivo.addActionListener(e -> {
                try {
                    //Eliminar el archivo
                    guardado.eliminarArea(botonSeleccionado.getText());

                    //Eliminar el boton
                    panelAreas.remove(botonSeleccionado);
                    Areas.remove(botonSeleccionado);
                    guardado.EliminarBotonMapa(botonSeleccionado.getText());
                    panelAreas.revalidate();
                    panelAreas.repaint();
                    botonSeleccionado = null;

                    titulo.setText("SELECCIONE UN AREA");
                    descripcion.setText("");
                    imagen.setIcon(new ImageIcon("Imagenes/Iconos/areaEstandar.png"));
                    
                    ventanaEliminar.dispose(); // Cerrar el diálogo después de agregar el botón
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panelEliminar.add(botonArchivo);
        
            JButton botonArea = new JButton("AREA");
            botonArea.setBounds(130,50,100,20);
            botonArea.setBackground(Color.decode(menu.colorBotonClaro));
            botonArea.setFocusPainted(false); //Quitamos las lineas de focus.
            botonArea.addActionListener(e -> {
                try {
                    //Eliminar el boton
                    panelAreas.remove(botonSeleccionado);
                    Areas.remove(botonSeleccionado);
                    panelAreas.revalidate();
                    panelAreas.repaint();
                    botonSeleccionado = null;

                    titulo.setText("SELECCIONE UN AREA");
                    descripcion.setText("");
                    imagen.setIcon(new ImageIcon("Imagenes/Iconos/areaEstandar.png"));
                    
                    ventanaEliminar.dispose(); // Cerrar el diálogo después de agregar el botón
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panelEliminar.add(botonArea);
            
            JButton botonCancelar = new JButton("CANCELAR");
            botonCancelar.setBounds(250,50,100,20);
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
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un área.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean isButtonOverlap(int x, int y, int width, int height) {
        Rectangle newButtonBounds = new Rectangle(x, y, width, height);
        for (JButton existingButton : Areas) {
            if (existingButton.getBounds().intersects(newButtonBounds)) {
                return true;
            }
        }
        return false;
    }

    public static void obtenerContrasteTexto(JButton button, Color backgroundColor) {
        double brillo = obtenerBrillo(backgroundColor);
        if (brillo < 0.5) {
            button.setForeground(Color.WHITE);
        } else {
            button.setForeground(Color.BLACK);
        }
    }

    private static double obtenerBrillo(Color color) {
        return (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
    }
    
    public static String colorAHexadecimal(Color color) {
    // Convierte los componentes RGB del color a su representación hexadecimal
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}