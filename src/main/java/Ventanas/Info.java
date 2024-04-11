package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Info extends JFrame{
    Menu menu;
    
    public JLabel fondo = new JLabel();
    JScrollPane scrollArea = new JScrollPane();
    JPanel panelMensajes = new JPanel();
    JTextField chat = new JTextField();
    
    public Info(){
        PanelFondo();
        ElementosManual();
    }
    
    private void PanelFondo(){
        
        //Creamos la ventana.
        setTitle("Información");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel();
        fondo.setIcon(new ImageIcon((new ImageIcon(menu.imagenFondo)).getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
        this.add(fondo);
    }
    
    private void ElementosManual(){
        //Creamos el boton para volver.
        JButton botonVolver = new JButton();
        botonVolver.setBackground(Color.decode(menu.colorBotonOscuro));
        botonVolver.setBounds(905, 15, 50, 50);
        botonVolver.setFocusPainted(false);
        
        ImageIcon logoInicio = new ImageIcon("Imagenes/Iconos/casa.png");
        botonVolver.setIcon(new ImageIcon(logoInicio.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        fondo.add(botonVolver);

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
        
        JButton botonEliminar = new JButton();
        botonEliminar.setBackground(Color.decode(menu.colorBotonOscuro));
        botonEliminar.setFocusPainted(false);
        botonEliminar.addActionListener(e-> reiniciarChat());
        botonEliminar.setBounds(845, 15, 50, 50);
        
        ImageIcon logoBasura = new ImageIcon("Imagenes/Iconos/basura.png");
        botonEliminar.setIcon(new ImageIcon(logoBasura.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))); //Redimencionamos la imagen para darle tamaño al boton.
        fondo.add(botonEliminar);
        
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollArea.setBounds(140,10,650,500);
        scrollArea.setOpaque(true);
        scrollArea.setBorder(null);
        fondo.add(scrollArea);
        
        panelMensajes.setBackground(Color.decode(menu.colorPanelOscuro));
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        
        panelMensajes.add(Box.createRigidArea(new Dimension(0, 5)));
        
        Introduccion();

        scrollArea.setViewportView(panelMensajes);
        
        chat.setBounds(140, 510, 610, 40);
        chat.setFont(new Font("Arial", 1, 20));
        fondo.add(chat);
        
        
        JButton enviar = new JButton();
        enviar.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/enviar.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
        enviar.setBackground(Color.decode(menu.colorBotonClaro));
        enviar.setFocusPainted(false);
        enviar.setBounds(750, 510, 40, 40);
        fondo.add(enviar);
        
        enviar.addActionListener(e -> chatIA());
    }
    
    private void Introduccion(){
        JPanel introduccion = new JPanel();
        introduccion.setPreferredSize(new Dimension(620, 150));
        introduccion.setMaximumSize(new Dimension(620, 150));
        introduccion.setBackground(Color.decode(menu.colorPanelMedio));
        introduccion.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelMensajes.add(introduccion);
        
        JLabel imagenHormig = new JLabel();
        imagenHormig.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png")).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
        imagenHormig.setPreferredSize(new Dimension(130, 130));
        imagenHormig.setMaximumSize(new Dimension(130, 130));
        introduccion.add(imagenHormig);
        
        JTextArea info = new JTextArea("Hola, este es el manual, aquí podras encontrar informacion que te sea de utilidad.");
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setBackground(Color.decode(menu.colorPanelClaro));
        info.setForeground(Color.white);
        info.setPreferredSize(new Dimension(455, 130));
        info.setPreferredSize(new Dimension(455, 130));
        info.setBorder(new EmptyBorder(10, 10, 10, 10));
        introduccion.add(info);
    }
    
    private void chatIA(){          
        panelMensajes.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel mensaje = new JPanel();
        mensaje.setPreferredSize(new Dimension(620, 150));
        mensaje.setMaximumSize(new Dimension(620, 150));
        mensaje.setBackground(Color.decode(menu.colorPanelMedio));
        mensaje.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelMensajes.add(mensaje);
        
        JLabel imagenHormig = new JLabel();
        imagenHormig.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/perfilEstandar.png")).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
        imagenHormig.setPreferredSize(new Dimension(130, 130));
        imagenHormig.setMaximumSize(new Dimension(130, 130));
        mensaje.add(imagenHormig);
        
        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setBackground(Color.decode(menu.colorPanelClaro));
        info.setForeground(Color.white);
        info.setPreferredSize(new Dimension(455, 130));
        info.setPreferredSize(new Dimension(455, 130));
        info.setBorder(new EmptyBorder(10, 10, 10, 10));
        mensaje.add(info);

        chat.setText("");
        
        panelMensajes.revalidate();
        panelMensajes.repaint();
        
        // Metodo para ir a la parte final del scroll cuando se re pinte el nuevo mensaje.
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollArea.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }
    
    private void reiniciarChat(){
        chat.setText("");
        panelMensajes.removeAll();
        
        Introduccion();
        
        panelMensajes.revalidate();
        panelMensajes.repaint();
    }
}