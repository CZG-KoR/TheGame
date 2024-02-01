package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

public class Bar extends JInternalFrame {

    int width, height;

    public Bar(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        JTabbedPane tabs = new JTabbedPane(SwingConstants.TOP);

        this.setSize(width, height / 3);
        this.setLocation(0, height - height / 3);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // entfernt leiste bei tabbedpane
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        Color internalBorderColor = new Color(85, 53, 5);
        this.setBorder(BorderFactory.createLineBorder(internalBorderColor, 4));

        // widht -8 für "sauberen" Rahmen
        tabs.setPreferredSize(new Dimension(width - 100, height / 3));
        JPanel panelBuildings = new JPanel();
        panelBuildings.setBackground(Color.GRAY);
        JPanel panelTroops = new JPanel();
        tabs.addTab("Gebäude", panelBuildings);
        tabs.addTab("Truppen", panelTroops);
        tabs.setVisible(true);

        this.add(tabs);
        this.requestFocus();

        // immer am Ende
        this.setVisible(true);
    }

    public BasicArrowButton closeBarButton() {
        BasicArrowButton close = new BasicArrowButton(BasicArrowButton.SOUTH);
        close.setLocation(width - 50, height - height / 3 - 20);
        close.setSize(50, 20);
        close.setVisible(true);
        return close;
    }

    public BasicArrowButton openBarButton() {
        BasicArrowButton open = new BasicArrowButton(BasicArrowButton.NORTH);
        open.setLocation(width - 50, height - 20);
        open.setSize(50, 20);
        open.setVisible(true);
        return open;
    }
}
