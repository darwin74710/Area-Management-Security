package Ventanas;

import Logica.AnimMenu;
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
    public AnimMenu anim = new AnimMenu();
    
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
        fondo.setLayout(null);
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
                anim.detenerMensajes();
                anim.standar();
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
        
        JTextArea info = new JTextArea("Hola, este es el manual, aquí podras encontrar informacion que te sea de utilidad mediante las siguientes opciones:\n\n"
                                      +"1- Manual de uso.\n"
                                      +"2- Seguridad de monitoreo.\n"
                                      +"3- Seguridad en el trabajo.\n"
                                      +"4- Buen control de áreas.\n");
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
        imagenHormig.setIcon(new ImageIcon((new ImageIcon(menu.usuario.get(9))).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
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

        IAmensajes(info, mensaje);
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
            mensaje.setText("MANUAL DE USO");
        }else if (numeroChat == 0 && chat.getText().equals("2")){
            mensaje.setText("SEGURIDAD DE MONITOREO");
        }else if (numeroChat == 0 && chat.getText().equals("3")){
            mensaje.setText("SEGURIDAD EN EL TRABAJO");
        }else if (numeroChat == 0 && chat.getText().equals("4")){
            mensaje.setText("BUEN CONTROL DE ÁREAS");
        }
        
        //---------- MANUAL DE USO -------------------
        else if (numeroChat == 01 && chat.getText().equals("1")){
            mensaje.setText("SISTEMA DE ÁREAS");
        }else if (numeroChat == 01 && chat.getText().equals("2")){
            mensaje.setText("SISTEMA DE MONITOREO");
        }else if (numeroChat == 01 && chat.getText().equals("3")){
            mensaje.setText("SISTEMA DE INFORMES");
        }else if (numeroChat == 02 && chat.getText().equals("1")){
            mensaje.setText("VOLVER");
        }
        
        //---------- SEGURIDAD DE MONITOREO -------------------
        else if (numeroChat == 11 && chat.getText().equals("1")){
            mensaje.setText("¿QUÉ ES UN SISTEMA DE SEGURIDAD DE MONITOREO PERMANENTE?");
        }else if (numeroChat == 11 && chat.getText().equals("2")){
            mensaje.setText("¿CÓMO FUNCIONA UN SISTEMA DE SEGURIDAD DE MONITOREO?");
        }else if (numeroChat == 11 && chat.getText().equals("3")){
            mensaje.setText("¿CUÁLES SON LOS BENEFICIOS DE UN SISTEMA DE SEGURIDAD DE MONITOREO PERMANENTE?");
        }else if (numeroChat == 12 && chat.getText().equals("1")){
            mensaje.setText("VOLVER");
        }
        
        //---------- SEGURIDAD EN EL TRABAJO -------------------
        else if (numeroChat == 21 && chat.getText().equals("1")){
            mensaje.setText("¿QUÉ ES LA SEGURIDAD EN EL TRABAJO?");
        }else if (numeroChat == 21 && chat.getText().equals("2")){
            mensaje.setText("¿CÓMO PUEDO APLICAR LA SEGURIDAD EN MI TRABAJO?");
        }else if (numeroChat == 21 && chat.getText().equals("3")){
            mensaje.setText("¿CUÁLES SON LOS BENEFICIOS DE APLICAR LA SEGURIDAD EN MI TRABAJO?");
        }else if (numeroChat == 22 && chat.getText().equals("1")){
            mensaje.setText("VOLVER");
        }
        
        //---------- BUEN CONTROL DE AREAS -------------------
        else if (numeroChat == 31 && chat.getText().equals("1")){
            mensaje.setText("¿QUÉ ES EL CONTROL DE ÁREAS?");
        }else if (numeroChat == 31 && chat.getText().equals("2")){
            mensaje.setText("¿CÓMO PUEDO MANTENER UN BUEN CONTROL DE ÁREAS?");
        }else if (numeroChat == 31 && chat.getText().equals("3")){
            mensaje.setText("¿CUÁLES LOS BENEFICIOS DE MANTENER UN BUEN CONTROL DE ÁREAS?");
        }else if (numeroChat == 32 && chat.getText().equals("1")){
            mensaje.setText("VOLVER");
        }
        
        else if ((numeroChat == 01 || numeroChat == 11 || numeroChat == 21 || numeroChat == 31 )  && chat.getText().equals("4")){
            mensaje.setText("VOLVER");
        }else{
            mensaje.setText(chat.getText());
        }
    }
    
    private void IAmensajes(JTextArea mensaje, JPanel cuadroMensaje){
        //------------ PRIMERA OPCIÓN ---------------
        if (numeroChat == 0 && chat.getText().equals("1")){
            mensaje.setText("¿Qué deseas saber acerca del manual de uso?\n\n"
                    + "1- Sistema de áreas.\n"
                    + "2- Sistema de monitoreo.\n"
                    + "3- Sistema de informes.\n"
                    + "4- Volver.");
            numeroChat = 01;
            
        }else if (numeroChat == 0 && chat.getText().equals("2")){
            mensaje.setText("¿Qué deseas saber acerca de la seguridad de monitoreo?\n\n"
                    + "1- ¿Qué es un sistema de seguridad de monitoreo permanente?\n"
                    + "2- ¿Cómo funciona un sistema de seguridad monitoreado?\n"
                    + "3- ¿Cuáles son los beneficios de un sistema de seguridad de monitoreo permanente?\n"
                    + "4- Volver.");
            numeroChat = 11;
            
        }else if (numeroChat == 0 && chat.getText().equals("3")){
            mensaje.setText("¿Qué deseas saber acerca de la seguridad en el trabajo?\n\n"
                    + "1- ¿Qué es la seguridad en el trabajo?\n"
                    + "2- ¿Cómo puedo aplicar la seguridad en mi trabajo?\n"
                    + "3- ¿Cuáles son los beneficios de aplicar la seguridad en mi trabajo?\n"
                    + "4- Volver.");
            numeroChat = 21;
            
        }else if (numeroChat == 0 && chat.getText().equals("4")){
            mensaje.setText("¿Qué deseas saber acerca del buen control de áreas?\n\n"
                    + "1- ¿Qué es el control de áreas?\n"
                    + "2- ¿Cómo puedo mantener un buen control de áreas?\n"
                    + "3- ¿Cuáles son los beneficios de mantener un buen control de áreas?\n"
                    + "4- Volver.");
            numeroChat = 31;
        }
        
        
        //---------- MANUAL DE USO -------------------
        else if (numeroChat == 01 && chat.getText().equals("1")){
            mensaje.setText("Nuestro sistema de áreas cuenta con un mapa en el cual podrás diseñar "
                    + "tu propio esquema con figuras geométricas (cuadrados y rectángulos), cada "
                    + "elemento del mapa tendrá asignado un nombre, descripción, imagen, una cámara "
                    + "y un historial de informes.\n\n"
                    + "Solo el administrador puede guardar y eliminar los mapas, también puede crear, "
                    + "editar y eliminar áreas.\n\n"
                    + "1- Volver.");
            mensaje.setPreferredSize(new Dimension(455, 160));
            mensaje.setPreferredSize(new Dimension(455, 160));
            cuadroMensaje.setPreferredSize(new Dimension(620, 180));
            cuadroMensaje.setMaximumSize(new Dimension(620, 180));
            numeroChat = 02;
        }else if (numeroChat == 01 && chat.getText().equals("2")){
            mensaje.setText("Nuestro sistema de monitoreo está diseñado para utilizar las cámaras conectadas "
                    + "a la computadora, junto a estas realiza el proceso para detectar si hay movimiento en "
                    + "las cámaras asignadas a las áreas, puedes activar este sistema con un botón de un ojo "
                    + "que encuentras en la ventana de las áreas, debajo de este hay otro botón para acceder "
                    + "a las grabaciones guardadas para cada cámara.\n\n"
                    + "Es importante aclarar que solo se graba un video por detección, al finalizar el "
                    + "trabajo hay un tiempo de espera de 10 segundos para poder detectar el siguiente "
                    + "movimiento.\n\n"
                    + "1- Volver.");
            mensaje.setPreferredSize(new Dimension(455, 210));
            mensaje.setPreferredSize(new Dimension(455, 210));
            cuadroMensaje.setPreferredSize(new Dimension(620, 230));
            cuadroMensaje.setMaximumSize(new Dimension(620, 230));
            numeroChat = 02;
        }else if (numeroChat == 01 && chat.getText().equals("3")){
            mensaje.setText("Dentro de cada área se pueden crear informes según lo ocurrido en las grabaciones, "
                    + "de esto se encargan cualquiera de los dos usuarios, solamente el administrador puede "
                    + "editarlos y eliminarlos.\n\n"
                    + "1- Volver.");
            numeroChat = 02;
        }else if (numeroChat == 02 && chat.getText().equals("1")){
            mensaje.setText("¿Qué deseas saber acerca del manual de uso?\n\n"
                    + "1- Sistema de areas.\n"
                    + "2- Sistema de monitoreo.\n"
                    + "3- Sistema de informes.\n"
                    + "4- Volver.");
            numeroChat = 01;
        }
        
        //---------- SEGURIDAD DE MONITOREO -------------------
        else if (numeroChat == 11 && chat.getText().equals("1")){
            mensaje.setText("Un sistema de seguridad de monitoreo permanente está integrado por "
                    + "un conjunto de profesionales trabajando 24/7 que emplean tecnología de punta "
                    + "y en algunos casos inteligencia artificial, para proteger nuestro edificio o "
                    + "propiedad.\n\n"
                    + "Este sistema funciona mediante la conexión y supervisión constante "
                    + "de los dispositivos de seguridad, como cámaras de seguridad con detección de "
                    + "movimiento, alarmas de seguridad perimetral, alarmas de procesos de "
                    + "producción, alarmas del sistema de aire acondicionado, alarmas de incendio, "
                    + "etc. También se incluyen los controles de acceso y los sistemas de vigilancia "
                    + "en las porterías, entre otros.\n\n"
                    + "Extraido de: https://porteroseguro.com/que-es-un-sistema-de-seguridad-de-monitoreo-permanente-y-como-funciona/"
                    + "\n\n"
                    + "1- Volver.");
            
            mensaje.setPreferredSize(new Dimension(455, 290));
            mensaje.setPreferredSize(new Dimension(455, 290));
            cuadroMensaje.setPreferredSize(new Dimension(620, 310));
            cuadroMensaje.setMaximumSize(new Dimension(620, 310));
            numeroChat = 12;
            
        }else if (numeroChat == 11 && chat.getText().equals("2")){
            mensaje.setText("Un sistema de seguridad de monitoreo permanente funciona mediante la"
                    + " integración de diversos componentes que se encargan de proteger y"
                    + " supervisar nuestro edificio o propiedad. Por ejemplo, cuando una cámara"
                    + " detecta una actividad sospechosa, como una persona que cubre su rostro y"
                    + " que está observando mucho tiempo el edificio, se envía una señal a la central"
                    + " de monitoreo.\n\n"
                    + "Los operadores de la central reciben la alerta y visualizan las imágenes de "
                    + "las cámaras en tiempo real para evaluar la situación. Los operadores pueden "
                    + "tomar acciones inmediatas de manera preventiva, como dar un mensaje disuasivo "
                    + "por un altavoz, contactar a los propietarios, notificar a la policía o activar "
                    + "una alarma sonora para disuadir al intruso.\n\n"
                    + "Extraido de: https://porteroseguro.com/que-es-un-sistema-de-seguridad-de-monitoreo-permanente-y-como-funciona/"
                    + "\n\n"
                    + "1- Volver.");
            
            mensaje.setPreferredSize(new Dimension(455, 310));
            mensaje.setPreferredSize(new Dimension(455, 310));
            cuadroMensaje.setPreferredSize(new Dimension(620, 330));
            cuadroMensaje.setMaximumSize(new Dimension(620, 330));
            numeroChat = 12;
        }else if (numeroChat == 11 && chat.getText().equals("3")){
            mensaje.setText("Un sistema de seguridad de monitoreo permanente ofrece una serie de "
                    + "beneficios importantes para protegernos. A continuación, enumeramos algunos:\n\n"
                    + "- Respuesta rápida ante emergencias\n"
                    + "- Mayor nivel de protección\n"
                    + "- Tranquilidad y confianza\n"
                    + "- Supervisión continua\n"
                    + "- Integración con otros sistemas\n\n"
                    + "Extraido de: https://porteroseguro.com/que-es-un-sistema-de-seguridad-de-monitoreo-permanente-y-como-funciona/"
                    + "\n\n"
                    + "1- Volver.");
            
            mensaje.setPreferredSize(new Dimension(455, 260));
            mensaje.setPreferredSize(new Dimension(455, 260));
            cuadroMensaje.setPreferredSize(new Dimension(620, 280));
            cuadroMensaje.setMaximumSize(new Dimension(620, 280));
            numeroChat = 12;
            
        }else if (numeroChat == 12 && chat.getText().equals("1")){
            mensaje.setText("¿Qué deseas saber acerca de la seguridad de monitoreo?\n\n"
                    + "1- ¿Qué es un sistema de seguridad de monitoreo permanente?\n"
                    + "2- ¿Cómo funciona un sistema de seguridad monitoreado?\n"
                    + "3- ¿Cuáles son los beneficios de un sistema de seguridad de monitoreo permanente?\n"
                    + "4- Volver.");
            numeroChat = 11;
        }
        
        //---------- SEGURIDAD EN EL TRABAJO -------------------
        else if (numeroChat == 21 && chat.getText().equals("1")){
            mensaje.setText("La seguridad en el trabajo es la disciplina encuadrada en la prevención "
                    + "de riesgos laborales cuyo objetivo es la aplicación de medidas y el desarrollo "
                    + "de las actividades necesarias para la prevención de riesgos derivados del "
                    + "trabajo. Se trata de un conjunto de técnicas y procedimientos que tienen como "
                    + "resultado eliminar o disminuir el riesgo de que se produzcan accidentes.\n\n"
                    + "Fuente: https://www.quironprevencion.com/blogs/es/prevenidos/seguridad-trabajo"
                    + "\n\n"
                    + "1- Volver.");
            mensaje.setPreferredSize(new Dimension(455, 180));
            mensaje.setPreferredSize(new Dimension(455, 180));
            cuadroMensaje.setPreferredSize(new Dimension(620, 200));
            cuadroMensaje.setMaximumSize(new Dimension(620, 200));
            numeroChat = 22;
        }else if (numeroChat == 21 && chat.getText().equals("2")){
            mensaje.setText("Puedes aplicarla siguiendo unos cuantos consejos:\n\n" +
                    "- Sé consciente de lo que te rodea.\n" +
                    "- Mantén la postura correcta para proteger tu espalda.\n" +
                    "- Toma descansos regulares.\n" +
                    "- Utiliza herramientas y máquinas correctamente.\n" +
                    "- Mantén libres las salidas de emergencia.\n" +
                    "- Informa a tu supervisor si detectas una situación insegura.\n" +
                    "- Usa ayudas mecánicas siempre que sea posible.\n" +
                    "- No trabajes ebrio.\n" +
                    "- Reduce el estrés laboral.\n" +
                    "- Usa el equipo de seguridad correcto.\n\n"
                    + "Fuente: https://www.vivosano.org/10-consejos-de-seguridad-en-el-trabajo/"
                    + "\n\n"
                    + "1- Volver.");
            mensaje.setPreferredSize(new Dimension(455, 280));
            mensaje.setPreferredSize(new Dimension(455, 280));
            cuadroMensaje.setPreferredSize(new Dimension(620, 300));
            cuadroMensaje.setMaximumSize(new Dimension(620, 300));
            numeroChat = 22;
        }else if (numeroChat == 21 && chat.getText().equals("3")){
            mensaje.setText("Algunos de los beneficios al aplicar la seguridad y salud en mi trabajo son:\n\n" +
                    "- Aumentar la productividad laboral y la confianza de los trabajadores hacia la empresa.\n" +
                    "- Reducir la rotación y el absentismo laboral.\n" +
                    "- Potenciar de manera favorable el clima laboral y la motivación.\n" +
                    "- Mejorar la reputación y la imagen de la organización.\n" +
                    "- Disminuir las pérdidas y costos que conllevan los accidentes y enfermedades que merman las actividades de los empleados.\n" +
                    "- Un beneficio general para la sociedad en temas de seguridad social, costos sanitarios, indemnizaciones y más.\n\n" +
                    "Fuente: https://www.esan.edu.pe/conexion-esan/salud-ocupacional-que-es-y-cuales-son-los-beneficios-que-aporta-a-la-empresa"
                    + "\n\n"
                    + "1- Volver.");
            mensaje.setPreferredSize(new Dimension(455, 290));
            mensaje.setPreferredSize(new Dimension(455, 290));
            cuadroMensaje.setPreferredSize(new Dimension(620, 310));
            cuadroMensaje.setMaximumSize(new Dimension(620, 310));
            numeroChat = 22;
        }else if (numeroChat == 22 && chat.getText().equals("1")){
            mensaje.setText("¿Qué deseas saber acerca de la seguridad en el trabajo?\n\n"
                    + "1- ¿Qué es la seguridad en el trabajo?\n"
                    + "2- ¿Cómo puedo aplicar la seguridad en mi trabajo?\n"
                    + "3- ¿Cuáles son los beneficios de aplicar la seguridad en mi trabajo?\n"
                    + "4- Volver.");
            numeroChat = 21;
        }
        
        //---------- BUEN CONTROL DE AREAS -------------------
        else if (numeroChat == 31 && chat.getText().equals("1")){
            mensaje.setText("El control de áreas se refiere a la gestión y supervisión de diversas "
                    + "zonas o espacios dentro de una organización, proyecto o territorio, con el "
                    + "fin de asegurar que se cumplan los objetivos específicos, normas y "
                    + "estándares establecidos. Este concepto puede aplicarse en diferentes "
                    + "contextos y sectores, como la industria, la seguridad, la gestión ambiental, "
                    + "la construcción, y otros.\n\n"
                    + "1- Volver.");
            mensaje.setPreferredSize(new Dimension(455, 150));
            mensaje.setPreferredSize(new Dimension(455, 150));
            cuadroMensaje.setPreferredSize(new Dimension(620, 170));
            cuadroMensaje.setMaximumSize(new Dimension(620, 170));
            numeroChat = 32;
        }else if (numeroChat == 31 && chat.getText().equals("2")){
            mensaje.setText("Puedes mantener un buen control de áreas siguiendo los siguientes consejos:\n\n" +
                    "- Evalúa y planifica inicialmente.\n" +
                    "- Implementa tecnología para mejorar la gestión de las áreas.\n" +
                    "- Establece procedimientos y protocolos.\n" +
                    "- Introduce formación y capacitación.\n" +
                    "- Emplea monitoreo continuo.\n\n"
                    + "1- Volver.");
            mensaje.setPreferredSize(new Dimension(455, 180));
            mensaje.setPreferredSize(new Dimension(455, 180));
            cuadroMensaje.setPreferredSize(new Dimension(620, 200));
            cuadroMensaje.setMaximumSize(new Dimension(620, 200));
            numeroChat = 32;
        }else if (numeroChat == 31 && chat.getText().equals("3")){
            mensaje.setText("Algunos de los beneficios de mantener un buen control de áreas son:\n\n" +
                    "- Mayor productividad y eficiencia.\n" +
                    "- Mejorar la seguridad.\n" +
                    "- Ahorrar tiempo y dinero.\n" +
                    "- Evitar accidentes.\n" +
                    "- Mejorar la calidad de vida.\n" +
                    "- Mejor toma de decisiones.\n\n"
                    + "1- Volver.");
            mensaje.setPreferredSize(new Dimension(455, 180));
            mensaje.setPreferredSize(new Dimension(455, 180));
            cuadroMensaje.setPreferredSize(new Dimension(620, 200));
            cuadroMensaje.setMaximumSize(new Dimension(620, 200));
            numeroChat = 32;
        }else if (numeroChat == 32 && chat.getText().equals("1")){
            mensaje.setText("¿Qué deseas saber acerca del buen control de areas?\n\n"
                    + "1- ¿Qué es el control de áreas?\n"
                    + "2- ¿Cómo puedo mantener un buen control de áreas?\n"
                    + "3- ¿Cuáles son los beneficios de mantener un buen control de áreas?\n"
                    + "4- Volver.");
            numeroChat = 31;
        }
        
        else if ((numeroChat == 01 || numeroChat == 11 || numeroChat == 21 || numeroChat == 31 ) && chat.getText().equals("4")){
            mensaje.setText("Hola, este es el manual, aquí podras encontrar informacion que te sea de "
                    + "utilidad mediante las siguientes opciones:\n\n"
                    + "1- Manual de uso.\n"
                    + "2- Seguridad de monitoreo.\n"
                    + "3- Seguridad en el trabajo.\n"
                    + "4- Buen control de areas.\n");
            numeroChat = 0;
        }else{
            mensaje.setText("Escoja una opción valida.");
        }
    }
}