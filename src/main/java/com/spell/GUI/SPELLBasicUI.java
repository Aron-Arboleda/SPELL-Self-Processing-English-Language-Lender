package com.spell.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

class SPELLPage extends JPanel {
    String pageName;
    private Image backgroundImage;

    public SPELLPage(String name, Color background) {
        pageName = name;
        this.setLayout(null);
        this.setSize(1210, 680);
        this.setBackground(background);
    }

    public void setImage(Image backgroundImage){
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static Image newScaledImage(String imageName, int width, int height){
        ImageIcon image = new ImageIcon(SPELLPage.class.getResource("/" + imageName));
        ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return scaledImage.getImage();
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

class OpaqueButton extends JButton {
    public OpaqueButton(String tooltip) {
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setToolTipText(tooltip);
        this.setFocusable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBackground(new Color(255,255,255,50));
        this.setOpaque(false);
        final OpaqueButton button = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setOpaque(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setOpaque(false);
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
    public SPELLTextArea(Font font, Insets margin, Color backColor, Color foreColor) {
        this.setFont(font);
        this.setMargin(margin);
        this.setBackground(backColor);
        this.setForeground(foreColor);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setCaretColor(Color.WHITE);
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
