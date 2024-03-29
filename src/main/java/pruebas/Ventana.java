package pruebas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ventana extends JFrame{
    
    public JPanel panel; //Panel en dónde colocas cosas, como en python.
    
    public Ventana(){       
        setTitle("Ingreso de usuario"); //Titulo pantalla
        setSize(700, 700); //Tamaño Pantallaa
        setLocationRelativeTo(null); //Centrar pantalla
        
        iniciarComponentes();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Cerrar la aplicación del todo al darle a la X
    }
    
    private void iniciarComponentes(){
        panel = new JPanel();
 
        colocarPaneles();
        //colocarEtiquetas(); 
        //colocarBotones();
        //colocarRadioBotones();
        //colocarCajasDeTexto();
        //colocarAreasDeTexto();
        //colocarListasDesplegables();
        accionBoton();  
    }
    
    private void colocarPaneles(){
        panel = new JPanel();
        
        panel.setLayout(null);
        //panel.setBackground(Color.blue); //Color del panel.
        this.getContentPane().add(panel); //Agregar el panel a la ventana.
    }
    
    private void colocarEtiquetas(){
        JLabel etiqueta = new JLabel("Si"); //Creamos una etiqueta de texto.
        etiqueta.setText("Ola");//Establecer texto en la etiqueta.
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER); //Centramos el texto de la imagén.
        etiqueta.setBounds(10, 10, 100, 40); //Establecer posición del texto.
        etiqueta.setForeground(Color.red); // Establecer color de la letra.
        etiqueta.setOpaque(true); //Activamos el permiso para cambiar el color de fondo de la etiqueta.
        etiqueta.setBackground(Color.gray);//Cambiar color de fondo de la etiqueta.
        etiqueta.setFont(new Font("Arial",Font.PLAIN,30)); //Establecemos la fuente del texto.
        panel.add(etiqueta); //Agregar la etiqueta al panel.
        
        //Etiqueta 2
        ImageIcon imagen = new ImageIcon("Imagenes/LogoInicio.png"); //Creamos la ruta de una imagen.
        JLabel etiqueta2 = new JLabel();//Creamos un layout de imagen.
        etiqueta2.setBounds(10,80,300,300);
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH))); //Ajustamos el tamaño de la imagen.
        panel.add(etiqueta2);
    }
    
    private void colocarBotones(){
        //Boton 1
        JButton boton1 = new JButton("Siguiente"); //Creamos un botón.
        boton1.setBounds(100, 100, 100, 40);
        boton1.setEnabled(true); //Habilitar o deshabilitar el botón.
        boton1.setMnemonic('a'); //Establecer interacción con la tecla alt + letra
        boton1.setForeground(Color.white); //Cambiar color a la letra.
        boton1.setBackground(Color.blue); //Establecer color de botón.
        boton1.setFont(new Font("Arial",Font.PLAIN,10));
        panel.add(boton1);
        
        //Boton 2
        JButton boton2 = new JButton();
        boton2.setBounds(100, 200, 100, 40);
        ImageIcon imagen = new ImageIcon("Imagenes/LogoInicio.png");
        boton2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(boton2.getWidth(), boton2.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(boton2);
    }
    
    private void colocarRadioBotones(){
        JRadioButton radioBoton1 = new JRadioButton("Opcion 1", true); //Botón para opción de verdadero o falso.
        radioBoton1.setBounds(50, 100, 100, 50);
        radioBoton1.setEnabled(true); //Activar disponibilidad de botón
        panel.add(radioBoton1);
        
        JRadioButton radioBoton2 = new JRadioButton("Opcion 2", false);
        radioBoton2.setBounds(50, 150, 100, 50);
        panel.add(radioBoton2);
        
        JRadioButton radioBoton3 = new JRadioButton("Opcion 3", true);
        radioBoton3.setBounds(50, 200, 100, 50);
        panel.add(radioBoton3);
        
        ButtonGroup grupoRadioBotones = new ButtonGroup(); //Grupo para los radioBotones para que no se activen todos a la vez.
        grupoRadioBotones.add(radioBoton1);
        grupoRadioBotones.add(radioBoton2);
        grupoRadioBotones.add(radioBoton3);
    }
    
    private void colocarCajasDeTexto(){
        JTextField cajaTexto = new JTextField(); //Creamos una caja de texto de 1 linea.
        cajaTexto.setBounds(50, 50, 100, 30);
        cajaTexto.setText("Hola...");
        
        System.out.println("Texto en la caja"+ cajaTexto.getText()); //Obtener el texto de la caja.
        panel.add(cajaTexto);
    }
    
    private void colocarAreasDeTexto(){
        JTextArea areaTexto = new JTextArea(); //Creamos un area de texto.
        areaTexto.setBounds(20, 20, 300, 200);
        areaTexto.append("si"); //Añadimos texto al area.
        areaTexto.setEditable(true); //Activamos edición del area de texto.
        panel.add(areaTexto);
    }
    
    private void colocarListasDesplegables(){
        String [] paises = {"Perú","Colombia","Paraguay","Ecuador"}; //Crear un array con strings.
        
        JComboBox listaDesplegable = new JComboBox(paises); //Crear una lista de opciones.
        listaDesplegable.setBounds(20, 20, 100,30);
        
        listaDesplegable.addItem("Argentina"); //Agregarle una nueva opción a la lista.
        listaDesplegable.setSelectedItem("Colombia"); //Seleccionar el primer objeto visto.
        panel.add(listaDesplegable);
    }
    
    private void accionBoton(){
        JLabel texto = new JLabel("Ingrese su nombre.");
        texto.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Borde para identificar si está bien.
        texto.setBounds(50, 0, 300, 40);
        texto.setFont(new Font("arial",1,20));
        panel.add(texto);
        
        JButton boton = new JButton("Pulse Aquí");
        boton.setBounds(50, 200, 150, 40);
        boton.setEnabled(true);
        panel.add(boton);
        
        JTextField cajaTexto = new JTextField();
        cajaTexto.setBounds(50, 100, 150, 40);
        panel.add(cajaTexto);
        
        JLabel saludo = new JLabel();
        saludo.setBounds(50, 300, 300, 40);
        saludo.setFont(new Font("arial",1,20));
        panel.add(saludo);
        
        //EventoBoton(boton, cajaTexto, saludo);
        EventoRaton(boton, cajaTexto);
    }
    
    private void EventoBoton(JButton boton, JTextField cajaTexto, JLabel saludo){
        //Implementar metodos abstractos del actionListener.
        ActionListener oyenteDeAccion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saludo.setText("Hola "+cajaTexto.getText());
            }
        };
        boton.addActionListener(oyenteDeAccion); //Asignar un evento al botón (una acción).
    }
    
    private void EventoRaton(JButton boton, JTextField cajaTexto) {
        //Agregamos las acciones del raton.
        MouseListener oyenteDeRaton = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("click dentro del botón completo.");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("presionar el botón.");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("click de botón completo incluso por fuera.");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("El mouse está dentro del botón.");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("El mouse salió del botón.");
            }
        };
        boton.addMouseListener(oyenteDeRaton);
    }
}