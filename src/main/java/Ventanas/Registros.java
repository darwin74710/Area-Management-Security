package Ventanas;

import Logica.SaveRegistros;
import static Ventanas.Camaras.nombreRegistro;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Registros extends JFrame{
    Menu menu;
    
    public JLabel fondo = new JLabel();
    public JFrame ventanaAnterior = new JFrame();
    
    SaveRegistros guardado = new SaveRegistros();
    
    int numImage = 2;
    
    public Registros(){
        PanelFondo();
    }
    
    private void PanelFondo(){
        //Creamos la ventana.
        setTitle(nombreRegistro);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon((new ImageIcon(menu.imagenFondo)).getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(null);
        fondo.setBorder(new EmptyBorder(10,0,10,10)); //Establecemos margenes en el fondo.
        this.add(fondo);
        
        Elementos();
        infoRegistros();
    }
    
    private void Elementos(){
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBounds(910, 20, 50, 50);
        botonVolver.setFocusPainted(false);

        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setBackground(Color.decode(menu.colorBotonOscuro));
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        fondo.add(botonVolver);
        
        JLabel tituloRegistro = new JLabel(nombreRegistro);
        tituloRegistro.setForeground(Color.white);
        tituloRegistro.setFont(new Font("Arial",1,40));
        tituloRegistro.setBounds(10, 15, 500, 50);
        fondo.add(tituloRegistro);
        
        // Metodo para volver al menu
        ActionListener irCamaras = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAnterior.setVisible(true);
                dispose();
            }
        };
        botonVolver.addActionListener(irCamaras);
    }
    
    private void infoRegistros(){
        List<String> datos = guardado.CargarDatosRegistros(nombreRegistro);
        
        JPanel panelRegistros = new JPanel();
        panelRegistros.setBackground(Color.decode(menu.colorPanelMedio));
        panelRegistros.setLayout(null);
        panelRegistros.setBounds(10, 80, 960, 470);
        fondo.add(panelRegistros);
        
        // Fecha del registro
        JLabel fecha = new JLabel(datos.get(5));
        fecha.setBounds(10, 10, 500, 20);
        fecha.setForeground(Color.white);
        panelRegistros.add(fecha);

        //DECORACION
        JPanel panelDescript = new JPanel();
        panelDescript.setBackground(Color.decode(menu.colorPanelClaro));
        panelDescript.setLayout(new BoxLayout(panelDescript, BoxLayout.X_AXIS));
        panelDescript.setBounds(10, 40, 400, 420);
        panelRegistros.add(panelDescript);
        
        // Descripción del registro
        JTextArea descripcion = new JTextArea();
        descripcion.setText(datos.get(1).replace("\\n", "\n"));
        descripcion.setMargin(new Insets(10, 10, 10, 10));
        descripcion.setForeground(Color.white);
        descripcion.setBackground(Color.decode(menu.colorPanelClaro));
        descripcion.setEditable(false);
        JScrollPane scrollDescript = new JScrollPane();
        scrollDescript.setBorder(null);
        scrollDescript.setViewportView(descripcion);
        panelDescript.add(scrollDescript);
        
        // Imagenes de muestra
        JLabel imagen = new JLabel();
        imagen.setIcon(new ImageIcon((new ImageIcon(datos.get(2))).getImage().getScaledInstance(500, 360, Image.SCALE_SMOOTH)));
        imagen.setOpaque(true);
        imagen.setBackground(Color.white);
        imagen.setBounds(440, 40, 500, 360);
        panelRegistros.add(imagen);
        
        // BOTON ATRAS
        JButton atras = new JButton();
        atras.setBounds(440, 410, 70, 40);
        atras.setBackground(Color.decode(menu.colorBotonOscuro));
        atras.setFocusPainted(false); //Quitamos las lineas de focus.
        
        ImageIcon logoAtras = new ImageIcon("Imagenes/Iconos/flechaIzquierda.png"); 
        atras.setIcon(new ImageIcon(logoAtras.getImage().getScaledInstance(60, 30, Image.SCALE_SMOOTH)));
        panelRegistros.add(atras);
        
        atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (numImage <= 4 && numImage > 2){
                    numImage = numImage - 1;
                    imagen.setIcon(new ImageIcon((new ImageIcon(datos.get(numImage))).getImage().getScaledInstance(500, 360, Image.SCALE_SMOOTH)));
                    imagen.revalidate();
                    imagen.repaint();
                } 
            }
        });
        
        // BOTON SIGUIENTE
        JButton siguiente = new JButton();
        siguiente.setBounds(870, 410, 70, 40);
        siguiente.setBackground(Color.decode(menu.colorBotonOscuro));
        siguiente.setFocusPainted(false); //Quitamos las lineas de focus.
        
        ImageIcon logoSiguiente = new ImageIcon("Imagenes/Iconos/flechaDerecha.png"); 
        siguiente.setIcon(new ImageIcon(logoSiguiente.getImage().getScaledInstance(60, 30, Image.SCALE_SMOOTH)));
        panelRegistros.add(siguiente);
        
        siguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (numImage >= 2 && numImage < 4){
                    numImage = numImage + 1;
                    imagen.setIcon(new ImageIcon((new ImageIcon(datos.get(numImage))).getImage().getScaledInstance(500, 360, Image.SCALE_SMOOTH)));
                    imagen.revalidate();
                    imagen.repaint();
                } 
            }
        });
        
        // BOTON EDITAR DESCRIPCION
        JButton editarDescript = new JButton("EDITAR");
        editarDescript.setBackground(Color.decode(menu.colorBotonClaro));
        editarDescript.setFocusPainted(false);
        editarDescript.setBounds(330, 10, 80, 20);
        editarDescript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Utilizamos un JDialog para mostrar la creación de la cámara
                JDialog ventanaAgregar = new JDialog(Registros.this, "Editar descripción", true);
                ventanaAgregar.setSize(395, 450);
                ventanaAgregar.setLocationRelativeTo(null);
                ventanaAgregar.setResizable(false);
                
                JPanel fondo = new JPanel();
                fondo.setLayout(null);
                fondo.setBackground(Color.decode(menu.colorPanelMedio));
                
                JTextArea descriptData = new JTextArea();
                descriptData.setText(datos.get(1).replace("\\n", "\n"));
                
                JScrollPane scrollDescript = new JScrollPane(descriptData);
                scrollDescript.setBounds(10, 10, 360, 360);
                fondo.add(scrollDescript);
                
                JButton botonEditar = new JButton("EDITAR");
                botonEditar.setBounds(160,380,100,20);
                botonEditar.setBackground(Color.decode(menu.colorBotonClaro));
                botonEditar.setFocusPainted(false);
                botonEditar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (descriptData.getText().contains("|")){
                            JOptionPane.showMessageDialog(Registros.this, "No puede ingresar el caracter \" | \"", "Caracter Invalido.", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        guardado.editarDescriptRegistro(nombreRegistro, datos, descriptData.getText());
                        
                        descripcion.setText(datos.get(1).replace("\\n", "\n"));
                        ventanaAgregar.dispose();
                    }
                });
                fondo.add(botonEditar);

                JButton botonCancelar = new JButton("CANCELAR");
                botonCancelar.setBounds(270,380,100,20);
                botonCancelar.setBackground(Color.decode(menu.colorBotonClaro));
                botonCancelar.setFocusPainted(false);
                botonCancelar.addActionListener(ex -> ventanaAgregar.dispose());
                fondo.add(botonCancelar);
                
                ventanaAgregar.add(fondo);
                ventanaAgregar.setLocationRelativeTo(Registros.this);
                ventanaAgregar.setVisible(true);
            }
        });
        panelRegistros.add(editarDescript);
        
        // BOTON EDITAR IMAGEN
        JButton editarImagen = new JButton("EDITAR");
        editarImagen.setBackground(Color.decode(menu.colorBotonClaro));
        editarImagen.setFocusPainted(false);
        editarImagen.setBounds(860, 10, 80, 20);
        editarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Ruta = datos.get(numImage);
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG", "jpg", "png");
                jFileChooser.setFileFilter(filtrado);
                
                int respuesta = jFileChooser.showOpenDialog(Registros.this);
                
                if (respuesta == JFileChooser.APPROVE_OPTION){
                    Ruta = jFileChooser.getSelectedFile().getPath();
                    
                    guardado.editarImagenRegistro(nombreRegistro, datos, numImage, Ruta);
                    imagen.setIcon(new ImageIcon((new ImageIcon(Ruta)).getImage().getScaledInstance(500, 360, Image.SCALE_SMOOTH)));
                    imagen.revalidate();
                    imagen.repaint();
                }else{
                    Ruta = datos.get(numImage);
                }
            }
        });
        panelRegistros.add(editarImagen);
    }
}