package Logica;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class botones {
    public static boolean activContra = false;
    public static SistemMonitoreo sistemaMapas = new SistemMonitoreo();
    
    public static void verContra(JPasswordField textoContraseña, JButton botonVerContra, ImageIcon logoVer, ImageIcon logoNoVer){
        
        ActionListener activar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activContra == false){
                    textoContraseña.setEchoChar((char) 0);
                    botonVerContra.setIcon(new ImageIcon(logoVer.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                    activContra = true;
                }else{
                    textoContraseña.setEchoChar('*');
                    botonVerContra.setIcon(new ImageIcon(logoNoVer.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                    activContra = false;
                }  
            }
        };
        botonVerContra.addActionListener(activar);
    }
}