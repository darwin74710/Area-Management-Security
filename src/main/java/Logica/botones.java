package Logica;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class botones {
    public static boolean activContra = false;
    public static SistemMonitoreo sistemaMapas = new SistemMonitoreo();
    
    public static void verContra(JPasswordField textoContraseña, JLabel botonVerContra, ImageIcon logoVer, ImageIcon logoNoVer){
        
        botonVerContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (activContra == false){
                    textoContraseña.setEchoChar((char) 0);
                    botonVerContra.setIcon(new ImageIcon(logoVer.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                    activContra = true;
                }else{
                    textoContraseña.setEchoChar('*');
                    botonVerContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                    activContra = false;
                }
            }
            
        });
    }
}