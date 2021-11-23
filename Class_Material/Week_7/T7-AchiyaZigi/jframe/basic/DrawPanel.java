package jframe.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class DrawPanel extends JPanel implements MouseInputListener {

    private List<Point> points;

    public DrawPanel(){
        super();
        this.setBackground(Color.DARK_GRAY);
        this.points = new LinkedList<>();
        this.addMouseListener(this);
    }
    private static int[] leftTop(int r, Point p){
        int[] res = {p.x-r, p.y-r};
        return res;
    }
    public void reset(){
        this.points = new LinkedList<>();
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int r = 10;
        Point prev = null;
        for (Point p : this.points) {
            g.setColor(Color.BLACK);
            int[] posP = leftTop(r, p);
            g.fillOval(posP[0], posP[1], r*2, r*2);
            g.setColor(Color.WHITE);
            if(prev != null){
                g.drawLine(prev.x, prev.y, p.x, p.y);
                Double dist = p.distance(prev);
                String distStr = String.format("%.2f", dist);
                g.setFont(new Font("name", Font.PLAIN, 15));
                g.setColor(Color.ORANGE);
                g.drawString(distStr, (prev.x + p.x)/2, (prev.y + p.y)/2);
            }
            prev = p;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point p = new Point(x,y);
        points.add(p);
        System.out.println(p);
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
}
