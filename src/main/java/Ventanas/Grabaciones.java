package Ventanas;

import Logica.SistemGrabaciones;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class Grabaciones extends JFrame{
    
    public JLabel fondo = new JLabel();
    public JFrame ventanaAnterior;
    
    public JComboBox listaCamaras;
    
    JPanel panelVideo = new JPanel();
    public JPanel panelListaVideos = new JPanel();
    public JButton videoSeleccionado;
    
    SistemGrabaciones sistem = new SistemGrabaciones();
    
    public Grabaciones(){
        PanelFondo();
        Elementos();
    }
    
    private void PanelFondo(){
        setTitle("Grabaciones");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel(new ImageIcon("Imagenes/fondo.png"));
        fondo.setLayout(null);
        fondo.setBorder(new EmptyBorder(10,10,10,10)); //Establecemos margenes en el fondo.
        this.add(fondo);
    }
    
    private void Elementos(){
        JPanel elementos = new JPanel();
        elementos.setLayout(null);
        elementos.setBounds(10,10,965,540);
        elementos.setOpaque(false);
        fondo.add(elementos);
        
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBackground(Color.decode("#000a45"));
        botonVolver.setFocusPainted(false);
        botonVolver.setBounds(905, 10, 50, 50);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        elementos.add(botonVolver);
        //Funciones botones.
        ActionListener irMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistem.cerrarReproductor();
                ventanaAnterior.setVisible(true);
                setVisible(false);
            }
        };
        botonVolver.addActionListener(irMenu);
        
        JPanel panelGrab = new JPanel();
        panelGrab.setBackground(Color.decode("#000a45"));
        panelGrab.setLayout(null);
        panelGrab.setBounds(10, 10, 965, 540);
        fondo.add(panelGrab);
        
        panelVideo.setBackground(Color.decode("#121a2d"));
        panelVideo.setBounds(10, 10, 680, 520);
        panelGrab.add(panelVideo);
        
        // PANEL PARA LA LISTA DE GRABACIONES
        JPanel panelConfig = new JPanel();
        panelConfig.setLayout(null);
        panelConfig.setBackground(Color.decode("#011b5a"));
        panelConfig.setBounds(700, 70, 250, 455);
        panelGrab.add(panelConfig);
        
        File carpeta = new File("Videos/");
        File[] carpetas = carpeta.listFiles(File::isDirectory); // Listar solo directorios
        
        if (carpetas != null) {
            String[] nombresCarpetas = new String[carpetas.length + 1];
            nombresCarpetas[0] = "Seleccione una cámara"; // Elemento predeterminado
            for (int i = 0; i < carpetas.length; i++) {
                nombresCarpetas[i + 1] = carpetas[i].getName();
            }
            
            // Agregar los nombres de las carpetas al JComboBox
            listaCamaras = new JComboBox<>(nombresCarpetas);
            listaCamaras.setBounds(10, 10, 230, 20);
            panelConfig.add(listaCamaras);
        }else{
            String primerElemento = "Seleccione una cámara";
            // Agregar los nombres de las carpetas al JComboBox
            listaCamaras = new JComboBox<>();
            listaCamaras.addItem(primerElemento);
            listaCamaras.setBounds(10, 10, 230, 20);
            panelConfig.add(listaCamaras);
        }

        listaCamaras.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int seleccion = listaCamaras.getSelectedIndex();

                    if (seleccion == 0){
                        panelListaVideos.removeAll();
                        panelListaVideos.revalidate();
                        panelListaVideos.repaint();
                    }else{
                        cargarVideos(listaCamaras.getSelectedIndex() - 1);
                    }
                }
            });
        
        panelListaVideos.setLayout(new BoxLayout(panelListaVideos, BoxLayout.Y_AXIS));
        panelListaVideos.setBackground(Color.decode("#000a45"));
        
        JScrollPane scrollDescript = new JScrollPane(panelListaVideos);
        scrollDescript.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollDescript.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDescript.setOpaque(true);
        scrollDescript.setBorder(null);
        scrollDescript.setBounds(10,40,230,405);
        panelConfig.add(scrollDescript);
        
        sistem.crearReproductor(panelVideo);
    }
    
    private void cargarVideos(int Index) {
        panelListaVideos.removeAll();
        File carpetaVideos = new File("Videos/Camara " + Index);
        File[] archivos = carpetaVideos.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp4"));

        if (archivos != null) {
            for (File archivo : archivos) {
                JButton botonVideo = new JButton(archivo.getName());
                botonVideo.setBackground(Color.decode("#85add5"));
                botonVideo.setFocusPainted(false);
                botonVideo.setPreferredSize(new Dimension(213, 50));
                botonVideo.setMaximumSize(new Dimension(213, 50));
                botonVideo.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        // Metodo para darle un color de selección
                        if (videoSeleccionado != null) {
                            videoSeleccionado.setBackground(Color.decode("#85add5"));
                        }
                        videoSeleccionado = botonVideo;
                        videoSeleccionado.setBackground(Color.decode("#415F7E"));
                        
                        sistem.reproducirVideo(panelVideo, archivo.getName(), Index);
                    }
                });
                panelListaVideos.add(botonVideo);
            }
        }
        panelListaVideos.revalidate();
        panelListaVideos.repaint();
    }
}
