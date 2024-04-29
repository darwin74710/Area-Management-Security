package Logica;

import Guardado.SaveCamara;
import static Logica.CameraManager.cargarCamaras;
import static Logica.CameraManager.cerrarCamaras;
import Ventanas.Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SistemCamaras extends JFrame{
    SaveCamara guardado = new SaveCamara();
    public static JComboBox listaCamaras;
    public static JPanel panelCam = new JPanel();
    public int numIndex = 0;
    
    private JPanel panelPrin = new JPanel();
    
    Menu menu;
    
    // VALIDADOR DE CAMARAS
    int seleccion = 0;
    
    // METODO PARA CREAR UNA CAMARA
    public void AgregarCamara(JPanel panelPrincipal){
        panelPrin = panelPrincipal;
        // Utilizamos un JDialog para mostrar la creación de la cámara
        JDialog ventanaAgregar = new JDialog(this, "Agregar Cámara", true);
        ventanaAgregar.setSize(700, 550);
        ventanaAgregar.setLocationRelativeTo(null);
        ventanaAgregar.setResizable(false);

        JPanel fondo = new JPanel();
        fondo.setLayout(null);
        fondo.setBackground(Color.decode(menu.colorPanelMedio));
        
        panelCam = new JPanel();
        panelCam.setLayout(null);
        panelCam.setBackground(Color.decode(menu.colorPanelClaro));
        panelCam.setBounds(10,10,660,460);
        fondo.add(panelCam);
        
        listaCamaras = new JComboBox();
        listaCamaras.setBounds(80,0,500,20);
        panelCam.add(listaCamaras);
        
        //ELIMINAMOS LISTA DE CAMARAS
        listaCamaras.removeAllItems();    
        listaCamaras.addItem("Seleccione una camara");
        
        //CARGAMOS Y MOSTRALOS LAS CAMARAS
        CameraManager.cargarCamarasComboBox(listaCamaras);

        seleccion = 0;
        
        // Agregar ActionListener al JComboBox
        listaCamaras.addActionListener(e -> {
            if (listaCamaras.getSelectedIndex() == 0){
                CameraManager.eliminarCamaraDelPanel(panelCam);
                seleccion = 0;
            }else{
                if (seleccion == listaCamaras.getSelectedIndex()){
                    JOptionPane.showMessageDialog(this, "SELECCIONE OTRA CÁMARA.", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    seleccion = listaCamaras.getSelectedIndex();
                    CameraManager.eliminarCamaraDelPanel(panelCam);
                    cerrarCamaras();
                    
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                    cargarCamaras();
                    CameraManager.mostrarCamaraEnPanel(listaCamaras.getSelectedIndex() - 1, panelCam);
                    numIndex = listaCamaras.getSelectedIndex() - 1;
                }
            }
        });

        JButton botonAgregar = new JButton("AGREGAR");
        botonAgregar.setBounds(460,480,100,20);
        botonAgregar.setBackground(Color.decode(menu.colorBotonClaro));
        botonAgregar.setFocusPainted(false);
        botonAgregar.addActionListener(e -> guardar(ventanaAgregar));
        fondo.add(botonAgregar);
        
        JButton botonCancelar = new JButton("CANCELAR");
        botonCancelar.setBounds(570,480,100,20);
        botonCancelar.setBackground(Color.decode(menu.colorBotonClaro));
        botonCancelar.setFocusPainted(false);
        botonCancelar.addActionListener(e -> cancelar());
        fondo.add(botonCancelar);
        
        ventanaAgregar.add(fondo, BorderLayout.CENTER);
        ventanaAgregar.setLocationRelativeTo(this);
        
        ventanaAgregar.setVisible(true);
    }
    
    private void guardar(JDialog ventanaAgregar){
        guardado.GuardarCamara(ventanaAgregar, panelPrin, numIndex);
        CameraManager.eliminarCamaraDelPanel(panelCam);
        cerrarCamaras();
        
        cargarCamaras();
        CameraManager.mostrarCamaraEnPanel(listaCamaras.getSelectedIndex() - 1, panelPrin);
        
        // Boton para edicar la camara
        JButton editarCam = new JButton("EDITAR");
        editarCam.setBackground(Color.decode(menu.colorBotonClaro));
        editarCam.setFocusPainted(false);
        editarCam.setBounds(560, 0, 80, 20);
        editarCam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarCamara(panelPrin);
            }
        });
        panelPrin.add(editarCam);

    }
    
    private void cancelar(){
        CameraManager.eliminarCamaraDelPanel(panelCam);
        dispose();
    }
}