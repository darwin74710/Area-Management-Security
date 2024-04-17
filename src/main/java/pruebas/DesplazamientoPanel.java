
package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DesplazamientoPanel extends JFrame {
    private JPanel contentPane;
    private JPanel scrollPanel;
    private JPanel[] panels;
    private int currentIndex = 0;
    private Point initialClick;
    private double speedFactor = 0.05; // Factor de velocidad

    public DesplazamientoPanel() {
        setTitle("Panel Desplazable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Crear un panel de desplazamiento horizontal
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(30, 30, 300, 200);
        contentPane.add(scrollPane);

        // Crear un panel para contener los paneles de desplazamiento
        scrollPanel = new JPanel();
        scrollPanel.setLayout(null);
        scrollPanel.setPreferredSize(new Dimension(1350, 200)); // Establecer el tamaño preferido para el desplazamiento horizontal
        scrollPane.setViewportView(scrollPanel);

        panels = new JPanel[3]; // Definir la cantidad de paneles
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(null);
            panels[i].setBackground(generateColor(i)); // Generar color para el panel
            panels[i].setBounds(i * 450, 0, 450, 200); // Establecer la posición y tamaño de cada panel
            JLabel label = new JLabel("Panel " + (i + 1)); // Etiqueta para identificar el panel
            label.setForeground(Color.WHITE);
            label.setBounds(10, 11, 414, 23);
            panels[i].add(label);
            scrollPanel.add(panels[i]);
        }

        scrollPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                // Mover los paneles para que el más cercano al centro sea completamente visible
                int minDistance = Integer.MAX_VALUE;
                int targetIndex = 0;
                for (int i = 0; i < panels.length; i++) {
                    int distance = Math.abs(scrollPanel.getWidth() / 2 - (panels[i].getX() + panels[i].getWidth() / 2));
                    if (distance < minDistance) {
                        minDistance = distance;
                        targetIndex = i;
                    }
                }
                movePanelsToCenter(targetIndex);
            }
        });
        scrollPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = panels[currentIndex].getX() + e.getX() - initialClick.x;
                int indexDelta = (int) ((e.getX() - initialClick.x) * speedFactor); // Ajustar la velocidad
                for (int i = 0; i < panels.length; i++) {
                    panels[i].setLocation(panels[i].getX() + indexDelta, panels[i].getY());
                }
            }
        });
    }

    private Color generateColor(int index) {
        // Ajustar los valores de rojo, verde y azul dentro del rango válido (0-255)
        int red = Math.min((index + 1) * 100, 255);
        int green = Math.min((index + 1) * 50, 255);
        int blue = Math.min((index + 1) * 20, 255);
        return new Color(red, green, blue);
    }

    private void movePanelsToCenter(int targetIndex) {
        int targetX = scrollPanel.getWidth() / 2 - panels[targetIndex].getWidth() / 2;
        for (int i = 0; i < panels.length; i++) {
            int newX = targetX + (i - targetIndex) * 450;
            panels[i].setLocation(newX, panels[i].getY());
        }
        currentIndex = targetIndex;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DesplazamientoPanel frame = new DesplazamientoPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}