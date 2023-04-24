package com.zefiroza.fractal;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements ActionListener {
    private Timer timer;
    public Surface() {
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(0,this);
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.red);

        int h = getHeight();
        int ax = 300;
        int ay = 450;
        int bx = 30;
        int by = 30;
        int cx = 600;
        int cy = 30;

        AffineTransform tform = AffineTransform.getTranslateInstance( 0, h);
        tform.scale( 1, -1);
        g2d.setTransform( tform);

        double r1 = Math.random();
        double r2 = Math.random();
        double x = (1 - Math.sqrt(r1)) * ax + (Math.sqrt(r1) * (1 - r2)) * bx + (Math.sqrt(r1) * r2) * cx;
        double y = (1 - Math.sqrt(r1)) * ay + (Math.sqrt(r1) * (1 - r2)) * by + (Math.sqrt(r1) * r2) * cy;
        for (int i = 0; i<500000;){
            double r3 = Math.random();
            if(r3 <= 0.33 ){
                double deltaX = ax - x;
                double deltaY = ay - y;
                double coeff = 0.5;
                x = x + coeff * deltaX;
                y = y + coeff * deltaY;
            }
            if(r3 > 0.33 && r3 <= 0.66){
                double deltaX = bx - x;
                double deltaY = by - y;
                double coeff = 0.5;
                x = x + coeff * deltaX;
                y = y + coeff * deltaY;
            }
            if(r3 > 0.66){
                double deltaX = cx - x;
                double deltaY = cy - y;
                double coeff = 0.5;
                x = x + coeff * deltaX;
                y = y + coeff * deltaY;
            }
            g2d.drawLine((int)x,(int)y,(int)x,(int)y);

            i++;
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}

public class FractalTriangle extends JFrame {

    public FractalTriangle() {

        initUI();
    }

    private void initUI() {

        final Surface surface = new Surface();
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });

        setTitle("Sierpinski Triangle");
        setSize(640, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            FractalTriangle ex = new FractalTriangle();
            ex.setVisible(true);
        });
    }
}
