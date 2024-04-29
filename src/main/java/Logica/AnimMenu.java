package Logica;

import Ventanas.Menu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class AnimMenu{
    
    JPanel fondo = Menu.animFondo;
    public static JLabel hormiguita = new JLabel();
    
    Timer tiempoScroll;
    private Timer animadorVD;
    private Timer animadorMensj;
    public static Timer mensajesTemp;
    public static Timer hablarTemp;
    
    int posicionBarra = 0;
    int yPanel1 = -500;
    int yDar = 500;
    int yVar = 510;
    
    boolean cambiador1 = false;
    
    public void standar(){
        fondo.removeAll();
        JPanel panelStandar = new JPanel();
        panelStandar.setOpaque(false);
        panelStandar.setLayout(null);
        panelStandar.setBounds(0, 0, 985, 495);
        fondo.add(panelStandar);
        
        hormiguita.setOpaque(false);
        hormiguita.setBounds(550, 90, 400, 400);
        panelStandar.add(hormiguita);

        // PANEL QUE MUESTRA INFORMACIÓN DESPLAZABLE
        JPanel mensaje = new JPanel();
        mensaje.setLayout(null);
        
        if (Menu.usuario.get(16).equals("Administrador")){
            mensaje.setPreferredSize(new Dimension(2000, 200));
        }else if(Menu.usuario.get(16).equals("Usuario")){
            mensaje.setPreferredSize(new Dimension(1500, 200));
        }

        mensaje.setBackground(Color.decode(Menu.colorPanelMedio));
        
        JPanel cambioMensajes = new JPanel();
        cambioMensajes.setLayout(null);
        cambioMensajes.setOpaque(false);
        cambioMensajes.setBounds(-100, 190, 75, 15);
        panelStandar.add(cambioMensajes);
        
        JLabel irPanel1 = new JLabel();
        irPanel1.setIcon(new ImageIcon("Imagenes/Iconos/selectorActiv.png"));
        irPanel1.setBounds(0, 0, 15, 15);
        cambioMensajes.add(irPanel1);
        
        JLabel irPanel2 = new JLabel();
        irPanel2.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
        irPanel2.setBounds(20, 0, 15, 15);
        cambioMensajes.add(irPanel2);
        
        JLabel irPanel3 = new JLabel();
        irPanel3.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
        irPanel3.setBounds(40, 0, 15, 15);
        cambioMensajes.add(irPanel3);
        
        JLabel irPanel4 = new JLabel();
        irPanel4.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
        irPanel4.setBounds(60, 0, 15, 15);
            
        if (Menu.usuario.get(16).equals("Administrador")){
            
            cambioMensajes.add(irPanel4);
        }

        JScrollPane fondoMensajes = new JScrollPane(mensaje);
        fondoMensajes.setBorder(null);
        fondoMensajes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        fondoMensajes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        fondoMensajes.setBounds(50, 20, 500, 200);
        panelStandar.add(fondoMensajes);
        
        JScrollBar horizontalScrollBar = fondoMensajes.getHorizontalScrollBar();
        
        tiempoScroll = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (horizontalScrollBar.getValue() < posicionBarra) {
                    horizontalScrollBar.setValue(horizontalScrollBar.getValue() + 10);
                } else if (horizontalScrollBar.getValue() > posicionBarra) {
                    horizontalScrollBar.setValue(horizontalScrollBar.getValue() - 10);
                } else {
                    // Detenemos el Timer cuando alcanzamos la posición deseada
                    tiempoScroll.stop();
                    cambioMensajes.setVisible(true);
                    panelStandar.revalidate();
                    panelStandar.repaint();
                }
            }
        });

        //FUNCIONES DE BOTONES
        // Modifica los listeners de tus botones para que inicien la animación en lugar de setear el valor directamente
        irPanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                posicionBarra = 0;
                cambioMensajes.setVisible(false);
                
                irPanel1.setIcon(new ImageIcon("Imagenes/Iconos/selectorActiv.png"));
                irPanel2.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel3.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel4.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                
                panelStandar.revalidate();
                panelStandar.repaint();
                
                tiempoScroll.start();
            }
        });

        irPanel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                posicionBarra = 500;
                cambioMensajes.setVisible(false);
                
                irPanel1.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel2.setIcon(new ImageIcon("Imagenes/Iconos/selectorActiv.png"));
                irPanel3.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel4.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                
                panelStandar.revalidate();
                panelStandar.repaint();
                
                tiempoScroll.start();
            }
        });

        irPanel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                posicionBarra = 1000;
                cambioMensajes.setVisible(false);
                
                irPanel1.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel2.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel3.setIcon(new ImageIcon("Imagenes/Iconos/selectorActiv.png"));
                irPanel4.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                
                
                panelStandar.revalidate();
                panelStandar.repaint();
                
                tiempoScroll.start();
            }
        });

        irPanel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                posicionBarra = 1500;
                cambioMensajes.setVisible(false);
                
                irPanel1.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel2.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel3.setIcon(new ImageIcon("Imagenes/Iconos/selectorDesActiv.png"));
                irPanel4.setIcon(new ImageIcon("Imagenes/Iconos/selectorActiv.png"));
                
                panelStandar.revalidate();
                panelStandar.repaint();
                
                tiempoScroll.start();
            }
        });
        

        PanelesInfo(mensaje);
        
        // PERONAJES CON CHAT INTERACTUABLE
        JLabel darwin = new JLabel();
        darwin.setIcon(new ImageIcon((new ImageIcon("Imagenes/Animaciones/darwin1-1.png")).getImage().getScaledInstance(131, 205, Image.SCALE_SMOOTH)));
        darwin.setBounds(300, yDar, 131, 205);
        fondo.add(darwin);
        
        JLabel vargas = new JLabel();
        vargas.setIcon(new ImageIcon((new ImageIcon("Imagenes/Animaciones/vargas1-1.png")).getImage().getScaledInstance(146, 193, Image.SCALE_SMOOTH)));
        vargas.setBounds(100, yVar, 146, 193);
        fondo.add(vargas);
        
        JLabel chat1 = new JLabel();
        chat1.setLayout(null);
        chat1.setBounds(370, 230, 160, 100);
        fondo.add(chat1);
        
        JLabel textDarwin = new JLabel();
        textDarwin.setFont(new Font("Arial", 1, 10));
        textDarwin.setBounds(10, 10, 140, 40);
        chat1.add(textDarwin);
        
        JLabel chat2 = new JLabel();
        chat2.setLayout(null);
        chat2.setBounds(10, 230, 160, 100);
        fondo.add(chat2);
        
        JLabel textVargas = new JLabel();
        textVargas.setFont(new Font("Arial", 1, 10));
        textVargas.setBounds(10, 10, 140, 40);
        chat2.add(textVargas);

        // TEMPORIZADOR DE ANIMACIÓN 1
        // Crear un temporizador para actualizar la posición del panel
        animadorVD = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar la posición del panel
                yDar -= 4; // Cambia la velocidad del desplazamiento ajustando este valor
                yVar -= 4;
                darwin.setLocation(300, yDar);
                vargas.setLocation(100, yVar);
                fondo.repaint();

                // Detener la animación cuando el panel llegue a su destino
                if (yDar <= 290 && yVar <= 300) {
                    animadorVD.stop();
                    mensajesDV(darwin, vargas, chat1, chat2, textDarwin, textVargas);
                }
            }
        });
        
        animadorMensj = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar la posición del panel
                yPanel1 += 10; // Cambia la velocidad del desplazamiento ajustando este valor
                fondoMensajes.setLocation(yPanel1, 20);
                cambioMensajes.setLocation(yPanel1 + 230, 190);
                fondo.repaint();

                // Detener la animación cuando el panel llegue a su destino
                if (yPanel1 >= 20) {
                    animadorMensj.stop();
                }
            }
        });
        
        anim1();
    }
    
    private void anim1(){
        animadorVD.start();
        animadorMensj.start();
    }
    
    private void mensajesDV(JLabel darwin, JLabel vargas, JLabel chat1, JLabel chat2, JLabel textDarwin, JLabel textVargas){
        // Crear un temporizador Swing
        ImageIcon darwin1 = new ImageIcon((new ImageIcon("Imagenes/Animaciones/darwin1-1.png")).getImage().getScaledInstance(131, 205, Image.SCALE_SMOOTH));
        ImageIcon darwin2 = new ImageIcon((new ImageIcon("Imagenes/Animaciones/darwin1-2.png")).getImage().getScaledInstance(131, 205, Image.SCALE_SMOOTH));
        ImageIcon vargas1 = new ImageIcon((new ImageIcon("Imagenes/Animaciones/vargas1-1.png")).getImage().getScaledInstance(146, 193, Image.SCALE_SMOOTH));
        ImageIcon vargas2 = new ImageIcon((new ImageIcon("Imagenes/Animaciones/vargas1-2.png")).getImage().getScaledInstance(146, 193, Image.SCALE_SMOOTH));
        ImageIcon imgchat1 = new ImageIcon((new ImageIcon("Imagenes/Animaciones/chat.png")).getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH));
        ImageIcon imgchat2 = new ImageIcon((new ImageIcon("Imagenes/Animaciones/chat2.png")).getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH));
        
        mensajesTemp = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Random rand = new Random();
                 int numeroAleatorio = rand.nextInt(4);
                 
                hablarTemp = new Timer(100, new ActionListener() {
                    int contador = 0;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Cambiar los íconos cada 2 segundos durante 20 segundos
                        if (contador < 30) {
                            if (cambiador1 == false){
                                darwin.setIcon(darwin2);
                                vargas.setIcon(vargas2);
                                chat1.setIcon(imgchat1);
                                chat2.setIcon(imgchat2);
                                mensajeAleatorio(textDarwin, textVargas, numeroAleatorio);
                                fondo.revalidate();
                                fondo.repaint();
                                cambiador1 = true;
                            }else{
                                darwin.setIcon(darwin1);
                                vargas.setIcon(vargas1);
                                chat1.setIcon(imgchat1);
                                chat2.setIcon(imgchat2);
                                mensajeAleatorio(textDarwin, textVargas, numeroAleatorio);
                                fondo.revalidate();
                                fondo.repaint();
                                cambiador1 = false;
                            }
                            contador++;
                        } else {
                            // Detener el temporizador
                            darwin.setIcon(darwin1);
                            vargas.setIcon(vargas1);
                            chat1.setIcon(null);
                            chat2.setIcon(null);
                            textDarwin.setText("");
                            textVargas.setText("");
                            fondo.revalidate();
                            fondo.repaint();
                            ((Timer) e.getSource()).stop();
                        }
                    }
                });

                // Iniciar el temporizador secundario
                hablarTemp.start();
            }
        });

        // Iniciar el temporizador
        mensajesTemp.start();
    }
    
    public void detenerMensajes() {
        // Detener el temporizador de animación de mensajes
        if (mensajesTemp != null && mensajesTemp.isRunning()) {
            mensajesTemp.stop();
        }
        if (hablarTemp != null && hablarTemp.isRunning()) {
            hablarTemp.stop();
        }
    }
    
    private void mensajeAleatorio(JLabel textDarwin, JLabel textVargas, int Numero){
        if (Numero == 0){
            textDarwin.setText("<html><p>NT</p></html>");
            textVargas.setText("<html><p>Jelou guorld.</p></html>");
        }else if (Numero == 1){
            textDarwin.setText("<html><p>Espero que disfrutes de nuestra aplicación.</p></html>");
            textVargas.setText("<html><p>Pasala bien dentro de nuestro aplicativo.</p></html>");
        }else if (Numero == 2){
            textDarwin.setText("<html><p>Sopas.</p></html>");
            textVargas.setText("<html><p>Buenas vibras.</p></html>");
        }else if (Numero == 3){
            textDarwin.setText("<html><p>Te juraron falso amor y lo creiste.</p></html>");
            textVargas.setText("<html><p>Te pintaron pajaritos en el aire.</p></html>");
        }
    }
    
    private void PanelesInfo(JPanel mensaje){
        // PANEL 1
        JPanel info1 = new JPanel();
        info1.setLayout(null);
        info1.setBackground(Color.decode(Menu.colorBotonOscuro));
        info1.setBounds(10, 10, 480, 180);
        mensaje.add(info1);
        
        JLabel titulo = new JLabel();
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(0, 10, 480, 30);
        if (Menu.usuario != null){
            if (Menu.usuario.get(7).equals("Masculino")){
                titulo.setText("Bienvenido " + Menu.usuario.get(0));
            }else if(Menu.usuario.get(7).equals("Femenino")){
                titulo.setText("Bienvenida " + Menu.usuario.get(0));
            }else if(Menu.usuario.get(7).equals("No especificado")){
                titulo.setText("Bienvenide " + Menu.usuario.get(0));
            }
        }
            
        titulo.setForeground(Color.white);
        titulo.setFont(new Font("Arial", 1, 30));
        info1.add(titulo);
        
        JLabel textoIntro = new JLabel();
        textoIntro.setBounds(0, 50, 480, 120);
        textoIntro.setBorder(new EmptyBorder(10,10,10,10));
        textoIntro.setText("<html><p>Area Management Security es una aplicación para escritorio enfocada"
                + " en reducir los hurtos y anomalías generados en las diferentes zonas de trabajo.</p></html>");
        textoIntro.setForeground(Color.white);
        textoIntro.setPreferredSize(new Dimension(500, 150));
        textoIntro.setMaximumSize(new Dimension(500, 150));
        textoIntro.setFont(new Font("Arial", 1, 20));
        info1.add(textoIntro);
        
        // PANEL 2
        JPanel info2 = new JPanel();
        info2.setBackground(Color.decode(Menu.colorBotonOscuro));
        
        JLabel imgInfo2 = new JLabel();
        imgInfo2.setIcon(new ImageIcon("Imagenes/Info/infoMonitoreo.png"));
        info2.add(imgInfo2);
        
        info2.setBounds(510, 10, 480, 180);
        mensaje.add(info2);
        
        // PANEL 3
        JPanel info3 = new JPanel();
        info3.setBackground(Color.decode(Menu.colorBotonOscuro));
        
        JLabel imgInfo3 = new JLabel();
        imgInfo3.setIcon(new ImageIcon("Imagenes/Info/infoChatBot.png"));
        info3.add(imgInfo3);
        
        info3.setBounds(1010, 10, 480, 180);
        mensaje.add(info3);
        
        // PANEL 4
        JPanel info4 = new JPanel();
        info4.setBackground(Color.decode(Menu.colorBotonOscuro));
        
        JLabel imgInfo4 = new JLabel();
        imgInfo4.setIcon(new ImageIcon("Imagenes/Info/infoRegistrar.png"));
        info4.add(imgInfo4);
        
        info4.setBounds(1510, 10, 480, 180);
        mensaje.add(info4);
    }
}
