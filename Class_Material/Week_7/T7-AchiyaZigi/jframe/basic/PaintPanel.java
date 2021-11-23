package jframe.basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class PaintPanel extends JPanel implements MouseInputListener {

    public int brushSize = 10;
    private int mouseX = -1;
    private int mouseY = -1;
    private boolean mousePressed = false;

    public PaintPanel() {
        super();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mousePressed = true;
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.repaint(this.mouseX, this.mouseY, this.brushSize, this.brushSize);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        g.setColor(new Color(red, green, blue));
        if (this.mousePressed) {
            g.fillRect(this.mouseX, this.mouseY, this.brushSize * 2, this.brushSize * 2);
        }
    }

}
