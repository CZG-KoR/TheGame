package gui;

import map.Map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tilemap extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private transient Map m;
    //! startPoint, camX und camY sind static werden aber durch Methoden verändert
    private Point startPoint;
    private int camX = 0;
    private int camY = 0;

    public Tilemap(int width, int height, Map m) {
        super();
        this.m = m;
        this.setSize(new Dimension(width, height));
        this.setLocation(0, 0);
        this.setVisible(true);
        // Testfall
        m.setT(3, 3, "desert");

    }

    // painComponent; erst schwarzer Hintergrund
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < m.getWidth(); i++) {
            for (int j = 0; j < m.getHeight(); j++) {
                g.drawImage(m.getTerrainPicture(i, j), 64 * i + camX, 64 * j + camY, null);
            }
        }
        // g.drawImage(Toolkit.getDefaultToolkit().getImage("src/GUI/res/ResourceBar.png"),
        // 0, 0, null);

    }

    // Grenze für scroll
    private int clamp(int a, int min, int max) {
        if (a > max) {
            return max;
        }

        if (a < min) {
            return min;
        }

        return a;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = SwingUtilities.convertPoint(this.getParent(), e.getPoint(), this.getParent());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        startPoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (startPoint == null)
            return;

        Point location = SwingUtilities.convertPoint(this, e.getPoint(), this.getParent());

        if (this.getRootPane().getBounds().contains(location)) {
            Point newLocation = this.getLocation();
            newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);

            camX += newLocation.x;
            camY += newLocation.y;

            // Grenze von unten rechts und oben links festgelegt
            camX = clamp(camX, (m.getWidth() * 64 - Toolkit.getDefaultToolkit().getScreenSize().width) * -1, 0);
            camY = clamp(camY, (m.getHeight() * 64 - Toolkit.getDefaultToolkit().getScreenSize().height) * -1, 0);

            startPoint = location;
        } else
            startPoint = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // tileSize -= e.getUnitsToScroll();
    }
}
