package helloworld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel implements MouseMotionListener, MouseListener{

        private boolean p;
        private boolean painting;
        private int px, py;
        private boolean[][] data;

        
        
         
        public DrawingPanel() {
        	/* this method creates a panel for the user to draw in.*/
                setPreferredSize(new Dimension(280, 280));
                setBounds(30, 10, 280, 280);
                setBackground(Color.BLACK);
                setBorder(BorderFactory.createLineBorder(Color.WHITE));
                p = false;
                painting = false;
                px = 0; py = 0;
                data = new boolean[280][280];
                addMouseListener(this);
                addMouseMotionListener(this);
        }

        public void clear() {
                data = new boolean[280][280];
                getGraphics().clearRect(0, 0, 280, 280);
                setBackground(Color.BLACK);
                setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }
        
        public boolean[][] getData() {
                return data;
        /*this method creates a boolean array of the bits drawn in the panel */        
        }

        public void mousePressed(MouseEvent e) {
                p = true;
                painting = true;
        }

        public void mouseDragged(MouseEvent e) {
                int x = e.getX(), y = e.getY();
                Graphics graphics = getGraphics();
                graphics.setColor(Color.WHITE);
                if (painting && p) {
                        graphics.drawLine(x, y, x, y);
                        p = false;
                } else if (painting) {
                        graphics.drawLine(px,py,x,y);
                }
                px = x;
                py = y;
                if (painting) data[x][y] = true;
        }

        public void mouseExited(MouseEvent e) {
                painting = false;
        }

        public void mouseEntered(MouseEvent e) {
                painting = true;
        }

        public void mouseMoved(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}

}