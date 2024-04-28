package com.spell.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

class SPELLPage extends JPanel {
    String pageName;

    public SPELLPage(String name, Color background) {
        pageName = name;
        this.setLayout(null);
        this.setSize(1250, 780);
        this.setBackground(background);
    }
}

class SPELLButton extends JButton {
    public SPELLButton(String text, int fontSize, final Color backColor, Color foreColor, String tooltip) {
        this.setText(text);
        this.setMargin(new Insets(3, 3, 3, 3));
        this.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setBackground(backColor);
        this.setForeground(foreColor);
        this.setToolTipText(tooltip);
        this.setFocusable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        final SPELLButton button = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(button.getBackground().brighter());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backColor);
            }
        });
    }
}

class SPELLLabel extends JLabel {
    public SPELLLabel(String text, int fontSize, Color foreColor) {
        this.setText(text);
        this.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
        this.setForeground(foreColor);
    }
}

class SPELLTextArea extends JTextArea {
    public SPELLTextArea(Font font, Insets margin, Color color) {
        this.setFont(font);
        this.setMargin(margin);
        this.setBackground(color);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
    }
}

class SPELLComboBox extends JComboBox<String> {
    public SPELLComboBox(String[] items, String toolTip) {
        for (String item : items) {
            this.addItem(item);
        }
        this.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setToolTipText(toolTip);
        this.setBackground(Color.white);
        this.setForeground(Color.darkGray);
        this.setMaximumSize(new Dimension(200, 50));
    }
}

class SPELLAutoIconsPanel extends JPanel {
    String iconName;
    JToggleButton iconToggleButton;

    public SPELLAutoIconsPanel(String iconName, Color background) {
        this.iconName = iconName;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(200, 200));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setBackground(background);

        JPanel taskBar = new JPanel();
        taskBar.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        taskBar.setBackground(background.darker());

        iconToggleButton = new JToggleButton("•");
        iconToggleButton.setSize(15, 15);
        taskBar.add(iconToggleButton);

        this.add(taskBar, BorderLayout.SOUTH);
    }
}