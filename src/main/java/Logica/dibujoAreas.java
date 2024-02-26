package Logica;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JButton;

public class dibujoAreas extends JFrame implements ActionListener{
    JMenu archivo,dibujar,ayuda;
    JMenuItem color;
    ButtonGroup btn;
    MiPanel miPanel;
    
    public dibujoAreas(){
        crearmenu();
        addlisteners();
        miPanel = new MiPanel();
        this.add(miPanel);
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    public void addlisteners(){
        color.addActionListener(this);
    }
    
    public void crearmenu(){
        JMenuBar menu = new JMenuBar();
        dibujar = new JMenu("Dibuja pelotudo"); btn=new ButtonGroup();
        color = new JMenuItem("colorsito uwu");
        dibujar.add(color);
        menu.add(dibujar);
        this.setJMenuBar(menu);
    }
    
    public static void main (String[] args){
        dibujoAreas miVentana = new dibujoAreas();
        miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==color){
            Color color = JColorChooser.showDialog(this, "Seleccione un color puto", this.miPanel.getColorActual());
            this.miPanel.setColorActual(color);
        }
        }
}

class MiPanel extends JPanel{
    Point p1;
    Point p2;
    Shape figura;
    Random R=new Random();
    public Color coloractual = Color.MAGENTA;
    BufferedImage myImage;
    Graphics2D g2D;
    
    public MiPanel(){
        OyenteDeRaton miOyente = new OyenteDeRaton();
        OyenteDeMovimiento miOyente2 = new OyenteDeMovimiento();
        addMouseListener(miOyente);
        addMouseMotionListener(miOyente2);
    }
    
    public Color getColorActual(){
        return coloractual;
    }
    
    public void setColorActual(Color color){
        coloractual = color;
    }
    
    public Graphics2D crearGraphics2D(){
        Graphics2D g2 = null;
        if (myImage == null || myImage.getWidth() != getSize().width
                || myImage.getHeight() != getSize().height) {
            myImage = (BufferedImage) createImage(getSize().width, getSize().height);
        }
        if (myImage != null){
            g2 = myImage.createGraphics();
            g2.setColor(coloractual);
            g2.setBackground(getBackground());
        }
        g2.clearRect(0,0,getSize().width,getSize().height);
        return g2;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(myImage == null){
            g2D = crearGraphics2D();
        }
        if(figura!=null){
            g2D.setColor(coloractual);
            g2D.draw(figura);
            g2D.fill(figura);
            if(myImage != null && isShowing()){
                g.drawImage(myImage, 0, 0, this);
            }
            figura=null;
        }
    }
    public Shape crearFigura(Point p1, Point p2){
        double xInicio = Math.min(p1.getX(), p2.getX());
        double yInicio = Math.min(p1.getY(), p2.getY());
        double ancho = Math.abs(p2.getX() - p1.getX());
        double altura = Math.abs(p2.getY() - p1.getY());
        Shape nuevaFigura = new Rectangle2D.Double(xInicio,yInicio, ancho, altura);
        
        
        int xInicioInt = (int) xInicio;
        int yInicioInt = (int) yInicio;
        int anchoInt = (int) ancho;
        int alturaInt = (int) altura;
        
        //VOY AQUI
        JButton boton = new JButton();
        boton.setBounds(xInicioInt,yInicioInt,anchoInt,alturaInt);
        
        return nuevaFigura;
    }
    
    public void resetAll(){
        myImage = null;
        
        repaint();
    }
    
    class OyenteDeRaton extends MouseAdapter{
        public void mousePressed(MouseEvent evento){
            MiPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            p1 = evento.getPoint();
        }
        public void mouseReleased (MouseEvent evento){
            MiPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            p2 = evento.getPoint();
            figura = crearFigura(p1,p2);
            repaint();
            
        }
    }
    class OyenteDeMovimiento extends MouseMotionAdapter{
        public void mouseDragged(MouseEvent evento){
            Graphics2D g2D;
            if (figura != null){
                g2D = (Graphics2D) MiPanel.this.getGraphics();
                g2D.setXORMode(MiPanel.this.getBackground());
                g2D.setColor(coloractual);
                g2D.draw(figura);
            }
            p2 = evento.getPoint();
            figura = crearFigura(p1,p2);
            
            g2D = (Graphics2D) MiPanel.this.getGraphics();
            g2D.setXORMode(MiPanel.this.getBackground());
            g2D.setColor(coloractual);
            g2D.draw(figura);
        }
    }
}
