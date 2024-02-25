package Ventanas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ZonasMapa extends JFrame{
    public JLabel fondo = new JLabel();
    
    public ZonasMapa(){
        PanelFondo();
    }
    
    private void PanelFondo(){
        
        //Creamos la ventana.
        setTitle("Mapas");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //Establecemos imagen de fondo.
        fondo = new JLabel(new ImageIcon("Imagenes/fondo.png"));
        this.add(fondo);
    }
}
