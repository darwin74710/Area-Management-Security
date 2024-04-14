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
import javax.swing.JOptionPane;
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
    
    public JPanel fondo = new JPanel();
    JScrollPane scrollArea = new JScrollPane();
    JPanel panelMensajes = new JPanel();
    JTextField chat = new JTextField();
    
    int numeroChat = 0;
    
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
        
        fondo = new JPanel();
        fondo.setBackground(Color.decode(menu.colorPanelClaro));
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
        chat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!chat.getText().equals("")){
                    chatPersonal();
                    chatIA();
                }else{
                    JOptionPane.showMessageDialog(Info.this, "Debes escoger una opción.", "Escoge algo.", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        
        JButton enviar = new JButton();
        enviar.setIcon(new ImageIcon((new ImageIcon("Imagenes/Iconos/enviar.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
        enviar.setBackground(Color.decode(menu.colorBotonClaro));
        enviar.setFocusPainted(false);
        enviar.setBounds(750, 510, 40, 40);
        fondo.add(enviar);
        
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chat.getText().equals("")){
                    chatPersonal();
                    chatIA();
                }else{
                    JOptionPane.showMessageDialog(Info.this, "Debes escoger una opción.", "Escoge algo.", JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });
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
    
    private void chatPersonal(){
        panelMensajes.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel mensaje = new JPanel();
        mensaje.setPreferredSize(new Dimension(620, 150));
        mensaje.setMaximumSize(new Dimension(620, 150));
        mensaje.setBackground(Color.decode(menu.colorPanelMedio));
        mensaje.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelMensajes.add(mensaje);
        
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
        
        JLabel imagenHormig = new JLabel();
        imagenHormig.setIcon(new ImageIcon((new ImageIcon(menu.usuario[9])).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
        imagenHormig.setPreferredSize(new Dimension(130, 130));
        imagenHormig.setMaximumSize(new Dimension(130, 130));
        mensaje.add(imagenHormig);

        personalMensajes(info);
        
        panelMensajes.revalidate();
        panelMensajes.repaint();
        
        // Metodo para ir a la parte final del scroll cuando se re pinte el nuevo mensaje.
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollArea.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
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

        IAmensajes(info);
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
        numeroChat = 0;
        panelMensajes.removeAll();
        
        Introduccion();
        
        panelMensajes.revalidate();
        panelMensajes.repaint();
    }
    
    private void personalMensajes(JTextArea mensaje){
        //------------ PRIMERA OPCIÓN ---------------
        if (numeroChat == 0 && chat.getText().equals("1")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 1 PERSONAL");
            System.out.println("1");
        }else if (numeroChat == 0 && chat.getText().equals("2")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 2 PERSONAL");
        }else if (numeroChat == 0 && chat.getText().equals("3")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 3 PERSONAL");
        }else if (numeroChat == 0 && chat.getText().equals("4")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 4 PERSONAL");
        }else if (numeroChat == 0 && chat.getText().equals("5")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 5 PERSONAL");
        }else if (numeroChat == 0){
            mensaje.setText(chat.getText());
        }
    }
    
    private void IAmensajes(JTextArea mensaje){
        //------------ PRIMERA OPCIÓN ---------------
        if (numeroChat == 0 && chat.getText().equals("1")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 1 IA");
            numeroChat = 1;
        }else if (numeroChat == 0 && chat.getText().equals("2")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 2 IA");
            numeroChat = 2;
        }else if (numeroChat == 0 && chat.getText().equals("3")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 3 IA");
            numeroChat = 3;
        }else if (numeroChat == 0 && chat.getText().equals("4")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 4 IA");
            numeroChat = 4;
        }else if (numeroChat == 0 && chat.getText().equals("5")){
            mensaje.setText("SE ESCOGIO LO OPCIÓN 5 IA");
            numeroChat = 5;
        }else if (numeroChat == 0){
            mensaje.setText("Debes escoger una de las opciones.");
        }
    }
}